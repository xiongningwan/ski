package com.ski.box.view.view.dialog.update;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ski.box.R;
import com.ski.box.bean.UpdateBean;
import com.yb.core.utils.LanguageUtil;


public class UpdateDialog {
    private final Context mContext;
    private final BaseDownDialog mBaseDialog;
    private UpdateBean updateBean;
    TextView tvTitle;
    ScrollView slContent;
    TextView tvContent;
    TextView tvCancel;
    View viewSpace;
    TextView tvSure;

    public UpdateDialog(@NonNull Context context, View.OnClickListener listener) {
        mContext = context;
        mBaseDialog = new BaseDownDialog(context, R.style.progress_down_dialog);
        mBaseDialog.setContentView(R.layout.ski_dialog_update);
        mBaseDialog.setCancelable(false);
        mBaseDialog.setCanceledOnTouchOutside(false);
        tvTitle = mBaseDialog.findViewById(R.id.tv_title);
        slContent = mBaseDialog.findViewById(R.id.sl_content);
        tvContent = mBaseDialog.findViewById(R.id.tv_update_info);
        tvCancel = mBaseDialog.findViewById(R.id.tv_cancel);
        viewSpace = mBaseDialog.findViewById(R.id.view_space);
        tvSure = mBaseDialog.findViewById(R.id.tv_sure);
        tvSure.setOnClickListener(listener);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
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

    public void setUpdateBean(UpdateBean updateBean) {
        this.updateBean = updateBean;
        tvTitle.setText(LanguageUtil.getText("发现新版本"));
        if(TextUtils.isEmpty(updateBean.getIntroduction())) {
            slContent.setVisibility(View.GONE);
        }

        Spanned content;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            content = Html.fromHtml(updateBean.getIntroduction(), Html.FROM_HTML_MODE_LEGACY);
        } else {
            content = Html.fromHtml(updateBean.getIntroduction());
        }
        tvContent.setText(content);
        if (1 == updateBean.getIs_force()) {
            tvCancel.setVisibility(View.GONE);
            viewSpace.setVisibility(View.GONE);
        }
    }

    public UpdateBean getUpdateBean() {
        return updateBean;
    }
}
