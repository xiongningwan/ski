package com.ski.box.view.fragment.bet.d;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.ski.box.R;
import com.ski.box.adapter.FragmentPager2Adapter;
import com.ski.box.bean.BetStatus;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.MemberDetailEntity;
import com.ski.box.bean.lottery.LotteryPlayStart;
import com.ski.box.bean.lottery.RemoteLotteryPlay;
import com.ski.box.mvp.contract.PlayContract;
import com.ski.box.mvp.presenter.PlayPresenter;
import com.ski.box.view.view.BetBottomView;
import com.ski.box.view.view.dialog.LoadingDialog;
import com.ski.box.view.view.verticaltablyout.TabAdapter;
import com.ski.box.view.view.verticaltablyout.TabView;
import com.ski.box.view.view.verticaltablyout.VerticalTab2Layout;
import com.yb.core.base.BaseMVPFragment;
import com.yb.core.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import static com.ski.box.ConstantValue.EVENT_CLEAN_XUAN_HAO_PAN;
import static com.ski.box.ConstantValue.EVENT_TYPE_BALANCE_UPDATE;
import static com.ski.box.ConstantValue.EVENT_TYPE_BET_NO_CHECK_UPDATE;
import static com.ski.box.ConstantValue.EVENT_TYPE_BET_SUCCESS;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_PLAY_MODE_DOUBLE;


/**
 * 双面盘整体
 **/

public class DoubleFragment extends BaseMVPFragment<PlayContract.Presenter> implements PlayContract.View {
    private VerticalTab2Layout mTabLayout;
    public ViewPager2 mViewPager;
    private TextView mBetPrompt;
    List<Fragment> fragmentList;
    private int mLotteryId;
    private int mMode;
    private int mPosition;
    public BetBottomView mViewBottom;
    private List<LotteryPlayStart> mPlays;
    private BodyFragment mCurBodyFragment;
    public View viewYinYing;
    private Handler mHandler;
    private LoadingDialog mLoadingDialog;
    /**
     * table  名称集合
     */
    private List<LotteryPlayStart> plays;

    public DoubleFragment() {
    }

    @Override
    protected PlayContract.Presenter bindPresenter() {
        return new PlayPresenter(getActivity());
    }

    public static DoubleFragment newInstance() {
        DoubleFragment fragment = new DoubleFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_fragment_double;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.get().unregister(this);
    }

    @Override
    protected void initView(View view) {
        RxBus.get().register(this);
        mTabLayout = view.findViewById(R.id.left_menu_tablelayout);
        mViewPager = view.findViewById(R.id.betting_area);
        mBetPrompt = view.findViewById(R.id.tv_double_bet_prompt);
        mViewBottom = view.findViewById(R.id.view_bet_bottom);
        viewYinYing = view.findViewById(R.id.view_yinying);
//        mViewBottom.setFragmentManager(getChildFragmentManager());
//        viewYinYing.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                /*键盘消失*/
//                RxBus.get().post(EVENT_KEYBOARD_STATUE_CHANGE, "3");
//            }
//        });

        ViewGroup.LayoutParams lp = mTabLayout.getLayoutParams();
        lp.width = ScreenUtils.getScreenWidth(requireActivity())/4;
        mTabLayout.setLayoutParams(lp);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mHandler = new Handler();
        mLotteryId = DataCenter.getInstance().getCurLotteryId();
        mMode = LOTTERY_PLAY_MODE_DOUBLE;
        mLoadingDialog = new LoadingDialog(requireActivity());
    }

    @Override
    protected void processLogic() {
        super.processLogic();
        mLoadingDialog.setLoading("加载中...");
        mLoadingDialog.show();
        mPresenter.getPlays(mLotteryId, mMode);
        mPresenter.getBalance();
    }

    @Override
    protected void loadData() {
    }


    private void intViewPager(List<LotteryPlayStart> plays) {
        fragmentList = new ArrayList<>();
        for (int i = 0; i < plays.size(); i++) {
            LotteryPlayStart lotteryPlay = plays.get(i);
            fragmentList.add(BodyFragment.newInstance(mLotteryId, lotteryPlay, i));
        }
        mViewPager.setOffscreenPageLimit(plays.size() - 1);
        mViewPager.setAdapter(new FragmentPager2Adapter(getActivity(), fragmentList));
        mViewPager.setCurrentItem(0);
    }

