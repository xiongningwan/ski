package com.ski.box.view.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ski.box.R;
import com.ski.box.adapter.BankCardAdapter;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.user.BankCard;
import com.ski.box.bean.user.User;
import com.ski.box.mvp.contract.my.BankCardContract;
import com.ski.box.mvp.presenter.my.BankCardPresenter;
import com.ski.box.utils.LanguageUtil;
import com.ski.box.view.view.HeaderView;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import static com.ski.box.ConstantValue.EVENT_BIND_BANK_CARD_SUCCESS;


public class BankCardActivity extends BaseMVPActivity<BankCardContract.Presenter> implements BankCardContract.View, View.OnClickListener, OnRefreshListener, OnLoadMoreListener {
    private HeaderView mHeadView;
    private RefreshLayout mRefreshLayout;
    private RecyclerView mRvRecord;
//    private Button mBtnSure;
//    private TextView mTvTip2;
//    private TextView mTvTip4;
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
        mRefreshLayout =findViewById(R.id.refreshLayout);
        mRvRecord = findViewById(R.id.recycler_view);
//        mBtnSure = mFooterView.findViewById(R.id.btn_sure);
//        mTvTip2 = mFooterView.findViewById(R.id.tv_tip_2);
//        mTvTip4 = mFooterView.findViewById(R.id.tv_tip_4);
        mHeadView.setHeader(LanguageUtil.getText(getString(R.string.ski_my_bank_card)), true);
      //  mRefreshLayout.setRefreshFooter(mFooterView);
//        mBtnSure.setOnClickListener(this);

        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
    }

    @Override
    protected void initData(Bundle bundle) {
        mAdapter = new BankCardAdapter();
        mRvRecord.setLayoutManager(new LinearLayoutManager(this));
        mRvRecord.setAdapter(mAdapter);

        List<BankCard> bankCards = new ArrayList<>();
        bankCards.add(getFooterData());
        mAdapter.setNewInstance(bankCards);
        //setRedTip();

        mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                if(view.getId() == R.id.btn_sure) {
                    goToBind();
                }
            }
        });
    }

    private BankCard getFooterData() {
        BankCard bankCard = new BankCard();
        bankCard.setItemType(1);
        return bankCard;
    }


    @Override
    protected void processLogic() {
        super.processLogic();
        mPresenter.getBankCardList();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
//        if (id == R.id.btn_sure) {
//            goToBind();
//        }
    }


    private void goToBind() {
        User user = DataCenter.getInstance().getUser();
        if (TextUtils.isEmpty(user.getMobile())) {
            ToastUtil.showInfo(LanguageUtil.getText("先设置手机号"));
            startActivity(new Intent(this, BindPhoneActivity.class));
            return;
        }
        if (0 == user.getHavefundPwd()) {
            ToastUtil.showInfo(LanguageUtil.getText("先设置资金密码"));
            startActivity(new Intent(this, UpdateFundPwdActivity.class));
            return;
        }
        startActivity(new Intent(this, BankCardAddActivity.class));
    }

    @Override
    public void onSuccessResult(List<BankCard> list) {
        mRefreshLayout.finishRefresh();

        list.add(getFooterData());
        mAdapter.setNewInstance(list);
    }

    @Override
    public void onFailResult(String s) {
    }

    @Subscribe(tags = {@Tag(EVENT_BIND_BANK_CARD_SUCCESS)})
    public void onBindBankSuccess(String s) {
        mPresenter.getBankCardList();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPresenter.getBankCardList();
    }
}
