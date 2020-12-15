package com.ski.box.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.ski.box.bean.DataCenter;
import com.ski.box.mvp.contract.RegisterContract;
import com.ski.box.mvp.presenter.RegisterPresenter;
import com.ski.box.mvp.service.IUserService;
import com.ski.box.utils.SignUtil;
import com.ski.box.utils.SoftHideKeyBoardUtil;
import com.ski.box.utils.SoftHideKeyBoardUtil2;
import com.ski.box.utils.ValidateUtil;
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
    private EditText etPasswordConirm;
    private EditText etCode;
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
        etPasswordConirm = findViewById(R.id.et_password_confirm);
        etCode = findViewById(R.id.et_code);
        btRegister = findViewById(R.id.btn_register);
        tvBackLogin = findViewById(R.id.tv_back_login);
        tvBackLogin.setOnClickListener(this);
        btRegister.setOnClickListener(this);
        mLoading = new ProgressDialog(this);
        mLoading.setCancelable(true);
        mLoading.setCanceledOnTouchOutside(true);
        SoftHideKeyBoardUtil2.assistActivity(this);
    }


    @Override
    protected void initData(Bundle bundle) {
//        setEtListener();
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
        String passwordConfirm = etPasswordConirm.getText().toString();
        String code = etCode.getText().toString();
        if (StringUtils.isEmpty(member)) {
            ToastUtil.showInfo("请输入账号");
            return;
        }
        if (StringUtils.isEmpty(password)) {
            ToastUtil.showInfo("请输入密码");
            return;
        }
        if (StringUtils.isEmpty(passwordConfirm)) {
            ToastUtil.showInfo("请输入确认密码");
            return;
        }
        if(member.length() < 6 || member.length() > 16) {
            String err = "账号必须为6-16位";
            ToastUtil.showInfo(err);
            return;
        }
        if(password.length() < 6 || password.length() > 16) {
            String err = "密码必须为6-16位";
            ToastUtil.showInfo(err);
            return;
        }
        if(!password.equals(passwordConfirm)) {
            ToastUtil.showInfo("两次输入的密码不一致");
            return;
        }
//        if (!ValidateUtil.validatePwd_new(member)) {
//            String err = "账号必须为6-16位包含英文与数字组合，区分大小写";
//            ToastUtil.showInfo(err);
//            return;
//        }
//        if (!ValidateUtil.validatePwd_new(password)) {
//            String err = "密码必须为6-16位包含英文与数字组合，区分大小写";
//            ToastUtil.showInfo(err);
//            return;
//        }
        String memberDomain = RetrofitHelper.getInstance().getBaseUrl();
        if(!TextUtils.isEmpty(memberDomain)&& memberDomain.contains("/")) {
            memberDomain = memberDomain.replace("https://", "").replace("http://", "");
            String[] domainArr = memberDomain.split("/");
            if(domainArr.length > 0) {
                memberDomain = domainArr[0];
            }
        }
        String tester = "1";
        mLoading.show();
        // md5
        password = MD5Util.md5Password(password);
        if(TextUtils.isEmpty(code)) {
            mPresenter.doRegister(member, password, memberDomain);
        } else {
            mPresenter.doRegisterCode(member, password, code);
        }

    }


    @Override
    public void onRegisterSuccessResult(Object o) {
      //  ToastUtil.showSuccess(getString(R.string.ski_login_register));
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

    private void setEtListener() {
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String member = editable.toString().trim();
                if (!ValidateUtil.validatePwd_new(member) && !TextUtils.isEmpty(member)) {
                    String err = "账号必须为6-16位包含英文与数字组合，区分大小写";
                    etName.setError(err);
                }
            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String password = editable.toString();
                if (!ValidateUtil.validatePwd_new(password) && !TextUtils.isEmpty(password)) {
                    String err = "密码必须为6-16位包含英文与数字组合，区分大小写";
                    etPassword.setError(err);
                }
            }
        });
    }
}
