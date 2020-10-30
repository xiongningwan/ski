package com.ski.box.adapter;

import android.content.Context;
import android.util.ArrayMap;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.group.InviteUrl;
import com.ski.box.bean.group.RebateKV;
import com.ski.box.bean.record.RecordBet;
import com.ski.box.utils.DecimalSetUtils;
import com.ski.box.view.view.LotteryRecordResultView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupInviteUrlAdapter extends BaseQuickAdapter<InviteUrl, BaseViewHolder> {
    private Context mContext;
    private Map<Integer, String> mMap = new HashMap<>();

    public GroupInviteUrlAdapter(Context context) {
        super(R.layout.ski_item_group_invite_url);
        mContext = context;
        addChildClickViewIds(R.id.iv_detail, R.id.iv_copy, R.id.iv_delete);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, @Nullable InviteUrl bean) {
        //  float f = (i - rebateScope.getBaseRebate())*100f / 2000;
        // String percent = String.format("%.2f", f) + "%";

        String kv = String.valueOf(mMap.get(bean.getRebate()));
        bean.setKv(kv);
        holder.setText(R.id.tv_rebate, kv);
        holder.setText(R.id.tv_time, bean.getCreateAt());
    }

    public void setReBateList(List<RebateKV> reBateList) {
        for (RebateKV bean : reBateList) {
            mMap.put(bean.getRebate(), bean.getRebate() + "-" + bean.getPercent());
        }
    }

}
