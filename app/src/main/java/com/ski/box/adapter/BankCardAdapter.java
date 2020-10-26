package com.ski.box.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.user.BankCard;

import org.jetbrains.annotations.NotNull;

/**
 * Created by tom on 2020/10/26.
 */
public class BankCardAdapter extends BaseQuickAdapter<BankCard, BaseViewHolder> {
    public BankCardAdapter() {
        super(R.layout.ski_item_bank_card_list);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, BankCard bean) {
        TextView name = holder.getView(R.id.tv_bank_name);
        TextView no = holder.getView(R.id.tv_bank_no);
        TextView owner = holder.getView(R.id.tv_value_bank_owner);
        TextView time = holder.getView(R.id.tv_value_bank_time);
        name.setText(bean.getBankName());
        no.setText(bean.getCardNo());
        owner.setText(bean.getCardName());
       // time.setText(bean.getCreateAt());
    }


}
