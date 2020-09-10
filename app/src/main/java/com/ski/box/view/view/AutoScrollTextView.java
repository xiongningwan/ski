package com.ski.box.view.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.WindowManager;

import androidx.appcompat.widget.AppCompatTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Doms
 */
public class AutoScrollTextView extends AppCompatTextView {
    /**
     * 文本长度
     */
    private float textLength = 0f;
    private float viewWidth = 0f;
    /**
     * 文字的横坐标
     */
    private float step = 0f;
    /**
     * 文字的纵坐标
     */
    private float y = 0f;
    /**
     * 用于计算的临时变量
     */
    private float tempViewPlusTextLength = 0.0f;
    /**
     * 用于计算的临时变量
     */
    private float tempViewPlusTwoTextLength = 0.0f;
    /**
     * 是否开始滚动
     */
    public boolean isStarting = false;
    /**
     * 绘图样式
     */
    private Paint paint = null;
    /**
     * 文本内容
     */
    private String text = "";

    private int position;
    private List<String> datas = new ArrayList<>();

    public AutoScrollTextView(Context context) {
        super(context);
        initView();
    }

    public AutoScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AutoScrollTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        init();
    }

    /**
     * 文本初始化，每次更改文本内容或者文本效果等之后都需要重新初始化一下
     */
    public void init(WindowManager windowManager) {
        paint = getPaint();
        text = getText().toString();
        paint.setColor(Color.parseColor("#343434"));

        paint.setTextSize(getTextSize());
        textLength = paint.measureText(text);
        viewWidth = getWidth();
        if (viewWidth == 0) {
            if (windowManager != null) {
                Display display = windowManager.getDefaultDisplay();
                viewWidth = display.getWidth();
            } else {
                Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
                viewWidth = display.getWidth();
            }
        }
        step = textLength;
        tempViewPlusTextLength = viewWidth + textLength;
        tempViewPlusTwoTextLength = viewWidth + textLength * 2;
        y = getTextSize() + getPaddingTop();
    }

    public void init() {
        init(null);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);

        ss.step = step;
        ss.isStarting = isStarting;

        return ss;

    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
        if (state instanceof SavedState) {
            SavedState ss = (SavedState) state;
            step = ss.step;
            isStarting = ss.isStarting;
        }
    }

    public static class SavedState extends BaseSavedState {
        public boolean isStarting = false;
        public float step = 0.0f;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeByte(isStarting ? (byte) 1 : (byte) 0);
            out.writeFloat(step);
        }

        //必须
        @Override
        public int describeContents() {
            return 0;
        }
        //必须
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

    /**
     * 开始滚动
     */
    public void startScroll() {
        isStarting = true;
        invalidate();
    }

    /**
     * 停止滚动
     */
    public void stopScroll() {
        isStarting = false;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawText(text, tempViewPlusTextLength - step, y, paint);
        if (!isStarting) {
            return;
        }
        step += 3.5;
        if (step > tempViewPlusTwoTextLength) {
            step = textLength;
            if (datas.size() > 0) {
                position = position + 1;
                if (position >= datas.size()) {
                    position = 0;
                }
                setText(datas.get(position));
                init(null);
            }
        }
        invalidate();

    }

    public void setList(List<String> datas) {
        position = 0;
        this.datas.clear();
        this.datas.addAll(datas);
        if (datas.size() > 0) {
            setText(datas.get(position));
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                init(null);
                startScroll();
            }
        }, 2000);

    }

    public boolean hasList() {
        return datas.size() > 0;
    }

    public int getPosition() {
        return position;
    }
}
