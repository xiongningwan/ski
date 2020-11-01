package com.ski.box.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.user.BankCard;

import org.jetbrains.annotations.NotNull;

/**
 * Created by tom on 2020/10/26.
 */
public class BankCardAdapter extends BaseMultiItemQuickAdapter<BankCard, BaseViewHolder> {
    public BankCardAdapter() {
        addItemType(0, R.layout.ski_item_bank_card_list);
        addItemType(1, R.layout.ski_layout_bank_card_list_footer);
        addChildClickViewIds(R.id.btn_sure);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, BankCard bean) {
        // time.setText(bean.getCreateAt());
        switch (holder.getItemViewType()) {
            case 0:
                TextView name = holder.getView(R.id.tv_bank_name);
                TextView no = holder.getView(R.id.tv_bank_no);
                TextView owner = holder.getView(R.id.tv_value_bank_owner);
                TextView time = holder.getView(R.id.tv_value_bank_time);
                name.setText(bean.getBankName());
                no.setText(bean.getCardNo());
                owner.setText(bean.getCardName());
                break;
            case 1:
                TextView mTvTip2 = holder.getView(R.id.tv_tip_2);
                TextView mTvTip4 = holder.getView(R.id.tv_tip_4);
                setRedTip(getContext(), mTvTip2, mTvTip4);
                break;
        }
    }

    private void setRedTip(Context context, TextView mTvTip2, TextView mTvTip4) {
        String tip2 = context.getString(R.string.ski_my_bank_card_tip_2);
        if (!TextUtils.isEmpty(tip2) && tip2.contains("5")) {
            int index = tip2.indexOf("5");
            int length = "5".length();

            SpannableStringBuilder ssb = new SpannableStringBuilder(tip2);
            ForegroundColorSpan red = new ForegroundColorSpan(context.getResources().getColor(R.color.ski_color_tip_red));
            ssb.setSpan(red, index, index + length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTvTip2.setText(ssb);
        }

        String tip4 = context.getString(R.string.ski_my_bank_card_tip_4);
        if (!TextUtils.isEmpty(tip4) && tip4.contains("12")) {
            int index = tip4.indexOf("12");
            int length = "12".length();

            SpannableStringBuilder ssb = new SpannableStringBuilder(tip4);
            ForegroundColorSpan red = new ForegroundColorSpan(context.getResources().getColor(R.color.ski_color_tip_red));
            ssb.setSpan(red, index, index + length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTvTip4.setText(ssb);
        }
    }

}
