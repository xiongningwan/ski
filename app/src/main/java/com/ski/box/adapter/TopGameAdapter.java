package com.ski.box.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.TopGameBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TopGameAdapter extends BaseMultiItemQuickAdapter<TopGameBean, BaseViewHolder> {
    public static final int TOP_GAME_TYPE_SERIES = 1; // 彩系
    public static final int TOP_GAME_TYPE_TICKET = 2; // 彩种


    public TopGameAdapter() {
        addItemType(TOP_GAME_TYPE_SERIES, R.layout.ski_item_trend_game_type);
        addItemType(TOP_GAME_TYPE_TICKET, R.layout.ski_item_trend_game);
        addChildClickViewIds(R.id.item_trend_game);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder holder, @Nullable TopGameBean topGameBean) {
        switch (holder.getItemViewType()) {
            case TOP_GAME_TYPE_SERIES:
                holder.setText(R.id.item_trend_game_type, topGameBean.getTicketSeriesName());
                break;
            case TOP_GAME_TYPE_TICKET:
                holder.setText(R.id.item_trend_game, topGameBean.getTicketName());
                if (topGameBean.isSelected()) {
                    holder.itemView.setSelected(true);
                } else {
                    holder.itemView.setSelected(false);
                }

                break;
        }
    }
}
