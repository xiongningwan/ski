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
import com.ski.box.adapter.RecordBetAdapter2;
import com.ski.box.bean.ConditionBean;
import com.ski.box.bean.FrontTradeTypesBean;
import com.ski.box.bean.TopGameBean;
import com.ski.box.bean.record.RecordBet;
import com.ski.box.bean.record.RecordBetRequest;
import com.ski.box.mvp.contract.RecordBetContract;
import com.ski.box.mvp.presenter.RecordBetPresenter;
import com.ski.box.utils.ActivityUtil;
import com.ski.box.view.view.ClearEditText;
import com.ski.box.view.view.HeaderView;
import com.ski.box.view.view.dialog.pop.record.AllLotteryPop;
import com.ski.box.view.view.dialog.pop.record.RecordDatePop;
import com.ski.box.view.view.dialog.pop.record.RecordMorePop;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.ScreenUtils;
import com.yb.core.utils.ToastUtil;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

import java.util.ArrayList;
import java.util.List;

public class GroupRecordBetActivity extends BaseMVPActivity<RecordBetContract.Presenter> implements RecordBetContract.View, View.OnClickListener, OnRefreshListener, OnLoadMoreListener,
        RecordDatePop.DateChooseListener, AllLotteryPop.LotteryChooseListener, RecordMorePop.MoreChooseListener {

    private HeaderView mHeadView;
    private LinearLayout mLLDay;
    private TextView mTvDay;
    private ImageView mIvDay;
    private LinearLayout mLLLottery;
    private TextView mTvLottery;
    private ImageView mIvLottery;
    private LinearLayout mLLStatus;
    private TextView mTvStatus;
    private ImageView mIvStatus;
    private RefreshLayout mRefreshLayout;
    private RecyclerView mRvRecord;
    private RecordBetAdapter2 mRecordAdapter;
    private RecordBetRequest mRecordRequest;
    private int mTotalPage = 1;
    private RecordDatePop mDatePop;
    private AllLotteryPop mLotteryPop;
    private RecordMorePop mMorePop;
    private long lastMills = 0;
    private ClearEditText mEtAccount;
    private Button mBtnSure;

    public GroupRecordBetActivity() {
    }

    public static GroupRecordBetActivity newInstance() {
        GroupRecordBetActivity fragment = new GroupRecordBetActivity();
        return fragment;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_record_group_bet;
    }

    @Override
    protected void initViews() {
        RxBus.get().register(this);
        ImmersionBar.with(this).init();
        mHeadView = findViewById(R.id.head_view);
        mLLDay = findViewById(R.id.ll_day);
        mTvDay = findViewById(R.id.tv_day);
        mIvDay = findViewById(R.id.iv_day);
        mLLLottery = findViewById(R.id.ll_lottery);
        mTvLottery = findViewById(R.id.tv_lottery);
        mIvLottery = findViewById(R.id.iv_lottery);
        mLLStatus = findViewById(R.id.ll_status);
        mTvStatus = findViewById(R.id.tv_status);
        mIvStatus = findViewById(R.id.iv_status);
        mEtAccount = findViewById(R.id.et_account);
        mBtnSure = findViewById(R.id.btn_sure);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRvRecord = findViewById(R.id.recycler_view);

        mHeadView.setHeader(getString(R.string.ski_group_manage), true);

        mLLDay.setOnClickListener(this);
        mLLLottery.setOnClickListener(this);
        mLLStatus.setOnClickListener(this);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mRecordRequest = new RecordBetRequest();
        mRecordAdapter = new RecordBetAdapter2(this);
        mRecordAdapter.setEmptyView(ActivityUtil.getEmptyView(this));
        mRvRecord.setLayoutManager(new LinearLayoutManager(this));
        mRvRecord.setAdapter(mRecordAdapter);
    }


    @Override
    protected void processLogic() {
        super.processLogic();
    }


    @Override
    protected RecordBetContract.Presenter bindPresenter() {
        return new RecordBetPresenter(this);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ll_day) {
            showDay();
        } else if (id == R.id.ll_lottery) {
            showLottery();
        } else if (id == R.id.ll_status) {
            showMore();
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mRecordRequest.setPageNum(1);
        mPresenter.getBetRecordData(mRecordRequest);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        int pageNum = mRecordRequest.getPageNum();
        if (pageNum > mTotalPage) {
            mRefreshLayout.finishLoadMore();
            ToastUtil.showInfo("已经是最后一页了");
            return;
        }
        mRecordRequest.setPageNum(pageNum + 1);
        mPresenter.getBetRecordData(mRecordRequest);
    }

    @Override
    public void onSuccessful(Object o) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();

        mRecordAdapter.setUseEmpty(true);
        RecordBet recordBet = (RecordBet) o;
        mTotalPage = recordBet.getTotalPage();
        int currentPage = recordBet.getCurrentPage();
        if (currentPage == 0) {
            /*返回为null*/
            mRecordAdapter.setList(new ArrayList<>());
        } else if (currentPage > 1) {
//            加载更多
            mRecordAdapter.addData(recordBet.getList());
        } else {
            mRecordAdapter.setList(recordBet.getList());
        }
    }

    @Override
    public void onError(Throwable o) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
    }

    @Override
    public void getMoneyTypeSuccess(List<FrontTradeTypesBean> beans) {

    }

    @Override
    public void onCancelSuccess() {
        mRefreshLayout.autoRefresh();
    }


    @Override
    public void onCancelFail(String s) {

    }

    @Override
    public void onDateChoose(ConditionBean bean) {
        if (mDatePop != null) {
            mDatePop.dismiss();
        }

        mRecordRequest.setStartDate(bean.getStartDate());
        mRecordRequest.setEndDate(bean.getEndDate());
        mRecordRequest.setIsLow(bean.getIsLow());
        mTvDay.setText(bean.getName());
        mRefreshLayout.autoRefresh();
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

    private void showLottery() {
        if (mLotteryPop == null) {
            mLotteryPop = AllLotteryPop.create(this);
            mLotteryPop.setDateChooseListener(this);
            mLotteryPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    onArrowAnimation(mIvLottery, 0);
                }
            });
        }
        onArrowAnimation(mIvLottery, 180);
        mLotteryPop.showAtAnchorView(mLLLottery, YGravity.BELOW, XGravity.LEFT, 0, ScreenUtils.dip2px(5));
    }


    private void showMore() {
        if (mMorePop == null) {
            mMorePop = RecordMorePop.create(this, 0);
            mMorePop.setMoreChooseListener(this);
            mMorePop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    onArrowAnimation(mIvStatus, 0);
                }
            });
        }
        onArrowAnimation(mIvStatus, 180);
        mMorePop.showAtAnchorView(mLLStatus, YGravity.BELOW, XGravity.LEFT, 0, ScreenUtils.dip2px(5));
    }

    @Override
    public void onLotteryChoose(TopGameBean bean) {
        if (mLotteryPop != null) {
            mLotteryPop.dismiss();
        }
        String lotteryId;
        if (0 == bean.getTicketId()) {
            lotteryId = "";
        } else {
            lotteryId = String.valueOf(bean.getTicketId());
        }
        mRecordRequest.setTicketId(lotteryId);
        mTvLottery.setText(bean.getTicketName());
        mRefreshLayout.autoRefresh();
    }

    @Override
    public void onMoreChoose(String status) {
        if (mMorePop != null) {
            mMorePop.dismiss();
        }
        mRecordRequest.setStatus(status);
        mRefreshLayout.autoRefresh();
    }


    private void onArrowAnimation(ImageView ivArrowIcon, int angle) {
        ivArrowIcon.setPivotX(ivArrowIcon.getWidth() / 2);
        ivArrowIcon.setPivotY(ivArrowIcon.getHeight() / 2);
        ivArrowIcon.setRotation(angle);
    }



}
