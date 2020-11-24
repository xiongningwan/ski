package com.ski.box.view.activity.group;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ski.box.R;
import com.ski.box.adapter.GroupRecordMoneyAdapter;
import com.ski.box.bean.ConditionBean;
import com.ski.box.bean.FrontTradeTypesBean;
import com.ski.box.bean.group.GroupMoneyData;
import com.ski.box.mvp.contract.group.GroupRecordMoneyContract;
import com.ski.box.mvp.presenter.group.GroupRecordMoneyPresenter;
import com.ski.box.utils.ActivityUtil;
import com.ski.box.view.view.ClearEditText;
import com.ski.box.view.view.HeaderView;
import com.ski.box.view.view.dialog.pop.record.MoneyTypePop;
import com.ski.box.view.view.dialog.pop.record.RecordDate2Pop;
import com.ski.box.view.view.dialog.pop.record.RecordDatePop;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.LanguageUtil;
import com.yb.core.utils.ScreenUtils;
import com.yb.core.utils.TimeUtils;
import com.yb.core.utils.ToastUtil;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

import java.util.List;

public class GroupRecordMoneyActivity extends BaseMVPActivity<GroupRecordMoneyContract.Presenter> implements GroupRecordMoneyContract.View, View.OnClickListener,
        OnRefreshListener, OnLoadMoreListener, RecordDate2Pop.DateChooseListener, MoneyTypePop.MoneyTypeChooseListener {

    private HeaderView mHeadView;
    private LinearLayout mLLDay;
    private TextView mTvDay;
    private ImageView mIvDay;
    private LinearLayout mLLType;
    private TextView mTvType;
    private ImageView mIvType;
    private RefreshLayout mRefreshLayout;
    private RecyclerView mRvRecord;
    private GroupRecordMoneyAdapter mRecordAdapter;
    private RecordDate2Pop mDatePop;
    private MoneyTypePop mMoneyTypePop;
    private ClearEditText mEtAccount;
    private Button mBtnSure;
    private String startDate;
    private String endDate;
    private String status;
    private String memberAccount;
    private int mPageSize = 10;
    private int mPageNo = 1;
    private int mTotalPage = 1;
    private List<FrontTradeTypesBean> mTypeList;

    @Override
    protected GroupRecordMoneyContract.Presenter bindPresenter() {
        return new GroupRecordMoneyPresenter(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_record_group_money;
    }

    @Override
    protected void initViews() {
        RxBus.get().register(this);
        ImmersionBar.with(this).init();
        mHeadView = findViewById(R.id.head_view);
        mLLDay = findViewById(R.id.ll_day);
        mTvDay = findViewById(R.id.tv_day);
        mIvDay = findViewById(R.id.iv_day);
        mLLType = findViewById(R.id.ll_type);
        mTvType = findViewById(R.id.tv_type);
        mIvType = findViewById(R.id.iv_type);
        mEtAccount = findViewById(R.id.et_account);
        mBtnSure = findViewById(R.id.btn_sure);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRvRecord = findViewById(R.id.recycler_view);

        mHeadView.setHeader(LanguageUtil.getText(getString(R.string.ski_group_money_change)), true);

        mLLDay.setOnClickListener(this);
        mLLType.setOnClickListener(this);
        mBtnSure.setOnClickListener(this);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
        createType();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mRecordAdapter = new GroupRecordMoneyAdapter(this);
        mRecordAdapter.setEmptyView(ActivityUtil.getEmptyView(this));
        mRvRecord.setLayoutManager(new LinearLayoutManager(this));
        mRvRecord.setAdapter(mRecordAdapter);

        startDate = TimeUtils.getBeginStringOfToday();
        endDate = TimeUtils.getEndStringOfToday();
        mTvDay.setText(LanguageUtil.getText("今天"));
    }


    @Override
    protected void processLogic() {
        super.processLogic();
        mPresenter.getFrontTradeTypes();
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ll_day) {
            showDay();
        }  else if (id == R.id.ll_type) {
            showType();
        }else if (id == R.id.btn_sure) {
            memberAccount = mEtAccount.getText().toString().trim();
            mRefreshLayout.autoRefresh();
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPageNo = 1;
        mPresenter.getTeamTransList(startDate, endDate, status, memberAccount, mPageSize, mPageNo);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (mPageNo > mTotalPage) {
            mRefreshLayout.finishLoadMore();
            ToastUtil.showInfo("已经是最后一页了");
            return;
        }
        mPageNo++;
        mPresenter.getTeamTransList(startDate, endDate, status, memberAccount, mPageSize, mPageNo);
    }

    @Override
    public void onSuccessful(GroupMoneyData bean) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();

        mRecordAdapter.setUseEmpty(true);
        mTotalPage = bean.getTotalPage();
        int currentPage = bean.getCurrentPage();
        if (currentPage > 1) {
//            加载更多
            mRecordAdapter.addData(bean.getList());
        } else {
            mRecordAdapter.setList(bean.getList());
        }
    }

    @Override
    public void onFailResult(String s) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
    }

    @Override
    public void onMoneyTypeSuccess(List<FrontTradeTypesBean> beans) {
        mTypeList = beans;
        mMoneyTypePop.setMoneyType(mTypeList);
        mRecordAdapter.setMoneyType(mTypeList);
        mRefreshLayout.autoRefresh();
    }


    @Override
    public void onDateChoose(ConditionBean bean) {
        if (mDatePop != null) {
            mDatePop.dismiss();
        }
        startDate = bean.getStartDate();
        endDate = bean.getEndDate();
        mTvDay.setText(bean.getName());
        mRefreshLayout.autoRefresh();
    }

    @Override
    public void onMoneyChoose(ConditionBean bean) {
        if (mMoneyTypePop != null) {
            mMoneyTypePop.dismiss();
        }
        this.status = bean.getTradeType();
        mTvType.setText(bean.getName());
        mRefreshLayout.autoRefresh();
    }


    private void onArrowAnimation(ImageView ivArrowIcon, int angle) {
        ivArrowIcon.setPivotX(ivArrowIcon.getWidth() / 2);
        ivArrowIcon.setPivotY(ivArrowIcon.getHeight() / 2);
        ivArrowIcon.setRotation(angle);
    }

    private void showDay() {
        if (mDatePop == null) {
            mDatePop = RecordDate2Pop.create(this);
            mDatePop.setDateChooseListener(this);
            mDatePop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    onArrowAnimation(mIvDay, 0);
                }
            });
        }
        onArrowAnimation(mIvDay, 180);
        mDatePop.showAtAnchorView(mLLDay, YGravity.BELOW, XGravity.LEFT, 0, ScreenUtils.dip2px(5));
    }

    private void createType() {
        if (mMoneyTypePop == null) {
            mMoneyTypePop = MoneyTypePop.create(this);
            mMoneyTypePop.setMoneyTypeChooseListener(this);
            mMoneyTypePop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    onArrowAnimation(mIvType, 0);
                }
            });
        }
    }


    private void showType() {
        onArrowAnimation(mIvType, 180);
        mMoneyTypePop.showAtAnchorView(mLLType, YGravity.BELOW, XGravity.LEFT, 0, ScreenUtils.dip2px(5));
    }



}
