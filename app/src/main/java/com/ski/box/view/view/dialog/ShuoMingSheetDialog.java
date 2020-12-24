package com.ski.box.view.view.dialog;

import android.content.Context;
import android.view.Window;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.ski.box.bean.ShuoMingDoubleBean;
import com.ski.box.view.view.ShuoMingDoubleView;
import com.yb.core.utils.ScreenUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ShuoMingSheetDialog extends StrongBottomSheetDialog {

    private final Context mContext;
    private int mPeekHeight;
    private int mMaxHeight;
    private boolean mCreated;
    private Window mWindow;
    private BottomSheetBehavior mBottomSheetBehavior;
    ShuoMingDoubleView mPlayDescripView;
    public ShuoMingSheetDialog(@NonNull @NotNull Context context) {
        super(context);
        mContext = context;
        createView(context);
    }

    private void createView(Context context) {
//        mBottomSheetBehavior = getBehavior();
//        mBottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                if (newState == BottomSheetBehavior.STATE_DRAGGING) {//判断为向下拖动行为时，则强制设定状态为展开
//                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED );
//                }
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//
//            }
//        });
//        int height =  ScreenUtils.dip2px(740);
        int height =  ScreenUtils.getScreenHeight(context);
        setPeekHeight(height);
        setMaxHeight(height);
        mBottomSheetBehavior = getBehavior();
         mPlayDescripView = new ShuoMingDoubleView(getContext());
        setContentView(mPlayDescripView);
        mPlayDescripView.setBottomFramentDailog(this);
    }

    public void refreshShuoMingData(List<ShuoMingDoubleBean.DescriptionBean> datas, String curTicketName, String curPlayName) {
        mPlayDescripView.refreshShuoMingData(datas, curTicketName, curPlayName);
    }

}