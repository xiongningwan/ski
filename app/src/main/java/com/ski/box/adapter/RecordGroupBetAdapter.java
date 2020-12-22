package com.ski.box.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.group.GroupBetData;
import com.ski.box.bean.lottery.LotteryConstant;
import com.ski.box.utils.DecimalSetUtils;
import com.ski.box.utils.lottery.ConfigurationUiUtils;
import com.ski.box.view.view.LotteryRecordResultView;
import com.yb.core.utils.LanguageUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

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
        holder.setText(R.id.tv_play_name, bean.getPlayName() + " - ");
        holder.setText(R.id.tv_user_name, bean.getMemberAccount());
        LotteryRecordResultView lotteryResultView = holder.getView(R.id.lottery_record_result_view);

        String playItemName = bean.getBetContent();
        TextView tvPlayItemName = holder.getView(R.id.tv_item_play_name);
        ImageView ivPlayItemName = holder.getView(R.id.iv_play_item_name);
        ImageView ivPlayItemName2 = holder.getView(R.id.iv_play_item_name_2);
        ImageView ivPlayItemName3 = holder.getView(R.id.iv_play_item_name_3);
        tvPlayItemName.setText(playItemName);
        ivPlayItemName.setImageResource(0);
        ivPlayItemName2.setImageResource(0);
        ivPlayItemName3.setImageResource(0);
        int currentSerId = bean.getSeriesId();
        if (LotteryConstant.SER_ID_F1_JJS == currentSerId || LotteryConstant.SER_ID_F1_CCL == currentSerId) {
            if (TextUtils.isDigitsOnly(playItemName)) {
                setItemNameVisible(holder, true, false, false);
                ivPlayItemName.setImageResource(ConfigurationUiUtils.getF1JJSIcon(playItemName));
            } else {
                setItemNameVisible(holder, false, false, false);
            }
        } else if (LotteryConstant.SER_ID_F1_SW == currentSerId) {
            if (TextUtils.isDigitsOnly(playItemName)) {
                List<Integer> iconList = getSwIcons(playItemName);
                if (1 == iconList.size()) {
                    ivPlayItemName.setImageResource(iconList.get(0));
                    setItemNameVisible(holder, true, false, false);
                } else if (2 == iconList.size()) {
                    ivPlayItemName.setImageResource(iconList.get(0));
                    ivPlayItemName2.setImageResource(iconList.get(1));
                    setItemNameVisible(holder, true, true, false);
                } else if (3 == iconList.size()) {
                    ivPlayItemName.setImageResource(iconList.get(0));
                    ivPlayItemName2.setImageResource(iconList.get(1));
                    ivPlayItemName3.setImageResource(iconList.get(2));
                    setItemNameVisible(holder, true, true, true);
                }
            } else {
                setItemNameVisible(holder, false, false, false);
            }
        } else {
            setItemNameVisible(holder, false, false, false);
        }


        String code = bean.getOpenResult();
        code = code.replace(",", " ");
        lotteryResultView.setResult(bean.getTicketId(), code);
        /*投注金额*/
        String s = DecimalSetUtils.setMoneySaveFour(bean.getBetAmt() + "");
        String betMoney = s ;
        String info = LanguageUtil.getText("投注") + ": " + betMoney;
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
            String winMoney = LanguageUtil.getText("已中奖") + ": " + winAmount;
            tvWin.setText(winMoney);
        }
    }

    private void setItemNameVisible(BaseViewHolder holder, boolean ivName1, boolean ivName2, boolean ivName3) {
        TextView tvPlayItemName = holder.getView(R.id.tv_item_play_name);
        ImageView ivPlayItemName = holder.getView(R.id.iv_play_item_name);
        ImageView ivPlayItemName2 = holder.getView(R.id.iv_play_item_name_2);
        ImageView ivPlayItemName3 = holder.getView(R.id.iv_play_item_name_3);
        if (ivName1) {
            ivPlayItemName.setVisibility(View.VISIBLE);
            tvPlayItemName.setVisibility(View.GONE);
        } else {
            ivPlayItemName.setVisibility(View.GONE);
            tvPlayItemName.setVisibility(View.VISIBLE);
        }

        if (ivName2) {
            ivPlayItemName2.setVisibility(View.VISIBLE);
        } else {
            ivPlayItemName2.setVisibility(View.GONE);
        }

        if (ivName3) {
            ivPlayItemName3.setVisibility(View.VISIBLE);
        } else {
            ivPlayItemName3.setVisibility(View.GONE);
        }
    }

    private List<Integer> getSwIcons(String playCode) {
        List<Integer> icons = new ArrayList<>();
        for (int i = 0; i < playCode.length(); i++) {
            icons.add(ConfigurationUiUtils.getF1JJSIcon(String.valueOf(playCode.charAt(i))));
        }
        return icons;
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
