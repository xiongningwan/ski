package com.ski.box.view.fragment.bet.d;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.ski.box.R;
import com.ski.box.adapter.lottery.d.ContentAdapter;
import com.ski.box.bean.BetStatus;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.ShuoMingDoubleBean;
import com.ski.box.bean.lottery.LotteryPlayEnd;
import com.ski.box.bean.lottery.LotteryPlayStart;
import com.ski.box.bean.lottery.LotteryPlaySub;
import com.yb.core.utils.LanguageUtil;
import com.ski.box.utils.lottery.LotteryNoUtil;
import com.ski.box.view.view.RecyclerViewAtViewPager2;
import com.ski.box.view.view.ShuoMingDoubleView;
import com.ski.box.view.view.dialog.ShuoMingSheetDialog;
import com.yb.core.base.BaseFragment;
import com.yb.core.utils.AssetsReader;
import com.yb.core.utils.ScreenUtils;
import com.yb.core.utils.ToastUtil;
import com.zyyoona7.popup.EasyPopup;
import com.zyyoona7.popup.XGravity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.ski.box.ConstantValue.EVENT_BET_ODDS_CHANGE;
import static com.ski.box.ConstantValue.EVENT_TYPE_BET_NO_CHECK_UPDATE;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_PL35;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_SSC;

/**
 * body fragment
 */
public class BodyFragment extends BaseFragment {
    private static final String KEY_LOTTERY_ID = "lottery_id";
    private static final String KEY_LOTTERY_PLAY = "lottery_play";
    private static final String KEY_LOTTERY_FIRST_POSITION = "lottery_first_position";

    /* 双面盘冷热遗漏持久状态key*/
    public final String DOUBLE_SIDE_COLD_HOT_MISS_STATUS = "DOUBLE_SIDE_COLD_HOT_MISS_STATUS";
    public static final String EVENT_COLDHOT = "COLD_HOT";
    public static final String EVENT_OMISSION = "OMIT";

    private RecyclerViewAtViewPager2 mRvContent;
    private ContentAdapter mContentAdapter;
    private int mLotteryId;
    private LotteryPlayStart mLotteryPlay;
    private TabLayout mTabLayout_2;
    private LinearLayout mTabLayout_2LL;
    /*一个赔率时显示*/
    private TextView mOdds;
    /*两个赔率时显示*/
    private TextView mOddsTwo;
    private TextView mPlayDescribe;
    private FrameLayout mCheckAllLayout;
    private View viewFlag;
    private TextView mFreshColdHot;
    private TextView mFreshMiss;

    private EasyPopup mCheckAllPopup;

    private int mCurrentFirstCategoryLevel;
    private int mPosition2 = 0;


    /*一级栏目需要设置赔率的玩法*/
//    public ArrayList<String> firstCategoryOdds = new ArrayList<String>() {{
//        add("自选不中");
//        add("三字定位");
//        add("组三");
//        add("组六");
//    }};
    public BodyFragment() {
    }

    public static BodyFragment newInstance(int lotteryId, LotteryPlayStart lotteryPlay, int position) {
        BodyFragment fragment = new BodyFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_LOTTERY_ID, lotteryId);
        args.putParcelable(KEY_LOTTERY_PLAY, lotteryPlay);
        args.putInt(KEY_LOTTERY_FIRST_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_fragment_body_double;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try {
            RxBus.get().unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mRvContent != null) {
            mRvContent.removeAllViews();
            mRvContent = null;
        }
        if (mContentAdapter != null) {
            mContentAdapter = null;
        }
        if (mTabLayout_2 != null) {
            mTabLayout_2.removeAllTabs();
            mTabLayout_2 = null;
        }
        if (mCheckAllPopup != null) {
            mCheckAllPopup.dismiss();
            mCheckAllPopup = null;
        }
        if (mLotteryPlay != null) {
            mLotteryPlay = null;
        }
        mTabLayout_2LL = null;
        mOdds = null;
        mOddsTwo = null;
        mPlayDescribe = null;
        mCheckAllLayout = null;
        viewFlag = null;
        mFreshColdHot = null;
        mFreshMiss = null;
    }

