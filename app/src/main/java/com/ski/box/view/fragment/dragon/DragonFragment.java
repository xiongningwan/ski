package com.ski.box.view.fragment.dragon;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ski.box.R;
import com.ski.box.adapter.DragonAdapter;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.LongDragonPushInfoEntity;
import com.ski.box.bean.NoticeConfigInfoEntity;
import com.ski.box.bean.SelfProfileEntity;
import com.ski.box.bean.TicketLotteryTimeBean;
import com.ski.box.bean.lottery.LotteryConstant;
import com.ski.box.bean.lottery.LotteryPlayStart;
import com.ski.box.bean.lottery.RemoteLotteryPlay;
import com.ski.box.mvp.contract.DragonContract;
import com.ski.box.mvp.contract.EmptyContract;
import com.ski.box.mvp.presenter.DragonPresenter;
import com.ski.box.mvp.presenter.EmptyPresenter;
import com.ski.box.view.view.SpaceItemDecoration;
import com.yb.core.base.BaseMVPFragment;
import com.yb.core.utils.SPUtils;
import com.yb.core.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;


public class DragonFragment extends BaseMVPFragment<DragonContract.Presenter> implements DragonContract.View, View.OnClickListener, OnRefreshListener {
    public static final int BUTTON_CURRENT = 0;
    public static final int BUTTON_SER = 1;
    public static final int BUTTON_ALL = 2;
    private static final String LONG_DRAGON_MODE = "2";
    private RecyclerView mRVDragon;
    private SmartRefreshLayout mRefreshLayout;
    private RelativeLayout mEmpty;
    private DragonAdapter mDragonAdapter;
    private RelativeLayout mSettingOut;
    //    private ChoseLotteryDialog mChoseLotteryDialog;
    private TextView tvLotteryCurrent;
    private TextView tvLotteryChose;
    private TextView tvSetting;
    /**
     * 自定义彩种
     **/
    private LinearLayout llTickets;
    private LinearLayout rlSettingLottery;
    private TextView tvLotterys;
    /**
     * 彩种数量
     **/
    private TextView tvLotterysNo;
    private ImageView ivTicket;
    private ImageView ivArrowOut;
    private TextView tvOutValue;
    private String mThreshold_min;
    private String mThreshold_max;
    //用于存放数据彩种id数据
    private List<Integer> mAllTicketIdList = new ArrayList<>();
    private List<Integer> mRequestTicketIdList = new ArrayList<>();
    private int mButtonPosition = BUTTON_CURRENT;
    private LongDragonPushInfoEntity mInfoEntity;
    /**
     * 当前彩种id
     **/
    private int ticketId;
    /**
     * 防止快速点击
     **/
    private int mBtmTimeInterval = 1000;
    private long mLastClickTime;
    private long mTimeInterval = 300L;

    /**
     * 连出弹框
     */
//    private ChosePeroidDialog choseOutPeriodDialog;
    public static DragonFragment newInstance() {
        DragonFragment fragment = new DragonFragment();
        Bundle bundle = new Bundle();
//        bundle.putInt(KEY_FROM, from);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_hall_fragment_long_dragon_layout;
    }

