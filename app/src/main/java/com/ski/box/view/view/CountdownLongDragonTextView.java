package com.ski.box.view.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.bean.DataCenter;
import com.ski.box.service.AlarmService;
import com.ski.box.utils.lottery.LotteryNoUtil;


public class CountdownLongDragonTextView extends AppCompatTextView {
    private Context mContext;
    private AlarmReceive alarmReceive;
    private long mTimes = 0;
    private long mShowTime = Long.MAX_VALUE;

    private boolean mAttached;
    private boolean isDrawing;
    private int mTicketId;
    private boolean isVisible = true;
    private String colorRed = "#ea6464";
    private String colorNormal = "#999999";
    private boolean isRecentPlay;
    private boolean requestByOutside; // 外部请求倒计时

    public void setRecentPlay(boolean b) {
        isRecentPlay = true;
    }

    /**
     * 倒计时广播
     */
    private class AlarmReceive extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            long time = DataCenter.getInstance().getLotteryTime(mTicketId);
            if (isVisible) {
                String[] arr = LotteryNoUtil.getTime(time);
                String s = arr[0] + ":" + arr[1] + ":" + arr[2];
                setText(s);
                if (isRecentPlay) {
                    setBackground(ContextCompat.getDrawable(getContext(), R.drawable.ski_bg_bob_update_time));
                } else {
                    setBackground(null);
                }
                setTextColor(Color.parseColor(time >= 1000 && time < 6000 ? colorRed : colorNormal));

                if(0 == time) {
                   // RxBus.get().post(EVENT_DRAGON_ITEM_REQUEST_COUNT_DOWN,new Integer(mTicketId));
                }
            }
//            else {
//                setText("00:00:00");
//            }
        }
    }


    public CountdownLongDragonTextView(Context context) {
        super(context);
        init(context);
    }

    public CountdownLongDragonTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CountdownLongDragonTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!mAttached) {
            registerBCast();
        }
    }

    public void registerBCast() {
        try {
            mAttached = true;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(AlarmService.ALARM_ACTION + DataCenter.getInstance().getToken());
            alarmReceive = new AlarmReceive();
            mContext.registerReceiver(alarmReceive, intentFilter);
        } catch (Exception e) {

        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unregisterBCast();
    }

    public void unregisterBCast() {
        try {
            if (null != alarmReceive && mAttached) {
                mContext.unregisterReceiver(alarmReceive);
                alarmReceive = null;
                mAttached = false;
            }
        } catch (Exception e) {

        }
    }

    /*不可见时不刷新UI*/
    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        isVisible = visibility == View.VISIBLE;
    }

    /**
     * 开奖中
     */
    public void setDrawing() {
        isDrawing = true;
        setText("开奖中...");
        if (isRecentPlay) {
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.ski_bg_bob_update_time));
        } else {
            setBackground(ContextCompat.getDrawable(mContext, R.drawable.ski_bg_bob_update_time_red));
        }
    }

    /*暂停销售*/
    public void setPause() {
        isDrawing = true;
        setText("暂停销售");
    }

    public void setColorRed(String color) {
        colorRed = color;
    }

    public void setColorNormal(String color) {
        colorNormal = color;
    }

    /**
     * 设置开始显示的时间
     *
     * @param showTime
     */
    public void setShowTime(long showTime) {
        mShowTime = showTime;
    }

    /*设置彩种Id*/
    public void setTicketId(int ticketId) {
        mTicketId = ticketId;
    }

    public boolean isRequestByOutside() {
        return requestByOutside;
    }

    public void setRequestByOutside(boolean requestByOutside) {
        this.requestByOutside = requestByOutside;
    }
}

