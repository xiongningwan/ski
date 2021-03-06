package com.ski.box.view.fragment.record;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
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
import com.yb.core.utils.LanguageUtil;
import com.ski.box.view.activity.RecordDetailActivity;
import com.ski.box.view.view.dialog.CancelDialog;
import com.ski.box.view.view.dialog.pop.record.AllLotteryPop;
import com.ski.box.view.view.dialog.pop.record.RecordDatePop;
import com.ski.box.view.view.dialog.pop.record.RecordMorePop;
import com.yb.core.base.BaseMVPFragment;
import com.yb.core.utils.ScreenUtils;
import com.yb.core.utils.TimeUtils;
import com.yb.core.utils.ToastUtil;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

import java.util.ArrayList;
import java.util.List;

import static com.ski.box.ConstantValue.EVENT_RECORD_CANCEL_SUCCESS;

public class RecordBetFragment extends BaseMVPFragment<RecordBetContract.Presenter> implements RecordBetContract.View, View.OnClickListener, OnRefreshListener, OnLoadMoreListener,
        RecordDatePop.DateChooseListener, AllLotteryPop.LotteryChooseListener, RecordMorePop.MoreChooseListener {

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
    private CancelDialog mCancelDialog;

    public RecordBetFragment() {
    }

    public static RecordBetFragment newInstance() {
        RecordBetFragment fragment = new RecordBetFragment();
        return fragment;
    }

    @Override
    protected RecordBetContract.Presenter bindPresenter() {
        return new RecordBetPresenter(mContext);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.get().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_fragment_record_bet;
    }

    @Override
    protected void initView(View view) {
        RxBus.get().register(this);
        mLLDay = view.findViewById(R.id.ll_day);
        mTvDay = view.findViewById(R.id.tv_day);
        mIvDay = view.findViewById(R.id.iv_day);
        mLLLottery = view.findViewById(R.id.ll_lottery);
        mTvLottery = view.findViewById(R.id.tv_lottery);
        mIvLottery = view.findViewById(R.id.iv_lottery);
        mLLStatus = view.findViewById(R.id.ll_status);
        mTvStatus = view.findViewById(R.id.tv_status);
        mIvStatus = view.findViewById(R.id.iv_status);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        mRvRecord = view.findViewById(R.id.recycler_view);

        mLLDay.setOnClickListener(this);
        mLLLottery.setOnClickListener(this);
        mLLStatus.setOnClickListener(this);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mRecordRequest = new RecordBetRequest();
        mRecordAdapter = new RecordBetAdapter2(getActivity());
        mRecordAdapter.setEmptyView(ActivityUtil.getEmptyView(requireActivity()));
        mRvRecord.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvRecord.setAdapter(mRecordAdapter);
        mRecordAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                long curMills = System.currentTimeMillis();
                long l = curMills - lastMills;
                if (l < 1000) {
                    return;
                }
                lastMills = curMills;
                List<RecordBet.ListBean> list = adapter.getData();
                RecordBet.ListBean bean = list.get(position);
                int id = view.getId();
                if (id == R.id.tv_cancel) { // 撤销订单
                    showBetCancelDialog(bean);
                }
            }
        });

        mRecordAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                List<RecordBet.ListBean> list = (List<RecordBet.ListBean>) adapter.getData();
                RecordBet.ListBean bean = list.get(position);
                gotoDetail(bean);
            }
        });

        mRecordRequest.setStartDate(TimeUtils.getBeginStringOfToday());
        mRecordRequest.setEndDate(TimeUtils.getEndStringOfToday());
        mTvDay.setText(LanguageUtil.getText("今天"));
        mTvStatus.setText(LanguageUtil.getText("全部状态"));
    }

    private void gotoDetail(RecordBet.ListBean bean) {
        Intent intent = new Intent(requireActivity(), RecordDetailActivity.class);
        intent.putExtra(RecordDetailActivity.KEY_RECORD_BEAN, bean);
        startActivity(intent);
    }

    //这个是一个懒加载
    @Override
    protected void loadData() {
        mRefreshLayout.autoRefresh();
    }

    @Override
    protected void processLogic() {
        super.processLogic();
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

    @Subscribe(tags = {@Tag(EVENT_RECORD_CANCEL_SUCCESS)})
    public void onCancelSuccess(String s) {
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
            mDatePop = RecordDatePop.create(getActivity());
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
            mLotteryPop = AllLotteryPop.create(getActivity());
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
            mMorePop = RecordMorePop.create(getActivity(), 0);
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
            lotteryId = null;
        } else {
            lotteryId = String.valueOf(bean.getTicketId());
        }
        mRecordRequest.setTicketId(lotteryId);
        mTvLottery.setText(bean.getTicketName());
        mRefreshLayout.autoRefresh();
    }

    @Override
    public void onMoreChoose(ConditionBean  status) {
        if (mMorePop != null) {
            mMorePop.dismiss();
        }
        mRecordRequest.setStatus(status.getStatus());
        mTvStatus.setText(status.getName());
        mRefreshLayout.autoRefresh();
    }

//    @Override
//    public void onDismiss() {
//        restoreFilterText();
//    }

//    public void restoreFilterText() {
//        onArrowAnimation(mIvDay,0);
////        tvLotteryName.setTextColor(ContextCompat.getColor(getActivity(), R.color.ybcp_pront_three));
////        ivArrowIcon.setColorFilter(ContextCompat.getColor(getActivity(), R.color.ybcp_double_bottom_balance_disabled));
////        tvDay.setTextColor(ContextCompat.getColor(getActivity(), R.color.ybcp_pront_three));
////        ivDayIcon.setColorFilter(ContextCompat.getColor(getActivity(), R.color.ybcp_double_bottom_balance_disabled));
////        moreImage.setColorFilter(ContextCompat.getColor(getActivity(), R.color.ybcp_double_bottom_balance_disabled));
//    }

    private void onArrowAnimation(ImageView ivArrowIcon, int angle) {
        ivArrowIcon.setPivotX(ivArrowIcon.getWidth() / 2);
        ivArrowIcon.setPivotY(ivArrowIcon.getHeight() / 2);
        ivArrowIcon.setRotation(angle);
    }


    /**
     * 投注撤单
     *
     * @param bean
     */
    private void showBetCancelDialog(RecordBet.ListBean bean) {
        mCancelDialog = new CancelDialog(getActivity(), new CancelDialog.OnClickconfirmListener() {
            @Override
            public void confirm() {
                /** 撤单 **/
                mPresenter.showCancelDialog(bean);
            }

            @Override
            public void cancel() {

            }
        });
        mCancelDialog.show();
    }



}
