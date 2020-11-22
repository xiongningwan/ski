package com.ski.box.view.view.dialog.update;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ski.box.R;
import com.yb.core.utils.LanguageUtil;


public class DownDialog {
    private final Context mContext;
    private final BaseDownDialog mBaseDialog;
    private TextView tvTitle;
    private ProgressBar pbDown;
    private TextView tvSize;
    private TextView tvPercent;
    private LinearLayout llbtn;
    private TextView tvCancel;
    private View viewSpace;
    private TextView tvSure;

    public DownDialog(@NonNull Context context) {
        mContext = context;
        mBaseDialog = new BaseDownDialog(context, R.style.progress_down_dialog);
        mBaseDialog.setContentView(R.layout.ski_dialog_down);
        mBaseDialog.setCancelable(false);
        mBaseDialog.setCanceledOnTouchOutside(false);

        tvTitle = mBaseDialog.findViewById(R.id.tv_title);
        pbDown = mBaseDialog.findViewById(R.id.pb_down);
        tvSize = mBaseDialog.findViewById(R.id.tv_size);
        tvPercent = mBaseDialog.findViewById(R.id.tv_percent);
        llbtn = mBaseDialog.findViewById(R.id.ll_btn);
        tvCancel = mBaseDialog.findViewById(R.id.tv_cancel);
        viewSpace = mBaseDialog.findViewById(R.id.view_space);
        tvSure = mBaseDialog.findViewById(R.id.tv_sure);
    }


    public void show() {
        if (mBaseDialog != null && !mBaseDialog.isShowing()) {
            mBaseDialog.show();
        }
    }

    public void dismiss() {
        if (mBaseDialog != null && mBaseDialog.isShowing()) {
            mBaseDialog.dismiss();
        }
    }

    public void setProgress(float progress, long total) {
        int progressInt = Math.round(progress * 100);
        pbDown.setProgress(progressInt);
        tvPercent.setText(progressInt + "%");
        long currentDown = (long) (total * progress);
        String currentDownStr = byte2FitMemorySize(currentDown);
        String totalStr = byte2FitMemorySize(total);
        // 8M/20M
        tvSize.setText(currentDownStr + "/" + totalStr);
//        tvSize.setText();
        if(100 == progressInt) {
            dismiss();
        }
    }

    private static String byte2FitMemorySize(final long byteNum) {
        if (byteNum <= 0) {
            return "";
        } else if (byteNum < 1024) {
            return String.format("%.1fB", (double) byteNum);
        } else if (byteNum < 1048576) {
            return String.format("%.1fKB", (double) byteNum / 1024);
        } else if (byteNum < 1073741824) {
            return String.format("%.1fMB", (double) byteNum / 1048576);
        } else {
            return String.format("%.1fGB", (double) byteNum / 1073741824);
        }
    }

    public void downloadError(View.OnClickListener listenerCancel, View.OnClickListener listener) {
        tvTitle.setText(LanguageUtil.getText("下载失败"));
        pbDown.setVisibility(View.GONE);
        tvSize.setVisibility(View.GONE);
        tvPercent.setVisibility(View.GONE);
        llbtn.setVisibility(View.VISIBLE);
        tvCancel.setOnClickListener(listenerCancel);
        tvSure.setOnClickListener(listener);
    }

    public void setDownReadyStatus() {
        tvTitle.setText(LanguageUtil.getText("更新中"));
        pbDown.setVisibility(View.VISIBLE);
        tvSize.setVisibility(View.VISIBLE);
        tvPercent.setVisibility(View.VISIBLE);
        llbtn.setVisibility(View.GONE);
    }
}
