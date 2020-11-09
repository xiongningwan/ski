package com.ski.box.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.lottery.LotteryBean;
import com.ski.box.bean.lottery.LotteryConstant;
import com.ski.box.bean.lottery.LotteryUtil;

import org.jetbrains.annotations.NotNull;

/**
 * Created by tom on 2020/8/6.
 */
public class HallAdapter extends BaseQuickAdapter<LotteryBean, BaseViewHolder> {

    public static final int TYPE_TIME = 1; // 彩种时间分类
    public static final int TYPE_LOTTERY = 2; // 彩种


    public HallAdapter() {
//        addItemType(TYPE_TIME, R.layout.ski_item_hall_type_time);
//        addItemType(TYPE_LOTTERY, R.layout.ski_item_hall_type_lottery);
        super(R.layout.ski_item_hall_type_lottery);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, LotteryBean bean) {
        LinearLayout llItem = holder.getView(R.id.ll_item);
        ImageView ivIcon = holder.getView(R.id.iv_icon);
        TextView tvName = holder.getView(R.id.tv_name);
        tvName.setText(bean.getTicketName());
        int id = bean.getTicketId();
        ivIcon.setImageResource(LotteryUtil.getLotteryIcon(id));
        int position = holder.getAdapterPosition();
        if(0 == position) {
            llItem.setBackgroundResource(R.drawable.ski_hall_rv_item_unclick_top_left);
        } else if(1 == position) {
            llItem.setBackgroundResource(R.drawable.ski_hall_rv_item_unclick_top_right);
        } else {
            llItem.setBackgroundResource(R.drawable.ski_hall_rv_item_unclick);
        }
    }


}
