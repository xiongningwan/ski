package com.ski.box.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.ViewPager;

import com.ski.box.R;


public class HallTabLayout extends RelativeLayout implements View.OnClickListener {
    public static final int TAB_INDEX_0 = 0;
    public static final int TAB_INDEX_1 = 1;
    public static final int TAB_INDEX_2 = 2;
    public static final int TAB_INDEX_3 = 3;

    private LinearLayout mLL_0;
    private LinearLayout mLL_1;
    private LinearLayout mLL_2;
    private LinearLayout mLL_3;
    private ImageView mIv_0;
    private ImageView mIv_1;
    private ImageView mIv_2;
    private ImageView mIv_3;
    private ViewPager mViewPager;


    public HallTabLayout(Context context) {
        super(context);
        init();
    }

    public HallTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HallTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.ski_hall_bottom_tab_view, this);
        mLL_0 = findViewById(R.id.lottery_0);
        mLL_1 = findViewById(R.id.lottery_1);
        mLL_2 = findViewById(R.id.lottery_2);
        mLL_3 = findViewById(R.id.lottery_3);
        mIv_0 = findViewById(R.id.iv_0);
        mIv_1 = findViewById(R.id.iv_1);
        mIv_2 = findViewById(R.id.iv_2);
        mIv_3 = findViewById(R.id.iv_3);

        mLL_0.setOnClickListener(this);
        mLL_1.setOnClickListener(this);
        mLL_2.setOnClickListener(this);
        mLL_3.setOnClickListener(this);

//        TextView tv1 = findViewById(R.id.tv_1);
//        TextView tv2 = findViewById(R.id.tv_2);
//        TextView tv3 = findViewById(R.id.tv_3);
//        TextView tv4 = findViewById(R.id.tv_4);
//        tv1.setText(LanguageUtil.getText("首页大厅"));
//        tv2.setText(LanguageUtil.getText("活动优惠"));
//        tv3.setText(LanguageUtil.getText("充值中心"));
//        tv4.setText(LanguageUtil.getText("个人中心"));
    }

    public void bindViewPager(ViewPager viewPager){
        mViewPager = viewPager;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lottery_0:
                switchTab(TAB_INDEX_0);
                break;
            case R.id.lottery_1:
                switchTab(TAB_INDEX_1);
                break;
            case R.id.lottery_2:
                switchTab(TAB_INDEX_2);
                break;
            case R.id.lottery_3:
                switchTab(TAB_INDEX_3);
                break;
            default:
        }
    }

    /**
     * 切换页面
     * 连带改变tab选中状态
     *
     * @param index tab下标
     */
    public void switchTab(int index) {
        switch (index) {
            case TAB_INDEX_0:
                mViewPager.setCurrentItem(TAB_INDEX_0, false);
                changeTabState(index);
                break;
            case TAB_INDEX_1:
                mViewPager.setCurrentItem(TAB_INDEX_1, false);
                changeTabState(index);
                break;
            case TAB_INDEX_2:
                mViewPager.setCurrentItem(TAB_INDEX_2, false);
                changeTabState(index);
                break;
            case TAB_INDEX_3:
                mViewPager.setCurrentItem(TAB_INDEX_3, false);
                changeTabState(index);
                break;
            default:
        }
    }

    /**
     * 改变tab的选中和未选中状态
     *
     * @param index
     */
    private void changeTabState(int index) {
        switch (index) {
            case TAB_INDEX_0:
                mLL_0.setSelected(true);
                mLL_1.setSelected(false);
                mLL_2.setSelected(false);
                mLL_3.setSelected(false);
                break;
            case TAB_INDEX_1:
                mLL_0.setSelected(false);
                mLL_1.setSelected(true);
                mLL_2.setSelected(false);
                mLL_3.setSelected(false);
                break;
            case TAB_INDEX_2:
                mLL_0.setSelected(false);
                mLL_1.setSelected(false);
                mLL_2.setSelected(true);
                mLL_3.setSelected(false);
                break;
            case TAB_INDEX_3:
                mLL_0.setSelected(false);
                mLL_1.setSelected(false);
                mLL_2.setSelected(false);
                mLL_3.setSelected(true);
                break;
            default:
        }
    }
}

