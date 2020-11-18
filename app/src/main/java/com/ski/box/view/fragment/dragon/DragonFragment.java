package com.ski.box.view.fragment.dragon;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ski.box.R;
import com.ski.box.adapter.DragonAdapter;
import com.ski.box.bean.BallBean;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.LongDragonPushInfoEntity;
import com.ski.box.bean.LotteryNumBean;
import com.ski.box.bean.NoticeConfigInfoEntity;
import com.ski.box.bean.SelfProfileEntity;
import com.ski.box.bean.TicketLotteryTimeBean;
import com.ski.box.bean.lottery.LotteryBean;
import com.ski.box.bean.lottery.LotteryConstant;
import com.ski.box.bean.lottery.LotteryPlayStart;
import com.ski.box.bean.lottery.LotterySer;
import com.ski.box.bean.lottery.LotteryUtil;
import com.ski.box.bean.lottery.PlayUtil;
import com.ski.box.bean.lottery.RemoteLotteryPlay;
import com.ski.box.mvp.contract.DragonContract;
import com.ski.box.mvp.presenter.DragonPresenter;
import com.yb.core.utils.LanguageUtil;
import com.ski.box.view.view.SpaceItemDecoration;
import com.ski.box.view.view.dialog.LotteryDialog;
import com.yb.core.base.BaseMVPFragment;
import com.yb.core.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import static com.ski.box.ConstantValue.EVENT_DRAGON_ITEM_REQUEST_COUNT_DOWN;
import static com.ski.box.ConstantValue.EVENT_OPEN_RESULT_UPDATE;
import static com.ski.box.ConstantValue.EVENT_TYPE_LONG_DRAGON_BET_CLICK;


public class DragonFragment extends BaseMVPFragment<DragonContract.Presenter> implements DragonContract.View, View.OnClickListener, OnRefreshListener {
    public static final int BUTTON_CURRENT = 0;
    public static final int BUTTON_SER = 1;
    public static final int BUTTON_ALL = 2;
    private static final String LONG_DRAGON_MODE = "2";
    private RecyclerView mRVDragon;
    private SmartRefreshLayout mRefreshLayout;
    private RelativeLayout mEmpty;
    private DragonAdapter mDragonAdapter;
    private TextView tvLotteryCurrent;
    private TextView tvSer;
    private TextView tvAll;
    private List<Integer> mAllTicketIdList = new ArrayList<>();
    private List<Integer> mRequestTicketIdList = new ArrayList<>();
    private int mButtonPosition = BUTTON_CURRENT;
    private LongDragonPushInfoEntity mInfoEntity;
    /**
     * 防止快速点击
     **/
    private int mBtmTimeInterval = 1000;
    private long mLastClickTime;
    private long mTimeInterval = 300L;

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
        tvLotteryCurrent = view.findViewById(R.id.tv_lottery_current);
        tvSer = view.findViewById(R.id.tv_ser);
        tvAll = view.findViewById(R.id.tv_all);
        mDragonAdapter = new DragonAdapter(requireActivity());
        tvLotteryCurrent.setOnClickListener(this);
        tvSer.setOnClickListener(this);
        tvAll.setOnClickListener(this);
        mRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initData(Bundle bundle) {
        int distance = ScreenUtils.dp2px(3);
        mRVDragon.setLayoutManager(new LinearLayoutManager(getContext()));
        if (mRVDragon.getItemDecorationCount() == 0) {
            mRVDragon.addItemDecoration(new SpaceItemDecoration(distance));
        }
        mDragonAdapter.setOnItemClickListener((adapter, view1, position) -> {
            mInfoEntity = mDragonAdapter.getData().get(position);
            int mode = LotteryConstant.LOTTERY_PLAY_MODE_DOUBLE;
            int ticketId = mInfoEntity.getTicketId().intValue();
            List<LotteryPlayStart> plays = DataCenter.getInstance().getRemotePlay(ticketId, mode);
            if (0 == plays.size()) {
                mPresenter.getPlays(ticketId, mode);
            } else {
                 setBetDragon();
            }
        });
        mRVDragon.setAdapter(mDragonAdapter);
    }


