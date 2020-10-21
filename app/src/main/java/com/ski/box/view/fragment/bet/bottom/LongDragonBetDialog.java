package com.ski.box.view.fragment.bet.bottom;

import android.content.Context;

import androidx.annotation.NonNull;

import com.ski.box.bean.LongDragonPushInfoEntity;
import com.ski.box.view.view.LongDragonBetView;
import com.ski.box.view.view.dialog.StrongBottomSheetDialog;
import com.yb.core.utils.ScreenUtils;

/**
 * 快速投注
 */
public class LongDragonBetDialog extends StrongBottomSheetDialog {
    private int mDialogHeight;
    private LongDragonPushInfoEntity title;

    public LongDragonBetDialog(@NonNull Context context, LongDragonPushInfoEntity title) {
        super(context);
        this.title = title;
        mDialogHeight = ScreenUtils.getScreenHeight(context);
        createDialog(context);
    }

    private void createDialog(Context context) {
        LongDragonBetView quickBetView = new LongDragonBetView(context);
        quickBetView.setOnDismissListener(new LongDragonBetView.OnDismissListener() {
            @Override
            public void onDismiss() {
                dismiss();
            }
        });
        quickBetView.freshData(title);
        setMaxHeight(mDialogHeight);
        setPeekHeight(mDialogHeight);
        setContentView(quickBetView);
    }
}
