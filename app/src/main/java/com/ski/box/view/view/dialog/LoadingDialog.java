package com.ski.box.view.view.dialog;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ski.box.R;


public class LoadingDialog extends BaseDialog {
    private ProgressBar pbLoading;
    private ImageView ivSuccess;
    private TextView tvMsg;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.progress_dialog);
        setContentView(R.layout.ski_loading);
        pbLoading = findViewById(R.id.progress_loading);
        ivSuccess = findViewById(R.id.iv_success);
        tvMsg = findViewById(R.id.id_tv_loadingmsg);
    }


    public void setLoading(String msg) {
        pbLoading.setVisibility(View.VISIBLE);
        ivSuccess.setVisibility(View.GONE);
        tvMsg.setText(msg);
    }

    public void setReloading(String msg) {
        pbLoading.setVisibility(View.VISIBLE);
        ivSuccess.setVisibility(View.GONE);
        tvMsg.setText(msg);
    }

    public void setLoadingSuccess(String msg) {
        pbLoading.setVisibility(View.GONE);
        ivSuccess.setVisibility(View.VISIBLE);
        tvMsg.setText(msg);
    }


}