    @Override
    protected void initView(View view) {
        RxBus.get().register(this);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mLotteryId = bundle.getInt(KEY_LOTTERY_ID, 0);
            mLotteryPlay = bundle.getParcelable(KEY_LOTTERY_PLAY);
            mCurrentFirstCategoryLevel = bundle.getInt(KEY_LOTTERY_FIRST_POSITION, 0);
        }
        if (mLotteryPlay == null) {
            return;
        }
        mRvContent = view.findViewById(R.id.allplay_content_xrecycler);
        mRvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        mContentAdapter = new ContentAdapter(mLotteryId, mLotteryPlay);
        mRvContent.setAdapter(mContentAdapter);
        if (mLotteryPlay.getSubPlays().size() > 0) {
            List<LotteryPlayEnd> list = mLotteryPlay.getSubPlays().get(0).getLotteryPlayEnds();
            mContentAdapter.setNewInstance(list);
        }
        /*冷热遗漏*/
        mTabLayout_2 = view.findViewById(R.id.all_ticketplay_describ);
        mTabLayout_2LL = view.findViewById(R.id.doubleside_TopLinerLayout);
        mPlayDescribe = view.findViewById(R.id.ski_tv_double_playing_instrution);
        mCheckAllLayout = view.findViewById(R.id.tvCheckAll);
        viewFlag = view.findViewById(R.id.view_flag);
        mOdds = view.findViewById(R.id.tv_odds);
        mOddsTwo = view.findViewById(R.id.tv_special_odds);
        mFreshColdHot = view.findViewById(R.id.tvAllFreshColdHot);
        mFreshMiss = view.findViewById(R.id.tvAllFreshOmission);

        initTab2();
        initListener();
//        refreshTopView(mLotteryPlay.getSubPlays().get(0).getColdHotMissLayout());
//        getColdHotMissState();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }


    @Override
    protected void loadData() {
    }

    /**
     * 监听
     */
    private void initListener() {
        mFreshColdHot.setVisibility(View.GONE);
        mFreshMiss.setVisibility(View.GONE);
        mFreshColdHot.setOnClickListener(v -> {
            ToastUtil.showInfo("正在开发中");
            mContentAdapter.hotCold = !mContentAdapter.hotCold;
            if (mContentAdapter.hotCold) {
                mContentAdapter.missShow = false;
              //  showHotColdExplain(EVENT_COLDHOT);
            }
          //  recordColdHotMissState(mContentAdapter.hotCold ? 1 : 0);
        });
        mFreshMiss.setOnClickListener(v -> {
            ToastUtil.showInfo("正在开发中");
            mContentAdapter.missShow = !mContentAdapter.missShow;
            if (mContentAdapter.missShow) {
                mContentAdapter.hotCold = false;
             //   showHotColdExplain(EVENT_OMISSION);
            }
          //  recordColdHotMissState(mContentAdapter.missShow ? 2 : 0);
        });
        mPlayDescribe.setOnClickListener(v -> {
            readerShuoMingJson(DataCenter.getInstance().getLotterySeriesId(), DataCenter.getInstance().getCurLotteryName());
        });
    }

    /**
     * 获取定义好的json数据
     *
     * @param seriesId
     */
    private void readerShuoMingJson(int seriesId, String ticketName) {
        if (SER_ID_PL35 == seriesId) {
            seriesId = SER_ID_SSC;
        }
        List<ShuoMingDoubleBean> list = new ArrayList<>();
        Gson gson = new Gson();
        String json = "";
        if(LanguageUtil.VI.equals(LanguageUtil.getLanguage())) {
            json = AssetsReader.getJson(getContext(), "json" + File.separator + "language"+ File.separator + "def_play_des_vi.json");
        } else {
            json = AssetsReader.getJson(getContext(), "json" + File.separator + "def_play_des.json");
        }
        list = gson.fromJson(json, new TypeToken<List<ShuoMingDoubleBean>>() {
        }.getType());
        for (ShuoMingDoubleBean bean : list) {
            if (seriesId == bean.getSeriesId()) {
                playExplainShow(bean.getDescription(), ticketName);
                break;
            }
        }
    }

    /**
     * 玩法说明弹出框
     */
    private long mLastClickTime;
    private long mTimeInterval = 1000L;

    private void playExplainShow(List<ShuoMingDoubleBean.DescriptionBean> datas, String curTicketName) {
        long nowTime = System.currentTimeMillis();
        if (nowTime - mLastClickTime < mTimeInterval) {
            // 快速点击事件
            return;
        }
        mLastClickTime = nowTime;
        ShuoMingSheetDialog bottomFramentDailog = new ShuoMingSheetDialog(requireContext());
        bottomFramentDailog.setCanceledOnTouchOutside(true);
        bottomFramentDailog.refreshShuoMingData(datas, curTicketName, mLotteryPlay.getTitle());
        bottomFramentDailog.show();
    }


    /**
     * 初始化二级栏目
     */
    private void initTab2() {
        if (mLotteryPlay.getSubPlays().size() > 1) {
            mTabLayout_2LL.setVisibility(View.VISIBLE);
            createTab2(mLotteryPlay.getSubPlays());
        } else {
            mTabLayout_2LL.setVisibility(View.GONE);
        }
    }


