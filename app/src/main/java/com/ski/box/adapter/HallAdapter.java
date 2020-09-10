package com.ski.box.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.lottery.LotteryBean;
import com.ski.box.bean.lottery.LotteryConstant;

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
        TextView tvLy = holder.getView(R.id.tv_ly);
        ImageView ivHot = holder.getView(R.id.iv_hot);
        tvLy.setText(bean.getTicketName());
        int id = bean.getTicketId();
        if (LotteryConstant.LOTTERY_ID_PK10_JSSC == id || LotteryConstant.LOTTERY_ID_PK10_XYFT == id || LotteryConstant.LOTTERY_ID_PK10_BJ == id ||
                LotteryConstant.LOTTERY_ID_PK10_JSFT == id || LotteryConstant.LOTTERY_ID_PK10_HLFT == id || LotteryConstant.LOTTERY_ID_SSC_CQ == id ||
                LotteryConstant.LOTTERY_ID_SSC_JS == id || LotteryConstant.LOTTERY_ID_SSC_XYFFC == id || LotteryConstant.LOTTERY_ID_SSC_TX == id) {
            ivHot.setVisibility(View.VISIBLE);
        } else {
            ivHot.setVisibility(View.GONE);
        }
    }


}
