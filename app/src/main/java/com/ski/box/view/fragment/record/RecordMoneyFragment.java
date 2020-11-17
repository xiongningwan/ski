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
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ski.box.R;
import com.ski.box.adapter.RecordBetAdapter2;
import com.ski.box.adapter.RecordMoneyAdapter;
import com.ski.box.bean.ConditionBean;
import com.ski.box.bean.FrontTradeTypesBean;
import com.ski.box.bean.record.RecordBet;
import com.ski.box.bean.record.RecordBetRequest;
import com.ski.box.bean.record.RecordMoney;
import com.ski.box.bean.record.RecordMoneyRequest;
import com.ski.box.mvp.contract.RecordMoneyContract;
import com.ski.box.mvp.contract.group.GroupRecordMoneyContract;
import com.ski.box.mvp.presenter.RecordMoneyPresenter;
import com.ski.box.mvp.presenter.group.GroupRecordMoneyPresenter;
import com.ski.box.utils.ActivityUtil;
import com.ski.box.view.activity.RecordDetailActivity;
import com.ski.box.view.view.dialog.CancelDialog;
import com.ski.box.view.view.dialog.pop.record.AllLotteryPop;
import com.ski.box.view.view.dialog.pop.record.MoneyTypePop;
import com.ski.box.view.view.dialog.pop.record.RecordDate2Pop;
import com.ski.box.view.view.dialog.pop.record.RecordDatePop;
import com.ski.box.view.view.dialog.pop.record.RecordMorePop;
import com.yb.core.base.BaseMVPFragment;
import com.yb.core.utils.LanguageUtil;
import com.yb.core.utils.ScreenUtils;
import com.yb.core.utils.TimeUtils;
import com.yb.core.utils.ToastUtil;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

import java.util.ArrayList;
import java.util.List;

import static com.ski.box.ConstantValue.EVENT_RECORD_CANCEL_SUCCESS;

public class RecordMoneyFragment extends BaseMVPFragment<RecordMoneyContract.Presenter> implements RecordMoneyContract.View, View.OnClickListener, OnRefreshListener, OnLoadMoreListener,
        RecordDate2Pop.DateChooseListener, MoneyTypePop.MoneyTypeChooseListener {

    private LinearLayout mLLDay;
    private TextView mTvDay;
    private ImageView mIvDay;
    private LinearLayout mLLType;
    private TextView mTvType;
    private ImageView mIvType;
    private RefreshLayout mRefreshLayout;
    private RecyclerView mRvRecord;
    private RecordMoneyAdapter mRecordAdapter;
    private RecordMoneyRequest mRecordRequest;
    private int mTotalPage = 1;
    private RecordDate2Pop mDatePop;
    private MoneyTypePop mMoneyTypePop;
    private List<FrontTradeTypesBean> mTypeList;

    public RecordMoneyFragment() {
    }

    public static RecordMoneyFragment newInstance() {
        RecordMoneyFragment fragment = new RecordMoneyFragment();
        return fragment;
    }


    @Override
    protected RecordMoneyContract.Presenter bindPresenter() {
        return new RecordMoneyPresenter(mContext);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.get().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_fragment_record_money;
    }

    @Override
    protected void initView(View view) {
        RxBus.get().register(this);
        mLLDay = view.findViewById(R.id.ll_day);
        mTvDay = view.findViewById(R.id.tv_day);
        mIvDay = view.findViewById(R.id.iv_day);
        mLLType = view.findViewById(R.id.ll_type);
        mTvType = view.findViewById(R.id.tv_type);
        mIvType = view.findViewById(R.id.iv_type);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        mRvRecord = view.findViewById(R.id.recycler_view);

        mLLDay.setOnClickListener(this);
        mLLType.setOnClickListener(this);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
        createType();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mRecordRequest = new RecordMoneyRequest();
        mRecordAdapter = new RecordMoneyAdapter(getActivity());
        mRecordAdapter.setEmptyView(ActivityUtil.getEmptyView(requireActivity()));
        mRvRecord.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvRecord.setAdapter(mRecordAdapter);

        mRecordAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
               // List<RecordBet.ListBean> list = (List<RecordBet.ListBean>) adapter.getData();
              //  RecordBet.ListBean bean = list.get(position);
              //  gotoDetail(bean);
            }
        });

        mRecordRequest.setStartTime(TimeUtils.getBeginStringOfToday());
        mRecordRequest.setEndTime(TimeUtils.getEndStringOfToday());
        mTvDay.setText(LanguageUtil.getText("今天"));
        mTvType.setText(LanguageUtil.getText("全部账变"));
    }

    private void gotoDetail(RecordBet.ListBean bean) {
        Intent intent = new Intent(requireActivity(), RecordDetailActivity.class);
        intent.putExtra(RecordDetailActivity.KEY_RECORD_BEAN, bean);
        startActivity(intent);
    }

    //这个是一个懒加载
    @Override
    protected void loadData() {
        mPresenter.getFrontTradeTypes();
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
        }  else if (id == R.id.ll_type) {
            showType();
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mRecordRequest.setPageNum(1);
        mPresenter.getMoneyRecordData(mRecordRequest);
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
        mPresenter.getMoreMoneyRecordData(mRecordRequest);
    }

    @Override
    public void onSuccessful(Object o) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();

        RecordMoney recordMoney = (RecordMoney) o;
        mTotalPage = recordMoney.getPages();
        mRecordAdapter.setList(recordMoney.getList());
    }

    @Override
    public void onError(Throwable o) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
    }

    @Override
    public void onMoreSuccessful(Object o) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();

        RecordMoney recordMoney = (RecordMoney) o;
        mTotalPage = recordMoney.getPages();
        mRecordAdapter.addData(recordMoney.getList());
    }

    @Override
    public void onMoreError(Throwable o) {
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
        mRecordRequest.setStartTime(bean.getStartDate());
        mRecordRequest.setEndTime(bean.getEndDate());
        mTvDay.setText(bean.getName());
        mRefreshLayout.autoRefresh();
    }

    @Override
    public void onMoneyChoose(ConditionBean bean) {
        if (mMoneyTypePop != null) {
            mMoneyTypePop.dismiss();
        }
        mRecordRequest.setTradeType(bean.getTradeType());
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
            mDatePop = RecordDate2Pop.create(getActivity());
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
            mMoneyTypePop = MoneyTypePop.create(getActivity());
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


    //空布局
    public View getEmptyView() {
        View notDataView = View.inflate(requireActivity(),R.layout.ski_recycler_empty_view,  null);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return notDataView;
    }
}