    /**
     * 创建二级栏目Tab
     */
    private void createTab2(List<LotteryPlaySub> lotteryPlaySubs) {
        mTabLayout_2.removeAllTabs();
        mTabLayout_2.clearOnTabSelectedListeners();
        mTabLayout_2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                clearNumberPlate("event_clean_lottery_code");
                mPosition2 = tab.getPosition();
                refreshLayout(tab.getPosition());
                LotteryPlaySub playSub = lotteryPlaySubs.get(tab.getPosition());
                setCurrentOdds(playSub);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        /*构建Tab2*/
        for (int i = 0; i < lotteryPlaySubs.size(); i++) {
            LotteryPlaySub play = lotteryPlaySubs.get(i);
            TabLayout.Tab tab = mTabLayout_2.newTab().setCustomView(R.layout.ski_item_tab_double_second_play);
            TextView textView = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.tv_item_tab_double);
//            textView.setText(LanguageUtil.getText(play.getTitleSub()));
            textView.setText(LanguageUtil.getText(play.getTitleSub()));
            if (0 == i) {
                mTabLayout_2.addTab(tab, true);
            } else {
                mTabLayout_2.addTab(tab, false);
            }
        }
        if (lotteryPlaySubs.size() > 3) {
            mCheckAllLayout.setVisibility(View.VISIBLE);
            mCheckAllLayout.setOnClickListener(v -> showAllSecondHorizontalCategoryKind(lotteryPlaySubs));
        } else {
            mCheckAllLayout.setVisibility(View.GONE);
        }
        /*默认选中*/
        mTabLayout_2.getTabAt(mPosition2).select();
        //  DataCenter.getInstance().setTrendDoublePlayCode(lotteryPlaySubs.get(0).getTitleSub());
    }



    /**
     * 刷新界面
     *
     * @param position
     */
    public void refreshLayout(int position) {
        if (null == mLotteryPlay) {
            return;
        }
        List<LotteryPlayEnd> list = mLotteryPlay.getSubPlays().get(position).getLotteryPlayEnds();
        mContentAdapter.setNewInstance(list);
        mContentAdapter.setPlayPosition(position);
    }

    /**
     * 切换 玩法  清空选号盘
     */
    public void clearNumberPlate(String str) {
        mContentAdapter.isClear = true;
        mContentAdapter.notifyDataSetChanged();
        /*更新 投注单数提醒*/
        BetStatus betStatus = new BetStatus();
        RxBus.get().post(EVENT_TYPE_BET_NO_CHECK_UPDATE, betStatus);
    }


    /**
     * 显示全部玩法popupwindow
     */
    private void showAllSecondHorizontalCategoryKind(List<LotteryPlaySub> lotteryPlaySubs) {
        //创建 EasyPopup 对象
        mCheckAllPopup = EasyPopup.create().setContentView(getContext(), R.layout.ski_double_side_play_name_pop_layout_new, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOutsideTouchable(true)
                .setDimColor(Color.BLACK)
                .setXGravity(XGravity.ALIGN_RIGHT)
                .setHeight(((lotteryPlaySubs.size() / 3) + (lotteryPlaySubs.size() % 3 > 0 ? 1 : 0)) * ScreenUtils.dp2px(getContext(), (30 + 12 + 10)) + 10)
                .setFocusAndOutsideEnable(true)
                .setWidth(mTabLayout_2.getWidth() + mCheckAllLayout.getWidth())
                .setFocusAndOutsideEnable(true).apply();
        RecyclerView doubleSidePlayRecyclerView = mCheckAllPopup.findViewById(R.id.double_side_play_name);
        AllLotteryPlaySubAdapter adapter = new AllLotteryPlaySubAdapter(R.layout.ski_item_double_side_play, lotteryPlaySubs);
        for (LotteryPlaySub bean : lotteryPlaySubs) {
            bean.setSelected(false);
        }
        lotteryPlaySubs.get(mPosition2).setSelected(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        doubleSidePlayRecyclerView.setLayoutManager(gridLayoutManager);
        doubleSidePlayRecyclerView.setAdapter(adapter);
        mCheckAllPopup.showAsDropDown(viewFlag);
    }

    /*全部玩法Adapter*/
    public  class AllLotteryPlaySubAdapter extends BaseQuickAdapter<LotteryPlaySub, BaseViewHolder> {
        public AllLotteryPlaySubAdapter(int layoutResId, @Nullable List<LotteryPlaySub> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable LotteryPlaySub lotteryPlaySub) {
//            baseViewHolder.setText(R.id.item_double_side_play_name, LanguageUtil.getText(lotteryPlaySub.getTitleSub()));
            baseViewHolder.setText(R.id.item_double_side_play_name, LanguageUtil.getText(lotteryPlaySub.getTitleSub()));
//            baseViewHolder.setBackgroundResource(R.id.item_double_side_play_name, lotteryPlaySub.isSelected() ? R.drawable.ski_pub_bg_road_title_check : R.mipmap.ski_tab_normal);
//            baseViewHolder.setTextColorRes(R.id.item_double_side_play_name, lotteryPlaySub.isSelected() ? R.color.ski_color_ffffff : R.color.ski_color_7c88a8);
            baseViewHolder.itemView.setOnClickListener(v -> {
                mCheckAllPopup.dismiss();
                mTabLayout_2.getTabAt(getItemPosition(lotteryPlaySub)).select();
            });
        }
    }



    /**
     * 设置Tab当前赔率
     */
    private void setCurrentOdds(LotteryPlaySub lotteryPlaySub) {
        if (null != lotteryPlaySub) {
            String odds = lotteryPlaySub.getOdds();
            if (TextUtils.isEmpty(odds)) {
                if (null != mOdds && null != mOddsTwo) {
                    mOdds.setVisibility(View.GONE);
                    mOddsTwo.setVisibility(View.GONE);
                }
                return;
            }
            if (odds.contains("/")) {
                if (null != mOdds && null != mOddsTwo) {
                    mOdds.setVisibility(View.VISIBLE);
                    mOddsTwo.setVisibility(View.GONE);
                    mOdds.setText(Html.fromHtml(LanguageUtil.getText("赔率：") + "<font color='#ff6464'>" + "/" + "</font> "));
                }
            }

            String[] oodArr = LotteryNoUtil.setSpecialOdd(lotteryPlaySub.getPlayId(), odds);
            if (1 == oodArr.length) {
                if (null != mOdds && null != mOddsTwo) {
                    mOdds.setVisibility(View.VISIBLE);
                    mOddsTwo.setVisibility(View.GONE);
                    mOdds.setText(Html.fromHtml(LanguageUtil.getText("赔率：") + "<font color='#ff6464'>" + oodArr[0] + "</font> "));
                }
            } else if (2 == oodArr.length && ("sanzhonger".equals(lotteryPlaySub.getRemoteCode()) || "erzhongte".equals(lotteryPlaySub.getRemoteCode()))) {
                if (null != mOdds && null != mOddsTwo) {
                    mOdds.setVisibility(View.GONE);
                    mOddsTwo.setVisibility(View.VISIBLE);
                    String[] oddLabel = new String[2];
                    if ("sanzhonger".equals(lotteryPlaySub.getRemoteCode())) {
                        // lhc 三中二
                        oddLabel[0] = LanguageUtil.getText("中二 ");
                        oddLabel[1] = LanguageUtil.getText("中三 ");
                    } else if ("erzhongte".equals(lotteryPlaySub.getRemoteCode())) {
                        // lhc 三中二
                        oddLabel[0] = LanguageUtil.getText("中二 ");
                        oddLabel[1] = LanguageUtil.getText("中特 ");
                    }
                    mOddsTwo.setText(Html.fromHtml( oddLabel[0] + "<font color='#ff6464'>" + oodArr[0] + "</font> "
                            + oddLabel[1] + "<font color='#ff6464'>" + oodArr[1] + "</font> "));
                }
            } else if (3 == oodArr.length) {

            }

        }
    }


    /**
     * 自选不中可变赔率
     *
     * @param odds
     */
    @Subscribe(tags = {@Tag(EVENT_BET_ODDS_CHANGE)})
    public void setChangeOdds(String odds) {
        if (null != mOdds && View.VISIBLE == mOdds.getVisibility()) {
            mOdds.setText(Html.fromHtml(LanguageUtil.getText("赔率")+ "：<font color='#ff6464'>" + odds + "</font> "));
        }
    }

    /*滚动到头部*/
    public void scrollToTopOrBottom(boolean isTop) {
        int position = isTop ? 0 : mContentAdapter.getItemCount() - 1;
        if (null != mRvContent) {
            mRvContent.scrollToPosition(position);
            mRvContent.performClick();
        }
        if (null != mContentAdapter) {
            clearNumberPlate("event_clean_lottery_code");
        }
        /*一级栏目设置赔率*/
//        if (mLotteryPlay != null) {
//            if (firstCategoryOdds.contains(mLotteryPlay.getRemoteCode())) {
//                setCurrentOdds(mLotteryPlay.getSubPlays().get(0));
//            }
//        }

    }
}
