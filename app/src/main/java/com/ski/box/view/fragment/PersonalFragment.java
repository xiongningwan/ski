package com.ski.box.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.ski.box.R;
import com.ski.box.adapter.FragmentAdapter;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.MemberDetailEntity;
import com.ski.box.bean.PTabBean;
import com.ski.box.bean.user.MemberInfo;
import com.ski.box.bean.user.User;
import com.ski.box.mvp.contract.EmptyContract;
import com.ski.box.mvp.contract.PersonalContract;
import com.ski.box.mvp.presenter.EmptyPresenter;
import com.ski.box.mvp.presenter.PersonalPresenter;
import com.ski.box.view.fragment.personal.PersonalTabFragment;
import com.yb.core.base.BaseMVPFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.ski.box.ConstantValue.EVENT_TYPE_BALANCE_UPDATE;
import static com.ski.box.ConstantValue.EVENT_TYPE_USER_NAME_NICK_NAME;

public class PersonalFragment extends BaseMVPFragment<PersonalContract.Presenter> implements PersonalContract.View, View.OnClickListener {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TextView mTvUserName;
    private TextView mTvUserAcc;
    private TextView mTvBalance;
    private TextView mTvLevel;
    private TextView mGroupValue;

    public PersonalFragment() {
    }

    public static PersonalFragment newInstance() {
        PersonalFragment fragment = new PersonalFragment();
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.get().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_fragment_personal;
    }

    @Override
    protected void initView(View view) {
        ImmersionBar.with(this).statusBarDarkFont(false).init();
        RxBus.get().register(this);
        mTabLayout =  view.findViewById(R.id.tab_layout);
        mViewPager =  view.findViewById(R.id.tab_vp);
        mTvUserName =  view.findViewById(R.id.tv_user_name);
        mTvUserAcc =  view.findViewById(R.id.tv_user_acc);
        mTvUserAcc =  view.findViewById(R.id.tv_user_acc);
        mTvBalance =  view.findViewById(R.id.tv_balance_value);
        mTvLevel =  view.findViewById(R.id.iv_level_value);
        mGroupValue =  view.findViewById(R.id.iv_group_value);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        List<PTabBean> list =  getTabData();
        createTab(list);
        initViewPater(list);
    }



    //这个是一个懒加载
    @Override
    protected void loadData() {
        mPresenter.getMemberInfo();
    }

    @Override
    protected void processLogic() {
        super.processLogic();
    }


    @Override
    protected PersonalContract.Presenter bindPresenter() {
        return new PersonalPresenter(mContext);
    }


    @Override
    public void onClick(View view) {

    }



    private void createTab(List<PTabBean> list) {
        mTabLayout.removeAllTabs();
        mTabLayout.clearOnTabSelectedListeners();
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0; i < list.size(); i++) {
            PTabBean pTabBean = list.get(i);
            TabLayout.Tab tab = mTabLayout.newTab().setCustomView(R.layout.ski_item_tab_peronsal);
            ImageView ivLabel = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.iv_tab_label);
            TextView tvName = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.tv_item_name);
            ivLabel.setImageResource(pTabBean.getIcon());
            tvName.setText(pTabBean.getName());
            if (0 == i) {
                mTabLayout.addTab(tab, true);
            } else {
                mTabLayout.addTab(tab, false);
            }
        }
    }

    private void initViewPater(List<PTabBean> list) {
        FragmentManager fragmentManager = getChildFragmentManager();
        List<Fragment> fragmentList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            PTabBean pTabBean = list.get(i);
            fragmentList.add(PersonalTabFragment.newInstance(pTabBean.getId()));
        }
        FragmentAdapter adapter = new FragmentAdapter(fragmentManager, fragmentList);
        mViewPager.setAdapter(adapter);
        // 预加载数量
        mViewPager.setOffscreenPageLimit(3);
        // viewpager 设置滚动监听
         mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
             @Override
             public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

             }

             @Override
             public void onPageSelected(int position) {
                 mTabLayout.setScrollPosition(position,0,true);
             }

             @Override
             public void onPageScrollStateChanged(int state) {

             }
         });
    }
    //---------------------------tab-----------------

    private List<PTabBean> getTabData() {
        List<PTabBean> list = new ArrayList<>();
        list.add(new PTabBean(1,R.mipmap.icon_personal_tab_my_acc, "我的账户"));
        list.add(new PTabBean(2,R.mipmap.icon_personal_tab_baobiao, "报表中心"));
        list.add(new PTabBean(3,R.mipmap.icon_personal_tab_tuandui, "团队中心"));
        list.add(new PTabBean(4,R.mipmap.icon_personal_tab_setting, "系统中心"));
        return list;
    }


    @Subscribe(tags = {@Tag(EVENT_TYPE_BALANCE_UPDATE)})
    public void onBalanceUpdate(String balanceStr) {
        mTvBalance.setText(balanceStr);
    }

    @Subscribe(tags = {@Tag(EVENT_TYPE_USER_NAME_NICK_NAME)})
    public void onUserNameUpdate(String s) {
        User user = DataCenter.getInstance().getUser();
        mTvUserName.setText(user.getAccount());
        mTvUserAcc.setText(user.getAlias());
    }

    @Override
    public void onMemberInfoResult(MemberInfo memberInfo) {
        mTvLevel.setText("VIP"+ memberInfo.getVip());
        mGroupValue.setText(String.valueOf(memberInfo.getRebate()));
    }

    @Override
    public void onMemberInfoFailResult(String s) {

    }
}
