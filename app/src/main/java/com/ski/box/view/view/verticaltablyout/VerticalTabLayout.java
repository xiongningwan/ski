package com.ski.box.view.view.verticaltablyout;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;

import com.ski.box.R;
import com.yb.core.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import static androidx.viewpager.widget.ViewPager.SCROLL_STATE_IDLE;
import static androidx.viewpager.widget.ViewPager.SCROLL_STATE_SETTLING;

public class VerticalTabLayout extends ScrollView {
    private Context mContext;
    private TabStrip mTabStrip;
    private int mColorIndicator;
    private TabView mSelectedTab;
    private int mLastnearByPosition = -1;
    private int mInitialSelectPosition;
    private int mTabMargin;
    private int mIndicatorWidth;
    private int mIndicatorGravity;
    private float mIndicatorCorners;
    private int mTabMode;
    private int mTabHeight;
    private int mTabPaddingLeft;
    private int mTabPaddingTop;

    private int mIndicatorResId;
    private Drawable mIndicatorDrawable;

    public static int TAB_MODE_FIXED = 10;
    public static int TAB_MODE_SCROLLABLE = 11;

    private VerticalViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private TabAdapter mTabAdapter;

    private List<OnTabSelectedListener> mTabSelectedListeners;
    private OnTabPageChangeListener mTabPageChangeListener;
    private DataSetObserver mPagerAdapterObserver;

    private TabFragmentManager mTabFragmentManager;

    public VerticalTabLayout(Context context) {
        this(context, null);
    }

