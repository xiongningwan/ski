package com.ski.box.view.fragment.road;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.hwangjr.rxbus.RxBus;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ski.box.R;
import com.ski.box.bean.road.RoadTitle;
import com.ski.box.mvp.contract.EmptyContract;
import com.ski.box.mvp.presenter.EmptyPresenter;
import com.ski.box.view.view.verticaltablyout.VerticalTabLayout;
import com.yb.core.base.BaseMVPFragment;

import java.util.List;


/**
 * 路子图fragment
 */
public class RoadFragment extends BaseMVPFragment<EmptyContract.Presenter> implements EmptyContract.View {
    private VerticalTabLayout mTabLayout;
    private FrameLayout mViewPager;
    private List<Fragment> mFragmentList;
    private CardView cardView;
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
    protected EmptyContract.Presenter bindPresenter() {
        return new EmptyPresenter(getActivity());
    }

    @Override
    protected void initView(View view) {
        RxBus.get().register(this);
        mTabLayout = view.findViewById(R.id.left_menu_tablelayout);
        mViewPager = view.findViewById(R.id.viewpager2);
        cardView = view.findViewById(R.id.shadow_layout_doubleside);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.autoRefresh();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }


    @Override
    protected void loadData() {
    }


}
