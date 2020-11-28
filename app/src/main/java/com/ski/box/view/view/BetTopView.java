package com.ski.box.view.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.SKIApplication;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.LotteryNumBean;
import com.ski.box.bean.TicketLotteryTimeBean;
import com.ski.box.service.AlarmService;
import com.ski.box.utils.lottery.LotteryNoUtil;
import com.ski.box.utils.lottery.SettingManager;
import com.ski.box.view.activity.my.GameSetActivity;
import com.ski.box.view.view.dialog.pop.TopHelperPop;
import com.yb.core.utils.AppUtil;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

import java.io.IOException;
import java.util.List;

import static com.ski.box.ConstantValue.EVENT_BET_ACTIVITY_FINISH;
import static com.ski.box.ConstantValue.EVENT_BET_TOP_CHOSE_LOTTERY_DIALOG_START_OPEN;
import static com.ski.box.ConstantValue.EVENT_TYPE_CHANGE_OPEN_RESULT_HISTORY;


public class BetTopView extends FrameLayout implements OnClickListener {
    private TextView mTvName;
    private TextView mTvHour;
    private TextView mTvMinute;
    private TextView mTvSecond;
    private TextView mTvPlanPeriod;
    private TextView mTvCurrentPeriod;
    private TextView mTvStopSale;
    private TextView mTvStatus;
    private ImageView mIvDrop;
    private ImageView mIvTitleDrop;
    private FrameLayout mFlDrop;
    private FrameLayout mFlClose;
    private FrameLayout mFlHelper;
    private LinearLayout mLLName;

    private Context mContext;
    private AlarmBroadReceive alarmBroadReceive;
    private int normalColor;
    private int redColor;
    private String mPlanId = "";
    private LotteryResultView mLotteryResultView;
    private View mLotteryResultCoverView;
//    private TopHelperPop mTopHelperPop;
    private int mMode = 2;
    private boolean isBetPage = false;


    public BetTopView(Context context) {
        super(context);
        initView(context);
    }

