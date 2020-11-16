package com.ski.box.view.activity.money;

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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ski.box.R;
import com.ski.box.adapter.GroupRecordMoneyAdapter;
import com.ski.box.adapter.MoneyProgressAdapter;
import com.ski.box.bean.ConditionBean;
import com.ski.box.bean.FrontTradeTypesBean;
import com.ski.box.bean.group.GroupMoneyData;
import com.ski.box.bean.money.MoneyProgressData;
import com.ski.box.mvp.contract.group.GroupRecordMoneyContract;
import com.ski.box.mvp.contract.group.MoneyProgressContract;
import com.ski.box.mvp.presenter.group.GroupRecordMoneyPresenter;
import com.ski.box.mvp.presenter.money.MoneyProgressPresenter;
import com.ski.box.utils.ActivityUtil;
import com.ski.box.view.view.ClearEditText;
import com.ski.box.view.view.HeaderView;
import com.ski.box.view.view.dialog.pop.record.MoneyProgressTypePop;
import com.ski.box.view.view.dialog.pop.record.MoneyTypePop;
import com.ski.box.view.view.dialog.pop.record.RecordDatePop;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.AppUtil;
import com.yb.core.utils.LanguageUtil;
import com.yb.core.utils.ScreenUtils;
import com.yb.core.utils.TimeUtils;
import com.yb.core.utils.ToastUtil;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MoneyProgressActivity extends BaseMVPActivity<MoneyProgressContract.Presenter> implements MoneyProgressContract.View, View.OnClickListener,
        OnRefreshListener, OnLoadMoreListener, RecordDatePop.DateChooseListener, MoneyProgressTypePop.MoneyTypeChooseListener {

    private HeaderView mHeadView;
    private LinearLayout mLLDay;
    private TextView mTvDay;
    private ImageView mIvDay;
    private LinearLayout mLLType;
    private TextView mTvType;
    private ImageView mIvType;
    private RefreshLayout mRefreshLayout;
    private RecyclerView mRvRecord;
    private MoneyProgressAdapter mRecordAdapter;
    private RecordDatePop mDatePop;
    private MoneyProgressTypePop mMoneyTypePop;
    private String startDate;
    private String endDate;
    private String status;
    private int mPageSize = 10;
    private int mPageNo = 1;
    private int mTotalPage = 1;
    private List<FrontTradeTypesBean> mTypeList;

    @Override
    protected MoneyProgressContract.Presenter bindPresenter() {
        return new MoneyProgressPresenter(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_money_progress;
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
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRvRecord = findViewById(R.id.recycler_view);

        mHeadView.setHeader(LanguageUtil.getText(getString(R.string.ski_money_progress)), true);

        mLLDay.setOnClickListener(this);
        mLLType.setOnClickListener(this);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
        createType();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mRecordAdapter = new MoneyProgressAdapter(this);
        mRecordAdapter.setEmptyView(ActivityUtil.getEmptyView(this));
        mRvRecord.setLayoutManager(new LinearLayoutManager(this));
        mRvRecord.setAdapter(mRecordAdapter);

        startDate = TimeUtils.getBeginStringOfToday();
        endDate = TimeUtils.getEndStringOfToday();
        mTvDay.setText(LanguageUtil.getText("今天"));
        mTvType.setText(LanguageUtil.getText("全部类型"));

        mRecordAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull @NotNull BaseQuickAdapter adapter, @NonNull @NotNull View view, int position) {
                List<MoneyProgressData.ListBean> list = adapter.getData();
                MoneyProgressData.ListBean bean =  list.get(position);
                String text = bean.getOrderId();
                AppUtil.copy(text, MoneyProgressActivity.this);
                ToastUtil.showSuccess("复制成功");
            }
        });
    }


    @Override
    protected void processLogic() {
        super.processLogic();
        mRefreshLayout.autoRefresh();
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ll_day) {
            showDay();
        }  else if (id == R.id.ll_type) {
            showType();
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPageNo = 1;
        mPresenter.dwOrderList(startDate, endDate, status, mPageSize, mPageNo);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (mPageNo > mTotalPage) {
            mRefreshLayout.finishLoadMore();
            ToastUtil.showInfo("已经是最后一页了");
            return;
        }
        mPageNo++;
        mPresenter.dwOrderList(startDate, endDate, status, mPageSize, mPageNo);
    }

    @Override
    public void onSuccessful(MoneyProgressData bean) {
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
            mDatePop = RecordDatePop.create(this);
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
            mMoneyTypePop = MoneyProgressTypePop.create(this);
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
