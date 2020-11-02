package com.ski.box.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.record.RecordBet;
import com.ski.box.bean.user.HeadBean;
import com.ski.box.bean.user.User;
import com.ski.box.utils.ActivityUtil;
import com.ski.box.utils.DecimalSetUtils;
import com.ski.box.view.activity.RecordDetailActivity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UpdateHeadAdapter extends BaseQuickAdapter<HeadBean, BaseViewHolder> {
    private  Context mContext;

    public UpdateHeadAdapter(Context context) {
        super(R.layout.ski_item_update_head);
        mContext = context;
        addChildClickViewIds(R.id.iv_head);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, @Nullable HeadBean bean) {
        ImageView ivHead = holder.getView(R.id.iv_head);
        ivHead.setImageResource(ActivityUtil.getHeadByProfile(bean.getProfile()));
        if(bean.isSelected()) {
            ivHead.setSelected(true);
        } else {
            ivHead.setSelected(false);
        }
    }


}
