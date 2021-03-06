package com.ski.box.adapter;

import android.content.Context;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.FrontTradeTypesBean;
import com.ski.box.bean.group.GroupMoneyData;
import com.ski.box.bean.record.RecordMoney;
import com.ski.box.utils.ActivityUtil;
import com.yb.core.utils.LanguageUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;


public class GroupRecordMoneyAdapter extends BaseQuickAdapter<GroupMoneyData.ListBean, BaseViewHolder> {
    private Context mContext;
    private HashMap<Integer, String> mMapType = new HashMap<>();
    private int mGreen;
    private int mRed;

    public GroupRecordMoneyAdapter(Context context) {
        super(R.layout.ski_item_money_group_record);
        mContext = context;
        mGreen = ContextCompat.getColor(context, R.color.ski_acc_lose);
        mRed = ContextCompat.getColor(context, R.color.ski_acc_win);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, @Nullable GroupMoneyData.ListBean bean) {
        holder.setText(R.id.tv_no, LanguageUtil.getText("订单编号") + ": " + bean.getTransId())
                .setText(R.id.tv_time, bean.getTransTime())
                .setText(R.id.tv_user_name, bean.getMemberAccount())
                .setText(R.id.tv_name, bean.getTicketName())
                .setText(R.id.tv_type, mMapType.get(bean.getTransType()))
                .setText(R.id.tv_before, LanguageUtil.getText("账变前余额") + "  " + ActivityUtil.formatBonus2(bean.getBeforeAmt().doubleValue()))
                .setText(R.id.tv_after, LanguageUtil.getText("账变后余额") + "  " + ActivityUtil.formatBonus2(bean.getAfterAmt().doubleValue()) );
        TextView tvMoney = holder.getView(R.id.tv_money);
        tvMoney.setText(ActivityUtil.formatBonus2(bean.getTransAmt()));
        if (bean.getTransAmt() > 0) {
            tvMoney.setTextColor(mRed);
        } else {
            tvMoney.setTextColor(mGreen);
        }
    }


    public void setMoneyType(List<FrontTradeTypesBean> typeList) {
        for (int i = 0; i < typeList.size(); i++) {
            FrontTradeTypesBean frontTradeTypesBean = typeList.get(i);
            mMapType.put(frontTradeTypesBean.getTradeType(), frontTradeTypesBean.getName());
        }
    }
}
