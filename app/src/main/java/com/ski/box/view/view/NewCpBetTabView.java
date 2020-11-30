package com.ski.box.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.ski.box.R;
import com.yb.core.utils.ScreenUtils;


/**
 * 投注页导航
 */
public class NewCpBetTabView extends RelativeLayout implements View.OnClickListener {
    private TextView item_1;
    private TextView item_2;
//    private TextView item_3;
    private RelativeLayout item_4;
    private int mScreenWidth;
    private int mCurrentIndex;
    private ViewPager mViewPager;

    private int mPosition_0;
    private int mPosition_1;
    private int mPosition_road;
    private int mPosition_2;
    private TextView tvNum;

    public NewCpBetTabView(Context context) {
        super(context);
        initView(context);
    }

    public NewCpBetTabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public NewCpBetTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View v = View.inflate(context, R.layout.ski_bet_cp_new_tab_view, this);

        item_1 = v.findViewById(R.id.tv_bet_d);
        item_2 = v.findViewById(R.id.tv_bet_s);
//        item_3 = v.findViewById(R.id.tv_road);
        item_4 = v.findViewById(R.id.rl_4);
        tvNum = v.findViewById(R.id.tv_unpaid_num);
        item_1.setOnClickListener(this);
        item_2.setOnClickListener(this);
//        item_3.setOnClickListener(this);
        item_4.setOnClickListener(this);
        mScreenWidth = ScreenUtils.getScreenWidth(context);


        mPosition_0 = 0;
        mPosition_1 = 1;
//        mPosition_road = 2;
        mPosition_2 = 2;
    }

    public void scrollPosition(int position) {
        if (mCurrentIndex != position) {
            mCurrentIndex = position;
            mViewPager.setCurrentItem(position);
            switchItem(position);
        }
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (R.id.tv_bet_d == id) {
            scrollPosition(mPosition_0);
        } else if (R.id.tv_bet_s == id) {
            scrollPosition(mPosition_1);
        } else if (R.id.tv_road == id) {
            scrollPosition(mPosition_road);
        } else if (R.id.rl_4 == id) {
            scrollPosition(mPosition_2);

        }

    }

    public void bindViewPager(ViewPager viewPager) {
        this.mViewPager = viewPager;
    }

    private void switchItem(int position) {
        switch (position) {
            case 0:
                item_1.setSelected(true);
                item_2.setSelected(false);
//                item_3.setSelected(false);
                item_4.setSelected(false);
                break;
            case 1:
                item_1.setSelected(false);
                item_2.setSelected(true);
//                item_3.setSelected(false);
                item_4.setSelected(false);
                break;
            case 2:
                item_1.setSelected(false);
                item_2.setSelected(false);
//                item_3.setSelected(true);
//                item_4.setSelected(false);
                item_4.setSelected(true);
                break;
            case 3:
                item_1.setSelected(false);
                item_2.setSelected(false);
//                item_3.setSelected(false);
                item_4.setSelected(true);
                break;
        }
    }

    public void setDefaultState(int mPosition) {
        switchItem(mPosition);
    }


    public void setTvUnpaidNum(int size) {
        if (null != tvNum) {
            if (size > 0) {
                tvNum.setText(String.valueOf(size));
                tvNum.setVisibility(VISIBLE);
            } else {
                tvNum.setText(String.valueOf(size));
                tvNum.setVisibility(GONE);
            }
        }
    }
}