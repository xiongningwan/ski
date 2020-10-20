package com.ski.box.view.fragment.bet.bottom;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.ski.box.view.view.RoadDragonQuickBetView;
import com.ski.box.view.view.dialog.StrongBottomSheetDialog;
import com.yb.core.utils.ScreenUtils;

/**
 * 快速投注
 */
public class QuickBetDialog extends StrongBottomSheetDialog {
    private int mDialogHeight;
    private String title;
    private View mLBetConfirmView;

    public QuickBetDialog(@NonNull Context context, String title) {
        super(context);
        this.title = title;
        mDialogHeight = ScreenUtils.getScreenHeight(context);
        createDialog(context);
    }

    private void createDialog(Context context) {
        RoadDragonQuickBetView quickBetView = new RoadDragonQuickBetView(context);
        mLBetConfirmView = quickBetView.getBetConfirm();
        if (quickBetView.ivTouzhuDragonDown != null) {
            quickBetView.ivTouzhuDragonDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
        quickBetView.freshData(title);
        setMaxHeight(mDialogHeight);
        setPeekHeight(mDialogHeight);
        quickBetView.setCloseListener(new RoadDragonQuickBetView.OnCloseListener() {
            @Override
            public void closeDialog() {
                dismiss();
            }
        });
        setContentView(quickBetView);
    }

    public View getQuickBetView() {
        return mLBetConfirmView;
    }
}
