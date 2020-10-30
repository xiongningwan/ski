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
import com.ski.box.view.view.LotteryRecordResultView;
import com.ski.box.view.view.LotteryResultView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RecordBetAdapter2 extends BaseQuickAdapter<RecordBet.ListBean , BaseViewHolder> {
    private  Context mContext;

    public RecordBetAdapter2(Context context) {
        super(R.layout.ski_item_bet_recond_second2);
        mContext = context;
        addChildClickViewIds(R.id.tv_cancel);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, @Nullable RecordBet.ListBean bean) {
        holder.setText(R.id.tv_lottery_name, bean.getTicketName());
        holder.setText(R.id.tv_period, bean.getTicketPlanNo());
        holder.setText(R.id.tv_play_name, bean.getPlayName());
        LotteryRecordResultView lotteryResultView = holder.getView(R.id.lottery_record_result_view);
        String code = bean.getTicketResult();
        code = code.replace(",", " ");
        lotteryResultView.setResult(bean.getTicketId(), code);
        /*投注金额*/
        String s = DecimalSetUtils.setMoneySaveFour(bean.getBetMoney() + "");
        String betMoney = s + "元";
        String info = "投注: " + betMoney;
//        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(info);
//        ForegroundColorSpan blue = new ForegroundColorSpan(getContext().getResources().getColor(R.color.ski_color_037bff_blue));
//        stringBuilder.setSpan(blue, info.indexOf(betMoney), info.indexOf(betMoney) + betMoney.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.setText(R.id.tv_bet_money, info);

       TextView tvWin = holder.getView(R.id.tv_win);
       TextView tvStatus = holder.getView(R.id.tv_status);
       TextView tvCancel = holder.getView(R.id.tv_cancel);

        if(bean.isCanCancel()) {
            tvCancel.setVisibility(View.VISIBLE);
        } else {
            tvCancel.setVisibility(View.GONE);
        }
        int betStatus = bean.getBetStatus();
        if (betStatus == 1) {
            tvStatus.setVisibility(View.VISIBLE);
            tvWin.setVisibility(View.GONE);
            tvStatus.setText(bean.getBetStatusDes());
        } else if (betStatus == 5) {
            tvStatus.setVisibility(View.GONE);
            tvWin.setVisibility(View.VISIBLE);
            String winAmount = DecimalSetUtils.setMoneySaveFour(bean.getWinAmount() + "");
            String winMoney = "中奖: " + winAmount + "元";
//            SpannableStringBuilder ssb = new SpannableStringBuilder(winMoney);
//            ForegroundColorSpan red = new ForegroundColorSpan(getContext().getResources().getColor(R.color.ski_red));
//            ssb.setSpan(red, 2, winMoney.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvWin.setText(winMoney);
        } else {
            tvStatus.setVisibility(View.VISIBLE);
            tvWin.setVisibility(View.GONE);
            tvStatus.setText(bean.getBetStatusDes());
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
