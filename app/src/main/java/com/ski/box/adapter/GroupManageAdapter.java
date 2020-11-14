package com.ski.box.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.group.GroupMember;
import com.ski.box.bean.group.InviteUrl;
import com.ski.box.bean.group.RebateKV;
import com.ski.box.utils.ActivityUtil;
import com.yb.core.utils.LanguageUtil;

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
                .setText(R.id.tv_balance, LanguageUtil.getText("余额") + "  " + ActivityUtil.formatBonus(bean.getMemberAmt().doubleValue()))
                .setText(R.id.tv_rabate, LanguageUtil.getText("奖金组") + " " + bean.getMemberRebate())
                .setText(R.id.tv_group_balance, LanguageUtil.getText("团队余额") + "  " + ActivityUtil.formatBonus(bean.getTeamAmt().doubleValue()))
                .setText(R.id.tv_no, LanguageUtil.getText("下级人数") + " " + bean.getTeamCount())
                .setText(R.id.tv_time, LanguageUtil.getText("最后登录") + " " + bean.getLoginTime());
    }

    public void setReBateList(List<RebateKV> reBateList) {
        for (RebateKV bean : reBateList) {
            mMap.put(bean.getRebate(), bean.getRebate() + "-" + bean.getPercent());
        }
    }

}
