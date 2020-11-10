package com.ski.box.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.ski.box.bean.PTabBean;
import com.ski.box.bean.user.MemberInfo;
import com.ski.box.bean.user.User;
import com.ski.box.mvp.contract.PersonalContract;
import com.ski.box.mvp.presenter.PersonalPresenter;
import com.ski.box.utils.ActivityUtil;
import com.ski.box.utils.LanguageUtil;
import com.ski.box.view.activity.AgentWebViewActivity;
import com.ski.box.view.activity.ContainerActivity;
import com.ski.box.view.activity.LoginActivity;
import com.ski.box.view.activity.money.WithdrawActivity;
import com.ski.box.view.activity.my.PersonalInfoActivity;
import com.ski.box.view.activity.my.UpdateHeadActivity;
import com.ski.box.view.fragment.my.PersonalTabFragment;
import com.yb.core.base.BaseMVPFragment;
import com.yb.core.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.ski.box.ConstantValue.EVENT_TYPE_BALANCE_UPDATE;
import static com.ski.box.ConstantValue.EVENT_TYPE_USER_NAME_NICK_NAME;
import static com.ski.box.ConstantValue.EVENT_UPDATE_HEAD_SUCCESS;

public class PersonalFragment extends BaseMVPFragment<PersonalContract.Presenter> implements PersonalContract.View, View.OnClickListener {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private CircleImageView mIvUserHead;
    private ImageView mIvService;
    private TextView mTvUserName;
    private TextView mTvUserAcc;
    private TextView mTvBalance;
    private TextView mTvLevel;
    private TextView mGroupValue;
    private Button mBtnLogout;
    private Button mBtnRecharge;
    private Button mBtnWithdraw;
    private ImageView mIvEt;

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
    protected PersonalContract.Presenter bindPresenter() {
        return new PersonalPresenter(mContext);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_fragment_personal;
    }

    @Override
    protected void initView(View view) {
        ImmersionBar.with(this).statusBarDarkFont(false).init();
        RxBus.get().register(this);
        mTabLayout = view.findViewById(R.id.tab_layout);
        mViewPager = view.findViewById(R.id.tab_vp);
        mIvService = view.findViewById(R.id.iv_service);
        mIvUserHead = view.findViewById(R.id.iv_user);
        mTvUserName = view.findViewById(R.id.tv_user_name);
        mTvUserAcc = view.findViewById(R.id.tv_user_acc);
        mIvEt = view.findViewById(R.id.iv_et_nick_name);
        mTvBalance = view.findViewById(R.id.tv_balance_value);
        mTvLevel = view.findViewById(R.id.iv_level_value);
        mGroupValue = view.findViewById(R.id.iv_group_value);
        mBtnRecharge = view.findViewById(R.id.btn_recharge);
        mBtnWithdraw = view.findViewById(R.id.btn_withdraw);
        mBtnLogout = view.findViewById(R.id.btn_logout);

        mIvUserHead.setOnClickListener(this);
        mIvEt.setOnClickListener(this);
        mTvUserName.setOnClickListener(this);
        mTvUserAcc.setOnClickListener(this);
        mBtnLogout.setOnClickListener(this);
        mBtnRecharge.setOnClickListener(this);
        mBtnWithdraw.setOnClickListener(this);
        mIvService.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        List<PTabBean> list = getTabData();
        createTab(list);
        initViewPater(list);
    }


    //这个是一个懒加载
    @Override
    protected void loadData() {

    }

    @Override
    protected void processLogic() {
        super.processLogic();
        mPresenter.getMemberInfo();
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_logout) {
            saveSetSp_token_authorization("", "");
            startActivity(new Intent(requireActivity(), LoginActivity.class));
            requireActivity().finish();
            mPresenter.logout();
        } else if (id == R.id.iv_et_nick_name || id == R.id.tv_user_name || id == R.id.tv_user_acc) {
            startActivity(new Intent(requireActivity(), PersonalInfoActivity.class));
        } else if (id == R.id.btn_recharge) {
            Intent intent = new Intent(requireActivity(), ContainerActivity.class);
            intent.putExtra(ContainerActivity.KEY_CLASS, RechargeFragment.class.getSimpleName());
            startActivity(intent);
        } else if (id == R.id.btn_withdraw) {
            startActivity(new Intent(requireActivity(), WithdrawActivity.class));
        } else if (id == R.id.iv_user) {
            startActivity(new Intent(requireActivity(), UpdateHeadActivity.class));
        } else if (id == R.id.iv_service) {
            AgentWebViewActivity.startAgentWebView(requireActivity(), LanguageUtil.getText("客服中心"), "https://www.google.com");
        }
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
        for (int i = 0; i < list.size(); i++) {
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
                mTabLayout.setScrollPosition(position, 0, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    //---------------------------tab-----------------

    private List<PTabBean> getTabData() {
        List<PTabBean> list = new ArrayList<>();
        list.add(new PTabBean(1, R.mipmap.icon_personal_tab_my_acc, LanguageUtil.getText("我的账户")));
        list.add(new PTabBean(2, R.mipmap.icon_personal_tab_baobiao, LanguageUtil.getText("报表中心")));
        list.add(new PTabBean(3, R.mipmap.icon_personal_tab_tuandui, LanguageUtil.getText("团队中心")));
        list.add(new PTabBean(4, R.mipmap.icon_personal_tab_setting, LanguageUtil.getText("系统中心")));
        return list;
    }


    @Subscribe(tags = {@Tag(EVENT_TYPE_BALANCE_UPDATE)})
    public void onBalanceUpdate(String balanceStr) {
        mTvBalance.setText(balanceStr);
    }

    @Subscribe(tags = {@Tag(EVENT_TYPE_USER_NAME_NICK_NAME)})
    public void onUserNameUpdate(String s) {
        User user = DataCenter.getInstance().getUser();
        mTvUserAcc.setText(LanguageUtil.getText("登录账号") + ": " + user.getAccount());
        mTvUserName.setText(user.getAlias());
        mIvUserHead.setImageResource(ActivityUtil.getHeadByProfile(user.getProfile()));
    }

    @Subscribe(tags = {@Tag(EVENT_UPDATE_HEAD_SUCCESS)})
    public void onUserHeadUpdate(String s) {
        User user = DataCenter.getInstance().getUser();
        mIvUserHead.setImageResource(ActivityUtil.getHeadByProfile(user.getProfile()));
    }

    @Override
    public void onMemberInfoResult(MemberInfo memberInfo) {
        mTvLevel.setText("VIP" + memberInfo.getVip());
        mGroupValue.setText(String.valueOf(memberInfo.getRebate()));
    }

    @Override
    public void onMemberInfoFailResult(String s) {

    }

    @Override
    public void onLogoutResult(Object o) {
    }

    @Override
    public void onLogoutFailResult(String s) {
    }

    private void saveSetSp_token_authorization(String token, String authorization) {
        SPUtils.putString(requireActivity(), LoginActivity.KEY_TOKEN, token);
        SPUtils.putString(requireActivity(), LoginActivity.KEY_AUTHORIZATION, authorization);
    }
}