    private void initTab(List<LotteryPlayStart> plays) {
        this.plays = plays;
        mTabLayout.setmInitialSelectPosition(mPosition);
        mTabLayout.setTabSelected(true, 0);
        mViewPager.setCurrentItem(0);
        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                mPosition = position;
                mTabLayout.setTabSelected(false, position);
                mCurBodyFragment = ((BodyFragment) fragmentList.get(position));
            }
        });
        mTabLayout.setupWithViewPager(mViewPager, new VerticalTab2Layout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabView tab, int position, boolean isOnclick) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselected(TabView tab, int position, boolean isOnclick) {

            }
        });

        mTabLayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return plays.size();
            }

            @Override
            public TabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public TabView.TabIcon getIcon(int position) {
                if (position < plays.size() - 1) {
                    return new TabView.TabIcon.Builder()
                            .setIcon(R.color.ybcp_transparent, R.drawable.ski_shape_dotted_line)
                            .setIconGravity(Gravity.BOTTOM)
                            .setIconMargin(0)
                            .setIconSize(ScreenUtils.dp2px(96), ScreenUtils.dp2px(2))
                            .build();
                } else {
                    return null;
                }
            }

            @Override
            public TabView.TabTitle getTitle(int position) {
                return new TabView.TabTitle.Builder()
                        .setContent(plays.get(position).getTitle())
                        .setTextSize(13)
                        .setTitleTextBold(true)
                        .setTextColor(mContext.getResources().getColor(R.color.ski_white), mContext.getResources().getColor(R.color.ski_color_B27496))
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return 0;
            }
        });
    }

    @Subscribe(tags = {@Tag(EVENT_TYPE_BALANCE_UPDATE)})
    public void onBalanceUpdate(String balanceStr) {
        mViewBottom.setBalance(balanceStr);
    }

    @Subscribe(tags = {@Tag(EVENT_TYPE_BET_SUCCESS)})
    public void onBetSuccess(String s) {
        mPresenter.getBalance();
    }

//    @Subscribe(tags = {@Tag(EVENT_TYPE_BALANCE_SET)})
//    public void setBalance(MemberDetailEntity bean) {
//        mViewBottom.setBalance(bean);
//    }

    @Override
    public void onPlaysResult(List<RemoteLotteryPlay> plays) {
        mLoadingDialog.dismiss();
        mPlays = DataCenter.getInstance().getRemotePlay(mLotteryId, mMode);
        intViewPager(mPlays);
        initTab(mPlays);
    }


    @Override
    public void onPlaysErrorResult(String s) {

    }

    @Override
    public void onHotColdResult() {
    }

    /*更新 生成注单 提醒*/
    @Subscribe(tags = {@Tag(EVENT_TYPE_BET_NO_CHECK_UPDATE)})
    public void updateCheckBetInfo(BetStatus betStatus) {
//        int totalAmount = betStatus.getTotalAmount();
//        if (0 == betStatus.getZhuShu() || totalAmount == 0) {
//            mBetPrompt.setVisibility(View.GONE);
//            mViewBottom.enableView(false);
//        } else {
//            mBetPrompt.setVisibility(View.VISIBLE);
//            mViewBottom.enableView(true);
//        }
//        SpannableStringBuilder spanBuilder;
//        if (LotteryConstant.LOTTERY_PLAY_DAN == betStatus.getStatus()) {
//            String content = betStatus.getZhuShu() + " 单, " + betStatus.getTotalAmount() + " 元 ";
//            spanBuilder = new SpannableStringBuilder(content);
//            spanBuilder.setSpan(new AbsoluteSizeSpan(ScreenUtils.dp2px(getContext(), 14)), content.indexOf(betStatus.getZhuShu() + ""), content.indexOf("单"), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            spanBuilder.setSpan(new AbsoluteSizeSpan(ScreenUtils.dp2px(getContext(), 14)), content.indexOf(betStatus.getTotalAmount() + ""), content.indexOf("元"), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        } else {
//            String content = "1 单, " + betStatus.getZhuShu() + " 组, " + betStatus.getTotalAmount() + " 元 ";
//            spanBuilder = new SpannableStringBuilder(content);
//            spanBuilder.setSpan(new AbsoluteSizeSpan(ScreenUtils.dp2px(getContext(), 14)), content.indexOf("1"), content.indexOf("单"), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            spanBuilder.setSpan(new AbsoluteSizeSpan(ScreenUtils.dp2px(getContext(), 14)), content.indexOf(betStatus.getZhuShu() + ""), content.indexOf("组"), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            spanBuilder.setSpan(new AbsoluteSizeSpan(ScreenUtils.dp2px(getContext(), 14)), content.indexOf(betStatus.getTotalAmount() + ""), content.indexOf("元"), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        }
//        mBetPrompt.setText(spanBuilder);

        mViewBottom.setBetChose(betStatus);
    }


    /**
     * 点击 底部 清空按钮 点击购彩篮弹框 和 投注弹框 右上角清空
     */
    @Subscribe(tags = {@Tag(EVENT_CLEAN_XUAN_HAO_PAN)})
    public void clearNumberPlate(String s) {
        if (null != mCurBodyFragment) {
            mCurBodyFragment.clearNumberPlate("event_clean_lottery_code");
        }
    }

}
