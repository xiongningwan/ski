package com.ski.box.view.activity.my;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.bean.DataCenter;
import com.ski.box.mvp.contract.my.BindPhoneContract;
import com.ski.box.mvp.presenter.my.BindPhonePresenter;
import com.ski.box.utils.EasyCountDownTimer;
import com.yb.core.utils.LanguageUtil;
import com.ski.box.view.view.ClearEditText;
import com.ski.box.view.view.CusTextView;
import com.ski.box.view.view.HeaderView;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.ToastUtil;

import static com.ski.box.ConstantValue.EVENT_BIND_PHONE_SUCCESS;


public class BindPhoneActivity extends BaseMVPActivity<BindPhoneContract.Presenter> implements BindPhoneContract.View, View.OnClickListener {
    private HeaderView mHeadView;
    private ClearEditText mEtPhone;
    private ClearEditText mEtCode;
    private CusTextView mTvSendCode;
    private Button mBtnSure;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected BindPhoneContract.Presenter bindPresenter() {
        return new BindPhonePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_bind_phone;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        mHeadView = findViewById(R.id.head_view);
        mEtPhone = findViewById(R.id.et_phone);
        mEtCode = findViewById(R.id.et_code);
        mTvSendCode = findViewById(R.id.tv_send_code);
        mBtnSure = findViewById(R.id.btn_sure);
        mHeadView.setHeader(LanguageUtil.getText(getString(R.string.ski_my_bind_phone)), true);

        mTvSendCode.setOnClickListener(this);
        mBtnSure.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle bundle) {
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_send_code) {
            doSendCode();
        } else if (id == R.id.btn_sure) {
            doUpdate();
        }
    }

    private void doSendCode() {
        String phone = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showWarning(LanguageUtil.getText("请输入手机号码"));
            return;
        }

        if (phone.length() < 9 || phone.length() > 11) {
            ToastUtil.showWarning(LanguageUtil.getText("手机号码长度为9～11位"));
            return;
        }
        mPresenter.sendCode(phone);
        EasyCountDownTimer timer = new EasyCountDownTimer(mTvSendCode, 60000, 1000);
        timer.start();
    }

    private void doUpdate() {
        String phone = mEtPhone.getText().toString().trim();
        String code = mEtCode.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showWarning(LanguageUtil.getText("请输入手机号码"));
            return;
        }
        if (phone.length() < 9 || phone.length() > 11) {
            ToastUtil.showWarning(LanguageUtil.getText("手机号码长度为9～11位"));
            return;
        }

        if (TextUtils.isEmpty(code)) {
            ToastUtil.showWarning(LanguageUtil.getText("请输入验证码"));
            return;
        }

        mPresenter.bindPhone(phone);
    }

    @Override
    public void onCodeSendSuccessResult(String s) {
        ToastUtil.showSuccess(s);
    }

    @Override
    public void onSuccessResult() {
        String phone = mEtPhone.getText().toString().trim();
        DataCenter.getInstance().getUser().setMobile(phone);
        ToastUtil.showSuccess(LanguageUtil.getText("修改成功!"));
        RxBus.get().post(EVENT_BIND_PHONE_SUCCESS, "");
        finish();

    }

    @Override
    public void onFailResult(String s) {
    }
}