    public VerticalTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mTabSelectedListeners = new ArrayList<>();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ski_VerticalTabLayout);
        mColorIndicator = typedArray.getColor(R.styleable.ski_VerticalTabLayout_ski_indicator_color_vertical,
                ContextCompat.getColor(context, R.color.ski_colorAccent));
        mIndicatorWidth = (int) typedArray.getDimension(R.styleable.ski_VerticalTabLayout_ski_indicator_width, ScreenUtils.dpToPx(context, 3));
        mIndicatorCorners = typedArray.getDimension(R.styleable.ski_VerticalTabLayout_ski_indicator_corners, 0);
        mIndicatorGravity = typedArray.getInteger(R.styleable.ski_VerticalTabLayout_ski_indicator_gravity, Gravity.LEFT);
        mIndicatorResId = typedArray.getResourceId(R.styleable.ski_VerticalTabLayout_ski_indicator_drawable, 0);
        if (mIndicatorGravity == 3) {
            mIndicatorGravity = Gravity.LEFT;
        } else if (mIndicatorGravity == 5) {
            mIndicatorGravity = Gravity.RIGHT;
        } else if (mIndicatorGravity == 119) {
            mIndicatorGravity = Gravity.FILL;
        }
        mTabMargin = (int) typedArray.getDimension(R.styleable.ski_VerticalTabLayout_ski_tab_margin, 0);
        mTabPaddingLeft = (int) typedArray.getDimension(R.styleable.ski_VerticalTabLayout_ski_tab_padding_left, 0);
        mTabPaddingTop = (int) typedArray.getDimension(R.styleable.ski_VerticalTabLayout_ski_tab_padding_top, 0);
        mTabMode = typedArray.getInteger(R.styleable.ski_VerticalTabLayout_ski_tab_mode, TAB_MODE_FIXED);
        int defaultTabHeight = LinearLayout.LayoutParams.WRAP_CONTENT;
        mTabHeight = (int) typedArray.getDimension(R.styleable.ski_VerticalTabLayout_ski_tab_height, defaultTabHeight);
        typedArray.recycle();
        if (mIndicatorResId != 0) {
            mIndicatorDrawable = ContextCompat.getDrawable(getContext(), mIndicatorResId);
        }
    }

    public void setmInitialSelectPosition(int mInitialSelectPosition) {
        this.mInitialSelectPosition = mInitialSelectPosition;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            removeAllViews();
        }
        initTabStrip();
    }

    private void initTabStrip() {
        mTabStrip = new TabStrip(mContext);
        addView(mTabStrip, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    public void removeAllTabs() {
        mTabStrip.removeAllViews();
        mSelectedTab = null;
    }

    public TabView getTabAt(int position) {
        return (TabView) mTabStrip.getChildAt(position);
    }

    public int getTabCount() {
        return mTabStrip.getChildCount();
    }

    public int getSelectedTabPosition() {
        int index = mTabStrip.indexOfChild(mSelectedTab);
        return index == -1 ? 0 : index;
    }

    private void addTabWithMode(TabView tabView) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        initTabWithMode(params);
        mTabStrip.addView(tabView, params);
        if (mTabStrip.indexOfChild(tabView) == mInitialSelectPosition) {
            tabView.setChecked(true);
            params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
            params.setMargins(0, 0, 0, 0);
            tabView.setLayoutParams(params);
            mSelectedTab = tabView;
            mTabStrip.post(new Runnable() {
                @Override
                public void run() {
                    mTabStrip.moveIndicator(mInitialSelectPosition);
                    scrollToTab(mInitialSelectPosition);
                }
            });
        }
    }

    private void initTabWithMode(LinearLayout.LayoutParams params) {
        if (mTabMode == TAB_MODE_FIXED) {
            params.height = 0;
            params.weight = 1.0f;
            params.setMargins(0, 0, 0, 0);
            setFillViewport(true);
        } else if (mTabMode == TAB_MODE_SCROLLABLE) {
            params.height = mTabHeight;
            params.weight = 0f;
            params.setMargins(0, mTabMargin, 0, 0);
            setFillViewport(false);
        }
    }

    private void scrollToTab(int position) {
        final TabView tabView = getTabAt(position);
        int y = getScrollY();
        int tabTop = tabView.getTop() + tabView.getHeight() / 2 - y;
        int target = getHeight() / 2;
        if (tabTop > target) {
            smoothScrollBy(0, tabTop - target);
        } else if (tabTop < target) {
            smoothScrollBy(0, tabTop - target);
        }
    }

    private float mLastPositionOffset;

    private void scrollByTab(int position, final float positionOffset) {
        final TabView tabView = getTabAt(position);
        int y = getScrollY();
        int tabTop = tabView.getTop() + tabView.getHeight() / 2 - y;
        int target = getHeight() / 2;
        int nextScrollY = tabView.getHeight() + mTabMargin;
        if (positionOffset > 0) {
            float percent = positionOffset - mLastPositionOffset;
            if (tabTop > target) {
                smoothScrollBy(0, (int) (nextScrollY * percent));
            }
        }
        mLastPositionOffset = positionOffset;
    }

    public void addTab(TabView tabView) {
        if (tabView != null) {
            addTabWithMode(tabView);
            tabView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = mTabStrip.indexOfChild(view);
                    setTabSelected(true, position);
                    TabView tab = getTabAt(position);
                    tab.setChecked(true);
                }
            });
        } else {
            throw new IllegalStateException("tabview can't be null");
        }
    }

    public void setTabSelected(final boolean isOnclick, final int position) {
        setTabSelected(isOnclick, position, true, true);
    }

    private void setTabSelected(final boolean isOnclick, final int position, final boolean updataIndicator, final boolean callListener) {
        post(new Runnable() {
            @Override
            public void run() {
                setTabSelectedImpl(isOnclick, position, updataIndicator, callListener);
            }
        });
    }

    private void setTabSelectedImpl(final boolean isOnclick, final int position, boolean updataIndicator, boolean callListener) {
        TabView view = getTabAt(position);
        view.setChecked(true);
        boolean selected;
        /*上一次TabView隐藏Icon这里还原Tab样式*/
        if (mLastnearByPosition != -1 && mLastnearByPosition != position) {
            QTabView mLastTabView = (QTabView) getTabAt(mLastnearByPosition);
            if (mLastTabView != null) {
                mLastTabView.isNeayByChecked = false;
            }
            mLastTabView.setVisibleIcon();
        }

        /*还原上一个选中的Tab的样式*/

        if (selected = (view != mSelectedTab)) {
            if (mSelectedTab != null) {
                mSelectedTab.setChecked(false);
            }
//            view.setChecked(true);
            if (updataIndicator) {
                mTabStrip.moveIndicatorWithAnimator(position);
            }
            mSelectedTab = view;
//            ((QTabView)mSelectedTab).isNeayByChecked = true;
            scrollToTab(position);
        }
        /*上一个TabView隐藏Icon*/
        if (position > 0) {
            mLastnearByPosition = position - 1;
            ((QTabView) getTabAt(mLastnearByPosition)).isNeayByChecked = true;
            ((QTabView) getTabAt(mLastnearByPosition)).setInvisibleIcon();
        }
        if (callListener) {
            for (int i = 0; i < mTabSelectedListeners.size(); i++) {
                OnTabSelectedListener listener = mTabSelectedListeners.get(i);
                if (listener != null) {
                    if (selected) {
                        listener.onTabSelected(view, position, isOnclick);
                    } else {
                        listener.onTabReselected(view, position, isOnclick);
                    }
                }
            }
        }
    }

