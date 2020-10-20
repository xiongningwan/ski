package com.ski.box.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.LongDragonPushInfoEntity;
import com.ski.box.bean.lottery.LotteryConstant;
import com.ski.box.utils.lottery.LotteryNoUtil;
import com.ski.box.view.view.CountdownLongDragonTextView;
import com.yb.core.utils.AppUtil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 2020/5/12.
 */
public class DragonAdapter extends BaseMultiItemQuickAdapter<LongDragonPushInfoEntity, BaseViewHolder> {
    public static final int TYPE_CURRENT_LOTTERY = 1;
    public static final int TYPE_OTHER_LOTTERY = 2;
    private Typeface pingFangType;
    private Typeface tf;
    int curLotteryId;

    private List<CountdownLongDragonTextView> mCDList = new ArrayList<>();
    private List<Integer> minuteTicketList = new ArrayList<>();

    public DragonAdapter(Context context) {
        addItemType(TYPE_CURRENT_LOTTERY, R.layout.ski_item_double_dragon_current);
        addItemType(TYPE_OTHER_LOTTERY, R.layout.ski_item_double_dragon);

        AssetManager mgr = AppUtil.getContext().getAssets();
        tf = Typeface.createFromAsset(mgr, "fonts/DIN_Alternate_Bold.ttf");
        pingFangType = Typeface.createFromAsset(mgr, "fonts/pingfangscregular.ttf");
        curLotteryId = DataCenter.getInstance().getCurLotteryId();
        addMinuteTicketList();
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, LongDragonPushInfoEntity bean) {

        int itemViewType = holder.getItemViewType();
        holder.setIsRecyclable(false);
        String playName = bean.getPlayName();
        String value = bean.getPlayItemName();
        holder.setText(R.id.tv_play_d_dragon, String.format("%s-%s", playName, value));
        TextView view1 = holder.getView(R.id.tv_play_d_dragon);
        TextView view2 = holder.getView(R.id.tv_period_d_dragon);
        holder.setText(R.id.tv_key1, "连出");
        holder.setText(R.id.tv_key2, "期");
        view2.setTypeface(tf);
        view2.setText(String.valueOf(bean.getTimes()));
        view1.setTypeface(pingFangType);

        switch (itemViewType) {
            case TYPE_CURRENT_LOTTERY:
                break;
            case TYPE_OTHER_LOTTERY:
                TextView tv_name_d_dragon = holder.getView(R.id.tv_name_d_dragon);
                TextView ctv_d_dragon = holder.getView(R.id.ctv_d_dragon);
                tv_name_d_dragon.setTypeface(pingFangType);
                ctv_d_dragon.setTypeface(pingFangType);


                String ticketName = bean.getTicketName();
                holder.setText(R.id.tv_name_d_dragon, ticketName);
                long curEndSaleTime = bean.getCurEndSaleTime();
                CountdownLongDragonTextView countdownTextView = holder.getView(R.id.ctv_d_dragon);
                if (!mCDList.contains(countdownTextView)) {
                    mCDList.add(countdownTextView);
                }
                if (bean.isDrawing()) {
                    countdownTextView.setDrawing();
                } else {
                    countdownTextView.setTicketId(bean.getTicketId().intValue());
                    countdownTextView.setRequestByOutside(true);
                    long lotteryTime = DataCenter.getInstance().getLotteryTime(bean.getTicketId().intValue());
                    String[] arr = LotteryNoUtil.getTime(lotteryTime);
                    String s = arr[0] + ":" + arr[1] + ":" + arr[2];
                    countdownTextView.setText(s);
//                    if (curLotteryId != ticketId) {
//                        //如果都是分分彩,并且当前彩种也是分分彩
//                        boolean contains = minuteTicketList.contains(ticketId);
//                        boolean contains1 = minuteTicketList.contains(curLotteryId);
//                        if (contains && contains1) {
//
//                            setDownTime(curEndSaleTime, countdownTextView);
//
//                        } else {
//                            countdownTextView.setDate(curEndSaleTime - System.currentTimeMillis());
//                        }
//
//                    } else {
//                        setDownTime(curEndSaleTime, countdownTextView);
//                    }
                }
//                String trim = countdownTextView.getText().toString().trim();
//                if ("00:00:00".equalsIgnoreCase(trim)) {
//                    bean.setCurrentTime(0);
//                } else {
//                    bean.setCurrentTime(-1);
//                }

                break;
        }
    }

//    private void setDownTime(long curEndSaleTime, CountdownLongDragonTextView countdownTextView) {
//        long countDownTime = DataCenter.getInstance().getCountDownTime();
//        if (countDownTime > 0) {
//            countdownTextView.setDate(countDownTime - 1);
//        } else {
//            countdownTextView.setDate(curEndSaleTime - System.currentTimeMillis());
//        }
//    }

    public void unregisterBCast() {
        if (mCDList != null && mCDList.size() > 0) {
            for (int i = 0; i < mCDList.size(); i++) {
                CountdownLongDragonTextView countdownTextView = mCDList.get(0);
                if (countdownTextView != null) {
                    countdownTextView.unregisterBCast();
                }
            }
            mCDList.clear();
            getData().clear();
        }
    }

    public void  clearTimeList(){
        if (mCDList != null && mCDList.size() > 0){
            mCDList.clear();
        }
    }

    /**
     * 所有分分彩时间相同
     */
    private void addMinuteTicketList() {
        minuteTicketList.clear();
        minuteTicketList.add(LotteryConstant.LOTTERY_ID_PK10_JSSC);
        minuteTicketList.add(LotteryConstant.LOTTERY_ID_PK10_JSFT);
        minuteTicketList.add(LotteryConstant.LOTTERY_ID_SSC_XYFFC);
        minuteTicketList.add(LotteryConstant.LOTTERY_ID_SSC_TX);
        minuteTicketList.add(LotteryConstant.LOTTERY_ID_11X5_JS);
        minuteTicketList.add(LotteryConstant.LOTTERY_ID_K3_JISU);
        minuteTicketList.add(LotteryConstant.LOTTERY_ID_3D_JS);
        minuteTicketList.add(LotteryConstant.LOTTERY_ID_KL8_JS);
    }

}
