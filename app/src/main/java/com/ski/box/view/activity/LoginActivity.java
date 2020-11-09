package com.ski.box.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.gyf.immersionbar.ImmersionBar;
import com.ski.box.BuildConfig;
import com.ski.box.ConstantValue;
import com.ski.box.R;
import com.ski.box.SKISdkManger;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.user.LoginInfo;
import com.ski.box.mvp.contract.LoginContract;
import com.ski.box.mvp.presenter.LoginPresenter;
import com.ski.box.utils.ActivityUtil;
import com.ski.box.utils.HeaderUtil;
import com.ski.box.utils.LanguageUtil;
import com.ski.box.utils.SoftHideKeyBoardUtil;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.net.RetrofitHelper;
import com.yb.core.utils.MD5Util;
import com.yb.core.utils.SPUtils;
import com.yb.core.utils.StringUtils;
import com.yb.core.utils.ToastUtil;

/**
 * 登录
 */
public class LoginActivity extends BaseMVPActivity<LoginContract.Presenter> implements LoginContract.View, View.OnClickListener {
    public static final int REQUEST_CODE_LOGIN_REGISTER = 100;
    public static final String KEY_NAME = "login_key_name";
    public static final String KEY_PWD = "login_key_pwd";
    public static final String KEY_TOKEN = "login_key_token";
    public static final String KEY_AUTHORIZATION = "login_key_authorization";
    private EditText etName;
    private EditText etPassword;
    private Button btDevLogin;
    private TextView tvRegister;
    private TextView tvEnvironment;
    private TextView tvMerchant;
    private ProgressDialog mLoading;


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
        tvRegister = findViewById(R.id.tv_register);
        btDevLogin.setOnClickListener(this);
        mLoading = new ProgressDialog(this);
        mLoading.setCancelable(true);
        mLoading.setCanceledOnTouchOutside(true);

        tvEnvironment.setOnClickListener(this);
        tvMerchant.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        SoftHideKeyBoardUtil.assistActivity(this);
    }


    @Override
    protected void initData(Bundle bundle) {
        RetrofitHelper.getInstance().init(ConstantValue.BASE_HOST, BuildConfig.DEBUG,
                HeaderUtil.getHeader("","", ConstantValue.DEVICE, LanguageUtil.getLanguage()));
        initSetFromSp();
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_login_dev) {
            doLogin();
        } else if (id == R.id.tv_register) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivityForResult(intent, REQUEST_CODE_LOGIN_REGISTER);
        }
    }

    private void doLogin() {
        String member = etName.getText().toString();
        String password = etPassword.getText().toString();
        if (StringUtils.isEmpty(member)) {
            ToastUtil.showInfo(LanguageUtil.getText("请输入账号"));
            return;
        }
        if (StringUtils.isEmpty(password)) {
            ToastUtil.showInfo(LanguageUtil.getText("请输入密码"));
            return;
        }
        mLoading.show();
        // md5
        password = MD5Util.md5Password(password);
        mPresenter.doLogin(member, password);
    }


    @Override
    public void onLoginSuccessResult(LoginInfo loginInfo) {
        mLoading.dismiss();
        DataCenter.getInstance().setUser(loginInfo);
        // sp
        String member = etName.getText().toString();
        String password = etPassword.getText().toString();
        saveSetSp_name_pwd(member, password);
        saveSetSp_token_authorization(loginInfo.getToken(), loginInfo.getAuthorization());
        RetrofitHelper.getInstance().init(ConstantValue.BASE_HOST, BuildConfig.DEBUG,
                HeaderUtil.getHeader( loginInfo.getToken(), loginInfo.getAuthorization(),ConstantValue.DEVICE, LanguageUtil.getLanguage()));
        SKISdkManger.initLotteryIds(BuildConfig.DEBUG);
        DataCenter.getInstance().getLottery().clear();
        DataCenter.getInstance().getRemotePlayMap().clear();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onLoginFailResult(String str) {
        mLoading.dismiss();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        String name = data.getStringExtra(LoginActivity.KEY_NAME);
        String pwd = data.getStringExtra(LoginActivity.KEY_PWD);
        saveSetSp_name_pwd(name, pwd);
        // 设置
        initSetFromSp();
    }

    private void initSetFromSp() {
        if (!TextUtils.isEmpty(SPUtils.getString(this, KEY_NAME))) {
            etName.setText(SPUtils.getString(this, KEY_NAME));
        }
        if (!TextUtils.isEmpty(SPUtils.getString(this, KEY_PWD))) {
            etPassword.setText(SPUtils.getString(this, KEY_PWD));
        }

    }

    private void saveSetSp_name_pwd(String name, String pwd) {
        SPUtils.putString(this, KEY_NAME, name);
        SPUtils.putString(this, KEY_PWD, pwd);
    }
    private void saveSetSp_token_authorization(String token, String authorization) {
        SPUtils.putString(this, KEY_TOKEN, token);
        SPUtils.putString(this, KEY_AUTHORIZATION, authorization);
    }

}
