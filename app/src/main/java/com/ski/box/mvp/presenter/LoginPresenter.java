package com.ski.box.mvp.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ski.box.BuildConfig;
import com.ski.box.SKISdkManger;
import com.ski.box.bean.lottery.LotterySer;
import com.ski.box.mvp.contract.EmptyContract;
import com.ski.box.mvp.contract.LoginContract;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.ski.box.utils.lottery.LotteryTimeUtil;
import com.ski.box.view.activity.LoginActivityOld;
import com.ski.box.view.activity.MainActivity;
import com.yb.core.base.RxPresenter;
import com.yb.core.net.HttpResult;
import com.yb.core.utils.SPUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {
    private IUserModel mUserModel;

    public LoginPresenter(Context context) {
        super(context);
        mUserModel = new UserModel();
    }


    @Override
    public void doLogin(String environment, String merchantId, String account, String password, int loginType, String timestamp) {
        Disposable disposable = mUserModel.login(new Consumer<String>() {
            @Override
            public void accept(String str) {
                mView.onLoginResult(str);
            }
        },environment,merchantId, account, password, loginType, timestamp);
        addDisposable(disposable);
    }
    private static final String KEY_CONTENT_TYPE_VALUE_JSON = "application/json;charset=UTF-8";
    private static final String KEY_CONTENT_TYPE_VALUE_FORM = "application/x-www-form-urlencoded;charset=UTF-8";
    private static final String KEY_CONTENT_TYPE = "Content-Type";

    @Override
    public void doLogin2(ProgressDialog mLoading, String environment, String merchantId, String account, String password, int loginType, String timestamp) {
        String BASE_URL = "http://login.mkcp.online:8080";
        String LOGIN_URL = "/merchant/merchant_user/memberLogin";

        Map paraMap = new HashMap(7);
        paraMap.put("merchantId", merchantId);
        paraMap.put("account", account);
        paraMap.put("password", password);
        paraMap.put("loginType", String.valueOf(loginType));
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
                        Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
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
                        mView.onLoginResult(str);
                    }
                });
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
            Toast.makeText(mContext, "code:" + httpResult.getCode() + "error:" + httpResult.getMessage(), Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}


