package com.ski.box.view.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ski.box.R;
import com.ski.box.adapter.MySection;
import com.ski.box.adapter.PlayShuoMingAdapter;
import com.ski.box.adapter.SeriesAdapter;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.ShuoMingDoubleBean;
import com.ski.box.bean.lottery.LotteryBean;
import com.ski.box.bean.lottery.LotterySer;
import com.ski.box.bean.lottery.LotteryUtil;
import com.ski.box.view.view.dialog.ShuoMingSheetDialog;
import com.yb.core.utils.AppUtil;
import com.yb.core.utils.AssetsReader;
import com.yb.core.utils.LanguageUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_PL35;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_SSC;


/**
 * author Afton
 * date 2020/2/28
 * 玩法说明
 */
public class ShuoMingDoubleView extends LinearLayout {
    private ImageView mIvShuoMingShow;
    private TextView mTvSeriesNameShuoMing;
    private String mCurTicketName;
    private String mCurPlayName;
    private RecyclerView mRecycleShouMing;
    private RecyclerView mRecycleSeriesKind;
    private SeriesAdapter mSeriesAdapter;
    private PlayShuoMingAdapter mPlayShuoMingAdapter;
    private ShuoMingSheetDialog mStrongBottomSheetDialog;
    private Drawable mDrawable;

    private List<MySection> mSeriesData;
    private List<ShuoMingDoubleBean> mShuoMingData;

    public ShuoMingDoubleView(Context context) {
        this(context, null);
    }

