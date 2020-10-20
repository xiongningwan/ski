package com.ski.box.view.fragment.road;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.tabs.TabLayout;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.ski.box.R;
import com.ski.box.bean.BallBean;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.lottery.PlayUtil;
import com.ski.box.bean.road.RoadBean;
import com.ski.box.bean.road.RoadFactory;
import com.ski.box.bean.road.RoadSub;
import com.ski.box.bean.road.RoadTitle;
import com.ski.box.view.view.dialog.zd.ZDialog;
import com.yb.core.base.BaseFragment;
import com.yb.core.utils.AppUtil;
import com.yb.core.utils.SPUtils;
import com.yb.core.utils.ScreenUtils;
import com.zyyoona7.popup.EasyPopup;
import com.zyyoona7.popup.XGravity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import static com.ski.box.ConstantValue.EVENT_TYPE_QUICK_BET_CLICK;


/**
 * body fragment
 */
public class RoadBodyFragment extends BaseFragment {
    private static final String KEY_LOTTERY_ID = "lottery_id";
    private static final String KEY_LOTTERY_ROAD_PLAY = "lottery_road_play";
    private TabLayout mTabLayout_2;
    private FrameLayout mCheckAllLayout;
    private View mViewFlag;
    private RecyclerView mRv1;
    private RecyclerView mRv2;
    private RecyclerView mRv3;
    private RecyclerView mRv4;
    private LinearLayout bet1;
    private LinearLayout bet2;
    private TextView txtBet1;
    private TextView txtBet2;
    private TextView txtOdd1;
    private TextView txtOdd2;
    private TextView tvInfo;

    private TextView mPositiveBigEye;
    private TextView mPositiveSmall;
    private TextView mPositiveBug;
    private TextView mNegativeBigEye;
    private TextView mNegativeSmall;
    private TextView mNegativeBug;
    private ImageView mPositiveBigEyeImage;
    private ImageView mPositiveSmallImage;
    private ImageView mPositiveBugImage;
    private ImageView mNegativeBigEyeImage;
    private ImageView mNegativeSmallImage;
    private ImageView mNegativeBugImage;
    private int mLotteryId;
    private RoadTitle mRoadTitle;
    private List<Rv1Adapter> mAdapters1 = new ArrayList<>();
    private List<Rv2Adapter> mAdapters2 = new ArrayList<>();
    private List<Rv3Adapter> mAdapters3 = new ArrayList<>();
    private List<Rv4Adapter> mAdapters4 = new ArrayList<>();
    private int mPosition;

    private boolean isPositive;
    private boolean isNegative;
    private String positiveKey, negativeKey;
    private int index1, index2, index3, index4;
    private Timer mTimer;
    private ZDialog askWay;

    private boolean isFirstLoad = true, isFuture = true, isExistBig = true, isExistSmall = true, isExistBug = true;
    private RoadSub mRoadSub;
    private int stateCur = -1, state2 = -1, state3 = -1, state4 = -1;
    private EasyPopup mCheckAllPopup;
    private int mLeftPosition;

    public RoadBodyFragment() {
    }

