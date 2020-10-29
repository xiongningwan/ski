package com.ski.box.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.group.InviteUrl;
import com.ski.box.bean.record.RecordBet;
import com.ski.box.utils.DecimalSetUtils;
import com.ski.box.view.view.LotteryRecordResultView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GroupInviteUrlAdapter extends BaseQuickAdapter<InviteUrl, BaseViewHolder> {
    private  Context mContext;

    public GroupInviteUrlAdapter(Context context) {
        super(R.layout.ski_item_group_invite_url);
        mContext = context;
        addChildClickViewIds(R.id.iv_detail,R.id.iv_copy,R.id.iv_delete);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, @Nullable InviteUrl bean) {
      //  float f = (i - rebateScope.getBaseRebate())*100f / 2000;
       // String percent = String.format("%.2f", f) + "%";

        holder.setText(R.id.tv_rebate, String.valueOf(bean.getRebate()));
        holder.setText(R.id.tv_time, bean.getCreateAt());
    }

}
