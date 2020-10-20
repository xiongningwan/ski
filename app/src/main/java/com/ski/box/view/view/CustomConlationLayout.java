package com.ski.box.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.constraintlayout.widget.ConstraintLayout;

public class CustomConlationLayout extends ConstraintLayout {
    private float lastY;
    private float currY;

    public CustomConlationLayout(Context context) {
        super(context);
    }

    public CustomConlationLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomConlationLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        Log.d("aaaaa", "onInterceptTouchEvent: ev="+ev.getAction());
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_DOWN:
                float y = ev.getY();
                float x = ev.getX();
                if (onTouchYListener != null) {
                    onTouchYListener.touchY(x,y);
                }
                break;
            case MotionEvent.ACTION_UP:


                break;
        }

        return super.onInterceptTouchEvent(ev);
    }


    OnTouchYListener onTouchYListener;

    public interface OnTouchYListener {
        void touchY(float x, float y);
    }

    public void addOnTouchListener(OnTouchYListener listener) {
        onTouchYListener = listener;
    }
}