    public BetTopView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BetTopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (alarmBroadReceive != null) {
            mContext.unregisterReceiver(alarmBroadReceive);
        }
    }

    private void initView(Context context) {
        mContext = context;
        View v = View.inflate(context, R.layout.ski_bet_view_top, this);
        mLLName = v.findViewById(R.id.ll_name);
        mTvName = v.findViewById(R.id.tv_name);
        mIvTitleDrop = v.findViewById(R.id.iv_name_arrow);
        mTvHour = v.findViewById(R.id.tv_time_hour);
        mTvMinute = v.findViewById(R.id.tv_time_minute);
        mTvSecond = v.findViewById(R.id.tv_time_second);
        mTvPlanPeriod = v.findViewById(R.id.tv_plan_period);
        mTvCurrentPeriod = v.findViewById(R.id.tv_current_period);
        mTvStatus = v.findViewById(R.id.tv_status);
        mTvStopSale = v.findViewById(R.id.tv_stop_sale);
        mLotteryResultView = v.findViewById(R.id.lottery_result_view);
        mLotteryResultCoverView = v.findViewById(R.id.lottery_result_view_cover);
        mFlDrop = v.findViewById(R.id.fl_arrow_down_result);
        mIvDrop = v.findViewById(R.id.iv_arrow_down_result);
        mFlClose = v.findViewById(R.id.fl_close);
        mFlHelper = v.findViewById(R.id.fl_helper);

        mLotteryResultCoverView.setOnClickListener(this);
        mFlDrop.setOnClickListener(this);
        mIvDrop.setOnClickListener(this);
        mFlClose.setOnClickListener(this);
        mLLName.setOnClickListener(this);
        mFlHelper.setOnClickListener(this);
        initData();
    }

    private void initData() {
        normalColor = ContextCompat.getColor(getContext(), R.color.ski_white);
        redColor = ContextCompat.getColor(getContext(), R.color.ski_red);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AlarmService.ALARM_ACTION + DataCenter.getInstance().getToken());
        if (alarmBroadReceive == null) {
            alarmBroadReceive = new AlarmBroadReceive();
            mContext.registerReceiver(alarmBroadReceive, intentFilter);
        }

    }


    /**
     * 初次进入倒计时启动非常缓慢,导致逻辑出现一些偏差,当期号发生变化的时候,倒计时还为0;
     */
    private class AlarmBroadReceive extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int id = DataCenter.getInstance().getCurLotteryId();
            long time = DataCenter.getInstance().getLotteryTime(id);
            String planId = DataCenter.getInstance().getLotteryPlanId(id);
            DataCenter.getInstance().setPlanId(planId);
            updateLayout(time);
            if (!mPlanId.equals(planId)) {
                mPlanId = planId;
                mTvPlanPeriod.setText(LotteryNoUtil.getShortIssue(planId));
            }
        }
    }

    /**
     * 更新倒计时
     *
     * @param countDownTimes
     */
    private void updateLayout(long countDownTimes) {
        if (mTvHour == null) {
            return;
        }
        if (countDownTimes < 0) {
            return;
        }
        String[] arr = LotteryNoUtil.getTime(countDownTimes);
        mTvHour.setText(arr[0]);
        mTvMinute.setText(arr[1]);
        mTvSecond.setText(arr[2]);
        setCDTextColor(countDownTimes);
        downTimeVoice(countDownTimes);
    }

    private void setCDTextColor(long countDownTimes) {
        if (countDownTimes <= 10 * 1000) {
            mTvSecond.setTextColor(redColor);
            mTvMinute.setTextColor(redColor);
            mTvHour.setTextColor(redColor);
        } else {
            if (normalColor != mTvSecond.getCurrentTextColor()) {
                mTvSecond.setTextColor(normalColor);
                mTvMinute.setTextColor(normalColor);
                mTvHour.setTextColor(normalColor);
            }
        }
    }

    /**
     * 设置倒计时声音
     *
     * @param countDownTimes
     */
    private void downTimeVoice(long countDownTimes) {
        if (SettingManager.isBettingCountdownTone()) {

            // 最后3秒播放倒计时
            if (countDownTimes == 3000 && SKIApplication.isForeground && isBetPage) {
                try {
                    AssetManager assetManager = AppUtil.getContext().getAssets();
                    AssetFileDescriptor afd = assetManager.openFd("voice/ski_time_down.mp3");
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setPlanData(TicketLotteryTimeBean selling) {
        setStatus(selling.getSale());
        String planId = selling.getPlanId();
        mPlanId = planId;
        mTvPlanPeriod.setText(LotteryNoUtil.getShortIssue(planId));
    }

    /**
     * 设置是否是停售状态
     */
    public void setStatus(int sale) {
        if (0 == sale) {
            mTvStopSale.setVisibility(GONE);
            mTvPlanPeriod.setVisibility(VISIBLE);
            mTvHour.setVisibility(VISIBLE);
            mTvMinute.setVisibility(VISIBLE);
            mTvSecond.setVisibility(VISIBLE);
            mTvStatus.setVisibility(VISIBLE);
        } else {
            mTvStopSale.setVisibility(VISIBLE);
            mTvPlanPeriod.setVisibility(GONE);
            mTvHour.setVisibility(GONE);
            mTvMinute.setVisibility(GONE);
            mTvSecond.setVisibility(GONE);
            mTvStatus.setVisibility(GONE);
        }

    }

    public void setLotteryName(String ticketName) {
        mTvName.setText(ticketName);
    }

    public void setOpenResult(List<LotteryNumBean> list) {
        if (list != null && list.size() > 0) {
            String issue = LotteryNoUtil.getShortIssue(list.get(0).getIssue());
            mTvCurrentPeriod.setText(issue);
            mLotteryResultView.setResult(list, mMode);
            mMode = 1;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_arrow_down_result || v.getId() == R.id.lottery_result_view_cover || v.getId() == R.id.fl_arrow_down_result) {
            RxBus.get().post(EVENT_TYPE_CHANGE_OPEN_RESULT_HISTORY, "打开关闭投注历史页");
        } else if (v.getId() == R.id.fl_close) {
            RxBus.get().post(EVENT_BET_ACTIVITY_FINISH, "关闭投注页面");
        } else if (v.getId() == R.id.ll_name) {
            RxBus.get().post(EVENT_BET_TOP_CHOSE_LOTTERY_DIALOG_START_OPEN, "顶部选择彩种弹窗打开");
        } else if (v.getId() == R.id.fl_helper) {
            showHelper();
        }

    }

    public ImageView getIvDrop() {
        return mIvDrop;
    }

    public ImageView getIvTitleDrop() {
        return mIvTitleDrop;
    }

    public boolean isBetPage() {
        return isBetPage;
    }

    public void setBetPage(boolean betPage) {
        isBetPage = betPage;
    }

    private void showHelper() {
//        if (mTopHelperPop == null) {
//            mTopHelperPop = TopHelperPop.create(mContext);
//        }
//        mTopHelperPop.showAtAnchorView(mFlHelper, YGravity.BELOW, XGravity.CENTER, -100, -10);
        mContext.startActivity(new Intent(mContext, GameSetActivity.class));
    }
}
