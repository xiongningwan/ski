package com.ski.box.view.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ski.box.R;
import com.ski.box.adapter.FragmentAdapter;
import com.ski.box.mvp.contract.EmptyContract;
import com.ski.box.mvp.presenter.EmptyPresenter;
import com.ski.box.view.fragment.HallFragment;
import com.ski.box.view.fragment.PersonalFragment;
import com.ski.box.view.fragment.RechargeFragment;
import com.ski.box.view.fragment.ReportFragment;
import com.ski.box.view.fragment.record.RecordFragment;
import com.ski.box.view.view.HallTabLayout;
import com.ski.box.view.view.NoScrollViewPager;
import com.yb.core.base.BaseMVPActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseMVPActivity<EmptyContract.Presenter> implements EmptyContract.View {
    private NoScrollViewPager mViewPager;
    private HallTabLayout mTabView;

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_main;
    }

    @Override
    protected EmptyContract.Presenter bindPresenter() {
        return new EmptyPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initViews() {
        mViewPager = findViewById(R.id.viewPager);
        mTabView = findViewById(R.id.llTab);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(HallFragment.newInstance());
        fragmentList.add(ReportFragment.newInstance());
        fragmentList.add(RechargeFragment.newInstance());
        fragmentList.add(PersonalFragment.newInstance());
        FragmentAdapter adapter = new FragmentAdapter(fragmentManager, fragmentList);
        mViewPager.setAdapter(adapter);
        // 预加载数量
        mViewPager.setOffscreenPageLimit(3);
        // viewpager 设置滚动监听
       // mViewPager.addOnPageChangeListener(this);
        mTabView.bindViewPager(mViewPager);
        mTabView.switchTab(HallTabLayout.TAB_INDEX_HALL);
    }

    @Override
    protected void processLogic() {
        super.processLogic();
    }

}