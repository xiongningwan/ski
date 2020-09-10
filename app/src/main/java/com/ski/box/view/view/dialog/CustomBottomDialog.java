package com.ski.box.view.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.yb.core.utils.ScreenUtils;

public class CustomBottomDialog extends Dialog {
    private  int mHeight;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        // 在底部，宽度撑满
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM | Gravity.CENTER;//dialog从哪里弹出
//        params.width = screenWidth < screenHeight ? screenWidth : screenHeight;
        params.width = ScreenUtils.getScreenWidth(getContext());
        if (mHeight > 0) {
            params.height = mHeight;

        }
        getWindow().setAttributes(params);

    }



    public CustomBottomDialog(@NonNull Context context, int theme, int height) {
        super(context, theme);
        mHeight = height;
    }

    protected CustomBottomDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        //点击弹窗外部区域
        if (isOutOfBounds(getContext(), event)) {
            if (ontouchoutclicklistener != null) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_UP) {
                    ontouchoutclicklistener.onTouchOutside();
                    return true;
                }

            }

        }
        return super.onTouchEvent(event);
    }
    private boolean isOutOfBounds(Context context, MotionEvent event) {
        final int x = (int) event.getX();//相对弹窗左上角的x坐标
        final int y = (int) event.getY();//相对弹窗左上角的y坐标
        final int slop = ViewConfiguration.get(context).getScaledWindowTouchSlop();//最小识别距离
        final View decorView = getWindow().getDecorView();//弹窗的根View
        return (x < -slop) || (y < -slop) || (x > (decorView.getWidth() + slop))
                || (y > (decorView.getHeight() + slop));
    }

    public OnTouchOutClickListener ontouchoutclicklistener;
    public interface OnTouchOutClickListener{
       void onTouchOutside();
    }

    public void setOntouchoutclicklistener(OnTouchOutClickListener listener) {
        ontouchoutclicklistener = listener;
    }
}
