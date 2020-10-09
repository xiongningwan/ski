package com.ski.box.view.fragment.road;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ski.box.R;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.LotteryNumBean;
import com.ski.box.bean.lottery.LotteryConstant;
import com.ski.box.bean.lottery.LotteryUtil;
import com.ski.box.bean.road.RoadFactory;
import com.ski.box.bean.road.RoadTitle;
import com.ski.box.mvp.contract.EmptyContract;
import com.ski.box.mvp.contract.RoadContract;
import com.ski.box.mvp.presenter.EmptyPresenter;
import com.ski.box.mvp.presenter.RoadPresenter;
import com.ski.box.view.view.verticaltablyout.TabAdapter;
import com.ski.box.view.view.verticaltablyout.TabView;
import com.ski.box.view.view.verticaltablyout.VerticalTabLayout;
import com.yb.core.base.BaseMVPFragment;
import com.yb.core.utils.AppUtil;
import com.yb.core.utils.FragmentUtils;
import com.yb.core.utils.SPUtils;
import com.yb.core.utils.ScreenUtils;

import java.util.List;

import static com.ski.box.ConstantValue.EVENT_RESULT_HISTORY_LIST_UPDATE;


/**
 * 路子图fragment
 */
public class RoadFragment extends BaseMVPFragment<RoadContract.Presenter> implements RoadContract.View {
    private VerticalTabLayout mTabLayout;
    private FrameLayout mViewPager;
    private List<Fragment> mFragmentList;
    private int mLeftPosition;
    private int mLotteryId;
    private boolean isLoadFinished;
    private boolean isInit = true;
    private boolean isLotteryChange;
    List<RoadTitle> mRoadTitles;
    private RoadBodyFragment roadBodyFragment;
    private SmartRefreshLayout refreshLayout;

    public RoadFragment() {
    }

    public static RoadFragment newInstance() {
        RoadFragment fragment = new RoadFragment();
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.get().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_fragment_road;
    }

    @Override
    protected RoadContract.Presenter bindPresenter() {
        return new RoadPresenter(getActivity());
    }

    @Override
    protected void initView(View view) {
        RxBus.get().register(this);
        mTabLayout = view.findViewById(R.id.left_menu_tablelayout);
        mViewPager = view.findViewById(R.id.viewpager2);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.autoRefresh();

        ViewGroup.LayoutParams lp = mTabLayout.getLayoutParams();
        lp.width = ScreenUtils.getScreenWidth(requireActivity())/4;
        mTabLayout.setLayoutParams(lp);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mLotteryId = DataCenter.getInstance().getCurLotteryId();
        setLeftVisible();
    }


    @Override
    protected void loadData() {
        firstData();
    }

    private void setLeftVisible() {
        int ticketSeriesId = LotteryUtil.getSerIdByLotteryId(mLotteryId);
        if (ticketSeriesId == LotteryConstant.SER_ID_KL8 || ticketSeriesId == LotteryConstant.SER_ID_K3) {
            mTabLayout.setVisibility(View.GONE);
        }
    }

    private void firstData() {
        mLotteryId = DataCenter.getInstance().getCurLotteryId();
        isInit = false;
        mRoadTitles = RoadFactory.createPlay(DataCenter.getInstance().getCurLotteryId());
        initViewPager(mRoadTitles);
        initTab(mRoadTitles);
        List<LotteryNumBean> lotteryNumBeans = DataCenter.getInstance().getLotteryHistory(mLotteryId);
        mPresenter.refreshHistoryData(lotteryNumBeans, mRoadTitles);
    }

    @Subscribe(tags = {@Tag(EVENT_RESULT_HISTORY_LIST_UPDATE)})
    public void updateData(String s) {
        if (isInit) return;
        if (isLotteryChange) {
            isLotteryChange = false;
            firstData();
        } else {
            mLotteryId = DataCenter.getInstance().getCurLotteryId();
            setLeftVisible();
            List<LotteryNumBean> lotteryNumBeans = DataCenter.getInstance().getLotteryHistory(mLotteryId);
            mPresenter.refreshHistoryData(lotteryNumBeans, mRoadTitles);
        }
    }

//    @Subscribe(tags = {@Tag(EVENT_LOTTERY_CHANGE)})
//    public void lotteryChange(String s) {
//        isLotteryChange = true;
//    }

    /**
     * 路子图数据配置完毕
     *
     * @param roadTitles
     */
    @Override
    public void onRoadLoadSuccess(List<RoadTitle> roadTitles) {
        refreshLayout.finishRefresh();
        isLoadFinished = true;
//        roadBodyFragment.updateData(roadTitles.get(mLeftPosition));
//        roadBodyFragment.fragmentSelected();
    }


    private void initViewPager(List<RoadTitle> roadTitles) {
        String key = RoadFactory.ROAD_POSITION_PREFIX_TITLE + mLotteryId;
        int position = SPUtils.getInt(AppUtil.getContext(), key, 0);
     //   roadBodyFragment = RoadBodyFragment.newInstance(mLotteryId, roadTitles.get(position));
     //   FragmentUtils.add(getChildFragmentManager(), roadBodyFragment, R.id.viewpager2, false);

    }

    private void initTab(List<RoadTitle> roadTitles) {
        mTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position, boolean isOnclick) {
                mLeftPosition = position;
//
//                roadBodyFragment.updateData(roadTitles.get(position));
//
//                // 左侧菜单栏点击
//                RxBus.get().post(EVENT_ROAD_LEFT_BUTTON_CLICK, new Integer(mLeftPosition));
//                savePosition_title(position);
//                if (null != roadBodyFragment) {
//                    /*清空问路状态*/
//                    roadBodyFragment.clearState();
//                }
//                if (isLoadFinished) {
//                    roadBodyFragment.fragmentSelected();
//                }
            }

            @Override
            public void onTabReselected(TabView tab, int position, boolean isOnclick) {
            }
        });

        mTabLayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return roadTitles.size();
            }

            @Override
            public TabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public TabView.TabIcon getIcon(int position) {
                return new TabView.TabIcon.Builder()
                        .setIcon(R.color.ybcp_transparent, R.drawable.ski_shape_dotted_line)
                        .setIconGravity(Gravity.BOTTOM)
                        .setIconMargin(0)
                        .setIconSize(ScreenUtils.dp2px(96), ScreenUtils.dp2px(2))
                        .build();
            }

            @Override
            public TabView.TabTitle getTitle(int position) {
                return new TabView.TabTitle.Builder()
                        .setContent(roadTitles.get(position).getTitle())
                        .setTextSize(13)
                        .setTitleTextBold(true)
//                        .setTextColor(mContext.getResources().getColor(R.color.ybcp_color_678CF0), mContext.getResources().getColor(R.color.ybcp_double_play_side_uncheck))
                        .setTextColor(mContext.getResources().getColor(R.color.ski_white), mContext.getResources().getColor(R.color.ski_color_B27496))
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return 0;
            }
        });
    }


    /**
     * 保存左侧titile positon
     *
     * @param position
     */
    private void savePosition_title(int position) {
        String keyTitle = RoadFactory.ROAD_POSITION_PREFIX_TITLE + mLotteryId;
        SPUtils.putInt(AppUtil.getContext(), keyTitle, position);
    }
}
