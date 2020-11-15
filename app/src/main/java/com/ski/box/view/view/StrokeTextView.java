package com.ski.box.view.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;


import com.ski.box.R;

import java.util.Arrays;


public class StrokeTextView extends CusTextView {
    private static final int HORIZENTAL = 0;
    private static final int VERTICAL = 1;

    private int[] mGradientColor;
    private int mStrokeWidth;
    private int mStrokeColor = Color.BLACK;
    private LinearGradient mGradient;
    private boolean gradientChanged;
    private int mTextColor;
    private TextPaint mPaint;
    private int mGradientOrientation;


    public StrokeTextView(Context context) {
        super(context);
        init(context, null);
    }

    public StrokeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public StrokeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaint = getPaint();
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ski_StrokeTextView);
            mStrokeColor = a.getColor(R.styleable.ski_StrokeTextView_ski_strokeColor, Color.BLACK);
            mStrokeWidth = a.getDimensionPixelSize(R.styleable.ski_StrokeTextView_ski_strokeWidth, 0);
            mGradientOrientation = a.getInt(R.styleable.ski_StrokeTextView_ski_gradientOrientation, HORIZENTAL);

            setStrokeColor(mStrokeColor);
            setStrokeWidth(mStrokeWidth);
            setGradientOrientation(mGradientOrientation);
            a.recycle();
        }
    }

    public void setGradientOrientation(int orientation) {
        if (mGradientOrientation != orientation) {
            mGradientOrientation = orientation;
            gradientChanged = true;
            invalidate();
        }
    }

    public void setGradientColor(int[] gradientColor) {
        if (!Arrays.equals(gradientColor, mGradientColor)) {
            mGradientColor = gradientColor;
            gradientChanged = true;
            invalidate();
        }
    }

    public void setStrokeColor(int color) {
        if (mStrokeColor != color) {
            mStrokeColor = color;
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mStrokeWidth > 0) {
            /**
             * 绘制描边
             */
            mTextColor = getCurrentTextColor(); //保存文本的颜色
            mPaint.setStrokeWidth(mStrokeWidth); //设置描边宽度
            mPaint.setFakeBoldText(true); //设置粗体
            mPaint.setShadowLayer(mStrokeWidth, 0, 0, 0);
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            setColor(mStrokeColor); // 设置描边颜色
            mPaint.setShader(null);  //清空shader
            super.onDraw(canvas);
            /**
             * 绘制文本
             */
            if (gradientChanged) { //如果有渐变颜色，则设置LinearGradient
                if (mGradientColor != null) {
                    mGradient = getGradient();
                }
                gradientChanged = false;
            }
            if (mGradient != null) { //设置渐变Shader
                mPaint.setShader(mGradient);
                mPaint.setColor(Color.WHITE);
            } else {
                setColor(mTextColor);
            }

            mPaint.setStrokeWidth(0);
//            mPaint.setFakeBoldText(false);
            mPaint.setShadowLayer(0, 0, 0, 0);
            super.onDraw(canvas);
        } else {
            super.onDraw(canvas);
        }
    }

    public void setStrokeWidth(int width) {
        mStrokeWidth = width;
        invalidate();
    }

    private LinearGradient getGradient() {
        LinearGradient gradient;
        if (mGradientOrientation == HORIZENTAL) {
            gradient = new LinearGradient(0, 0, getWidth(), 0, mGradientColor, null, Shader.TileMode.CLAMP);
        } else {
            gradient = new LinearGradient(0, 0, 0, getHeight(), mGradientColor, null, Shader.TileMode.CLAMP);
        }
        return gradient;
    }

    private void setColor(int color) {
//        Field textColorField;
//        try {
//            textColorField = TextView.class.getDeclaredField("mCurTextColor");
//            textColorField.setAccessible(true);
//            textColorField.set(this, color);
//            textColorField.setAccessible(false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        setTextColor(color);
        mPaint.setColor(color);
    }
}