    @Override
    protected DragonContract.Presenter bindPresenter() {
        return new DragonPresenter(getContext());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected void initView(View view) {
        RxBus.get().register(this);
        mEmpty = view.findViewById(R.id.view_empty);
        mRVDragon = view.findViewById(R.id.recyc_double_dragon);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        mSettingOut = view.findViewById(R.id.rl_out);
        tvLotteryCurrent = view.findViewById(R.id.tv_lottery_current);
        tvLotteryChose = view.findViewById(R.id.tv_lottery_chose);
        tvSetting = view.findViewById(R.id.tv_setting);
        llTickets = view.findViewById(R.id.ll_tickets);
        tvLotterys = view.findViewById(R.id.tv_lotterys);
        tvLotterysNo = view.findViewById(R.id.tv_lotterys_no);
        rlSettingLottery = view.findViewById(R.id.rl_setting_lottery);
        ivTicket = view.findViewById(R.id.iv_ticket);
        ivArrowOut = view.findViewById(R.id.iv_arrow_out);
        tvOutValue = view.findViewById(R.id.tv_out_value);
        mDragonAdapter = new DragonAdapter(requireActivity());
        tvSetting.setOnClickListener(this);
        llTickets.setOnClickListener(this);
        mSettingOut.setOnClickListener(this);
        tvLotteryCurrent.setOnClickListener(this);
        tvLotteryChose.setOnClickListener(this);
        mRefreshLayout.setOnRefreshListener(this);
        tvLotterysNo.setOnClickListener(this);

        ticketId = DataCenter.getInstance().getCurLotteryId();

        mDragonAdapter.setOnItemClickListener((adapter, view1, position) -> {
            mInfoEntity = mDragonAdapter.getData().get(position);
            int mode = LotteryConstant.LOTTERY_PLAY_MODE_DOUBLE;
            int ticketId = mInfoEntity.getTicketId().intValue();
            List<LotteryPlayStart> plays = DataCenter.getInstance().getRemotePlay(ticketId, mode);
            if (0 == plays.size()) {
                mPresenter.getPlays(ticketId, mode);
            } else {
                // setBetDragon();
            }
        });


    }

    @Override
    protected void initData(Bundle bundle) {
        int distance = ScreenUtils.dp2px(3);
        mRVDragon.setLayoutManager(new LinearLayoutManager(getContext()));
        if (mRVDragon.getItemDecorationCount() == 0) {
            mRVDragon.addItemDecoration(new SpaceItemDecoration(distance));
        }
        mRVDragon.setAdapter(mDragonAdapter);
    }


    @Override
    protected void loadData() {
        mPresenter.getSelfProfile();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        if (mRequestTicketIdList != null && mRequestTicketIdList.size() != 0) {
            mPresenter.getLongDragonPushInfo(LONG_DRAGON_MODE, mThreshold_min, mThreshold_max, mRequestTicketIdList);
        } else {
            mRefreshLayout.finishRefresh();
        }
    }


    @Override
    public void onPlaysResult(List<RemoteLotteryPlay> plays) {

    }

    @Override
    public void onPlaysErrorResult(String s) {

    }

    @Override
    public void onLotteryConfigResult(List<NoticeConfigInfoEntity> list) {

    }

    @Override
    public void onLimitConfigResult(int min, int max, List<SelfProfileEntity.LongDragonTicketsBean> list) {
        mAllTicketIdList.clear();
        for (SelfProfileEntity.LongDragonTicketsBean bean : list) {
            String ticketIds = bean.getTicketIds();
            String[] split = ticketIds.split(",");
            for (String str : split) {
                mAllTicketIdList.add(Integer.valueOf(str));
            }
        }
        mRefreshLayout.autoRefresh();
    }

    @Override
    public void onSaveLongDragonLimitSuccess(String s) {

    }

    @Override
    public void onSaveLongDragonLimitError(String s) {

    }

    @Override
    public void onSaveLongDragonTicketIdsSuccess(String s) {

    }

    @Override
    public void onSaveLongDragonTicketIdsError(String s) {

    }

    @Override
    public void onListSuccessResult(List<LongDragonPushInfoEntity> list) {
        mRefreshLayout.finishRefresh();
        int lotteryId = DataCenter.getInstance().getCurLotteryId();
        ArrayList<LongDragonPushInfoEntity> removeList = new ArrayList<>();
        for (LongDragonPushInfoEntity bean : list) {
            if (BUTTON_CURRENT == mButtonPosition) {
                if (bean.getTicketId().intValue() == lotteryId) {
                    bean.setItemType(DragonAdapter.TYPE_CURRENT_LOTTERY);
                }
            } else {
                bean.setItemType(DragonAdapter.TYPE_OTHER_LOTTERY);
            }
            long curEndSaleTime = bean.getCurEndSaleTime();
            long times = curEndSaleTime - System.currentTimeMillis();
            if (times < 1000) {
                removeList.add(bean);
            }
        }
        mDragonAdapter.unregisterBCast();
        if (list.size() != 0) {
            list.removeAll(removeList);
            if (list.size() == 0) {
                setGoneRV();
            } else {
                setVisibilityRV();
                mDragonAdapter.clearTimeList();
                mDragonAdapter.setNewInstance(list);
            }
        } else {
            mRVDragon.setVisibility(View.GONE);
            mEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onListErrorResult(String msg) {
        mRefreshLayout.finishRefresh();
        setGoneRV();
    }

    @Override
    public void onSellsResult(TicketLotteryTimeBean dataBean) {

    }

    private void setVisibilityRV() {
        mRVDragon.setVisibility(View.VISIBLE);
        mEmpty.setVisibility(View.GONE);
    }

    private void setGoneRV() {
        mRVDragon.setVisibility(View.GONE);
        mEmpty.setVisibility(View.VISIBLE);
    }


}
