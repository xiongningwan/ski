package com.ski.box.view.view.dialog.group;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ski.box.R;
import com.ski.box.view.view.dialog.BaseDialog;
import com.ski.box.view.view.dialog.CancelDialog;


public class DeleteDialog  extends BaseDialog {
    private TextView tvSure;
    private TextView tvCancel;
    private TextView tvContent;

    public DeleteDialog(@NonNull Context context, View.OnClickListener listener) {
        super(context, R.style.progress_dialog);
        setContentView(R.layout.ski_dialog_group_delete);

        tvContent = findViewById(R.id.txt_tips);
        tvSure = findViewById(R.id.tv_sure);
        tvCancel = findViewById(R.id.tv_cancel);

        tvSure.setOnClickListener(listener);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }



    public void setContent(String content) {
        tvContent.setText(content);
    }


}
