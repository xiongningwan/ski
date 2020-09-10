package com.ski.box.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ski.box.BuildConfig;
import com.ski.box.R;
import com.ski.box.SKISdkManger;
import com.ski.box.bean.DataCenter;
import com.ski.box.view.activity.old.EnviromentBean;
import com.ski.box.view.activity.old.MerchantBean;
import com.ski.box.view.activity.old.SingleDialog;
import com.ski.box.view.activity.old.SingleDialog2;
import com.yb.core.net.HttpResult;
import com.yb.core.utils.SPUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 登录
 */
public class LoginActivityOld extends AppCompatActivity implements View.OnClickListener {
    private static final String KEY_JSON_ENVIRONMENT = "environment_list";
    private static final String KEY_JSON_MERCHANT = "merchant_list";
    private static final String KEY_CONTENT_TYPE = "Content-Type";
    private static final String KEY_CONTENT_TYPE_VALUE_JSON = "application/json;charset=UTF-8";
    private static final String KEY_CONTENT_TYPE_VALUE_FORM = "application/x-www-form-urlencoded;charset=UTF-8";
    private static final String KEY_NAME = "key_name";
    private static final String KEY_PWD = "key_pwd";
    private static final String KEY_ENV = "key_env";
    private static final String KEY_MERCH = "key_merch";
    private String environment = "";
    private String merchantId = "";
    EditText etName;
    EditText etPassword;
    Button btDevLogin;
    private TextView tvVersion;
    private ProgressDialog mLoading;
    private TextView tvEnvironment;
    private TextView tvMerchant;
//    String BASE_URL = "https://login.mkcp.online:8443";
    String BASE_URL = "http://login.mkcp.online:8080";
    String ENVIROMENT_URL = "/merchant/enironmen/list";
    String MERCHANT_URL = "/merchant/merchant/listAll";
    String LOGIN_URL = "/merchant/merchant_user/memberLogin";
    private List<EnviromentBean> mEnvironmentList;
    private List<MerchantBean> mMerchantList;
    private SingleDialog mSingleDialog;
    private SingleDialog2 mSingleDialog2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ski_activity_login);
        initViews();
        initData();
    }

    private void initViews() {
        etName = findViewById(R.id.et_name);
        tvEnvironment = findViewById(R.id.tv_environment);
        tvMerchant = findViewById(R.id.tv_merchant);
        etPassword = findViewById(R.id.et_password);
        btDevLogin = findViewById(R.id.btn_login_dev);
        tvVersion = findViewById(R.id.tv_version);
        btDevLogin.setOnClickListener(this);
        tvVersion.setText("版本：V" + getVersionName(this));
        mLoading = new ProgressDialog(this);
        mLoading.setCancelable(false);
        mLoading.setCanceledOnTouchOutside(false);

        tvEnvironment.setOnClickListener(this);
        tvMerchant.setOnClickListener(this);
    }

    private void initData() {
        if (!TextUtils.isEmpty(SPUtils.getString(this, KEY_NAME))) {
            etName.setText(SPUtils.getString(this, KEY_NAME));
            etPassword.setText(SPUtils.getString(this, KEY_PWD));
        }
        if (!TextUtils.isEmpty(SPUtils.getString(this, KEY_ENV))) {
            tvEnvironment.setText(SPUtils.getString(this, KEY_ENV));
            tvMerchant.setText(SPUtils.getString(this, KEY_MERCH));
            environment = SPUtils.getString(this, KEY_ENV + "value");
            merchantId = SPUtils.getString(this, KEY_MERCH + "value");
        }
    }


    private void requestEnvRemote() {
        mLoading.show();
        OkHttpUtils.get().url(BASE_URL + ENVIROMENT_URL).headers(getHeader(1)).build().readTimeOut(100000000).execute(new Callback() {

            @Override
            public Object parseNetworkResponse(Response response, int id) throws Exception {
                ResponseBody body = response.body();
                String s = body.string();
                return s;
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(LoginActivityOld.this, e.getMessage(), Toast.LENGTH_LONG).show();
                mLoading.dismiss();
            }

            @Override
            public void onResponse(Object response, int id) {
                mLoading.dismiss();
                String result = (String) response;
                HttpResult httpResult = new Gson().fromJson(result, HttpResult.class);
                if (checkData(httpResult)) {
                    return;
                }
                String json = new Gson().toJson(httpResult.getData());
                mEnvironmentList = new Gson().fromJson(json, new TypeToken<List<EnviromentBean>>() {
                }.getType());
                String json2 = new Gson().toJson(mEnvironmentList);
                SPUtils.putString(LoginActivityOld.this, KEY_JSON_ENVIRONMENT, json2);
                mSingleDialog2 = new SingleDialog2(LoginActivityOld.this, mEnvironmentList);
                mSingleDialog2.show();

                mSingleDialog2.getmAdapter().setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        mSingleDialog2.dismiss();
                        List<EnviromentBean> list = adapter.getData();
                        EnviromentBean enviromentBean = list.get(position);
                        environment = String.valueOf(enviromentBean.getId());
                        tvEnvironment.setText(enviromentBean.getName());
                        requestMerChantRemote();
                    }
                });
            }
        });
    }

    private void requestEnvRemote2() {
        mEnvironmentList = new ArrayList<>();

       /*bob 41 ob 45  bw57  test  2*/
       /*bob 43 ob 47  bw59  uat 4*/
        mEnvironmentList.add(new EnviromentBean(2, "test"));
        mEnvironmentList.add(new EnviromentBean(4, "uat"));
        mSingleDialog2 = new SingleDialog2(LoginActivityOld.this, mEnvironmentList);
        mSingleDialog2.show();
        mSingleDialog2.getmAdapter().setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mSingleDialog2.dismiss();
                List<EnviromentBean> list = adapter.getData();
                EnviromentBean enviromentBean = list.get(position);
                environment = String.valueOf(enviromentBean.getId());
                tvEnvironment.setText(enviromentBean.getName());
                mMerchantList = new ArrayList<>();
                int id = enviromentBean.getId();
                if (id == 2) {
                    mMerchantList.add(new MerchantBean(41, "bob"));
                    mMerchantList.add(new MerchantBean(45, "ob"));
                    mMerchantList.add(new MerchantBean(57, "bw"));
                } else if (id == 4) {
                    mMerchantList.add(new MerchantBean(43, "bob"));
                    mMerchantList.add(new MerchantBean(47, "ob"));
                    mMerchantList.add(new MerchantBean(59, "bw"));
                }

                mSingleDialog = new SingleDialog(LoginActivityOld.this, mMerchantList);
                mSingleDialog.show();
                mSingleDialog.getmAdapter().setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        mSingleDialog.dismiss();
                        List<MerchantBean> list = adapter.getData();
                        MerchantBean merchantBean = list.get(position);
                        tvMerchant.setText(merchantBean.getMerchantName());
                        merchantId = String.valueOf(merchantBean.getId());
                    }
                });


            }
        });
    }


    private void requestMerChantRemote() {
        mLoading.show();
        String url = BASE_URL + MERCHANT_URL + "?environment=" + environment;
        OkHttpUtils.get().url(url).headers(getHeader(1)).build().execute(new Callback() {

            @Override
            public Object parseNetworkResponse(Response response, int id) throws Exception {
                ResponseBody body = response.body();
                String s = body.string();
                return s;
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(LoginActivityOld.this, e.getMessage(), Toast.LENGTH_LONG).show();
                mLoading.dismiss();
            }

            @Override
            public void onResponse(Object response, int id) {
                mLoading.dismiss();
                String result = (String) response;
                HttpResult httpResult = new Gson().fromJson(result, HttpResult.class);
                if (checkData(httpResult)) {
                    return;
                }
                String json = new Gson().toJson(httpResult.getData());
                mMerchantList = new Gson().fromJson(json, new TypeToken<List<MerchantBean>>() {
                }.getType());
                String json2 = new Gson().toJson(mMerchantList);
                SPUtils.putString(LoginActivityOld.this, KEY_JSON_MERCHANT, json2);
                mSingleDialog = new SingleDialog(LoginActivityOld.this, mMerchantList);
                mSingleDialog.show();
                mSingleDialog.getmAdapter().setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        mSingleDialog.dismiss();
                        List<MerchantBean> list = adapter.getData();
                        MerchantBean merchantBean = list.get(position);
                        tvMerchant.setText(merchantBean.getMerchantName());
                        merchantId = String.valueOf(merchantBean.getId());
                    }
                });
            }
        });
    }


    private void login() {

        if (null != etName.getText() && etName.getText().length() > 0 && !TextUtils.isEmpty(merchantId) && null != etPassword.getText() && etPassword.getText().length() > 0) {
            String timestamp = System.currentTimeMillis() + "";
            Map paraMap = new HashMap(7);
            paraMap.put("merchantId", merchantId);
            paraMap.put("account", etName.getText().toString());
            paraMap.put("password", etPassword.getText().toString());
            paraMap.put("loginType", "2");
            paraMap.put("timestamp", timestamp);
            paraMap.put("environment", environment);

            mLoading.show();
            String json = new Gson().toJson(paraMap);
            OkHttpUtils.postString()
                    .url(BASE_URL + LOGIN_URL)
                    .headers(getHeader(1))
                    .content(json)
                    .mediaType(MediaType.parse(KEY_CONTENT_TYPE_VALUE_JSON))
                    .build()
                    .execute(new Callback() {
                        @Override
                        public Object parseNetworkResponse(Response response, int id) throws Exception {
                            ResponseBody body = response.body();
                            String s = body.string();
                            Log.d("sss", "onResponse: " + s);
                            return s;
                        }

                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(LoginActivityOld.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            mLoading.dismiss();
                        }

                        @Override
                        public void onResponse(Object response, int id) {
                            mLoading.dismiss();
                            String result = (String) response;
                            HttpResult<String> httpResult = new Gson().fromJson(result, HttpResult.class);
                            if (checkData(httpResult)) {
                                return;
                            }
                            String str = (String) httpResult.getData();
                            if (!TextUtils.isEmpty((str))) {
                                if (str.contains("?")) {
                                    String[] aar = str.split("\\?");
                                    if (aar[0].contains("http")) {

                                        if (aar[1].contains("=")) {
                                            String[] aar2 = str.split("=");
                                            String token = aar2[1];
                                            String url = aar[0];
                                            if ("http://newcp-bob-wap.emkcp.com".equals(url)) {
                                                url = "https://newcp-bob-web.emkcp.com";
                                            }
                                            SKISdkManger.setUrlAndToken(tvMerchant.getText().toString(), url, BuildConfig.DEBUG, token);
//                                            DataCenter.getInstance().getLottery().clear();
//                                            DataCenter.getInstance().getRemotePlayMap().clear();
                                            startActivity(new Intent(LoginActivityOld.this, MainActivity.class));
                                            finish();
                                        }
                                    }
                                }
                            }

                            // sp
                            SPUtils.putString(LoginActivityOld.this, KEY_NAME, etName.getText().toString().trim());
                            SPUtils.putString(LoginActivityOld.this, KEY_PWD, etPassword.getText().toString().trim());
                            SPUtils.putString(LoginActivityOld.this, KEY_ENV, tvEnvironment.getText().toString());
                            SPUtils.putString(LoginActivityOld.this, KEY_MERCH, tvMerchant.getText().toString());
                            SPUtils.putString(LoginActivityOld.this, KEY_ENV + "value", environment);
                            SPUtils.putString(LoginActivityOld.this, KEY_MERCH + "value", merchantId);

                        }
                    });
        }
    }

    public Map<String, String> getHeader(int type) {
        Map<String, String> headers = new HashMap<>();
        switch (type) {
            case 1:
                headers.put(KEY_CONTENT_TYPE, KEY_CONTENT_TYPE_VALUE_JSON);
                break;
            case 2:
                headers.put(KEY_CONTENT_TYPE, KEY_CONTENT_TYPE_VALUE_FORM);
                break;
        }
        return headers;
    }

    private boolean checkData(HttpResult httpResult) {
        if (httpResult.getCode() != 200) {
            Toast.makeText(LoginActivityOld.this, "code:" + httpResult.getCode() + "error:" + httpResult.getMessage(), Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_environment) {
//            requestEnvRemote();
            requestEnvRemote2();
        } else if (id == R.id.tv_merchant) {
            if (mSingleDialog != null) {
                mSingleDialog.show();
            }
        } else if (id == R.id.btn_login_dev) {
            //test环境：帐号：androidtest01
            //密码：123456
            String url="https://test-bob-wap.emkcp.com:8443/";
//            String token="eccae73a2b6a4ec682c8d0acdbd1abe51595825114055";
//            YBSdkManger.setUrlAndToken("bob", url, BuildConfig.DEBUG, token);
//            DataCenter.getInstance().getLottery().clear();
//            DataCenter.getInstance().getRemotePlayMap().clear();
//            startActivity(new Intent(LoginActivityOld.this, MainActivity.class));
            login();
        }
    }

    /**
     * get App versionName
     *
     * @param context
     * @return
     */
    public String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        String versionName = "";
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }


}
