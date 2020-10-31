package com.ski.box.adapter;

import android.content.Context;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.FrontTradeTypesBean;
import com.ski.box.bean.group.GroupMoneyData;
import com.ski.box.bean.money.MoneyProgressData;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;


public class MoneyProgressAdapter extends BaseQuickAdapter<MoneyProgressData.ListBean, BaseViewHolder> {
    private Context mContext;
    private HashMap<Integer, String> mMapType = new HashMap<>();
    private  int mGreen;
    private  int mRed;

    public MoneyProgressAdapter(Context context) {
        super(R.layout.ski_item_money_progress);
        mContext = context;
        mGreen = ContextCompat.getColor(context,R.color.ski_acc_lose);
        mRed = ContextCompat.getColor(context,R.color.ski_acc_win);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, @Nullable MoneyProgressData.ListBean bean) {
        holder.setText(R.id.tv_no, "金流编号: " + bean.getOrderId())
                .setText(R.id.tv_detail, bean.getDetail())
                .setText(R.id.tv_time, bean.getUpdateAt());
        TextView tvType = holder.getView(R.id.tv_type);
        String str = "";
        // 订单类型，1：充值，2：代充，3：提现
        if(1 == bean.getDwType()) {
            str = "充值";
        } else if(2 == bean.getDwType()) {
            str = "代充";
        } else if(3 == bean.getDwType()) {
            str = "提现";
        }
        tvType.setText(str);

        TextView tvStatus = holder.getView(R.id.tv_status);
        String statusStr = "";
        //状态，1：处理中（待审核），2：已完成（审核通过），3：失效（审核驳回）
        if(1 == bean.getDwType()) {
            statusStr = "待审核";
        } else if(2 == bean.getDwType()) {
            statusStr = "审核通过";
        } else if(3 == bean.getDwType()) {
            statusStr = "审核驳回";
        }
        tvStatus.setText(statusStr);

        TextView tvMoney = holder.getView(R.id.tv_money);
        tvMoney.setText(bean.getAmt() + "元");
        if(bean.getAmt() > 0) {
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
