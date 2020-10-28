package com.ski.box.utils.lottery;

import com.ski.box.bean.DataCenter;
import com.ski.box.bean.DateBean;
import com.ski.box.bean.TicketLotteryTimeBean;
import com.ski.box.bean.lottery.LotteryConstant;
import com.ski.box.mvp.remote.LotteryModel;
import com.ski.box.mvp.remote.imodel.ILotteryModel;
import com.yb.core.base.BaseConsumer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.Headers;

/**
 * Created by tom on 2020/6/29.
 */
public class LotteryTimeUtil {
    private static  List<Integer> mLotteryIds = new ArrayList<>();
    private static ILotteryModel mLotteryModel = new LotteryModel();
    private static CompositeDisposable mDisposable;

    public static void countTime() {
        Map<Integer, Long> lotteryTimeMap = DataCenter.getInstance().getLotteryTimeMap();
        Map<Integer, String> lotteryPlanMap = DataCenter.getInstance().getLotteryPlanMap();
        for (Integer id : mLotteryIds) {
            Long time = lotteryTimeMap.get(id);
            String planId = lotteryPlanMap.get(id);
            if (time != null) {
                if (time != 0) {
                    time = time - 1000;
                } else {
                    time = resetTime(id);
                    planId = resetPlanId(planId);
                    lotteryPlanMap.put(id, planId);
                }
                lotteryTimeMap.put(id, time);
            }
        }
    }

    private static long resetTime(int id) {
        switch (id) {
            // 1分钟1期
            case LotteryConstant.LOTTERY_ID_PK10_JSSC:
            case LotteryConstant.LOTTERY_ID_PK10_JSFT:
            case LotteryConstant.LOTTERY_ID_SSC_TX:
            case LotteryConstant.LOTTERY_ID_SSC_JS:
            case LotteryConstant.LOTTERY_ID_SSC_XYFFC:
            case LotteryConstant.LOTTERY_ID_3D_JS:
            case LotteryConstant.LOTTERY_ID_11X5_JS:
            case LotteryConstant.LOTTERY_ID_KL8_JS:
            case LotteryConstant.LOTTERY_ID_K3_JISU:
                return 59000;
            // 1.5分钟1期
            case LotteryConstant.LOTTERY_ID_LHC_JS:
                return 89000;
            // 5分钟1期
            case LotteryConstant.LOTTERY_ID_3D_XY:
            case LotteryConstant.LOTTERY_ID_PK10_HLFT:
            case LotteryConstant.LOTTERY_ID_SSC_XY5FC:
            case LotteryConstant.LOTTERY_ID_SSC_HN5FC:
            case LotteryConstant.LOTTERY_ID_11X5_XY:
            case LotteryConstant.LOTTERY_ID_LHC_5F:
            case LotteryConstant.LOTTERY_ID_PK10_XYFT:
            case LotteryConstant.LOTTERY_ID_PK10_METFT:
            case LotteryConstant.LOTTERY_ID_K3_XY:
            case LotteryConstant.LOTTERY_ID_SSC_AZXY5:
            case LotteryConstant.LOTTERY_ID_PK10_AZ:
                return 299000;
            // 20分钟1期
            case LotteryConstant.LOTTERY_ID_PK10_BJ:
            case LotteryConstant.LOTTERY_ID_SSC_CQ:
            case LotteryConstant.LOTTERY_ID_SSC_XJ:
            case LotteryConstant.LOTTERY_ID_SSC_TJ:
            case LotteryConstant.LOTTERY_ID_SSC_HLJ:
            case LotteryConstant.LOTTERY_ID_11X5_GD:
            case LotteryConstant.LOTTERY_ID_11X5_SD:
            case LotteryConstant.LOTTERY_ID_11X5_JX:
            case LotteryConstant.LOTTERY_ID_11X5_FJ:
            case LotteryConstant.LOTTERY_ID_11X5_GX:
            case LotteryConstant.LOTTERY_ID_K3_JL:
            case LotteryConstant.LOTTERY_ID_K3_JINAGSU:
            case LotteryConstant.LOTTERY_ID_K3_AH:
            case LotteryConstant.LOTTERY_ID_K3_GX:
            case LotteryConstant.LOTTERY_ID_K3_FJ:
                return 1199000;
            // 1天1期
            case LotteryConstant.LOTTERY_ID_3D_FC:
            case LotteryConstant.LOTTERY_ID_PL35_PL35:
            case LotteryConstant.LOTTERY_ID_KL8_BJ:
                return 86399000;
            // 每周2-3期
            case LotteryConstant.LOTTERY_ID_LHC_XG:
                // 2 4 6
                // 星期六3天，其他2天
                return 172799000;
            //return 259200;
            default:
                return 59000;
        }
    }

