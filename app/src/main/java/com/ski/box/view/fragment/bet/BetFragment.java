package com.ski.box.view.fragment.bet;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.ski.box.R;
import com.ski.box.adapter.FragmentAdapter;
import com.ski.box.bean.record.RecordBet;
import com.ski.box.bean.record.RecordRecent;
import com.ski.box.view.fragment.record.RecordFragment;
import com.ski.box.view.fragment.bet.d.DoubleFragment;
import com.ski.box.view.fragment.dragon.DragonFragment;
import com.ski.box.view.fragment.road.RoadFragment;
import com.ski.box.view.view.NewCpBetTabView;
import com.yb.core.base.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ski.box.ConstantValue.EVENT_UPDATE_RECENT_NO;


/**
 * 双面盘整体
 **/

public class BetFragment extends BaseFragment {
    //    private MagicIndicator mTabLayout;
    private NewCpBetTabView mTabCpBet;
    private ViewPager mViewPager;
    public View viewYinYing;
    private int mPosition;
    private FragmentAdapter fragmentAdapter;
    private String CURRENT_FRAGMENT = "CURRENT_FRAGMENT";

    public BetFragment() {
    }

    public static BetFragment instance() {
        BetFragment fragment = new BetFragment();
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.get().unregister(this);
        unbindDrawables(requireView());
        if (mViewPager != null) {
            mViewPager.removeAllViews();
            mViewPager = null;
        }
        viewYinYing = null;
    }

    private void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup && !(view instanceof AdapterView)) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_fragment_bet_double;
    }

    @Override
    protected void initView(View view) {
        RxBus.get().register(this);
        mTabCpBet = view.findViewById(R.id.cp_bet_tab);
        mViewPager = view.findViewById(R.id.viewPager);
        mTabCpBet.bindViewPager(mViewPager);
        mTabCpBet.setDefaultState(mPosition);
    }

    @Override
    protected void initData(Bundle bundle) {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(DoubleFragment.newInstance());
        fragmentList.add(RoadFragment.newInstance());
        fragmentList.add(DragonFragment.newInstance());
        fragmentList.add(RecordFragment.newInstance());
        fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), fragmentList);
        mViewPager.setAdapter(fragmentAdapter);
        mViewPager.setOffscreenPageLimit(fragmentList.size());
        List<String> titles = new ArrayList<>(Arrays.asList("投注", "路子图", "长龙", "投注记录"));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPosition = position;
                mTabCpBet.scrollPosition(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    protected void loadData() {

    }

    @Subscribe(tags = {@Tag(EVENT_UPDATE_RECENT_NO)})
    public void updateUnSettleNum(RecordBet bean) {
        mTabCpBet.setTvUnpaidNum(bean.getList().size());
    }


}
