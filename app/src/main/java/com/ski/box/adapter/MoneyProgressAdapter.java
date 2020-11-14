package com.ski.box.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.FrontTradeTypesBean;
import com.ski.box.bean.group.GroupMoneyData;
import com.ski.box.bean.money.MoneyProgressData;
import com.ski.box.utils.ActivityUtil;
import com.yb.core.utils.LanguageUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;


public class MoneyProgressAdapter extends BaseQuickAdapter<MoneyProgressData.ListBean, BaseViewHolder> {
    private Context mContext;
    private HashMap<Integer, String> mMapType = new HashMap<>();
    private  int mGreen;
    private  int mRed;
    private  int mGray;

    public MoneyProgressAdapter(Context context) {
        super(R.layout.ski_item_money_progress);
        mContext = context;
        mGreen = ContextCompat.getColor(context,R.color.ski_acc_lose);
        mRed = ContextCompat.getColor(context,R.color.ski_acc_win);
        mGray = ContextCompat.getColor(context,R.color.ski_color_333333);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, @Nullable MoneyProgressData.ListBean bean) {
        holder.setText(R.id.tv_no, LanguageUtil.getText("金流编号") +": " + bean.getOrderId())
                .setText(R.id.tv_detail, bean.getDetail())
                .setText(R.id.tv_time, bean.getUpdateAt());
        TextView tvType = holder.getView(R.id.tv_type);
        String str = "";
        // 订单类型，1：充值，2：代充，3：提现
        if(1 == bean.getDwType()) {
            str = LanguageUtil.getText("充值");
        } else if(2 == bean.getDwType()) {
            str = LanguageUtil.getText("代充");
        } else if(3 == bean.getDwType()) {
            str = LanguageUtil.getText("提现");
        }
        tvType.setText(str);

        TextView tvStatus = holder.getView(R.id.tv_status);
        String statusStr = "";
        //状态，1：处理中（待审核），2：已完成（审核通过），3：失效（审核驳回）
        if(1 == bean.getStatus()) {
            statusStr = LanguageUtil.getText("待审核");
            tvStatus.setTextColor(mGray);
        } else if(2 == bean.getStatus()) {
            statusStr = LanguageUtil.getText("审核通过");
            tvStatus.setTextColor(mGreen);
        } else if(3 == bean.getStatus()) {
            statusStr = LanguageUtil.getText("审核驳回");
            tvStatus.setTextColor(mRed);
        }
        tvStatus.setText(statusStr);

        TextView tvMoney = holder.getView(R.id.tv_money);
        tvMoney.setText(ActivityUtil.formatBonus(bean.getAmt().doubleValue()));
//        if(bean.getAmt() > 0) {
//            tvMoney.setTextColor(mRed);
//        } else {
//            tvMoney.setTextColor(mGreen);
//        }
        TextView tvRemark = holder.getView(R.id.tv_remark);
        tvRemark.setText(LanguageUtil.getText("备注") + "："+ bean.getRemark());
        if(TextUtils.isEmpty(bean.getRemark())) {
            tvRemark.setVisibility(View.GONE);
        } else {
            tvRemark.setVisibility(View.VISIBLE);
        }
    }


    public void setMoneyType(List<FrontTradeTypesBean> typeList) {
        for (int i = 0; i < typeList.size(); i++) {
            FrontTradeTypesBean frontTradeTypesBean = typeList.get(i);
            mMapType.put(frontTradeTypesBean.getTradeType(), frontTradeTypesBean.getName());
        }
    }
}
