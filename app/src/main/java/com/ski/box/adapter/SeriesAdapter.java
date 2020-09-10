package com.ski.box.adapter;

import android.widget.CheckBox;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.lottery.LotteryBean;
import com.ski.box.bean.lottery.LotterySer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author Afton
 * date 2020/2/28
 */
public class SeriesAdapter extends BaseSectionQuickAdapter<MySection, BaseViewHolder> {

    public SeriesAdapter(int sectionHeadResId, int layoutResId, @Nullable List<MySection> data) {
        super(sectionHeadResId, data);
        setNormalLayout(layoutResId);
    }

    @Override
    protected void convertHeader(@NotNull BaseViewHolder baseViewHolder, @Nullable MySection mySection) {
        if (mySection.getObject() instanceof LotterySer) {
            baseViewHolder.setText(R.id.header, ((LotterySer) mySection.getObject()).getName());
        }
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable MySection mySection) {
        LotteryBean bean = (LotteryBean) mySection.getObject();
        TextView tvName = baseViewHolder.itemView.findViewById(R.id.item_trend_game);
        tvName.setText(bean.getTicketName());
        tvName.setSelected(bean.isSelected());
    }
}
