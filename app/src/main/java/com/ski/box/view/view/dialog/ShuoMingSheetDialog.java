package com.ski.box.view.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.ski.box.R;
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
        int height =  ScreenUtils.getScreenHeight(context);
         mPlayDescripView = new ShuoMingDoubleView(getContext());
        setContentView(mPlayDescripView);
        mPlayDescripView.setBottomFramentDailog(this);
    }

    public void refreshShuoMingData(List<ShuoMingDoubleBean.DescriptionBean> datas, String curTicketName, String curPlayName) {
        mPlayDescripView.refreshShuoMingData(datas, curTicketName, curPlayName);
    }

}