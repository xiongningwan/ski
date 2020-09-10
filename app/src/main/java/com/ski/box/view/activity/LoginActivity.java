package com.ski.box.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.ski.box.BuildConfig;
import com.ski.box.ConstantValue;
import com.ski.box.R;
import com.ski.box.SKISdkManger;
import com.ski.box.mvp.contract.LoginContract;
import com.ski.box.mvp.presenter.LoginPresenter;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.SPUtils;
import com.yb.core.utils.StringUtils;
import com.yb.core.utils.ToastUtil;

/**
 * 登录
 */
public class LoginActivity extends BaseMVPActivity<LoginContract.Presenter> implements LoginContract.View, View.OnClickListener {
    private static final String KEY_NAME = "key_name";
    private static final String KEY_PWD = "key_pwd";
    private static final String KEY_ENV = "key_env";
    private static final String KEY_MERCH = "key_merch";
    private EditText etName;
    private EditText etPassword;
    private Button btDevLogin;
    private TextView tvEnvironment;
    private TextView tvMerchant;
    private ProgressDialog mLoading;

    private String mParam_environment = "";
    private String mParam_merchantId = "";
    private String mParam_account = "";
    private String mParam_password = "";
    private int mParam_loginType;
    private String mParam_timestamp = "";


    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_login_new;
    }

    @Override
    protected LoginContract.Presenter bindPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        etName = findViewById(R.id.et_name);
        tvEnvironment = findViewById(R.id.tv_environment);
        tvMerchant = findViewById(R.id.tv_merchant);
        etPassword = findViewById(R.id.et_password);
        btDevLogin = findViewById(R.id.btn_login_dev);
        btDevLogin.setOnClickListener(this);
        mLoading = new ProgressDialog(this);
        mLoading.setCancelable(true);
        mLoading.setCanceledOnTouchOutside(true);

        tvEnvironment.setOnClickListener(this);
        tvMerchant.setOnClickListener(this);
    }


    @Override
    protected void initData(Bundle bundle) {
        if (!TextUtils.isEmpty(SPUtils.getString(this, KEY_NAME))) {
            etName.setText(SPUtils.getString(this, KEY_NAME));
            etPassword.setText(SPUtils.getString(this, KEY_PWD));
        }
//        if (!TextUtils.isEmpty(SPUtils.getString(this, KEY_ENV))) {
//            tvEnvironment.setText(SPUtils.getString(this, KEY_ENV));
//            tvMerchant.setText(SPUtils.getString(this, KEY_MERCH));
//            environment = SPUtils.getString(this, KEY_ENV + "value");
//            merchantId = SPUtils.getString(this, KEY_MERCH + "value");
//        }
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_environment) {
//            requestEnvRemote2();
        } else if (id == R.id.tv_merchant) {

        } else if (id == R.id.btn_login_dev) {
            doLogin();
        }
    }

    private void doLogin() {
        mParam_environment = "4"; // 2 test 4 uat
        mParam_merchantId = "43"; // 43 bob 47 ob
        mParam_account = etName.getText().toString();
        mParam_password = etPassword.getText().toString();
        mParam_loginType = 2;
        mParam_timestamp = System.currentTimeMillis() + "";
        if (StringUtils.isEmpty(mParam_account)) {
            ToastUtil.showInfo("请输入帐号");
            return;
        }
        if (StringUtils.isEmpty(mParam_password)) {
            ToastUtil.showInfo("请输入密码");
            return;
        }
        mPresenter.doLogin2(mLoading, mParam_environment, mParam_merchantId, mParam_account, mParam_password, mParam_loginType, mParam_timestamp);
    }


    @Override
    public void onLoginResult(String str) {
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
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();

                        // sp
                        SPUtils.putString(this, KEY_NAME, etName.getText().toString().trim());
                        SPUtils.putString(this, KEY_PWD, etPassword.getText().toString().trim());
                        SPUtils.putString(this, KEY_ENV, tvEnvironment.getText().toString());
                        SPUtils.putString(this, KEY_MERCH, tvMerchant.getText().toString());
//                        SPUtils.putString(this, KEY_ENV + "value", environment);
//                        SPUtils.putString(this, KEY_MERCH + "value", merchantId);
                    }
                }
            }
        }
    }


}
