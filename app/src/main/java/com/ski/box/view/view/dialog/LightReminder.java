package com.ski.box.view.view.dialog;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;

import com.ski.box.R;
import com.ski.box.view.view.TriangleDrawable;
import com.yb.core.utils.ScreenUtils;

public class LightReminder extends PopupWindow {
    private View targeView;
    private int position = Gravity.TOP | Gravity.LEFT;
    private PopupWindow popupWindow;


    public LightReminder(View targeView, View layoutView) {
        this.targeView = targeView;
        configurationReminder(layoutView);
    }

    private void configurationReminder(View layoutView) {
          setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
          setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
          setContentView(layoutView);
           getContentView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                reminder(layoutView);
                dismiss();
                getContentView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

    }

    public LightReminder show() {
        showAtLocation(targeView,position,0,0);
        return this;
    }

    private void reminder(View layoutView) {
        int[] location1 = new int[2];
        targeView.getLocationInWindow(location1);
        int x = location1[0];
        int y = location1[1];
        int popuHeight = layoutView.getHeight();
        int popuWidth = layoutView.getWidth();


        ViewParent parent = layoutView.getParent();
        if(parent instanceof ViewGroup){
            ViewGroup viewgroup = (ViewGroup)parent;
            viewgroup.removeView(layoutView);
        }

        ConstraintLayout constraintLayout = layoutView.findViewById(R.id.constraint);
        View arrowView = layoutView.findViewById(R.id.v_arrow);
        if (arrowView == null || constraintLayout == null) {
            Toast.makeText(layoutView.getContext(),"layout id set error,please set view id to v_arry and constraint",Toast.LENGTH_SHORT).show();
            return;
        }

        arrowView.setVisibility(View.GONE);
        arrowView.setBackground(new TriangleDrawable(TriangleDrawable.BOTTOM, ContextCompat.getColor(targeView.getContext(), R.color.ski_color_694E85)));
         popupWindow = new PopupWindow();

        popupWindow.setOutsideTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(layoutView);

        int offsetX;
        int i1 = targeView.getWidth() / 2 + x;
        offsetX=i1 - popuWidth / 2;
        int offsetY = y - popuHeight;

        popupWindow.showAtLocation(targeView, position, offsetX, offsetY);
        arrowView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int[] ints1 = new int[2];
                arrowView.getLocationOnScreen(ints1);
                int arrowSmall = ints1[0];

                int[] ints = new int[2];
                targeView.getLocationOnScreen(ints);
                int anInt = ints[0];
                int i = anInt + targeView.getWidth() / 2 - arrowSmall;
                int i1 = arrowSmall + constraintLayout.getWidth() / 2;
                int i2 = i - i1;
                if (i2 < ScreenUtils.dp2px(10)) {
                    i = i - ScreenUtils.dp2px(10);
                }

                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(R.id.v_arrow, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, i);
                constraintSet.applyTo(constraintLayout);
                arrowView.setVisibility(View.VISIBLE);
                arrowView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    public void dismissReminder() {
        if (popupWindow != null) {
            popupWindow.dismiss();

        }

    }
}
