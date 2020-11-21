package com.ski.box.view.view;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.bean.BetStatus;
import com.ski.box.bean.MemberDetailEntity;
import com.ski.box.bean.lottery.LotteryConstant;
import com.ski.box.utils.ActivityUtil;
import com.ski.box.utils.DecimalSetUtils;
import com.ski.box.view.view.dialog.CustomBottomDialog;
import com.yb.core.utils.LanguageUtil;
import com.yb.core.utils.ScreenUtils;

import static com.ski.box.ConstantValue.EVENT_TYPE_BET_RiGHT_NOW_CLICK;


/**
 * 底部
 */
public class BetBottomView extends LinearLayout implements View.OnClickListener {
    private long mLastClickTime;
    private long mTimeInterval = 300L;


    private CustomBottomDialog tDialog;
    private Context mContext;
    private QuickMoneyView mQmView;
    private TextView mTvBetChose;
    private TextView mTvBalance;
    private TextView mTvBet;


    public BetBottomView(Context context) {
        super(context);
        initView(context);
    }

    public BetBottomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BetBottomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        mContext = context;
        View rootView = View.inflate(context, R.layout.ski_bet_view_bottom, this);
//        mQmView = rootView.findViewById(R.id.qm_view);
        mQmView =  new  QuickMoneyView(context, rootView);
        mTvBetChose = rootView.findViewById(R.id.tv_bet_chose);
        mTvBalance = rootView.findViewById(R.id.tv_total_balance);
        mTvBet = rootView.findViewById(R.id.tv_bet);
        initData();
    }

    private void initData() {
        mTvBet.setOnClickListener(this);
    }

    public View getBetView() {
        return mTvBet;
    }

    @Override
    public void onClick(View v) {
        long nowTime = System.currentTimeMillis();
        if (nowTime - mLastClickTime < mTimeInterval) {
            // 快速点击事件
            return;
        }
        mLastClickTime = nowTime;
        int id = v.getId();
        if (id == R.id.tv_bet) {
            RxBus.get().post(EVENT_TYPE_BET_RiGHT_NOW_CLICK, this);
        }
    }

    public void setBetChose(BetStatus betStatus) {
        int totalAmount = betStatus.getTotalAmount();
        if (0 == betStatus.getZhuShu() || totalAmount == 0) {
            mTvBetChose.setVisibility(View.GONE);
            // mViewBottom.enableView(false);
        } else {
            mTvBetChose.setVisibility(View.VISIBLE);
            //   mViewBottom.enableView(true);
        }
        SpannableStringBuilder spanBuilder;
        if (LotteryConstant.LOTTERY_PLAY_DAN == betStatus.getStatus()) {
            String s1 =  LanguageUtil.getText("共");
            String s2 =  LanguageUtil.getText("注单");
            String s4 =  LanguageUtil.getText(" , ");
            String content = s1 + " " + betStatus.getZhuShu() + " " + s2 + s4 + betStatus.getTotalAmount();
            spanBuilder = new SpannableStringBuilder(content);
            ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(getResources().getColor(R.color.ski_color_ff9307));
            ForegroundColorSpan colorSpan2 = new ForegroundColorSpan(getResources().getColor(R.color.ski_color_ff9307));
            spanBuilder.setSpan(colorSpan1, content.indexOf(s1) + s1.length(), content.indexOf(s2), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanBuilder.setSpan(colorSpan2, content.indexOf(s4) + s4.length(), content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            String s1 =  LanguageUtil.getText("共")+ " 1 "+ LanguageUtil.getText("注单");
            String s2 =  LanguageUtil.getText("组");
            String content = s1  + " " + betStatus.getZhuShu() + " " + s2 + " " + betStatus.getTotalAmount();
            spanBuilder = new SpannableStringBuilder(content);
            ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(getResources().getColor(R.color.ski_color_ff9307));
            ForegroundColorSpan colorSpan2 = new ForegroundColorSpan(getResources().getColor(R.color.ski_color_ff9307));
            spanBuilder.setSpan(colorSpan1, content.indexOf(s1) + s1.length(), content.indexOf(s2), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanBuilder.setSpan(colorSpan2, content.indexOf(s2) + s2.length(), content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        mTvBetChose.setText(spanBuilder);
    }


    public void enableView(boolean isGaoLiang) {
        if (isGaoLiang) {
            //   Drawable drawable = mContext.getResources().getDrawable(R.mipmap.ski_jiahao);
            //   drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        } else {
            //  Drawable drawable = mContext.getResources().getDrawable(R.mipmap.ski_jiahao_off);
            //  drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
    }


    public void setBalance(String balanceStr) {
        String balance = DecimalSetUtils.setMoneySaveThree(balanceStr);
//        String yue = "余额";
        String yue = LanguageUtil.getText("总金额") + ": ";
//        String zhegnshu = "";
//        String xiaoshu = "";
//        String[] split = balance.split("\\.");
//        zhegnshu = split[0];
//        if (split.length == 2) {
//            xiaoshu = "."+split[1];
//        }
//
//        String info = yue + ":" + zhegnshu  + xiaoshu;
//        SpannableStringBuilder builder = new SpannableStringBuilder(info);
//        AbsoluteSizeSpan big = new AbsoluteSizeSpan(13, true);
//        AbsoluteSizeSpan small = new AbsoluteSizeSpan(8, true);
//        builder.setSpan(big, info.indexOf(yue), info.indexOf(yue) + yue.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        builder.setSpan(small, info.lastIndexOf(xiaoshu), info.lastIndexOf(xiaoshu) + xiaoshu.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        // 颜色
//        ForegroundColorSpan span = new ForegroundColorSpan(Color.rgb(254,58,54));
//        builder.setSpan(span, 2, 4, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mTvBalance.setText(yue + balance);
    }

    public long getAmount() {
        try {
            EditText etDoubleAmount = mQmView.etDoubleAmount();
            if (etDoubleAmount.getText().toString().isEmpty()) {
                return 0;
            } else {
                return ActivityUtil.doBetMoneyWithK(etDoubleAmount.getText().toString());
            }
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}