    public static RoadBodyFragment newInstance(int lotteryId, RoadTitle roadTitle) {
        RoadBodyFragment fragment = new RoadBodyFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_LOTTERY_ID, lotteryId);
        args.putParcelable(KEY_LOTTERY_ROAD_PLAY, roadTitle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_fragment_body_road;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        clearTimer();
        try {
            RxBus.get().unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mTabLayout_2 != null) {
            mTabLayout_2.removeAllTabs();
            mTabLayout_2 = null;
        }
        if (mRv1 != null) {
            mRv1.removeAllViews();
            mRv1 = null;
        }
        if (mRv2 != null) {
            mRv2.removeAllViews();
            mRv2 = null;
        }
        if (mRv3 != null) {
            mRv3.removeAllViews();
            mRv3 = null;
        }
        if (mRv4 != null) {
            mRv4.removeAllViews();
            mRv4 = null;
        }
        if (mAdapters1 != null) {
            for (Rv1Adapter adapter : mAdapters1) {
                adapter = null;
            }
            mAdapters1.clear();
            mAdapters1 = null;
        }
        if (mAdapters2 != null) {
            for (Rv2Adapter adapter : mAdapters2) {
                adapter = null;
            }
            mAdapters2.clear();
            mAdapters2 = null;
        }
        if (mAdapters3 != null) {
            for (Rv3Adapter adapter : mAdapters3) {
                adapter = null;
            }
            mAdapters3.clear();
            mAdapters3 = null;
        }
        if (mAdapters4 != null) {
            for (Rv4Adapter adapter : mAdapters4) {
                adapter = null;
            }
            mAdapters4.clear();
            mAdapters4 = null;
        }
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        if (askWay != null) {
            askWay.dismiss();
            askWay = null;
        }
        if (mCheckAllPopup != null) {
            mCheckAllPopup.dismiss();
            mCheckAllPopup = null;
        }
        if (mRoadSub != null) {
            mRoadSub = null;
        }

        mCheckAllLayout = null;
        mViewFlag = null;
        bet1 = null;
        bet2 = null;
        txtBet1 = null;
        txtBet2 = null;
        txtOdd1 = null;
        txtOdd2 = null;
        tvInfo = null;
        mPositiveBigEye = null;
        mPositiveSmall = null;
        mPositiveBug = null;
        mNegativeBigEye = null;
        mNegativeSmall = null;
        mNegativeBug = null;
        mPositiveBigEyeImage = null;
        mPositiveSmallImage = null;
        mPositiveBugImage = null;
        mNegativeBigEyeImage = null;
        mNegativeSmallImage = null;
        mNegativeBugImage = null;
        mRoadTitle = null;
    }

    @Override
    protected void initView(View view) {
        RxBus.get().register(this);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mLotteryId = bundle.getInt(KEY_LOTTERY_ID, 0);
            mRoadTitle = bundle.getParcelable(KEY_LOTTERY_ROAD_PLAY);
        }
        mTabLayout_2 = view.findViewById(R.id.tab_layout);
        mCheckAllLayout = view.findViewById(R.id.tvCheckAll);
        mViewFlag = view.findViewById(R.id.view_flag);
        mRv1 = view.findViewById(R.id.recycler_road_big);
        mRv2 = view.findViewById(R.id.recycler_road_big_eye);
        mRv3 = view.findViewById(R.id.recycler_road_small);
        mRv4 = view.findViewById(R.id.recycler_road_bug);
        bet1 = view.findViewById(R.id.layout_bet1);
        bet2 = view.findViewById(R.id.layout_bet2);
        txtBet1 = view.findViewById(R.id.txt_bet1);
        txtBet2 = view.findViewById(R.id.txt_bet2);
        txtOdd1 = view.findViewById(R.id.txt_odd1);
        txtOdd2 = view.findViewById(R.id.txt_odd2);
        tvInfo = view.findViewById(R.id.txt_info);


        mPositiveBigEye = view.findViewById(R.id.chk_positive_big_eye);
        mPositiveSmall = view.findViewById(R.id.chk_positive_small);
        mPositiveBug = view.findViewById(R.id.chk_positive_bug);
        mNegativeBigEye = view.findViewById(R.id.chk_negative_big_eye);
        mNegativeSmall = view.findViewById(R.id.chk_negative_small);
        mNegativeBug = view.findViewById(R.id.chk_negative_bug);

        mPositiveBigEyeImage = view.findViewById(R.id.icon_positive_big_eye);
        mPositiveSmallImage = view.findViewById(R.id.icon_positive_small);
        mPositiveBugImage = view.findViewById(R.id.icon_positive_bug);
        mNegativeBigEyeImage = view.findViewById(R.id.icon_negative_big_eye);
        mNegativeSmallImage = view.findViewById(R.id.icon_negative_small);
        mNegativeBugImage = view.findViewById(R.id.icon_negative_bug);

        mRv1.setLayoutManager(new GridLayoutManager(getActivity(), 6, LinearLayoutManager.HORIZONTAL, false));
        mRv2.setLayoutManager(new GridLayoutManager(getActivity(), 6, LinearLayoutManager.HORIZONTAL, false));
        mRv3.setLayoutManager(new GridLayoutManager(getActivity(), 6, LinearLayoutManager.HORIZONTAL, false));
        mRv4.setLayoutManager(new GridLayoutManager(getActivity(), 6, LinearLayoutManager.HORIZONTAL, false));

        mRv1.setHasFixedSize(true);
        mRv2.setHasFixedSize(true);
        mRv3.setHasFixedSize(true);
        mRv4.setHasFixedSize(true);

