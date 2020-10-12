package com.ski.box.view.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gyf.immersionbar.ImmersionBar;
import com.ski.box.BuildConfig;
import com.ski.box.R;
import com.ski.box.mvp.contract.RegisterContract;
import com.ski.box.mvp.presenter.RegisterPresenter;
import com.ski.box.mvp.service.IUserService;
import com.ski.box.utils.SignUtil;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.net.RetrofitHelper;
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
        btRegister.setOnClickListener(this);
        mLoading = new ProgressDialog(this);
        mLoading.setCancelable(true);
        mLoading.setCanceledOnTouchOutside(true);
    }


    @Override
    protected void initData(Bundle bundle) {
        RetrofitHelper.getInstance().init("http://dev-sk-web.k5615.com/sk/", true, "");
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
        mLoading.show();
        String merchant = "43"; // 43 bob 47 ob
        String member = etName.getText().toString();
        String password = etPassword.getText().toString();
        int tester = 1; //1：正式会员，3：测试会员 测试会员不进入统计报表，并有其他功能限制
        String prizeGroup = getResources().getString(R.string.prizeGroup);
        long timestamp = System.currentTimeMillis();
        if (StringUtils.isEmpty(member)) {
            ToastUtil.showInfo("请输入帐号");
            return;
        }
        if (StringUtils.isEmpty(password)) {
            ToastUtil.showInfo("请输入密码");
            return;
        }
        if("0".equals(prizeGroup)) {
            mPresenter.doRegister(merchant, member, password, tester, timestamp);
        } else {
            mPresenter.doRegister(merchant, member, password, tester, prizeGroup, timestamp);
        }
    }


    @Override
    public void onRegisterSuccessResult(String str) {
        ToastUtil.showInfo(str);
        mLoading.dismiss();
    }

    @Override
    public void onRegisterFailResult(String str) {
        mLoading.dismiss();
    }
}
