package com.ski.box.view.view.dialog.group;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ski.box.R;
import com.ski.box.bean.group.InviteUrl;
import com.ski.box.view.view.dialog.BaseDialog;
import com.yb.core.utils.LanguageUtil;


public class InviteUrlDialog extends BaseDialog {
    private TextView mTvType;
    private TextView mTvNo;
    private TextView mTvMsg;
    private TextView mTvTime;
    private TextView mTvRebate;
    private TextView mTvClose;

    public InviteUrlDialog(@NonNull Context context) {
        super(context, R.style.progress_dialog);
        setContentView(R.layout.ski_dialog_group_invite_url);
        mTvType = findViewById(R.id.tv_value_type);
        mTvNo = findViewById(R.id.tv_value_uesr_no);
        mTvMsg = findViewById(R.id.tv_value_msg);
        mTvTime = findViewById(R.id.tv_value_time);
        mTvRebate = findViewById(R.id.tv_value_rebate);
        mTvClose = findViewById(R.id.tv_close);

        mTvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }


    public void setData(InviteUrl bean) {
        mTvType = findViewById(R.id.tv_value_type);
        mTvNo = findViewById(R.id.tv_value_uesr_no);
        mTvMsg = findViewById(R.id.tv_value_msg);
        mTvTime = findViewById(R.id.tv_value_time);
        mTvRebate = findViewById(R.id.tv_value_rebate);

        mTvType.setText(LanguageUtil.getText("代理"));
        mTvNo.setText(String.valueOf(bean.getCount()));
        mTvMsg.setText(bean.getWord());
        mTvTime.setText(bean.getCreateAt());
        if(TextUtils.isEmpty(bean.getKv())) {
            mTvRebate.setText(String.valueOf(bean.getRebate()));
        } else {
            mTvRebate.setText(bean.getKv());
        }

    }


}
