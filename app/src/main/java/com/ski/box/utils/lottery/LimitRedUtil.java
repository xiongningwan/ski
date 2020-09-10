package com.ski.box.utils.lottery;

import com.ski.box.bean.MkBetParamEntity;
import com.ski.box.bean.RedLimitBean;
import com.ski.box.bean.lottery.LotteryUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 2020/8/21.
 */
public class LimitRedUtil {

    // 遍历检查限红
    public static ArrayList<RedLimitBean> isMeetBettingInterval(List<MkBetParamEntity.BetParamEntity> doubleConfirmBeans) {
        ArrayList<RedLimitBean> noMeet = new ArrayList<>();
        Long[] arr = getBetRang();
        for (int i = 0; i < doubleConfirmBeans.size(); i++) {
            MkBetParamEntity.BetParamEntity doubleConfirmBean = doubleConfirmBeans.get(i);
            if (isRang(doubleConfirmBean, arr)) {// 在范围
                if (doubleConfirmBean.isRedLimit()) {
                    doubleConfirmBean.setRedLimit(false);
                    // 改变了就刷新
                  //  mShoppingAdapter.notifyItemChanged(i);
                } else {
                    doubleConfirmBean.setRedLimit(false);
                }
            } else {
                if (!doubleConfirmBean.isRedLimit()) {// 不在范围
                    doubleConfirmBean.setRedLimit(true);
                    // 改变了就刷新
                  //  mShoppingAdapter.notifyItemChanged(i);
                } else {
                    doubleConfirmBean.setRedLimit(true);
                }

                RedLimitBean redLimitBean = new RedLimitBean();
                String playName = doubleConfirmBean.getGroupName();
                String playItemName = doubleConfirmBean.getContent();
                String playItemId = doubleConfirmBean.getPlayId();
                redLimitBean.setPlayItemId(playItemId);
                redLimitBean.setPlayName(playName);
                redLimitBean.setPlayItemName(playItemName);
                noMeet.add(redLimitBean);
            }
        }
        return noMeet;
    }

    public static Long[] getBetRang() {
        Long[] arr = new Long[2];
        //获取区间投注金额
        String rang = SettingManager.getDoubleBetAmountRange();
        Long minAmount = null;
        Long maxAmount = null;
        if (!rang.isEmpty()) {
            minAmount = Long.valueOf(rang.split("～")[0]);
            maxAmount = Long.valueOf(rang.split("～")[1]);
        }
        arr[0] = minAmount;
        arr[1] = maxAmount;
        return arr;
    }

    /**
     * 是不是再范围
     */
    public static boolean isRang(MkBetParamEntity.BetParamEntity doubleConfirmBean, Long[] arr) {
        //获取区间投注金额
        Long minAmount = arr[0];
        Long maxAmount = arr[1];

        String playItemCode = doubleConfirmBean.getContent();
        double itemAmount = doubleConfirmBean.getBetAmount_d();
        boolean contains = LotteryUtil.analyzingCode.contains(playItemCode);
        if (contains) {
//            没有限制
            if (minAmount != null) {
//                判断是否符合投注条件
                if (itemAmount >= minAmount && itemAmount <= maxAmount) {
//                    满足条件
                    return true;
                } else {
//                    不满足条件
                    return false;
                }
            }
        }
        return true;
    }
}
