package com.ski.box.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.record.RecordBet;
import com.ski.box.utils.DecimalSetUtils;
import com.ski.box.view.activity.RecordDetailActivity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RecordBetAdapter2 extends BaseQuickAdapter<RecordBet.ListBean , BaseViewHolder> {
    private  Context mContext;
    private int bg0;
    private int bg1;

    public RecordBetAdapter2(Context context) {
        super(R.layout.ski_item_bet_recond_second2);
        mContext = context;
        bg0 = ContextCompat.getColor(context,R.color.ski_white);
        bg1 = ContextCompat.getColor(context,R.color.ski_color_f8f8f8);
        addChildClickViewIds(R.id.tv_copy,R.id.tv_cancel);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, @Nullable RecordBet.ListBean bean) {
        holder.setText(R.id.tv_lottery_name, bean.getTicketName());
        holder.setText(R.id.tv_time, bean.getBetTime());
        holder.setText(R.id.tv_no, bean.getOrderId());
        holder.setText(R.id.tv_period, bean.getTicketPlanNo());
        holder.setText(R.id.tv_play_name, bean.getPlayName());
        holder.setText(R.id.tv_bet_content, bean.getBetContent());

        /*投注金额*/
        String s = DecimalSetUtils.setMoneySaveFour(bean.getBetMoney() + "");
        String betMoney = s + "元";
        String info = "投注: " + betMoney;
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(info);
        ForegroundColorSpan blue = new ForegroundColorSpan(getContext().getResources().getColor(R.color.ski_color_037bff_blue));
        stringBuilder.setSpan(blue, info.indexOf(betMoney), info.indexOf(betMoney) + betMoney.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.setText(R.id.tv_bet_money, stringBuilder);

        ConstraintLayout clItem = holder.getView(R.id.cl_bet_record);
       TextView tvStatus = holder.getView(R.id.tv_bet_win);
       TextView tvBetContent = holder.getView(R.id.tv_bet_content);
       TextView tvCancel = holder.getView(R.id.tv_cancel);

//       if(0 == holder.getAdapterPosition() % 2) {
//           clItem.setBackgroundColor(bg0);
//       } else {
//           clItem.setBackgroundColor(bg1);
//       }
        if(bean.isCanCancel()) {
            tvCancel.setVisibility(View.VISIBLE);
        } else {
            tvCancel.setVisibility(View.GONE);
        }
        int betStatus = bean.getBetStatus();
        if (betStatus == 1) {
            tvStatus.setVisibility(View.VISIBLE);
            tvBetContent.setVisibility(View.VISIBLE);
            tvStatus.setText(bean.getBetStatusDes());
        } else if (betStatus == 5) {
            tvStatus.setVisibility(View.VISIBLE);
            tvBetContent.setVisibility(View.VISIBLE);
            String winAmount = DecimalSetUtils.setMoneySaveFour(bean.getWinAmount() + "");
            String winMoney = "中奖: " + winAmount + "元";
            SpannableStringBuilder ssb = new SpannableStringBuilder(winMoney);
            ForegroundColorSpan red = new ForegroundColorSpan(getContext().getResources().getColor(R.color.ski_red));
            ssb.setSpan(red, 2, winMoney.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvStatus.setText(ssb);
        } else {
            tvStatus.setVisibility(View.VISIBLE);
            tvBetContent.setVisibility(View.VISIBLE);
            tvStatus.setText(bean.getBetStatusDes());
        }
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
