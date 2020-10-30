package com.ski.box.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.group.GroupBetData;
import com.ski.box.bean.record.RecordBet;
import com.ski.box.utils.DecimalSetUtils;
import com.ski.box.view.view.LotteryRecordResultView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RecordGroupBetAdapter extends BaseQuickAdapter<GroupBetData.ListBean , BaseViewHolder> {
    private  Context mContext;

    public RecordGroupBetAdapter(Context context) {
        super(R.layout.ski_item_record_group_bet);
        mContext = context;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, @Nullable GroupBetData.ListBean bean) {
        holder.setText(R.id.tv_lottery_name, bean.getTicketName());
        holder.setText(R.id.tv_period, bean.getIssueNo());
        holder.setText(R.id.tv_play_name, bean.getPlayName());
        holder.setText(R.id.tv_user_name, bean.getMemberAccount());
        LotteryRecordResultView lotteryResultView = holder.getView(R.id.lottery_record_result_view);

        String code = bean.getOpenResult();
        code = code.replace(",", " ");
        lotteryResultView.setResult(bean.getTicketId(), code);
        /*投注金额*/
        String s = DecimalSetUtils.setMoneySaveFour(bean.getBetAmt() + "");
        String betMoney = s + "元";
        String info = "投注: " + betMoney;
//        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(info);
//        ForegroundColorSpan blue = new ForegroundColorSpan(getContext().getResources().getColor(R.color.ski_color_037bff_blue));
//        stringBuilder.setSpan(blue, info.indexOf(betMoney), info.indexOf(betMoney) + betMoney.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.setText(R.id.tv_bet_money, info);

       TextView tvWin = holder.getView(R.id.tv_win);
       TextView tvStatus = holder.getView(R.id.tv_status);

        if("0.0000".equals(bean.getWinAmt())) {
            tvStatus.setVisibility(View.VISIBLE);
            tvWin.setVisibility(View.GONE);
            tvStatus.setText(bean.getStatus());
        } else {
            tvStatus.setVisibility(View.GONE);
            tvWin.setVisibility(View.VISIBLE);
            String winAmount = DecimalSetUtils.setMoneySaveFour(bean.getWinAmt() + "");
            String winMoney = "中奖: " + winAmount + "元";
            tvWin.setText(winMoney);
        }
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
