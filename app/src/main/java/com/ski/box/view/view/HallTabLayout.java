package com.ski.box.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.ski.box.R;
import com.ski.box.utils.LanguageUtil;


public class HallTabLayout extends RelativeLayout implements View.OnClickListener {
    public static final int TAB_INDEX_HALL = 0;
    public static final int TAB_INDEX_RECORD = 1;
    public static final int TAB_INDEX_RECHARGE = 2;
    public static final int TAB_INDEX_PERSONAL = 3;

    private LinearLayout mLLHall;
    private LinearLayout mLLRecord;
    private LinearLayout mLLRecharge;
    private LinearLayout mLLPersonal;
    private ImageView mIvHall;
    private ImageView mIvRecord;
    private ImageView mIvRecharge;
    private ImageView mIvPersonal;
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
        mLLHall = findViewById(R.id.lottery_hall_ll);
        mLLRecord = findViewById(R.id.lottery_record_ll);
        mLLRecharge = findViewById(R.id.lottery_recharge_ll);
        mLLPersonal = findViewById(R.id.lottery_personal_ll);
        mIvHall = findViewById(R.id.iv_hall);
        mIvRecord = findViewById(R.id.iv_record);
        mIvRecharge = findViewById(R.id.iv_recharge);
        mIvPersonal = findViewById(R.id.iv_personal);

        mLLHall.setOnClickListener(this);
        mLLRecord.setOnClickListener(this);
        mLLRecharge.setOnClickListener(this);
        mLLPersonal.setOnClickListener(this);

        TextView tv1 = findViewById(R.id.tv_1);
        TextView tv2 = findViewById(R.id.tv_2);
        TextView tv3 = findViewById(R.id.tv_3);
        TextView tv4 = findViewById(R.id.tv_4);
        tv1.setText(LanguageUtil.getText("首页大厅"));
        tv2.setText(LanguageUtil.getText("活动优惠"));
        tv3.setText(LanguageUtil.getText("充值中心"));
        tv4.setText(LanguageUtil.getText("个人中心"));
    }

    public void bindViewPager(ViewPager viewPager){
        mViewPager = viewPager;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lottery_hall_ll:
                switchTab(TAB_INDEX_HALL);
                break;
            case R.id.lottery_record_ll:
                switchTab(TAB_INDEX_RECORD);
                break;
            case R.id.lottery_recharge_ll:
                switchTab(TAB_INDEX_RECHARGE);
                break;
            case R.id.lottery_personal_ll:
                switchTab(TAB_INDEX_PERSONAL);
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
            case TAB_INDEX_HALL:
                mViewPager.setCurrentItem(TAB_INDEX_HALL, false);
                changeTabState(index);
                break;
            case TAB_INDEX_RECORD:
                mViewPager.setCurrentItem(TAB_INDEX_RECORD, false);
                changeTabState(index);
                break;
            case TAB_INDEX_RECHARGE:
                mViewPager.setCurrentItem(TAB_INDEX_RECHARGE, false);
                changeTabState(index);
                break;
            case TAB_INDEX_PERSONAL:
                mViewPager.setCurrentItem(TAB_INDEX_PERSONAL, false);
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
            case TAB_INDEX_HALL:
                mLLHall.setSelected(true);
                mLLRecord.setSelected(false);
                mLLRecharge.setSelected(false);
                mLLPersonal.setSelected(false);
                break;
            case TAB_INDEX_RECORD:
                mLLHall.setSelected(false);
                mLLRecord.setSelected(true);
                mLLRecharge.setSelected(false);
                mLLPersonal.setSelected(false);
                break;
            case TAB_INDEX_RECHARGE:
                mLLHall.setSelected(false);
                mLLRecord.setSelected(false);
                mLLRecharge.setSelected(true);
                mLLPersonal.setSelected(false);
                break;
            case TAB_INDEX_PERSONAL:
                mLLHall.setSelected(false);
                mLLRecord.setSelected(false);
                mLLRecharge.setSelected(false);
                mLLPersonal.setSelected(true);
                break;
            default:
        }
    }
}

