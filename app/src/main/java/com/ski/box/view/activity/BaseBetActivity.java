package com.ski.box.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ski.box.BuildConfig;
import com.ski.box.R;
import com.ski.box.SKISdkManger;
import com.ski.box.adapter.TopGameAdapter;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.TopGameBean;
import com.ski.box.bean.lottery.LotteryBean;
import com.ski.box.bean.lottery.LotterySer;
import com.ski.box.mvp.contract.BetContract;
import com.ski.box.mvp.presenter.BetPresenter;
import com.ski.box.utils.AnimationUtil;
import com.ski.box.view.fragment.bet.BetFragment;
import com.ski.box.view.view.BetTopView;
import com.ski.box.view.view.VerticalDrawerLayout;
import com.ski.box.view.view.expandable.ExpandableLayout2;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.FragmentUtils;
import com.yb.core.utils.LogUtils;
import com.yb.core.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseBetActivity extends BaseMVPActivity<BetContract.Presenter> implements BetContract.View, View.OnClickListener {
    public static final String KEY_LOTTERY_ID = "lottery_id";
    public static final String KEY_LOTTERY_NAME = "lottery_name";
    protected BetTopView mViewTop;
    protected VerticalDrawerLayout mDrawerLayout;
    protected ExpandableLayout2 mLayoutExpandable;
    protected RecyclerView mRvTopGame;

    protected int mLotteryId;
    protected String mLotteryName;
    private BetFragment mBetFragment;
    protected Handler mHandler;
    protected TopGameAdapter mTopGameAdapter;
    protected List<TopGameBean> mTopGameList = new ArrayList<>();
    protected LoadTopLotteryRunnable mLoadTopLotteryRunnable;
    protected InitFragmentRunnable mInitFragmentRunnable;
    protected UnSettleRunnable mUnSettleRunnable;
    protected ReloadDataRunnable mReloadDataRunnable;

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_bet;
    }

    @Override
    protected BetContract.Presenter bindPresenter() {
        return new BetPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initViews() {
        mViewTop = findViewById(R.id.view_bet_top);
        mDrawerLayout = findViewById(R.id.vd_drawer);
        mLayoutExpandable = findViewById(R.id.layout_expandable);
        mRvTopGame = findViewById(R.id.recycler_game);
        initTopGameView();

        Intent intent = getIntent();
        mLotteryId = intent.getIntExtra(KEY_LOTTERY_ID, 0);
        mLotteryName = intent.getStringExtra(KEY_LOTTERY_NAME);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        DataCenter.getInstance().setCurLotteryId(mLotteryId);
        DataCenter.getInstance().setCurLotteryName(mLotteryName);
        mViewTop.setLotteryName(mLotteryName);
        mHandler = new Handler();
        mLoadTopLotteryRunnable = new LoadTopLotteryRunnable();
        mInitFragmentRunnable = new InitFragmentRunnable();
        mUnSettleRunnable = new UnSettleRunnable();
        mReloadDataRunnable = new ReloadDataRunnable();
        mHandler.postDelayed(mLoadTopLotteryRunnable,1000);
        mHandler.post(mInitFragmentRunnable);
//        initFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewTop.setBetPage(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mViewTop.setBetPage(false);
    }

    @Override
    protected void processLogic() {
        super.processLogic();
    }


    @Override
    public void onClick(View v) {

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    private class LoadTopLotteryRunnable implements Runnable {

        @Override
        public void run() {
            loadGameData();
        }
    }
    private class InitFragmentRunnable implements Runnable {

        @Override
        public void run() {
            initFragment();
        }
    }
    private class UnSettleRunnable implements Runnable {

        @Override
        public void run() {
            if (mPresenter != null) {
                mPresenter.getRecentBetList("", String.valueOf(0));
            }
        }
    }


    private void initFragment() {
        FragmentUtils.removeAll(getSupportFragmentManager());
        if (mBetFragment != null) {
            FragmentUtils.hide(mBetFragment);
            FragmentUtils.removeAll(getSupportFragmentManager());
        }
        mBetFragment = BetFragment.instance();
        FragmentUtils.add(getSupportFragmentManager(), mBetFragment, R.id.fl_bet_mid, false);
    }


    //初始化顶部彩种
    private void initTopGameView() {
        mTopGameAdapter = new TopGameAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (mTopGameList.size() > 0 && mTopGameList.get(position).getTicketSeriesId() > 0) {
                    return 3;
                }
                return 1;
            }
        });
        mRvTopGame.setLayoutManager(gridLayoutManager);
        mRvTopGame.setAdapter(mTopGameAdapter);
        mTopGameAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                List<TopGameBean> list = adapter.getData();
                for (int i = 0; i < list.size(); i++) {
                    TopGameBean bean = list.get(i);
                    bean.setSelected(false);
                    if (position == i) {
                        bean.setSelected(true);
                        adapter.notifyDataSetChanged();
                        switchTopGameList();
                        mLotteryId = bean.getTicketId();
                        String ticketName = bean.getTicketName();
                        mLotteryName = ticketName;
                        mViewTop.setLotteryName(ticketName);

                        mHandler.postDelayed(mReloadDataRunnable,500);
                    }
                }
            }
        });
    }

    private class ReloadDataRunnable implements Runnable {

        @Override
        public void run() {
            initData(null);
            processLogic();
        }
    }

    /**
     * 加载顶部彩种
     */
    protected void loadGameData() {
        List<LotterySer> lotterySers = DataCenter.getInstance().getLottery();
        mTopGameList.clear();
        List<TopGameBean> topGameBeans = convertTopData(lotterySers);
        mTopGameList.addAll(topGameBeans);
        mTopGameAdapter.setNewInstance(topGameBeans);

        for (int x = 0; x < mTopGameList.size(); x++) {
            TopGameBean topGameBean = mTopGameList.get(x);
            int itemType = topGameBean.getItemType();
            if (TopGameAdapter.TOP_GAME_TYPE_TICKET == itemType) {
                int ticketId = topGameBean.getTicketId();
                if (mLotteryId == ticketId) {
                    topGameBean.setSelected(true);
                    String ticketName = topGameBean.getTicketName();
                    mLotteryId = ticketId;
                    mLotteryName = ticketName;
                    mViewTop.setLotteryName(ticketName);
                }
            }
        }
    }

    /**
     * 切换顶部彩种
     */
    protected void switchTopGameList() {
        if (mLayoutExpandable.isExpanded()) {
            mLayoutExpandable.collapse();
            if (mViewTop != null && mViewTop.getIvTitleDrop() != null) {
                AnimationUtil.toCollapse(mViewTop.getIvTitleDrop());
            }
        } else {
            mLayoutExpandable.expand();
            if (mViewTop != null && mViewTop.getIvTitleDrop() != null) {
                AnimationUtil.toExpand(mViewTop.getIvTitleDrop());
            }
        }
    }

    /**
     * 顶部彩种数据转换
     */
    private List<TopGameBean> convertTopData(List<LotterySer> lotterySers) {
        List<TopGameBean> list = new ArrayList<>();
        if (lotterySers == null) {
            return new ArrayList<>();
        }
        for (LotterySer bean : lotterySers) {
            if (bean.getId() > 0) {
                TopGameBean topGameBean = new TopGameBean();
                topGameBean.setTicketSeriesId(bean.getId());
                topGameBean.setTicketSeriesName(bean.getName());
                topGameBean.setItemType(TopGameAdapter.TOP_GAME_TYPE_SERIES);
                list.add(topGameBean);
                for (LotteryBean typeListBean : bean.getList()) {
                    TopGameBean topGameBean1 = new TopGameBean();
                    topGameBean1.setTicketId(typeListBean.getTicketId());
                    topGameBean1.setTicketName(typeListBean.getTicketName());
                    topGameBean1.setMobileLogoPath(typeListBean.getPath());
                    topGameBean1.setItemType(TopGameAdapter.TOP_GAME_TYPE_TICKET);
                    list.add(topGameBean1);
                }
            }
        }
        return list;
    }

}
