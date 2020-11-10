package com.ski.box.view.activity.my;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.bean.DataCenter;
import com.ski.box.mvp.contract.my.BindEmailContract;
import com.ski.box.mvp.presenter.my.BindEmailPresenter;
import com.ski.box.utils.EasyCountDownTimer;
import com.ski.box.utils.ValidateUtil;
import com.ski.box.view.view.ClearEditText;
import com.ski.box.view.view.CusTextView;
import com.ski.box.view.view.HeaderView;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.ToastUtil;

import static com.ski.box.ConstantValue.EVENT_BIND_EMAIL_SUCCESS;


public class BindEmailActivity extends BaseMVPActivity<BindEmailContract.Presenter> implements BindEmailContract.View, View.OnClickListener {
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
    protected BindEmailContract.Presenter bindPresenter() {
        return new BindEmailPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_bind_email;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        mHeadView = findViewById(R.id.head_view);
        mEtPhone = findViewById(R.id.et_phone);
        mEtCode = findViewById(R.id.et_code);
        mTvSendCode = findViewById(R.id.tv_send_code);
        mBtnSure = findViewById(R.id.btn_sure);
        mHeadView.setHeader(getString(R.string.ski_my_bind_email), true);

        mTvSendCode.setOnClickListener(this);
        mBtnSure.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle bundle) {
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.tv_send_code) {
            doSendCode();
        } else if(id == R.id.btn_sure) {
            doUpdate();
        }
    }

    private void doSendCode() {
        String email = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            ToastUtil.showWarning("请输入邮箱");
            return;
        }
        if (!ValidateUtil.validateEamil(email)) {
            ToastUtil.showWarning("邮箱格式不正确");
            return;
        }
        EasyCountDownTimer timer = new EasyCountDownTimer(mTvSendCode,60000,1000);
        timer.start();

    }

    private void doUpdate() {
        String email = mEtPhone.getText().toString().trim();
        String code = mEtCode.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            ToastUtil.showWarning("请输入邮箱");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            ToastUtil.showWarning("请输入验证码");
            return;
        }

        mPresenter.bindEmail(email);
    }

    @Override
    public void onSuccessResult() {
        String phone = mEtPhone.getText().toString().trim();
        DataCenter.getInstance().getUser().setMobile(phone);
        ToastUtil.showSuccess("修改成功!");
        RxBus.get().post(EVENT_BIND_EMAIL_SUCCESS, "");
        finish();

    }

    @Override
    public void onFailResult(String s) {
    }
}
