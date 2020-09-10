package com.ski.box.view.view.keyboard;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ski.box.R;
import com.ski.box.view.view.expandable.ExpandableLayout;
import com.yb.core.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class NumsKeyBoardView extends FrameLayout {

    private NumsKeyBoardAdapter numsKeyBoardAdapter;
    /*双面盘键盘配置*/
    ArrayList<KeyBoardBean> doubleKeys = new ArrayList<>();
    /*标注盘 单式键盘配置*/
    ArrayList<KeyBoardBean> standardSingleleKeys = new ArrayList<>();


    {
        for (int x = 0; x < 13; x++) {
            KeyBoardBean keyBoardBean = new KeyBoardBean();
            float dimension = getResources().getDimension(R.dimen.ski_dp_50);
            keyBoardBean.setHeight(dimension);
            keyBoardBean.setBackResource(R.mipmap.key_white);
            keyBoardBean.setSpanSize(1);
            if (x == 0) {
//                keyBoardBean.setBackDrawable( ContextCompat.getDrawable(getContext(),R.drawable.ski_select_nums_keyborad_normal));
                keyBoardBean.setType(KeyBoardBean.DISMISS);
                keyBoardBean.setIcon(R.mipmap.ski_keyboard_xiala);
                keyBoardBean.setSpanSize(3);
                keyBoardBean.setIconHeight((int) getResources().getDimension(R.dimen.ski_dp_10));
                keyBoardBean.setIconWidth((int) getResources().getDimension(R.dimen.ski_dp_20));
                float dimension40 = getResources().getDimension(R.dimen.ski_dp_40);
                keyBoardBean.setHeight(dimension40);
            }


            if (x > 0 && x < 10) {
                keyBoardBean.setNum(x + "");
                keyBoardBean.setType(KeyBoardBean.NUMBER);
                keyBoardBean.setTextSize(getResources().getDimension(R.dimen.ski_dp_10));

            }
            if (x == 10) {

//                Drawable drawable1 =  ContextCompat.getDrawable(getContext(),R.drawable.ski_select_nums_keyborad_huise);
//                keyBoardBean.setBackDrawable(drawable1);

                keyBoardBean.setBackResource(R.mipmap.key_hui);

            }
            if (x == 11) {
                keyBoardBean.setNum(0 + "");
                keyBoardBean.setType(KeyBoardBean.NUMBER);
                keyBoardBean.setTextSize(getResources().getDimension(R.dimen.ski_dp_10));
            }
            if (x == 12) {
                keyBoardBean.setType(KeyBoardBean.DELETE);
                keyBoardBean.setIcon(R.mipmap.ski_keyboard_delete);
             /*   Drawable drawable1 =  ContextCompat.getDrawable(getContext(),R.drawable.ski_select_nums_keyborad_huise);
                keyBoardBean.setBackDrawable(drawable1);*/
                keyBoardBean.setBackResource(R.mipmap.key_hui);
            }
            doubleKeys.add(keyBoardBean);
        }


        for (int x = 0; x < 13; x++) {
            KeyBoardBean keyBoardBean = new KeyBoardBean();
            float dimension = getResources().getDimension(R.dimen.ski_dp_50);
            keyBoardBean.setBackResource(R.mipmap.key_white);
            keyBoardBean.setHeight(dimension);
            keyBoardBean.setSpanSize(1);
            if (x == 0) {
//                keyBoardBean.setBackDrawable( ContextCompat.getDrawable(getContext(),R.drawable.ski_select_nums_keyborad_normal));
                keyBoardBean.setType(KeyBoardBean.DISMISS);
                keyBoardBean.setIcon(R.mipmap.ski_keyboard_xiala);
                keyBoardBean.setSpanSize(3);
                keyBoardBean.setIconHeight((int) getResources().getDimension(R.dimen.ski_dp_10));
                keyBoardBean.setIconWidth((int) getResources().getDimension(R.dimen.ski_dp_20));
                float dimension40 = getResources().getDimension(R.dimen.ski_dp_40);
                keyBoardBean.setHeight(dimension40);
            }


            if (x > 0 && x < 10) {
                keyBoardBean.setNum(x + "");
                keyBoardBean.setType(KeyBoardBean.NUMBER);
                keyBoardBean.setTextSize(getResources().getDimension(R.dimen.ski_dp_10));

            }
            if (x == 10) {
           /*     Drawable drawable1 =  ContextCompat.getDrawable(getContext(),R.drawable.ski_select_nums_keyborad_huise);
                keyBoardBean.setBackDrawable(drawable1);*/
                keyBoardBean.setBackResource(R.mipmap.key_hui);
                keyBoardBean.setNum("分隔符");
                keyBoardBean.setType(KeyBoardBean.DELIMITER);
            }
            if (x == 11) {
                keyBoardBean.setNum(0 + "");
                keyBoardBean.setType(KeyBoardBean.NUMBER);
                keyBoardBean.setTextSize(getResources().getDimension(R.dimen.ski_dp_10));
            }
            if (x == 12) {
                keyBoardBean.setType(KeyBoardBean.DELETE);
                keyBoardBean.setIcon(R.mipmap.ski_keyboard_delete);
         /*       Drawable drawable1 =  ContextCompat.getDrawable(getContext(),R.drawable.ski_select_nums_keyborad_huise);
                keyBoardBean.setBackDrawable(drawable1);*/
                keyBoardBean.setBackResource(R.mipmap.key_hui);
            }
            standardSingleleKeys.add(keyBoardBean);
        }
    }



    public interface State {
        int COLLAPSED = 0;
        int COLLAPSING = 1;
        int EXPANDING = 2;
        int EXPANDED = 3;
    }

    public interface KeyboardMode {
        /*双面盘键盘*/
        int KEYBOARD_NUMERAL_1 = 0;
        /*标准盘 单式投注 键盘*/
        int KEYBOARD_NUMERAL_2 = 1;
    }



    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    private static final int DEFAULT_DURATION = 300;

    private int duration = DEFAULT_DURATION;
    private float parallax;
    private float expansion;
    private int orientation;
    private int state;
    /*键盘类型*/
    private int model;
    private Interpolator interpolator = new FastOutSlowInInterpolator();
    private ValueAnimator animator;

    private OnExpansionUpdateListener listener;

    public NumsKeyBoardView(Context context) {
        this(context, null);
    }


    public NumsKeyBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayout(attrs);
    }

    public NumsKeyBoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(attrs);
    }

    private void initLayout(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ski_ExpandableLayout2);
            duration = a.getInt(R.styleable.ski_ExpandableLayout2_ski_el_duration, DEFAULT_DURATION);
            expansion = a.getBoolean(R.styleable.ski_ExpandableLayout2_ski_el_expanded, false) ? 1 : 0;
            orientation = a.getInt(R.styleable.ski_ExpandableLayout2_android_orientation, VERTICAL);
            parallax = a.getFloat(R.styleable.ski_ExpandableLayout2_ski_el_parallax, 1);
            a.recycle();

            state = expansion == 0 ? State.COLLAPSED : State.EXPANDED;
            setParallax(parallax);
        }

        View inflate = View.inflate(getContext(), R.layout.ski_betting_nums_keyboard, this);
        RecyclerView recyclerView = inflate.findViewById(R.id.recycle_keyboard_nums);
        numsKeyBoardAdapter = new NumsKeyBoardAdapter();
        recyclerView.setAdapter(numsKeyBoardAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        //添加Android自带的分割线
        recyclerView.addItemDecoration(new KeyboardItemDecoration(ScreenUtils.dip2px(7)));

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                KeyBoardBean keyBoardBean = numsKeyBoardAdapter.keyBoardBeans.get(position);
                return keyBoardBean.getSpanSize();
            }
        });
        initData(0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        int size = orientation == LinearLayout.HORIZONTAL ? width : height;

        setVisibility(expansion == 0 && size == 0 ? GONE : VISIBLE);

        int expansionDelta = size - Math.round(size * expansion);
        if (parallax > 0) {
            float parallaxDelta = expansionDelta * parallax;
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                if (orientation == HORIZONTAL) {
                    int direction = -1;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1 && getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
                        direction = 1;
                    }
                    child.setTranslationX(direction * parallaxDelta);
                } else {
                    child.setTranslationY(-parallaxDelta);
                }
            }
        }

        if (orientation == HORIZONTAL) {
            setMeasuredDimension(width - expansionDelta, height);
        } else {
            setMeasuredDimension(width, height - expansionDelta);
        }
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        if (animator != null) {
            animator.cancel();
        }
        super.onConfigurationChanged(newConfig);
    }

    /**
     * Get expansion state
     *
     * @return one of {@link ExpandableLayout.State}
     */
    public int getState() {
        return state;
    }

    public boolean isExpanded() {
        return state == State.EXPANDING || state == State.EXPANDED;
    }

    public void toggle() {
        toggle(true);
    }

    public void toggle(boolean animate) {
        if (isExpanded()) {
            collapse(animate);
        } else {
            expand(animate);
        }
    }

    public void expand() {
        expand(true);

    }

    public void expand(boolean animate) {
        setExpanded(true, animate);

    }

    public void collapse() {
        collapse(true);
    }

    public void collapse(boolean animate) {
        setExpanded(false, animate);
    }

    /**
     * Convenience method - same as calling setExpanded(expanded, true)
     */
    public void setExpanded(boolean expand) {
        setExpanded(expand, true);
    }

    public void setExpanded(boolean expand, boolean animate) {
        if (expand == isExpanded()) {
            return;
        }

        int targetExpansion = expand ? 1 : 0;
        if (animate) {
            animateSize(targetExpansion);
        } else {
            setExpansion(targetExpansion);
        }

        if (keyBoardChangeListener != null) {
            if (expand) {
                keyBoardChangeListener.open();
            } else {
                keyBoardChangeListener.close();
            }

        }

    }

    public int getDuration() {
        return duration;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getExpansion() {
        return expansion;
    }

    public void setExpansion(float exp) {
        if (this.expansion == exp) {
            return;
        }

        // Infer state from previous value
        float delta = exp - this.expansion;
        if (exp == 0) {
            state = State.COLLAPSED;
        } else if (exp == 1) {
            state = State.EXPANDED;
        } else if (delta < 0) {
            state = State.COLLAPSING;
        } else if (delta > 0) {
            state = State.EXPANDING;
        }


        setVisibility(state == NumsKeyBoardView.State.COLLAPSED ? GONE : VISIBLE);
        expansion = exp;
        requestLayout();

        if (listener != null) {
            listener.onExpansionUpdate(exp, state);
        }
    }

    public float getParallax() {
        return parallax;
    }

    public void setParallax(float parallax) {
        // Make sure parallax is between 0 and 1
        parallax = Math.min(1, Math.max(0, parallax));
        this.parallax = parallax;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        if (orientation < 0 || orientation > 1) {
            throw new IllegalArgumentException("Orientation must be either 0 (horizontal) or 1 (vertical)");
        }
        this.orientation = orientation;
    }

    public void setOnExpansionUpdateListener(OnExpansionUpdateListener listener) {
        this.listener = listener;
    }

    private void animateSize(int targetExpansion) {
        if (animator != null) {
            animator.cancel();
            animator = null;
        }

        animator = ValueAnimator.ofFloat(expansion, targetExpansion);
        animator.setInterpolator(interpolator);
        animator.setDuration(duration);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                setExpansion((float) valueAnimator.getAnimatedValue());
            }
        });

        animator.addListener(new ExpansionListener(targetExpansion));

        animator.start();
    }

    public interface OnExpansionUpdateListener {
        void onExpansionUpdate(float expansionFraction, int state);
    }

    private class ExpansionListener implements Animator.AnimatorListener {
        private int targetExpansion;
        private boolean canceled;

        public ExpansionListener(int targetExpansion) {
            this.targetExpansion = targetExpansion;
        }

        @Override
        public void onAnimationStart(Animator animation) {
            state = targetExpansion == 0 ? State.COLLAPSING : State.EXPANDING;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            if (!canceled) {
                state = targetExpansion == 0 ? State.COLLAPSED : State.EXPANDED;
                setExpansion(targetExpansion);
            }
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            canceled = true;
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
        }
    }

    public void initData(int mode) {
        if (KeyboardMode.KEYBOARD_NUMERAL_1 == mode) {
            numsKeyBoardAdapter.setButtonsType(doubleKeys);
        } else if (KeyboardMode.KEYBOARD_NUMERAL_2 == mode) {
            numsKeyBoardAdapter.setButtonsType(standardSingleleKeys);
        }
    }

    private class NumsKeyBoardAdapter extends RecyclerView.Adapter<NumsKeyBoardHolder> {

        public List<KeyBoardBean> keyBoardBeans = new ArrayList<>();

        @NonNull
        @Override
        public NumsKeyBoardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.ski_item_keyboard, parent, false);
            NumsKeyBoardHolder numsKeyBoardHolder = new NumsKeyBoardHolder(inflate);

            return numsKeyBoardHolder;
        }


        @Override
        public void onBindViewHolder(@NonNull NumsKeyBoardHolder holder, int position) {
            KeyBoardBean keyBoardBean = keyBoardBeans.get(position);
            holder.setData(keyBoardBean);
            holder.llKeyboard.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.OnItemClick(position, keyBoardBeans.get(position));
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return keyBoardBeans.size();
        }

        public void setButtonsType(List<KeyBoardBean> beans) {
            keyBoardBeans.clear();
            keyBoardBeans.addAll(beans);
            notifyDataSetChanged();
        }
    }

    private class NumsKeyBoardHolder extends RecyclerView.ViewHolder {
        TextView tvButton;
        RelativeLayout llKeyboard;

        public NumsKeyBoardHolder(@NonNull View itemView) {
            super(itemView);
            tvButton = itemView.findViewById(R.id.tv_item_keyboard);
            llKeyboard = itemView.findViewById(R.id.ll_keyboard);
        }

        public void setData(KeyBoardBean keyBoardBean) {
            int icon = keyBoardBean.getIcon();
            int iconWidth = keyBoardBean.getIconWidth();
            int iconHeight = keyBoardBean.getIconHeight();

            if (iconWidth != 0) {
                tvButton.setWidth(keyBoardBean.getIconWidth());
            }
            if (iconHeight != 0) {
                tvButton.setHeight(keyBoardBean.getIconHeight());
            }


            float height = keyBoardBean.getHeight();
            ViewGroup.LayoutParams layoutParams = llKeyboard.getLayoutParams();
            layoutParams.height = (int) height;
            llKeyboard.setLayoutParams(layoutParams);
            Drawable backDrawable = keyBoardBean.getBackDrawable();
            if (backDrawable != null) {
                llKeyboard.setBackground(backDrawable);
            }
            int backResource = keyBoardBean.getBackResource();
            if (backResource != 0) {
                llKeyboard.setBackgroundResource(backResource);

            }



            int adapterPosition = getAdapterPosition();


            /*此处有局限性*/
            RelativeLayout.LayoutParams relayParams = (RelativeLayout.LayoutParams) tvButton.getLayoutParams();
            if (adapterPosition == 0) {
                relayParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.CENTER_VERTICAL);
                relayParams.rightMargin = (int) getResources().getDimension(R.dimen.ski_dp_10);
                tvButton.setLayoutParams(relayParams);
            } else {
                relayParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                tvButton.setLayoutParams(relayParams);
            }


            tvButton.setBackgroundResource(icon);

            String num = keyBoardBean.getNum();
            tvButton.setText(num);
            float textSize = keyBoardBean.getTextSize();

            if (textSize > 0) {
                tvButton.setTextSize(textSize);
            }

        }
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void OnItemClick(int position, KeyBoardBean bean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public KeyBoardChangeListener keyBoardChangeListener;
    public interface KeyBoardChangeListener {
        void open();
        void close();
    }

    public void addOnKeyBoardChangeListener(KeyBoardChangeListener listener) {
        keyBoardChangeListener = listener;
    }
}