//    public void setTabBadge(int tabPosition, int badgeNum) {
//        getTabAt(tabPosition).getBadgeView().setBadgeNumber(badgeNum);
//    }
//
//    public void setTabBadge(int tabPosition, String badgeText) {
//        getTabAt(tabPosition).getBadgeView().setBadgeText(badgeText);
//    }

    public void setTabMode(int mode) {
        if (mode != TAB_MODE_FIXED && mode != TAB_MODE_SCROLLABLE) {
            throw new IllegalStateException("only support TAB_MODE_FIXED or TAB_MODE_SCROLLABLE");
        }
        if (mode == mTabMode) {
            return;
        }
        mTabMode = mode;
        for (int i = 0; i < mTabStrip.getChildCount(); i++) {
            View view = mTabStrip.getChildAt(i);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
            initTabWithMode(params);
            if (i == 0) {
                params.setMargins(0, 0, 0, 0);
            }
            view.setLayoutParams(params);
        }
        mTabStrip.invalidate();
        mTabStrip.post(new Runnable() {
            @Override
            public void run() {
                mTabStrip.updataIndicator();
            }
        });
    }

    /**
     * only in TAB_MODE_SCROLLABLE mode will be supported
     *
     * @param margin margin
     */
    public void setTabMargin(int margin) {
        if (margin == mTabMargin) {
            return;
        }
        mTabMargin = margin;
        if (mTabMode == TAB_MODE_FIXED) {
            return;
        }
        for (int i = 0; i < mTabStrip.getChildCount(); i++) {
            View view = mTabStrip.getChildAt(i);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
            params.setMargins(0, i == 0 ? 0 : mTabMargin, 0, 0);
            view.setLayoutParams(params);
        }
        mTabStrip.invalidate();
        mTabStrip.post(() -> mTabStrip.updataIndicator());
    }

    /**
     * only in TAB_MODE_SCROLLABLE mode will be supported
     *
     * @param height height
     */
    public void setTabHeight(int height) {
        if (height == mTabHeight) {
            return;
        }
        mTabHeight = height;
        if (mTabMode == TAB_MODE_FIXED) {
            return;
        }
        for (int i = 0; i < mTabStrip.getChildCount(); i++) {
            View view = mTabStrip.getChildAt(i);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
            params.height = mTabHeight;
            view.setLayoutParams(params);
        }
        mTabStrip.invalidate();
        mTabStrip.post(() -> mTabStrip.updataIndicator());
    }

    public void setIndicatorColor(int color) {
        mColorIndicator = color;
        mTabStrip.invalidate();
    }

    public void setIndicatorWidth(int width) {
        mIndicatorWidth = width;
        mTabStrip.setIndicatorGravity();
    }

    public void setIndicatorCorners(int corners) {
        mIndicatorCorners = corners;
        mTabStrip.invalidate();
    }

    /**
     * @param gravity only support Gravity.BANKER,Gravity.PLAYER,Gravity.FILL
     */
    public void setIndicatorGravity(int gravity) {
        if (gravity == Gravity.LEFT || gravity == Gravity.RIGHT || Gravity.FILL == gravity) {
            mIndicatorGravity = gravity;
            mTabStrip.setIndicatorGravity();
        } else {
            throw new IllegalStateException("only support Gravity.BANKER,Gravity.PLAYER,Gravity.FILL");
        }
    }

    public void addOnTabSelectedListener(OnTabSelectedListener listener) {
        if (listener != null) {
            mTabSelectedListeners.add(listener);
        }
    }

    public void removeOnTabSelectedListener(OnTabSelectedListener listener) {
        if (listener != null) {
            mTabSelectedListeners.remove(listener);
        }
    }

    public void setTabAdapter(TabAdapter adapter) {
        removeAllTabs();
        if (adapter != null) {
            mTabAdapter = adapter;
            for (int i = 0; i < adapter.getCount(); i++) {
                QTabView qTabView = new QTabView(mContext)
                        .setTitle(adapter.getTitle(i)).setBadge(adapter.getBadge(i))
                        .setIcon(adapter.getIcon(i))
                        .setBackground(adapter.getBackground(i));
                qTabView.setPadding(mTabPaddingLeft, mTabPaddingTop, 0, 0);
                addTab(qTabView);
            }
        }
    }

    public void setupWithFragment(FragmentManager manager, List<Fragment> fragments) {
        setupWithFragment(manager, 0, fragments);
    }

    public void setupWithFragment(FragmentManager manager, List<Fragment> fragments, TabAdapter adapter) {
        setupWithFragment(manager, 0, fragments, adapter);
    }

    public void setupWithFragment(FragmentManager manager, int containerResid, List<Fragment> fragments) {
        if (mTabFragmentManager != null) {
            mTabFragmentManager.detach();
        }
        if (containerResid != 0) {
            mTabFragmentManager = new TabFragmentManager(manager, containerResid, fragments, this);
        } else {
            mTabFragmentManager = new TabFragmentManager(manager, fragments, this);
        }
    }

    public void setupWithFragment(FragmentManager manager, int containerResid, List<Fragment> fragments, TabAdapter adapter) {
        setTabAdapter(adapter);
        setupWithFragment(manager, containerResid, fragments);
    }

    public void setupWithViewPager(@Nullable VerticalViewPager viewPager, OnTabSelectedListener tabSelectedListener) {
        if (mViewPager != null && mTabPageChangeListener != null) {
            mViewPager.removeOnPageChangeListener(mTabPageChangeListener);
        }

        if (viewPager != null) {
            final PagerAdapter adapter = viewPager.getAdapter();
            if (adapter == null) {
                throw new IllegalArgumentException("ViewPager does not have a PagerAdapter set");
            }

            mViewPager = viewPager;

            if (mTabPageChangeListener == null) {
                mTabPageChangeListener = new OnTabPageChangeListener();
            }
            viewPager.addOnPageChangeListener(mTabPageChangeListener);

            addOnTabSelectedListener(tabSelectedListener);

            setPagerAdapter(adapter, true);
        } else {
            mViewPager = null;
            setPagerAdapter(null, true);
        }
    }

    private void setPagerAdapter(@Nullable final PagerAdapter adapter, final boolean addObserver) {
        if (mPagerAdapter != null && mPagerAdapterObserver != null) {
            mPagerAdapter.unregisterDataSetObserver(mPagerAdapterObserver);
        }

        mPagerAdapter = adapter;

        if (addObserver && adapter != null) {
            if (mPagerAdapterObserver == null) {
                mPagerAdapterObserver = new PagerAdapterObserver();
            }
            adapter.registerDataSetObserver(mPagerAdapterObserver);
        }

        populateFromPagerAdapter();
    }

    private void populateFromPagerAdapter() {
        removeAllTabs();
        if (mPagerAdapter != null) {
            final int adapterCount = mPagerAdapter.getCount();
            if (mPagerAdapter instanceof TabAdapter) {
                setTabAdapter((TabAdapter) mPagerAdapter);
            } else {
                for (int i = 0; i < adapterCount; i++) {
                    String title = mPagerAdapter.getPageTitle(i) == null ? "tab" + i : mPagerAdapter.getPageTitle(i).toString();
                    addTab(new QTabView(mContext).setTitle(
                            new QTabView.TabTitle.Builder().setContent(title).build()));
                }
            }

            // Make sure we reflect the currently set ViewPager item
            if (mViewPager != null && adapterCount > 0) {
                final int curItem = mViewPager.getCurrentItem();
                if (curItem != getSelectedTabPosition() && curItem < getTabCount()) {
                    setTabSelected(false, curItem);
                }
            }
        } else {
            removeAllTabs();
        }
    }

    private class TabStrip extends LinearLayout {
        private float mIndicatorTopY;
        private float mIndicatorX;
        private float mIndicatorBottomY;
        private int mLastWidth;
        private Paint mIndicatorPaint;
        private RectF mIndicatorRect;
        private AnimatorSet mIndicatorAnimatorSet;

        public TabStrip(Context context) {
            super(context);
            setWillNotDraw(false);
            setOrientation(LinearLayout.VERTICAL);
            mIndicatorPaint = new Paint();
            mIndicatorPaint.setAntiAlias(true);
            mIndicatorGravity = mIndicatorGravity == 0 ? Gravity.LEFT : mIndicatorGravity;
            mIndicatorRect = new RectF();
            setIndicatorGravity();
        }

        protected void setIndicatorGravity() {
            if (mIndicatorGravity == Gravity.LEFT) {
                mIndicatorX = 0;
                if (mLastWidth != 0) {
                    mIndicatorWidth = mLastWidth;
                }
                setPadding(mIndicatorWidth, 0, 0, 0);
            } else if (mIndicatorGravity == Gravity.RIGHT) {
                if (mLastWidth != 0) {
                    mIndicatorWidth = mLastWidth;
                }
                setPadding(0, 0, mIndicatorWidth, 0);
            } else if (mIndicatorGravity == Gravity.FILL) {
                mIndicatorX = 0;
                setPadding(0, 0, 0, 0);
            }
            post(() -> {
                if (mIndicatorGravity == Gravity.RIGHT) {
                    mIndicatorX = getWidth() - mIndicatorWidth;
                } else if (mIndicatorGravity == Gravity.FILL) {
                    mLastWidth = mIndicatorWidth;
                    mIndicatorWidth = getWidth();
                }
                invalidate();
            });
        }

        private void calcIndicatorY(float offset) {
            int index = (int) Math.floor(offset);
            QTabView childView = (QTabView) getChildAt(index);
            /*下滑动时还原默认背景*/
//            childView.setChecked(offset - index == 0);
            if (Math.floor(offset) != getChildCount() - 1 && Math.ceil(offset) != 0) {
                QTabView nextView = (QTabView) getChildAt(index + 1);
                mIndicatorTopY = childView.getTop() + (nextView.getTop() - childView.getTop()) * (offset - index);
                mIndicatorBottomY = childView.getBottom() + (nextView.getBottom() -
                        childView.getBottom()) * (offset - index);
                /*上滑动时还原默认背景*/
//                nextView.setChecked(false);
            } else {
                mIndicatorTopY = childView.getTop();
                mIndicatorBottomY = childView.getBottom();
            }
        }

        protected void updataIndicator() {
            moveIndicatorWithAnimator(getSelectedTabPosition());
        }

        protected void moveIndicator(float offset) {
            calcIndicatorY(offset);
            invalidate();
        }

        /**
         * move indicator to a tab location
         *
         * @param index tab location's index
         */
        protected void moveIndicatorWithAnimator(int index) {
            final int direction = index - getSelectedTabPosition();
            View childView = getChildAt(index);
            final float targetTop = childView.getTop();
            final float targetBottom = childView.getBottom();
            if (mIndicatorTopY == targetTop && mIndicatorBottomY == targetBottom) {
                return;
            }
            if (mIndicatorAnimatorSet != null && mIndicatorAnimatorSet.isRunning()) {
                mIndicatorAnimatorSet.end();
            }
            post(() -> {
                ValueAnimator startAnime = null;
                ValueAnimator endAnime = null;
                if (direction > 0) {
                    startAnime = ValueAnimator.ofFloat(mIndicatorBottomY, targetBottom)
                            .setDuration(100);
                    startAnime.addUpdateListener(animation -> {
                        mIndicatorBottomY = Float.parseFloat(animation.getAnimatedValue().toString());
                        invalidate();
                    });
                    endAnime = ValueAnimator.ofFloat(mIndicatorTopY, targetTop).setDuration(100);
                    endAnime.addUpdateListener(animation -> {
                        mIndicatorTopY = Float.parseFloat(animation.getAnimatedValue().toString());
                        invalidate();
                    });
                } else if (direction < 0) {
                    startAnime = ValueAnimator.ofFloat(mIndicatorTopY, targetTop).setDuration(100);
                    startAnime.addUpdateListener(animation -> {
                        mIndicatorTopY = Float.parseFloat(animation.getAnimatedValue().toString());
                        invalidate();
                    });
                    endAnime = ValueAnimator.ofFloat(mIndicatorBottomY, targetBottom).setDuration(100);
                    endAnime.addUpdateListener(animation -> {
                        mIndicatorBottomY = Float.parseFloat(animation.getAnimatedValue().toString());
                        invalidate();
                    });
                }
                if (startAnime != null) {
                    mIndicatorAnimatorSet = new AnimatorSet();
                    mIndicatorAnimatorSet.play(endAnime).after(startAnime);
                    mIndicatorAnimatorSet.start();
                }
            });
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            mIndicatorPaint.setColor(mColorIndicator);
            mIndicatorRect.left = mIndicatorX;
            mIndicatorRect.top = mIndicatorTopY;
            mIndicatorRect.right = mIndicatorX + mIndicatorWidth;
            mIndicatorRect.bottom = mIndicatorBottomY;
            if (null != mIndicatorDrawable) {
                mIndicatorDrawable.setBounds((int) mIndicatorX, (int) mIndicatorTopY, (int) (mIndicatorX + mIndicatorWidth), (int) mIndicatorBottomY);
                mIndicatorDrawable.draw(canvas);
            } else {
                if (mIndicatorCorners != 0) {
                    canvas.drawRoundRect(mIndicatorRect, mIndicatorCorners, mIndicatorCorners, mIndicatorPaint);
                } else {
                    canvas.drawRect(mIndicatorRect, mIndicatorPaint);
                }
            }
        }

    }

    private class OnTabPageChangeListener implements VerticalViewPager.OnPageChangeListener {
        private int mPreviousScrollState;
        private int mScrollState;
        boolean mUpdataIndicator;

        public OnTabPageChangeListener() {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            mPreviousScrollState = mScrollState;
            mScrollState = state;
            mUpdataIndicator = !(mScrollState == SCROLL_STATE_SETTLING
                    && mPreviousScrollState == SCROLL_STATE_IDLE);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
            if (mUpdataIndicator) {
                mTabStrip.moveIndicator(positionOffset + position);
            }
        }

        @Override
        public void onPageSelected(int position) {
            if (position != getSelectedTabPosition()) {
                setTabSelected(false, position, !mUpdataIndicator, true);
            }
        }
    }

    private class PagerAdapterObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            populateFromPagerAdapter();
        }

        @Override
        public void onInvalidated() {
            populateFromPagerAdapter();
        }
    }

    public interface OnTabSelectedListener {

        void onTabSelected(TabView tab, int position, boolean isOnclick);

        void onTabReselected(TabView tab, int position, boolean isOnclick);
    }
}
