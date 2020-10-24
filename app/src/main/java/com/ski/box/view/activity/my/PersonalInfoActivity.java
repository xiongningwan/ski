package com.ski.box.view.activity.my;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.ski.box.R;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.user.MemberInfo;
import com.ski.box.bean.user.User;
import com.ski.box.mvp.contract.PersonalInfoContract;
import com.ski.box.mvp.presenter.PersonalInfoPresenter;
import com.ski.box.view.view.HeaderView;
import com.yb.core.base.BaseActivity;
import com.yb.core.base.BaseMVPActivity;

import static com.ski.box.ConstantValue.EVENT_FUND_PWD_UPDATE;
import static com.ski.box.ConstantValue.EVENT_TYPE_USER_NAME_NICK_NAME;

public class PersonalInfoActivity extends BaseMVPActivity<PersonalInfoContract.Presenter> implements PersonalInfoContract.View, View.OnClickListener {

    private HeaderView mHeadView;
    private TextView mTvLv;
    private TextView mTvIp;
    private TextView mTvAdd;
    private TextView mTvTime;
    private TextView mTvTipNick;
    private TextView mTvTipMoneyPwd;
    private TextView mTvTipPhone;
    private TextView mTvTipEmail;
    private ConstraintLayout mClNickName;
    private ConstraintLayout mClLoginPwd;
    private ConstraintLayout mClFundPwd;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    @Override
    protected PersonalInfoContract.Presenter bindPresenter() {
        return new PersonalInfoPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_personal_info;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        RxBus.get().register(this);
        mHeadView = findViewById(R.id.head_view);
        mHeadView.setHeader(getResources().getString(R.string.ski_personal_info), true);
        mTvLv = findViewById(R.id.tv_level);
        mTvIp = findViewById(R.id.tv_value_ip);
        mTvAdd = findViewById(R.id.tv_value_add);
        mTvTime = findViewById(R.id.tv_value_time);

        mTvTipNick = findViewById(R.id.tv_tip_nick);
        mTvTipMoneyPwd = findViewById(R.id.tv_tip_money_pwd);
        mTvTipPhone = findViewById(R.id.tv_tip_phone);
        mTvTipEmail = findViewById(R.id.tv_tip_email);

        mClNickName = findViewById(R.id.cl_nick_name);
        mClLoginPwd = findViewById(R.id.cl_login_pwd);
        mClFundPwd = findViewById(R.id.cl_money_pwd);
        mClNickName.setOnClickListener(this);
        mClLoginPwd.setOnClickListener(this);
        mClFundPwd.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle bundle) {
        User user = DataCenter.getInstance().getUser();
        mTvLv.setText("VIP" + user.getVip());
        mTvIp.setText(user.getLoginIp());
        mTvAdd.setText(user.getLoginLocation());
        mTvTime.setText(user.getLoginTime());

        if(TextUtils.isEmpty(user.getAlias())) {
            mTvTipNick.setText(getString(R.string.ski_my_unset));
        } else {
            mTvTipNick.setText(user.getAlias());
        }
        if(0 == user.getHavefundPwd()) {
            mTvTipMoneyPwd.setVisibility(View.VISIBLE);
        } else {
            mTvTipMoneyPwd.setVisibility(View.GONE);
        }
        if(TextUtils.isEmpty(user.getMobile())) {
            mTvTipPhone.setText(getString(R.string.ski_my_unset));
        } else {
            mTvTipPhone.setText(user.getMobile());
        }
        if(TextUtils.isEmpty(user.getEmail())) {
            mTvTipEmail.setText(getString(R.string.ski_my_unset));
        } else {
            mTvTipEmail.setText(user.getEmail());
        }
    }


    @Override
    public void onMemberInfoResult(MemberInfo memberInfo) {

    }

    @Override
    public void onMemberInfoFailResult(String s) {

    }

    @Subscribe(tags = {@Tag(EVENT_TYPE_USER_NAME_NICK_NAME)})
    public void onUserNameUpdate(String s) {
        User user = DataCenter.getInstance().getUser();
        mTvTipNick.setText(user.getAlias());
    }

    @Subscribe(tags = {@Tag(EVENT_FUND_PWD_UPDATE)})
    public void onFundPwdUpdate(String s) {
        mTvTipMoneyPwd.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.cl_nick_name) {
            startActivity(new Intent(this, UpdateNickNameActivity.class));
        } else if(id == R.id.cl_login_pwd) {
            startActivity(new Intent(this, UpdateLoginPwdActivity.class));
        } else if(id == R.id.cl_money_pwd) {
            startActivity(new Intent(this, UpdateFundPwdActivity.class));
        }

    }
}
