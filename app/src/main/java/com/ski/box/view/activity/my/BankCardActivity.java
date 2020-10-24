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
import com.ski.box.mvp.contract.BindEmailContract;
import com.ski.box.mvp.presenter.BindEmailPresenter;
import com.ski.box.utils.EasyCountDownTimer;
import com.ski.box.utils.ValidateUtil;
import com.ski.box.view.view.ClearEditText;
import com.ski.box.view.view.HeaderView;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.ToastUtil;

import static com.ski.box.ConstantValue.EVENT_BIND_EMAIL_SUCCESS;


public class BankCardActivity extends BaseMVPActivity<BindEmailContract.Presenter> implements BindEmailContract.View, View.OnClickListener {
    HeaderView mHeadView;
    Button mBtnSure;

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
        return R.layout.ski_activity_bank_card;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        mHeadView = findViewById(R.id.head_view);
        mBtnSure = findViewById(R.id.btn_sure);
        mHeadView.setHeader(getString(R.string.ski_my_bank_card), true);

        mBtnSure.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle bundle) {
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btn_sure) {
            doUpdate();
        }
    }


    private void doUpdate() {
        String email = "";

        mPresenter.bindEmail(email);
    }

    @Override
    public void onSuccessResult() {

    }

    @Override
    public void onFailResult(String s) {
    }
}