    private static String resetPlanId(String planId) {
        if (planId != null) {
            if (planId.contains("-")) {
                String[] arr = planId.split("-");
                try {
                    if (arr.length == 2) {
                        String s = arr[1];
                        StringBuilder sub = new StringBuilder();
//                        for (int k = 0; k < s.length(); k++) {
//                            String c = String.valueOf(s.charAt(k));
//                            if (!"0".equals(c)) {
//                                sub=s.substring(0, k);
//                                break;
//                            }
//                        }
                        long planSub_L = Long.parseLong(arr[1]) + 1;
                        int dex = s.length() - String.valueOf(planSub_L).length();
                        for (int i = 0; i < dex; i++) {
                            sub.insert(0, "0");
                        }
                        planId = arr[0] + "-" + sub + planSub_L;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    planId = String.valueOf(Long.parseLong(planId) + 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return planId;
    }

    public static void setLotteryIds(List<Integer> lotteryIds) {
        mLotteryIds.clear();
        mLotteryIds.addAll(lotteryIds);
    }

    public static void addLotteryIds(int lotteryId) {
        if (!mLotteryIds.contains(lotteryId)) {
            mLotteryIds.add(lotteryId);
        }
    }


    public static List<Integer> getLotteryIds() {
        return mLotteryIds;
    }

    public static void syncLotteryTimeAll() {
        StringBuilder sb = new StringBuilder();
        for (Integer id : mLotteryIds) {
            sb.append(id).append(",");
        }
        // 去除最后一个竖线
        if (sb.length() - 1 >= 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        syncLotteryTime(sb.toString());
    }

    public static void syncLotteryTime(String ids) {
        Disposable disposable2 = mLotteryModel.getGetHeadTicketInfo(new Consumer<List<TicketLotteryTimeBean>>() {
            @Override
            public void accept(List<TicketLotteryTimeBean> data) throws Exception {
                if (data != null && data.size() > 0) {
                    Disposable disposable = mLotteryModel.getServiceTime(new Consumer<retrofit2.Response<DateBean>>() {
                        @Override
                        public void accept(retrofit2.Response<DateBean> dateBeanResponse) throws Exception {
                            Headers headers = dateBeanResponse.headers();
                            String s = headers.get("date");
                            SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
                            Date date = format.parse(s);
                            long serverTime = date.getTime();
                            DataCenter.getInstance().setServerTime(serverTime);
                            for (TicketLotteryTimeBean bean : data) {
                                long endTime = bean.getEndTime() /** 1000*/;
                                long countDownTime = Math.abs(endTime - serverTime);
                                DataCenter.getInstance().updateLotteryTime(bean.getTicketId(), bean.getPlanId(), countDownTime);
                            }
                        }
                    }, new BaseConsumer(false, false) {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            super.accept(throwable);
                        }
                    });
                    addDisposable(disposable);
                }
            }
        }, new BaseConsumer(false, false) {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
            }
        }, ids);
        addDisposable(disposable2);

    }

    public static void unSubscribe() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }

    }

    public static void addDisposable(Disposable subscription) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(subscription);
    }


    /**
     * 判断是否是20分钟一期的彩种
     * @return
     */
    public static  boolean  isShowPrizePeriodChange(){
        int curLotteryId = DataCenter.getInstance().getCurLotteryId();
        switch (curLotteryId){
            case LotteryConstant.LOTTERY_ID_PK10_BJ:
            case LotteryConstant.LOTTERY_ID_SSC_CQ:
            case LotteryConstant.LOTTERY_ID_SSC_XJ:
            case LotteryConstant.LOTTERY_ID_SSC_TJ:
            case LotteryConstant.LOTTERY_ID_SSC_HLJ:
            case LotteryConstant.LOTTERY_ID_11X5_GD:
            case LotteryConstant.LOTTERY_ID_11X5_SD:
            case LotteryConstant.LOTTERY_ID_11X5_JX:
            case LotteryConstant.LOTTERY_ID_11X5_FJ:
            case LotteryConstant.LOTTERY_ID_11X5_GX:
            case LotteryConstant.LOTTERY_ID_K3_JL:
            case LotteryConstant.LOTTERY_ID_K3_JINAGSU:
            case LotteryConstant.LOTTERY_ID_K3_AH:
            case LotteryConstant.LOTTERY_ID_K3_GX:
            case LotteryConstant.LOTTERY_ID_K3_FJ:
                return true;
            default:
                return  false;
        }
    }
}