    @Override
    protected void loadData() {
        mPresenter.getSelfProfile();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.tv_lottery_current) {
            long nowTime = System.currentTimeMillis();
            if (nowTime - mLastClickTime < mBtmTimeInterval) {
                return;
            }
            mLastClickTime = nowTime;
            if (mRefreshLayout.getState().isFinishing) {
                new LotteryDialog().showCenterRemind(getContext(), LanguageUtil.getText("加载中..."));
                return;
            }
            setCurrentLottery();
        } else  if(id == R.id.tv_ser) {
            long nowTime = System.currentTimeMillis();
            if (nowTime - mLastClickTime < mBtmTimeInterval) {
                return;
            }
            mLastClickTime = nowTime;
            if (mRefreshLayout.getState().isFinishing) {
                new LotteryDialog().showCenterRemind(getContext(), LanguageUtil.getText("加载中..."));
                return;
            }
            setCurrentSer();
        } else  if(id == R.id.tv_all) {
            long nowTime = System.currentTimeMillis();
            if (nowTime - mLastClickTime < mBtmTimeInterval) {
                return;
            }
            mLastClickTime = nowTime;
            if (mRefreshLayout.getState().isFinishing) {
                new LotteryDialog().showCenterRemind(getContext(), LanguageUtil.getText("加载中..."));
                return;
            }
           setAll();
        }
    }



    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        if (mRequestTicketIdList != null && mRequestTicketIdList.size() != 0) {
            requestData();
        } else {
            mRefreshLayout.finishRefresh();
        }
    }

    private void requestData() {
        mPresenter.getLongDragonPushInfo(LONG_DRAGON_MODE, "4", "0", mRequestTicketIdList);
    }

    private void setBetDragon() {
        if (mInfoEntity == null) {
            return;
        }
        Number playId = mInfoEntity.getPlayItemId();
        int ticketId = mInfoEntity.getTicketId().intValue();
        long l = playId.longValue();
        int mode = LotteryConstant.LOTTERY_PLAY_MODE_DOUBLE;
        List<BallBean> odds = PlayUtil.getDragonOdds(l, ticketId, mode);
        for (int i = 0; i < odds.size(); i++) {
            BallBean ballBean = odds.get(i);
            ballBean.setCheck(false);
        }
        DataCenter.getInstance().setBallBeanList(odds);
        RxBus.get().post(EVENT_TYPE_LONG_DRAGON_BET_CLICK, mInfoEntity);
    }

    @Override
    public void onPlaysResult(List<RemoteLotteryPlay> plays) {
        setBetDragon();
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
       setCurrentLottery();
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

    private void setCurrentLottery() {
        mButtonPosition = BUTTON_CURRENT;
        int lotteryId = DataCenter.getInstance().getCurLotteryId();
        mRequestTicketIdList.clear();
        mRequestTicketIdList.add(lotteryId);
        tvLotteryCurrent.setSelected(true);
        tvSer.setSelected(false);
        tvAll.setSelected(false);
        mRefreshLayout.autoRefresh();
    }

    private void setCurrentSer() {
        mButtonPosition = BUTTON_SER;
        mRequestTicketIdList.clear();
        mRequestTicketIdList = getSerIdList();
        tvLotteryCurrent.setSelected(false);
        tvSer.setSelected(true);
        tvAll.setSelected(false);
        mRefreshLayout.autoRefresh();
    }

    private void setAll() {
        mButtonPosition = BUTTON_ALL;
        mRequestTicketIdList.clear();
        mRequestTicketIdList.addAll(mAllTicketIdList);
        tvLotteryCurrent.setSelected(false);
        tvSer.setSelected(false);
        tvAll.setSelected(true);
        mRefreshLayout.autoRefresh();
    }

    public static List<Integer> getSerIdList() {
        List<Integer> setTicketIdList = new ArrayList<>();
        int seriesId = DataCenter.getInstance().getLotterySeriesId();
        List<LotterySer> mLotterySers = DataCenter.getInstance().getLottery();
        if (LotteryConstant.SER_ID_PL35 == seriesId) { // 特别处理 pl35
            for (int i = 0; i < mLotterySers.size(); i++) {
                LotterySer lotterySer = mLotterySers.get(i);
                List<LotteryBean> lotteryBeans = lotterySer.getList();
                for (int j = 0; j < lotteryBeans.size(); j++) {
                    LotteryBean lotteryBean = lotteryBeans.get(j);
                    if(LotteryConstant.LOTTERY_ID_PL35_PL35 == lotteryBean.getTicketId()) {
                        setTicketIdList.add(lotteryBean.getTicketId());
                    }
                }
            }
        } else {
            for (int i = 0; i < mLotterySers.size(); i++) {
                LotterySer lotterySer = mLotterySers.get(i);
                if (seriesId == lotterySer.getId()) {
                    List<LotteryBean> lotteryBeans = lotterySer.getList();
                    for (int j = 0; j < lotteryBeans.size(); j++) {
                        LotteryBean lotteryBean = lotteryBeans.get(j);
                        setTicketIdList.add(lotteryBean.getTicketId());
                    }
                }
            }
        }

        return setTicketIdList;
    }



    @Subscribe(tags = {@Tag(EVENT_OPEN_RESULT_UPDATE)})
    public void onMqttReceive(LotteryNumBean bean) {
        int ticketId = DataCenter.getInstance().getCurLotteryId();
        int ticket = bean.getTicketId();
        /**当前彩种**/
        if (mButtonPosition == BUTTON_CURRENT) {
            int curLotteryId = DataCenter.getInstance().getCurLotteryId();
            if (ticket == curLotteryId && mRequestTicketIdList.size() != 0) {
                refreshPullData();
            }

            /**当前彩系**/
        } else if (mButtonPosition == BUTTON_SER) {
            int id = LotteryUtil.getSerIdByLotteryId(ticket);
            int serId = DataCenter.getInstance().getLotterySeriesId();
            if (id == serId && mRequestTicketIdList.size() != 0) {
                refreshPullData();
            }
        } else {
            /**自定义彩系**/
            boolean isFlag = false;
            for (int i = 0; i < mRequestTicketIdList.size(); i++) {
                int id = mRequestTicketIdList.get(i);
                if (id == ticket) {
                    isFlag = true;
                }
            }

            if (isFlag) {
                if (mAllTicketIdList.size() != 0) {
                    refreshPullData();
                }
            }

        }
    }

    /**
     * >30 6s     >20 5s     > 10 4   剩下的 3s
     */
    private void refreshPullData() {
        mTimeInterval = 300;
        if (mRequestTicketIdList.size() > 30) {
            mTimeInterval = 1500;
        } else if (mRequestTicketIdList.size() > 20) {
            mTimeInterval = 1000;
        } else if (mRequestTicketIdList.size() > 10) {
            mTimeInterval = 800;
        } else {
            mTimeInterval = 500;
        }
        long nowTime = System.currentTimeMillis();
        if (nowTime - mLastClickTime < mTimeInterval) {
            // 快速点击事件
            return;
        }
        mLastClickTime = nowTime;
        requestData();
    }


    @Subscribe(tags = {@Tag(EVENT_DRAGON_ITEM_REQUEST_COUNT_DOWN)})
    public void onRequestCD(Integer ticketId) {
        if(mDragonAdapter != null) {
           List<LongDragonPushInfoEntity> list = mDragonAdapter.getData();
           for(LongDragonPushInfoEntity bean : list) {
               if(ticketId == bean.getTicketId()) {
                   list.remove(bean);
               }
           }
           mDragonAdapter.setNewInstance(list);
        }

        boolean requestCd = DragonUtils.isRequestCd(ticketId);
        if (requestCd) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    requestData();
                }
            }, 1000);

        }
    }

}
