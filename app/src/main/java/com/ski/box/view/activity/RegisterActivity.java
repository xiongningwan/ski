package com.ski.box.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.ski.box.BuildConfig;
import com.ski.box.R;
import com.ski.box.mvp.contract.RegisterContract;
import com.ski.box.mvp.presenter.RegisterPresenter;
import com.ski.box.mvp.service.IUserService;
import com.ski.box.utils.SignUtil;
import com.ski.box.utils.SoftHideKeyBoardUtil;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.net.RetrofitHelper;
import com.yb.core.utils.MD5Util;
import com.yb.core.utils.StringUtils;
import com.yb.core.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册
 */
public class RegisterActivity extends BaseMVPActivity<RegisterContract.Presenter> implements RegisterContract.View, View.OnClickListener {
    private EditText etName;
    private EditText etPassword;
    private Button btRegister;
    private TextView tvBackLogin;
    private ProgressDialog mLoading;


    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_register;
    }

    @Override
    protected RegisterContract.Presenter bindPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        etName = findViewById(R.id.et_name);
        etPassword = findViewById(R.id.et_password);
        btRegister = findViewById(R.id.btn_register);
        tvBackLogin = findViewById(R.id.tv_back_login);
        tvBackLogin.setOnClickListener(this);
        btRegister.setOnClickListener(this);
        mLoading = new ProgressDialog(this);
        mLoading.setCancelable(true);
        mLoading.setCanceledOnTouchOutside(true);
        SoftHideKeyBoardUtil.assistActivity(this);
    }


    @Override
    protected void initData(Bundle bundle) {

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_register) {
            doRegister();
        } else if (id == R.id.tv_back_login) {
            finish();
        }
    }

    private void doRegister() {
        String member = etName.getText().toString();
        String password = etPassword.getText().toString();
        if (StringUtils.isEmpty(member)) {
            ToastUtil.showInfo("请输入帐号");
            return;
        }
        if (StringUtils.isEmpty(password)) {
            ToastUtil.showInfo("请输入密码");
            return;
        }
        mLoading.show();
        // md5
        password = MD5Util.md5Password(password);
        mPresenter.doRegister(member, password);
    }


    @Override
    public void onRegisterSuccessResult(Object o) {
        ToastUtil.showSuccess(getString(R.string.ski_login_register));
        mLoading.dismiss();
        String member = etName.getText().toString();
        String password = etPassword.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(LoginActivity.KEY_NAME, member);
        intent.putExtra(LoginActivity.KEY_PWD, password);
        //设置返回数据
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onRegisterFailResult(String str) {
        mLoading.dismiss();
    }
}
