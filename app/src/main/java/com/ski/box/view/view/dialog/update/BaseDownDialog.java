package com.ski.box.view.view.dialog.update;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class BaseDownDialog extends Dialog{
    public BaseDownDialog(@NonNull Context context) {
        super(context);
        setParams();
    }

    public BaseDownDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setParams();
    }

    protected BaseDownDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        setParams();
    }

    void setParams() {
        Window win = getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
    //    win.setGravity(Gravity.BOTTOM);

        win.setAttributes(lp);
    }
}
