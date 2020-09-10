package com.ski.box.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.record.RecordBet;
import com.ski.box.utils.DecimalSetUtils;
import com.ski.box.view.activity.RecordDetailActivity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RecordBetAdapter extends BaseQuickAdapter<RecordBet.ListBean , BaseViewHolder> {
    private  Context mContext;

    public RecordBetAdapter(Context context) {
        super(R.layout.ski_item_bet_recond_second);
        mContext = context;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, @Nullable RecordBet.ListBean listBean) {
        /*投注记录*/
        setBetData(holder,listBean);
    }

    private void setBetData(BaseViewHolder holder, RecordBet.ListBean listBean) {

        holder.setText(R.id.tv_bet_day, listBean.getBetTime());
        holder.setText(R.id.tv_betting_issue, "第" + listBean.getTicketPlanNo() + "期");
        long playId = 0;
        if (!TextUtils.isEmpty(listBean.getPlayId())) {
            playId = Long.parseLong(listBean.getPlayId());
        }
//        String betTranscoding = LotteryNoUtil.getBetTranscoding(playId, listBean.getBetNum());
        /*投注玩法*/
        holder.setText(R.id.tv_bet_play_name, listBean.getTicketName()+"-"+listBean.getPlayName());


        /*投注金额*/
        String s = DecimalSetUtils.setMoneySaveFour(listBean.getBetMoney() + "");
        String betMoney = s + "元";
        String info = "投注: " + betMoney;
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(info);
        ForegroundColorSpan blue = new ForegroundColorSpan(getContext().getResources().getColor(R.color.ski_color_037bff_blue));
        stringBuilder.setSpan(blue, info.indexOf(betMoney), info.indexOf(betMoney) + betMoney.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.setText(R.id.tv_bet_money, stringBuilder);

        int betStatus = listBean.getBetStatus();
        TextView view = holder.getView(R.id.tv_bet_status);
        if (betStatus == 5) {
            view.setTextColor(mContext.getResources().getColor(R.color.ski_red));
            holder.setVisible(R.id.tv_win_money, true);
            String s1 = DecimalSetUtils.setMoneySaveFour(listBean.getWinAmount() + "");
            holder.setText(R.id.tv_win_money, s1+ "元");
        } else {
            view.setTextColor(mContext.getResources().getColor(R.color.ski_color_666666));
            holder.setVisible(R.id.tv_win_money, false);
        }
        holder.setText(R.id.tv_bet_status, listBean.getBetStatusDes());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean fastDoubleClick = isFastDoubleClick();
                if (fastDoubleClick) {
                    return;
                }
                int position = holder.getAdapterPosition();
                List<RecordBet.ListBean> data = getData();
                RecordBet.ListBean listBean = data.get(position);

                Intent intent = new Intent(mContext, RecordDetailActivity.class);
                /*投注详情*/
//                Bundle bundle = new Bundle();
//                bundle.putString("data", listBean.getOrderId());
//                bundle.putInt("type", RecordDetailActivity.BETDETAILS);
//                intent.putExtras(bundle);
                mContext.startActivity(intent);

            }
        });
    }


    private long lastClickTime;
    public boolean isFastDoubleClick() {
        long curMills = System.currentTimeMillis();
        long timeD = curMills - lastClickTime;
        if (timeD > 1000) {
            lastClickTime = curMills;
            return false;

        } else {
            return true;
        }
    }
}
