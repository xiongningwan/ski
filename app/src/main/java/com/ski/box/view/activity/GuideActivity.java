package com.ski.box.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ski.box.R;
import com.ski.box.mvp.contract.EmptyContract;
import com.ski.box.mvp.presenter.EmptyPresenter;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;


public class GuideActivity extends BaseMVPActivity<EmptyContract.Presenter> implements EmptyContract.View, View.OnClickListener {
    public static final String DATA_KEY = "DATA_KEY";
    public static final int GO_HOME = 200;
    TextView btn;
    ViewPager pager;
    LinearLayout mIndicatorLL;
    private List<View> mImageViewList;
    private List<View> mIndicatorViewList = new ArrayList<>();

    @Override
    protected EmptyContract.Presenter bindPresenter() {
        return new EmptyPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_guide;
    }

    @Override
    protected void initViews() {
        btn =  findViewById(R.id.welcome_guide_btn);
        pager =  findViewById(R.id.welcome_pager);
        mIndicatorLL =  findViewById(R.id.indicator_ll);

        btn.setOnClickListener(this);
        initPagerItemVies();
        initIndicator();
        initViewPager();
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    private void initPagerItemVies() {
        mImageViewList = new ArrayList<View>();
        ImageView iv1 = new ImageView(this);
        iv1.setImageResource(R.mipmap.guide_01);
        ImageView iv2 = new ImageView(this);
        iv2.setImageResource(R.mipmap.guide_02);
        ImageView iv3 = new ImageView(this);
        iv3.setImageResource(R.mipmap.guide_03);
        mImageViewList.add(iv1);
        mImageViewList.add(iv2);
        mImageViewList.add(iv3);
    }

    private void initIndicator() {
        for (int i = 0; i < mImageViewList.size(); i++) {
            View view = new View(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ScreenUtils.dp2px(this, 5), ScreenUtils.dp2px(this, 5));
            view.setBackgroundResource(R.drawable.ski_shape_oval_gray_white_bg_unselected);
//            view.setPadding(0, 0, DensityUtil.dp2px(this, 10), 0);
            lp.setMarginEnd(ScreenUtils.dp2px(this, 5));
            view.setLayoutParams(lp);
            mIndicatorLL.addView(view);
            mIndicatorViewList.add(view);
        }
        mIndicatorViewList.get(0).setBackgroundResource(R.drawable.ski_shape_oval_gray_white_bg_selected);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.welcome_guide_btn) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }


    //初始化ViewPager的方法
    private void initViewPager() {
        pager.setAdapter(new MyPagerAdapter());
        //监听ViewPager滑动效果
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //页卡被选中的方法
            @Override
            public void onPageSelected(int arg0) {
                //如果是第三个页面
                if (arg0 == 2) {
                    btn.setVisibility(View.VISIBLE);
                } else {
                    btn.setVisibility(View.GONE);
                }

                for (View view : mIndicatorViewList) {
                    if (view == mIndicatorViewList.get(arg0)) {
                        view.setBackgroundResource(R.drawable.ski_shape_oval_gray_white_bg_selected);
                    } else {
                        view.setBackgroundResource(R.drawable.ski_shape_oval_gray_white_bg_unselected);
                    }
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }



    //定义ViewPager的适配器
    class MyPagerAdapter extends PagerAdapter {
        //计算需要多少item显示
        @Override
        public int getCount() {
            return mImageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        //初始化item实例方法
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageViewList.get(position));
            return mImageViewList.get(position);
        }

        //item销毁的方法
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // 注销父类销毁item的方法，因为此方法并不是使用此方法
//          super.destroyItem(container, position, object);
            container.removeView(mImageViewList.get(position));
        }
    }
}