    public ShuoMingDoubleView(Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public ShuoMingDoubleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context);
    }

    private void initLayout(Context context) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.ski_hall_double_shuoming, this);
        mIvShuoMingShow = inflate.findViewById(R.id.iv_shuoming_show);
        mTvSeriesNameShuoMing = inflate.findViewById(R.id.tv_series_name_shuoming);
        mRecycleShouMing = inflate.findViewById(R.id.recyc_shouming);
        mRecycleSeriesKind = inflate.findViewById(R.id.recyc_series_kind);
        ((SimpleItemAnimator) mRecycleShouMing.getItemAnimator()).setSupportsChangeAnimations(false);
        ((SimpleItemAnimator) mRecycleSeriesKind.getItemAnimator()).setSupportsChangeAnimations(false);

        /*设置彩系列表*/
        mRecycleSeriesKind.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mSeriesAdapter = new SeriesAdapter(R.layout.ski_def_section_head, R.layout.ski_item_type_shuoming, null);
        mRecycleSeriesKind.addItemDecoration(new GridItemDecoration(10));
        mRecycleSeriesKind.setAdapter(mSeriesAdapter);

        /*玩法说明*/
        createShuoMingAdapter();
        mPlayShuoMingAdapter = new PlayShuoMingAdapter(R.layout.ski_item_double_shuoming);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        mRecycleShouMing.setLayoutManager(linearLayoutManager1);
        mRecycleShouMing.setAdapter(mPlayShuoMingAdapter);

        initListener();

    }

    private void createShuoMingAdapter() {
        /*设置内容说明列表*/
//        shuoMingAdapter = new RecyclerAdapter(getContext(), new RecyclerAdapter.AdapterTemplate() {
//            @Override
//            public Map<Class<?>, Integer> getItemViewType() {
//                Map<Class<?>, Integer> map = new HashMap<>(1);
//                map.put(ShuoMingDoubleBean.DescriptionBean.class, R.layout.ski_item_double_shuoming);
//                return map;
//            }
//
//            @Override
//            public SparseArray<Class<? extends RecyclerAdapter.BaseViewHolder>> getViewHolder() {
//                SparseArray<Class<? extends RecyclerAdapter.BaseViewHolder>> array = new SparseArray<>();
//                array.put(R.layout.ski_item_double_shuoming, ShuoMingHolder.class);
//                return array;
//            }
//        });
    }

    private void initListener() {
        mIvShuoMingShow.setOnClickListener(v -> {
            if (null != mStrongBottomSheetDialog) {
                mStrongBottomSheetDialog.dismiss();
            }
        });
        mTvSeriesNameShuoMing.setOnClickListener(view -> {
            int visibility = mRecycleShouMing.getVisibility();
            drawableChange(visibility == View.VISIBLE);
            if (visibility == View.VISIBLE) {
                /*隐藏说明 展示彩中*/
                showSmOrSeriesKind(false);
                mSeriesAdapter.replaceData(initSeriesData());
            } else {
                /*展示说明 隐藏彩种*/
                showSmOrSeriesKind(true);
            }
        });
        /*彩中选择*/
        mSeriesAdapter.setOnItemClickListener((adapter, view, position) -> {
            MySection mySection = mSeriesData.get(position);
            if (!mySection.isHeader()) {
                showSmOrSeriesKind(true);
                LotteryBean bean = (LotteryBean) mySection.getObject();
                mCurTicketName = bean.getTicketName();
                readerShuoMingJson(LotteryUtil.getSerIdByLotteryId(bean.getTicketId()), mCurTicketName);
            }

        });
    }

    /*隐藏 展示*/
    private void showSmOrSeriesKind(boolean isShow) {
        if (isShow) {
            /*展示玩法说明*/
            mRecycleShouMing.setVisibility(VISIBLE);
            mRecycleSeriesKind.setVisibility(GONE);
        } else {
            /*隐藏玩法说明*/
            mRecycleShouMing.setVisibility(GONE);
            mRecycleSeriesKind.setVisibility(VISIBLE);
        }
    }

    /*改变彩种名右侧三角*/
    private void drawableChange(boolean isUp) {
        mDrawable = ContextCompat.getDrawable(AppUtil.getContext(), isUp ? R.mipmap.ski_drop_up : R.mipmap.ski_drop_down);
        mDrawable.setBounds(0, 0, mDrawable.getMinimumWidth(), mDrawable.getMinimumHeight());
        mTvSeriesNameShuoMing.setCompoundDrawables(null, null, mDrawable, null);
    }

    /*加载所有菜系和菜系的彩中*/
    private List<MySection> initSeriesData() {
        List<LotterySer> lotterySers = DataCenter.getInstance().getLottery();
        mSeriesData = new ArrayList<>();
        if (null != lotterySers && lotterySers.size() > 0) {
            for (LotterySer bean : lotterySers) {
                mSeriesData.add(new MySection(true, bean));
                for (LotteryBean ticket : bean.getList()) {
                    ticket.setSelected(ticket.getTicketName().equals(mCurTicketName));
                    mSeriesData.add(new MySection(false, ticket));
                }
            }
        }
        return mSeriesData;
    }

    /**
     * 获取定义好的json数据
     */

    private void readerShuoMingJson(int seriesId, String ticketName) {
        if (SER_ID_PL35 == seriesId) {
            seriesId = SER_ID_SSC;
        }
        if (null == mShuoMingData || mShuoMingData.size() < 1) {
            Gson gson = new Gson();
            String json = "";
                    if(LanguageUtil.VI.equals(LanguageUtil.getLanguage())) {
                        json = AssetsReader.getJson(getContext(), "json" + File.separator + "language"+ File.separator + "def_play_des_vi.json");
                    } else {
                        json = AssetsReader.getJson(getContext(), "json" + File.separator + "def_play_des.json");
                    }
            mShuoMingData = gson.fromJson(json, new TypeToken<List<ShuoMingDoubleBean>>() {
            }.getType());
        }
        for (ShuoMingDoubleBean bean : mShuoMingData) {
            if (seriesId == bean.getSeriesId()) {
                refreshShuoMingData(bean.getDescription(), ticketName, "");
                break;
            }
        }


    }

    /**
     * 刷新数据
     *
     * @param datas
     */
    public void refreshShuoMingData(List<ShuoMingDoubleBean.DescriptionBean> datas, String curTicketName, String curPlayName) {
        /*展示玩法说明*/
        showSmOrSeriesKind(true);
        drawableChange(false);
        mCurTicketName = curTicketName;
        mCurPlayName = curPlayName;
        mTvSeriesNameShuoMing.setText(curTicketName);
        createShuoMingAdapter();
        mRecycleShouMing.setAdapter(mPlayShuoMingAdapter);
        mPlayShuoMingAdapter.replaceData(datas);
        smoothScroller.setTargetPosition(0);
        if (!TextUtils.isEmpty(curPlayName)) {
            if (specialPlay.contains(curPlayName)) {
                if (null != mRecycleShouMing) {
                    mRecycleShouMing.scrollToPosition(specialPlayMap.get(curPlayName));
                }
            } else {
                for (int i = 0; i < datas.size(); i++) {
                    if (datas.get(i).getTitle().contains(curPlayName)) {
                        if (null != mRecycleShouMing) {
                            mRecycleShouMing.scrollToPosition(i);
                            break;
                        }
                    }
                }
            }
        } else {
            mRecycleShouMing.scrollToPosition(0);
        }
    }

    /*RecyclerView滚动*/
    LinearSmoothScroller smoothScroller = new LinearSmoothScroller(getContext()) {

        @Override
        protected int getVerticalSnapPreference() {
            return LinearSmoothScroller.SNAP_TO_START;
        }
    };

    /*特殊玩法滚动*/
    private ArrayList<String> specialPlay = new ArrayList<String>() {{
        add("组三");
        add("组六");
        add("前中后三");
        add("冠亚和值");
    }};
    private HashMap<String, Integer> specialPlayMap = new HashMap<String, Integer>() {{
        put("组三", 9);
        put("组六", 10);
        put("前中后三", 3);
        put("冠亚和值", 3);
    }};

    public void setBottomFramentDailog(ShuoMingSheetDialog strongBottomSheetDialog) {
        this.mStrongBottomSheetDialog = strongBottomSheetDialog;
    }
}

