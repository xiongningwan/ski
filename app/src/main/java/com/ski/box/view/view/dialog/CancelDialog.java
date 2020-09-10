package com.ski.box.view.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ski.box.R;


/**
 * 撤单的弹框
 */
public class CancelDialog extends Dialog {
    private Activity mActivity;
    private OnClickconfirmListener listener;
    private TextView tvCancle;
    private TextView tvCheDan;
    private TextView txt_tips;


    public CancelDialog(Activity context, OnClickconfirmListener l) {
        super(context, R.style.dialog_with_alpha);
        this.listener = l;
        mActivity = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ski_dialog_tips);
        DisplayMetrics metric = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = metric.widthPixels - 300;
        Window window = getWindow();
        window.setAttributes(p);
        window.setGravity(Gravity.CENTER);
        setCancelable(true);
        this.setCanceledOnTouchOutside(true);
        txt_tips = findViewById(R.id.txt_tips);
        tvCancle = findViewById(R.id.tvCancle);
        tvCheDan = findViewById(R.id.tvCheDan);

        tvCancle.setOnClickListener(v -> {
            listener.cancel();
            dismiss();
        });
        tvCheDan.setOnClickListener(v -> {
            listener.confirm();
            dismiss();
        });
    }

    public void setContent(String content) {
        txt_tips.setText(content);
    }

    public void setTxtCancleHide() {
        tvCheDan.setVisibility(View.GONE);
    }

    public interface OnClickconfirmListener {
         void confirm();

         void cancel();
    }
}
