package com.ski.box.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.group.GroupMember;
import com.ski.box.bean.group.InviteUrl;
import com.ski.box.bean.group.RebateKV;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupManageAdapter extends BaseQuickAdapter<GroupMember, BaseViewHolder> {
    private Context mContext;
    private Map<Integer, String> mMap = new HashMap<>();

    public GroupManageAdapter(Context context) {
        super(R.layout.ski_item_group_manage);
        mContext = context;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, @Nullable GroupMember bean) {

        holder
                .setText(R.id.tv_name, bean.getMemberAccount())
                .setText(R.id.tv_balance, "余额  " + bean.getMemberAmt() + "元")
                .setText(R.id.tv_rabate, "奖金组 "+ bean.getMemberRebate())
                .setText(R.id.tv_group_balance, "团队余额  "+ bean.getTeamAmt()+ "元")
                .setText(R.id.tv_no, "下级人数 "+ bean.getTeamCount() + "人")
                .setText(R.id.tv_time,  "最后登录 " + bean.getLoginTime());
    }

    public void setReBateList(List<RebateKV> reBateList) {
        for (RebateKV bean : reBateList) {
            mMap.put(bean.getRebate(), bean.getRebate() + "-" + bean.getPercent());
        }
    }

}
