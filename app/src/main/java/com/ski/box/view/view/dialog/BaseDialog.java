package com.ski.box.view.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class BaseDialog extends Dialog{
    public BaseDialog(@NonNull Context context) {
        super(context);
        setParams();
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setParams();
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        setParams();
    }

    void setParams() {
        Window win = getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
    //    win.setGravity(Gravity.BOTTOM);

        win.setAttributes(lp);
    }
}
