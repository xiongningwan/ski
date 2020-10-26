package com.ski.box.view.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.ski.box.R;
import com.ski.box.adapter.BankCardAdapter;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.user.BankCard;
import com.ski.box.bean.user.User;
import com.ski.box.mvp.contract.BankCardContract;
import com.ski.box.mvp.presenter.BankCardPresenter;
import com.ski.box.view.view.HeaderView;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.ToastUtil;

import java.util.List;

import static com.ski.box.ConstantValue.EVENT_BIND_BANK_CARD_SUCCESS;


public class BankCardActivity extends BaseMVPActivity<BankCardContract.Presenter> implements BankCardContract.View, View.OnClickListener {
    HeaderView mHeadView;
    RecyclerView mRvBankCard;
    Button mBtnSure;
    private BankCardAdapter mAdapter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    @Override
    protected BankCardContract.Presenter bindPresenter() {
        return new BankCardPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_bank_card;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        RxBus.get().register(this);
        mHeadView = findViewById(R.id.head_view);
        mRvBankCard = findViewById(R.id.rv_bank_card);
        mBtnSure = findViewById(R.id.btn_sure);
        mHeadView.setHeader(getString(R.string.ski_my_bank_card), true);

        mBtnSure.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle bundle) {
        mAdapter = new BankCardAdapter();
        mRvBankCard.setLayoutManager(new LinearLayoutManager(this));
        mRvBankCard.setAdapter(mAdapter);
    }

    @Override
    protected void processLogic() {
        super.processLogic();
        mPresenter.getBankCardList();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_sure) {
            goToBind();
        }
    }


    private void goToBind() {
        User user = DataCenter.getInstance().getUser();
        if(TextUtils.isEmpty(user.getMobile())){
            ToastUtil.showInfo("先设置手机号");
            startActivity(new Intent(this, BindPhoneActivity.class));
            return;
        }
        if(0 == user.getHavefundPwd()){
            ToastUtil.showInfo("先设置资金密码");
            startActivity(new Intent(this, UpdateFundPwdActivity.class));
            return;
        }
        startActivity(new Intent(this, BankCardAddActivity.class));
    }

    @Override
    public void onSuccessResult(List<BankCard> list) {
        mAdapter.setNewInstance(list);
    }

    @Override
    public void onFailResult(String s) {
    }

    @Subscribe(tags = {@Tag(EVENT_BIND_BANK_CARD_SUCCESS)})
    public void onBindBankSuccess(String s) {
        mPresenter.getBankCardList();
    }

}