        bet1.setOnClickListener(this::onBetRequest);
        bet2.setOnClickListener(this::onBetRequest);
        tvInfo.setOnClickListener(this::askWayInfo);
        initListener();
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        updataRoad(mRoadTitle);
    }


    @Override
    protected void loadData() {

    }

    private void updataRoad(RoadTitle mRoadTitle) {
        mPosition = 0;
        if (mRoadTitle == null || mRoadTitle.getSubList().size() <= 0) {
            return;
        }
        List<RoadSub> subList = mRoadTitle.getSubList();
        for (int i = 0; i < subList.size(); i++) {
            RoadSub roadSub = subList.get(i);
            Rv1Adapter rv1Adapter = new Rv1Adapter();
            Rv2Adapter rv2Adapter = new Rv2Adapter();
            Rv3Adapter rv3Adapter = new Rv3Adapter();
            Rv4Adapter rv4Adapter = new Rv4Adapter();

            List<RoadBean> list1 = roadSub.getList1();
            List<RoadBean> list2 = roadSub.getList2();
            List<RoadBean> list3 = roadSub.getList3();
            List<RoadBean> list4 = roadSub.getList4();

            rv1Adapter.setNewInstance(list1);
            rv2Adapter.setNewInstance(list2);
            rv3Adapter.setNewInstance(list3);
            rv4Adapter.setNewInstance(list4);
            mAdapters1.add(rv1Adapter);
            mAdapters2.add(rv2Adapter);
            mAdapters3.add(rv3Adapter);
            mAdapters4.add(rv4Adapter);
        }
        if (mCheckAllLayout != null) {
            if (subList.size() > 3) {
                mCheckAllLayout.setVisibility(View.VISIBLE);
                mCheckAllLayout.setOnClickListener(this::checkAllDialog);
            } else {
                mCheckAllLayout.setVisibility(View.GONE);
            }
        }
        if (mTabLayout_2 != null) {
            createTab2(subList);
        }
    }

    /**
     * 左侧全部弹出框
     *
     * @param view
     */
    private void checkAllDialog(View view) {
        int size = mRoadTitle.getSubList().size();
        int height = ((size / 3) + (size % 3 > 0 ? 1 : 0)) * ScreenUtils.dp2px(getContext(), (30 + 12 + 10)) + 10;
        if (size > 6) {
            height -= ScreenUtils.dp2px(getContext(), 20);
        }
        mCheckAllPopup = EasyPopup.create()
                .setContentView(getContext(), R.layout.ski_road_menu_play_name_pop_layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOutsideTouchable(true)
                .setDimColor(Color.BLACK)
                .setXGravity(XGravity.ALIGN_RIGHT)
                .setHeight(height)
                .setFocusAndOutsideEnable(true)
                .setWidth(mTabLayout_2.getWidth() + mCheckAllLayout.getWidth() + ScreenUtils.dp2px(getContext(), 10))
                .setFocusAndOutsideEnable(true)
                .apply();
        RecyclerView doubleSidePlayRecyclerView = mCheckAllPopup.findViewById(R.id.double_side_play_name);
        AllLotteryPlaySubAdapter adapter = new RoadBodyFragment.AllLotteryPlaySubAdapter(R.layout.ski_item_double_side_play, mRoadTitle.getSubList());
        for (RoadSub bean : mRoadTitle.getSubList()) {
            bean.setSelected(false);
        }
        mRoadTitle.getSubList().get(mPosition).setSelected(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        doubleSidePlayRecyclerView.setLayoutManager(gridLayoutManager);
        doubleSidePlayRecyclerView.setAdapter(adapter);
        mCheckAllPopup.showAsDropDown(mViewFlag);
    }

    /*全部玩法Adapter*/
    public class AllLotteryPlaySubAdapter extends BaseQuickAdapter<RoadSub, BaseViewHolder> {
        public AllLotteryPlaySubAdapter(int layoutResId, @Nullable List<RoadSub> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable RoadSub roadSub) {
            baseViewHolder.setText(R.id.item_double_side_play_name, roadSub.getSubTitle());
          //  baseViewHolder.setBackgroundResource(R.id.item_double_side_play_name, roadSub.isSelected() ? R.drawable.ski_bg_ffffff_6dp_uncheck : R.drawable.ski_bg_road_uncheck);
//            baseViewHolder.setTextColorRes(R.id.item_double_side_play_name, roadSub.isSelected() ? R.color.ski_color_678CF0 : R.color.ski_color_ffffff);
            baseViewHolder.itemView.setOnClickListener(v -> {
                mCheckAllPopup.dismiss();
                mTabLayout_2.getTabAt(getItemPosition(roadSub)).select();
            });
        }
    }

    private void setOdds() {
        Integer[] ids = mRoadSub.getRoadPlayIds();
        List<BallBean> odds = PlayUtil.getRoadOdds(ids, 0);
        if (odds.size() > 1) {
            txtOdd1.setText(odds.get(0).getPlayItemOdds());
            txtOdd2.setText(odds.get(1).getPlayItemOdds());
        }
    }

    /**
     * 问路说明弹出框
     *
     * @param view
     */
    private void askWayInfo(View view) {
        if (askWay != null) {
            askWay = null;
        }
        ZDialog.Builder builder = new ZDialog.Builder(getChildFragmentManager())
                .setLayoutRes(R.layout.ski_dialog_road_info)
                .setScreenWidthAspect(AppUtil.getContext(), 0.8f)
                .setScreenHeightAspect(AppUtil.getContext(), 0.5f)
                .setGravity(Gravity.CENTER)
                .setDimAmount(0.8f)
                .setCancelableOutside(false);
        palyDescribBind(builder);
        askWay = builder.create();
        askWay.show();
    }

    private void palyDescribBind(ZDialog.Builder builder) {
        builder.setOnBindViewListener(viewHolder -> {
            viewHolder.setText(R.id.txt_state1,
                    Html.fromHtml("下期<font color='#e35555'>大 O</font>"));
            viewHolder.setText(
                    R.id.txt_title6,
                    Html.fromHtml("<font color='#e35555'>O</font>")
            );
            viewHolder.getView(R.id.dialog_close).setOnClickListener(v -> {
                if (askWay != null) {
                    askWay.dismiss();
                }
            });
        });
    }

    /*问路监听*/
    private void initListener() {
        mPositiveBigEye.setOnClickListener(v -> {
            setRoadLayout(isPositive ? 0 : 1);
        });
        mPositiveSmall.setOnClickListener(v -> {
            setRoadLayout(isPositive ? 0 : 1);
        });
        mPositiveBug.setOnClickListener(v -> {
            setRoadLayout(isPositive ? 0 : 1);
        });
        mNegativeBigEye.setOnClickListener(v -> {
            setRoadLayout(isNegative ? 0 : 2);
        });
        mNegativeSmall.setOnClickListener(v -> {
            setRoadLayout(isNegative ? 0 : 2);
        });
        mNegativeBug.setOnClickListener(v -> {
            setRoadLayout(isNegative ? 0 : 2);
        });
    }



    /*二级栏目Tab*/
    private void createTab2(List<RoadSub> roadSubs) {
        mTabLayout_2.removeAllTabs();
        mTabLayout_2.clearOnTabSelectedListeners();
        mTabLayout_2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switchRv(tab.getPosition());
                savePosition_sub(tab.getPosition());
                mRoadSub = mRoadTitle.getSubList().get(tab.getPosition());
                setRoadName(mRoadTitle.getSubList().get(tab.getPosition()).getSubTitle());
                /*防止第一次加载，RecyclerView没有完全绘制，滚动导致奔溃*/
                if (!isFirstLoad) {
                    clearState();
                    mPosition = tab.getPosition();
                    refreshAskImage();
                    scrollToLast();
                }
                isFirstLoad = false;
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        for (int i = 0; i < roadSubs.size(); i++) {
            RoadSub roadSub = roadSubs.get(i);
//            TabLayout.Tab tab = mTabLayout_2.newTab().setCustomView(R.layout.ski_item_tab_double_second_play2);
            TabLayout.Tab tab = mTabLayout_2.newTab().setCustomView(R.layout.ski_item_tab_double_second_play);
            TextView textView = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.tv_item_tab_double);
            textView.setText(roadSub.getSubTitle());
            if (roadSub.isSelected()) {
                mTabLayout_2.addTab(tab, true);
                mPosition = i;
            } else {
                mTabLayout_2.addTab(tab, false);
            }
        }
    }

    /**
     * 保存副标题 position
     *
     * @param position
     */
    private void savePosition_sub(int position) {
        String keySub = mRoadTitle.getTitle() + RoadFactory.ROAD_POSITION_PREFIX_SUB + mLotteryId;
        SPUtils.putInt(AppUtil.getContext(), keySub, position);
        setGenerateBet(position);
    }

    /*更新数据*/
    public void updateData(int leftPosition,RoadTitle roadTitle) {
        mLeftPosition = leftPosition;
        mRoadTitle = roadTitle;
        mAdapters1.clear();
        mAdapters2.clear();
        mAdapters3.clear();
        mAdapters4.clear();
        updataRoad(roadTitle);
    }

    public void fragmentSelected() {
//        if (mAdapters1 != null && mAdapters1.size() > 0) {
//            mAdapters1.get(mPosition).notifyDataSetChanged();
//            mAdapters2.get(mPosition).notifyDataSetChanged();
//            mAdapters3.get(mPosition).notifyDataSetChanged();
//            mAdapters4.get(mPosition).notifyDataSetChanged();
//        }
    }


    /*二级栏目切换*/
    private void switchRv(int position) {
        //  scrollStop();
        mRv1.swapAdapter(mAdapters1.size() > position ? mAdapters1.get(position) : new Rv1Adapter(), false);
        mRv1.setLayoutManager(new GridLayoutManager(getActivity(), 6, LinearLayoutManager.HORIZONTAL, false));
        mRv2.swapAdapter(mAdapters2.size() > position ? mAdapters2.get(position) : new Rv2Adapter(), false);
        mRv2.setLayoutManager(new GridLayoutManager(getActivity(), 6, LinearLayoutManager.HORIZONTAL, false));
        mRv3.swapAdapter(mAdapters3.size() > position ? mAdapters3.get(position) : new Rv3Adapter(), false);
        mRv3.setLayoutManager(new GridLayoutManager(getActivity(), 6, LinearLayoutManager.HORIZONTAL, false));
        mRv4.swapAdapter(mAdapters4.size() > position ? mAdapters4.get(position) : new Rv4Adapter(), false);
        mRv4.setLayoutManager(new GridLayoutManager(getActivity(), 6, LinearLayoutManager.HORIZONTAL, false));
    }

    /*刷新问路按钮的图标*/
    private void refreshAskImage() {
        Rv1Adapter adapter = mAdapters1.get(mPosition);
        stateCur = -1;
        index1 = setFuture(adapter, 1, index1, false);

        state2 = getXiaSanLuFuture(adapter, 1);
        state3 = getXiaSanLuFuture(adapter, 2);
        state4 = getXiaSanLuFuture(adapter, 3);
        stateCur = 1;

        mPositiveBigEyeImage.setImageResource(state2 == 0 ? R.mipmap.ski_trend_positive1 : R.mipmap.ski_trend_negative1);
        mPositiveSmallImage.setImageResource(state3 == 0 ? R.mipmap.ski_trend_positive2 : R.mipmap.ski_trend_negative2);
        mPositiveBugImage.setImageResource(state4 == 0 ? R.mipmap.ski_trend_positive3 : R.mipmap.ski_trend_negative3);
        mNegativeBigEyeImage.setImageResource(state2 == 3 ? R.mipmap.ski_trend_positive1 : R.mipmap.ski_trend_negative1);
        mNegativeSmallImage.setImageResource(state3 == 3 ? R.mipmap.ski_trend_positive2 : R.mipmap.ski_trend_negative2);
        mNegativeBugImage.setImageResource(state4 == 3 ? R.mipmap.ski_trend_positive3 : R.mipmap.ski_trend_negative3);
    }


    /*清空问路状态*/
    public void clearState() {
        //  clearTimer();
        setRoadLayout(0);
        index1 = 0;
        index2 = 0;
        index3 = 0;
        index4 = 0;
    }

    /*设置问路文字*/
    private void setRoadName(String name) {

        Spanned positiveSp, negativeSp;
        positiveSp = Html.fromHtml("<font color=\"#999999\">下期</font><font color=\"#e3253a\">" + positiveKey + "</font>");
        negativeSp = Html.fromHtml("<font color=\"#999999\">下期</font><font color=\"#367af6\">" + negativeKey + "</font>");
        mPositiveBigEye.setText(positiveSp);
        mPositiveSmall.setText(positiveSp);
        mPositiveBug.setText(positiveSp);
        mNegativeBigEye.setText(negativeSp);
        mNegativeSmall.setText(negativeSp);
        mNegativeBug.setText(negativeSp);
        setOdds();
    }

    /*设置问路按钮背景*/
    private void setRoadStare(int state) {
        isPositive = state == 1;
        isNegative = state == 2;

        mPositiveBigEye.setSelected(isPositive);
        mPositiveSmall.setSelected(isPositive);
        mPositiveBug.setSelected(isPositive);
        mNegativeBigEye.setSelected(isNegative);
        mNegativeSmall.setSelected(isNegative);
        mNegativeBug.setSelected(isNegative);

    }

    private void scrollStop() {
        mRv1.stopScroll();
        mRv2.stopScroll();
        mRv3.stopScroll();
        mRv4.stopScroll();
    }

    private void scrollToLast() {
        /*recyclerView没有绘制完毕滚动会崩溃*/
        try {
            mRv1.scrollToPosition(mAdapters1.get(mPosition).getItemCount() - 1);
            mRv2.scrollToPosition(mAdapters2.get(mPosition).getItemCount() - 1);
            mRv3.scrollToPosition(mAdapters3.get(mPosition).getItemCount() - 1);
            mRv4.scrollToPosition(mAdapters4.get(mPosition).getItemCount() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*问路样式 0：false false; 1 true false; 2 false true*/
    private void setRoadLayout(int state) {
        setRoadStare(state);
        scrollToLast();
        /*问路预测*/
        clearTimer();
        if (state != 0) {
            mTimer = new Timer();
            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    index1 = setFuture(mAdapters1.get(mPosition), state, index1, isFuture);
                    if (state2 != -1) {
                        index2 = setFuture(mAdapters2.get(mPosition), Math.abs(state - state2), index2, isFuture);
                    }
                    if (state3 != -1) {
                        index3 = setFuture(mAdapters3.get(mPosition), Math.abs(state - state3), index3, isFuture);
                    }
                    if (state4 != -1) {
                        index4 = setFuture(mAdapters4.get(mPosition), Math.abs(state - state4), index4, isFuture);
                    }

                    isFuture = !isFuture;
                    stateCur = state;
                }
            }, 1000, 800);

        } else {
            index1 = setFuture(mAdapters1.get(mPosition), state, index1, false);
            if (state2 != -1) {
                index2 = setFuture(mAdapters2.get(mPosition), state, index2, false);
            }
            if (state3 != -1) {
                index3 = setFuture(mAdapters3.get(mPosition), state, index3, false);
            }
            if (state4 != -1) {
                index4 = setFuture(mAdapters4.get(mPosition), state, index4, false);
            }
        }

    }

    private void clearTimer() {
        if (null != mTimer) {
            mTimer.cancel();
            mTimer = null;
        }
    }


    /*设置问路项*/
    private int setFuture(BaseQuickAdapter adapter, int state, int index, boolean isFuture) {
        /*recyclerView 滚动时notifyItemChanged崩溃*/
        try {
            RoadBean last = new RoadBean();
            RoadBean lastTop = new RoadBean();
            List<RoadBean> list = adapter.getData();
            int size = list.size();
            int lastPosition = 0, topPosition = 0;
            boolean isLast = false;
            if (state > 0) {
                /*还原上一个问路*/
                if (index > 0) {
                    list.set(index, last);
                    adapter.notifyItemChanged(index);
                }
                /*状态改变（大变小），计算新的预测位置*/
//                if (stateCur != state) {
                /*找到最后一个与顶部最右侧*/
                for (int i = size - 12; i > 0; i--) {
                    if (!isLast) {
                        last = list.get(i);
                        if (last.getBp() != 0) {
                            lastPosition = i;
                            isLast = true;
                        }
                    }
                    if (i % 6 == 0) {
                        lastTop = list.get(i);
                        if (lastTop.getBp() != 0) {
                            topPosition = i;
                            break;
                        }

                    }
                }
                /*计算预测位置*/
                if (last.getBp() == lastTop.getBp()) {
                    /*最后一个与最后顶部相等*/
                    if (last.getBp() == state) {
                        /*特殊情况，多条转折*/
                        if (list.get(topPosition + 5).getBp() == lastTop.getBp() && topPosition >= 12) {
                            /*找到转折点*/
                            for (int i = topPosition + 7; i < list.size(); i++) {
                                last = list.get(i);
                                if (last.getBp() != 0) {
                                    lastPosition = i;
                                    break;
                                }
                            }
                            /*找到最后的插入位置*/
                            for (int i = lastPosition; i < list.size(); i += 6) {
                                last = list.get(i);
                                if (last.getBp() == 0) {
                                    index = i;
                                    break;
                                }
                            }
                        } else {
                            index = lastPosition + (lastPosition % 6 == 5 ? 6 : 1);
                        }
                    } else {
                        index = topPosition + 6;
                    }

                } else {
                    /*找第一个空位*/
                    for (int i = topPosition + 1; i < size; i++) {
                        last = list.get(i);
                        if (last.getBp() == 0) {
                            index = i;
                            break;
                        }
                    }
                    if (lastTop.getBp() != state) {
                        index = topPosition + 6;
                    }
                }

//                }
            }
            /*第一次只需要计算出大的位置*/
            if (stateCur > 0 && index > 0) {
                list.set(index, getFuture(state, isFuture));
                adapter.notifyItemChanged(index);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return index;
    }

    /*获取预测*/
    private RoadBean getFuture(int state, boolean isFuture) {
        RoadBean future = new RoadBean();
        future.setBp(isFuture ? state : 0);
        future.setName(isFuture ? state == 1 ? positiveKey : negativeKey : "");
        future.setFuture(isFuture);
        return future;
    }

    /*通过大路判断下三路的状态 type:1 大路（与左侧一列比：6-5） 2小路（左侧两列比：6-4） 3曱由路（左侧3列比：6-3）
     * 路中牌：拍拍连（左侧不为空）红色，两房一厅（左侧为空且左侧上一个不为空）蓝色，两房两厅（左侧为空且左侧上一个也为空）红色
     * 路头牌：左侧两列个数相等为红，不等为蓝
     * */
    private int getXiaSanLuFuture(BaseQuickAdapter adapter, int type) {
        List<RoadBean> list = adapter.getData();
        int state = 0;
        int left1 = index1 - type * 6;
        int left2 = index1 - (type + 1) * 6;
        /*state=-1 数据太少时,问路后不存在大路，小路，曱由路*/
        if (list.size() <= type * 6) {
            return -1;
        }
        if (list.get(type * 6).getBp() == 0) {
            return -1;
        }
        if (index1 > 0 && left1 > 0 && left2 > 0) {
            /*路头牌*/
            if (index1 % 6 == 0) {
                int count1 = 0, count2 = 0;
                for (int i = 0; i < 6; i++) {
                    if (list.get(left1 + i).getBp() != 0) {
                        count1++;
                    }
                    if (list.get(left2 + i).getBp() != 0) {
                        count2++;
                    }
                    if (count1 != count2) {
                        state = 3;
                        break;
                    }
                }
            } else {
                /*路中牌*/
                if (list.get(left1).getBp() == 0 && list.get(left1 - 1).getBp() != 0) {
                    /*两房一厅 蓝色*/
                    state = 3;
                }
            }
        }
        return state;
    }


    private void setGenerateBet(int position) {
        RoadSub roadSub = mRoadTitle.getSubList().get(position);
        String name = roadSub.getSubTitle();
        List<String> betTitles = new ArrayList<String>(Arrays.asList(roadSub.getSubTitle().split("")));
        betTitles.removeAll(Arrays.asList("", null));
        String positive, negative;
        if (name.startsWith("尾") || name.startsWith("合")) {
            positive = name.substring(0, 2);
            positiveKey = name.substring(1, 2);
            negativeKey = name.substring(2);
            negative = name.substring(0, 1) + name.substring(2);
        } else if (name.startsWith("天") || name.startsWith("前") || name.startsWith("家") || "单双多".equalsIgnoreCase(name)) {
            positive = String.format("%s%s", name.substring(0, 1), name.substring(2));
            negative = name.substring(1);
            positiveKey = name.substring(0, 1);
            negativeKey = name.substring(1, 2);
        } else {
            positive = name.substring(0, 1);
            negative = name.substring(1);
            positiveKey = name.substring(0, 1);
            negativeKey = name.substring(1, 2);
        }
        txtBet1.setText(applyText(positive, true));
        txtBet2.setText(applyText(negative, false));
    }


    public Spanned applyText(String string, boolean flag) {
        Spanned positiveSp;
        if (flag) {
            positiveSp = Html.fromHtml("<font color=\"#999999\">投</font><font color=\"#e3253a\">" + string + "</font>");
        } else {
            positiveSp = Html.fromHtml("<font color=\"#999999\">投</font><font color=\"#367af6\">" + string + "</font>");
        }
        return positiveSp;
    }

    private void onBetRequest(View view) {

        int index = view.getId() == R.id.layout_bet1 ? 0 : 1;
        Integer[] ids = mRoadSub.getRoadPlayIds();
        List<BallBean> odds = PlayUtil.getRoadOdds(ids, index);
        DataCenter.getInstance().setBallBeanList(odds);
        RxBus.get().post(EVENT_TYPE_QUICK_BET_CLICK, mRoadTitle.getTitle() + mRoadSub.getSubTitle());
    }




    /*Adapter*/
    class Rv1Adapter extends BaseQuickAdapter<RoadBean, BaseViewHolder> {

        public Rv1Adapter() {
            super(R.layout.ski_road_item_1);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder holder, @Nullable RoadBean bean) {
            TextView name = holder.itemView.findViewById(R.id.tv_name);
            holder.setText(R.id.tv_name, bean.getName());
            int bp = bean.getBp();
            if (RoadBean.Con.BANKER == bp) {
                holder.setTextColor(R.id.tv_name, ContextCompat.getColor(mContext, R.color.ski_red));
            } else if (RoadBean.Con.PLAYER == bp) {
                holder.setTextColor(R.id.tv_name, ContextCompat.getColor(mContext, R.color.ski_text_pub_blue));
            } else {
                holder.setTextColor(R.id.tv_name, ContextCompat.getColor(mContext, R.color.ski_lawngreen));
            }
            name.setBackgroundColor(ContextCompat.getColor(getContext(), bean.isFuture() ? R.color.ski_road_future : R.color.ski_transparent));
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }
    }

    class Rv2Adapter extends BaseQuickAdapter<RoadBean, BaseViewHolder> {

        public Rv2Adapter() {
            super(R.layout.ski_road_item_2);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder holder, @Nullable RoadBean bean) {
            ImageView viewI = holder.getView(R.id.iv_road);
            if (RoadBean.Con.BANKER == bean.getBp()) {
                viewI.setImageResource(R.drawable.ski_road_positive1);
            } else if (RoadBean.Con.PLAYER == bean.getBp()) {
                viewI.setImageResource(R.drawable.ski_road_negative1);
            } else {
                viewI.setImageResource(0);
            }
            viewI.setBackgroundColor(ContextCompat.getColor(getContext(), bean.isFuture() ? R.color.ski_road_future : R.color.ski_transparent));
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }
    }


    class Rv3Adapter extends BaseQuickAdapter<RoadBean, BaseViewHolder> {

        public Rv3Adapter() {
            super(R.layout.ski_road_item_2);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder holder, @Nullable RoadBean bean) {
            ImageView viewI = holder.getView(R.id.iv_road);
            if (RoadBean.Con.BANKER == bean.getBp()) {
                viewI.setImageResource(R.drawable.ski_road_positive2);
            } else if (RoadBean.Con.PLAYER == bean.getBp()) {
                viewI.setImageResource(R.drawable.ski_road_negative2);
            } else {
                viewI.setImageResource(0);
            }
            viewI.setBackgroundColor(ContextCompat.getColor(getContext(), bean.isFuture() ? R.color.ski_road_future : R.color.ski_transparent));
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }
    }


    class Rv4Adapter extends BaseQuickAdapter<RoadBean, BaseViewHolder> {

        public Rv4Adapter() {
            super(R.layout.ski_road_item_2);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder holder, @Nullable RoadBean bean) {
            ImageView viewI = holder.getView(R.id.iv_road);
            if (RoadBean.Con.BANKER == bean.getBp()) {
                viewI.setImageResource(R.mipmap.ski_trend_positive3);
            } else if (RoadBean.Con.PLAYER == bean.getBp()) {
                viewI.setImageResource(R.mipmap.ski_trend_negative3);
            } else {
                viewI.setImageResource(0);
            }
            viewI.setBackgroundColor(ContextCompat.getColor(getContext(), bean.isFuture() ? R.color.ski_road_future : R.color.ski_transparent));
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }
    }


}
