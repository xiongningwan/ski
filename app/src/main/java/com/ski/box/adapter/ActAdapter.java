package com.ski.box.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.ActBean;
import com.ski.box.bean.record.RecordBet;
import com.ski.box.utils.DecimalSetUtils;
import com.ski.box.view.activity.RecordDetailActivity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ActAdapter extends BaseQuickAdapter<ActBean, BaseViewHolder> {
    private Context mContext;
    private int color_0;
    private int color_1;
    private int color_2;
    private int color_3;

    public ActAdapter(Context context) {
        super(R.layout.ski_item_act_list);
        mContext = context;
        color_0 = Color.parseColor("#8467DB");
        color_1 = Color.parseColor("#FFC084");
        color_2 = Color.parseColor("#72E2C5");
        color_3 = Color.parseColor("#FF8077");
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, @Nullable ActBean bean) {
        ConstraintLayout clItem = holder.getView(R.id.cl_item);
        TextView tvTitle = holder.getView(R.id.tv_title);
        TextView tvStart = holder.getView(R.id.tv_time_start);
        TextView tvEnd = holder.getView(R.id.tv_time_end);
        TextView tvJoin = holder.getView(R.id.tv_btn_join);
        ImageView ivIcon = holder.getView(R.id.iv_icon);

        tvTitle.setText(bean.getActivityName());
        tvStart.setText("开始时间：" + bean.getStartDate());
        tvEnd.setText("结束时间：" + bean.getEndDate());
        int position = holder.getAdapterPosition();
        if (position % 2 == 0) {
//            clItem.setBackgroundResource(R.drawable.ski_bg_item_act_0);
            clItem.setBackgroundResource(R.mipmap.ski_act_bg_0);
            tvJoin.setTextColor(color_0);
            ivIcon.setImageResource(R.mipmap.ski_act_icon_0);
        } else if (position % 2 == 1) {
//            clItem.setBackgroundResource(R.drawable.ski_bg_item_act_1);
            clItem.setBackgroundResource(R.mipmap.ski_act_bg_1);
            tvJoin.setTextColor(color_1);
            ivIcon.setImageResource(R.mipmap.ski_act_icon_1);
        }
//        else if (position % 4 == 2) {
//            clItem.setBackgroundResource(R.drawable.ski_bg_item_act_2);
//            tvJoin.setTextColor(color_2);
//        } else if (position % 4 == 3) {
//            clItem.setBackgroundResource(R.drawable.ski_bg_item_act_3);
//            tvJoin.setTextColor(color_3);
//        }
    }

}
