package com.ski.box.utils.lottery.shape;

import com.ski.box.utils.lottery.LotteryNoUtil;

/**
 * Created by tom on 2020/11/21.
 */
public class SSCUtil {
    /**
     * 时时彩,大小,单双,龙虎
     *
     * @param numArr 0-22 为小, 23-45 为大
     * @return
     */
    public static String[] getSSC_ShapeData(String[] numArr) {
        String[] arr = new String[4];
        int sum = LotteryNoUtil.calculationSum(numArr);
        String danShuang = getSingleDouble(sum, numArr);
        // 大小
        String daXiao = getBigOrSmall(sum, 22);
        String longHuHe = dragonTigerSum(1, numArr);
        arr[0] = String.valueOf(sum);
        arr[1] = daXiao;
        arr[2] = danShuang;
        arr[3] = longHuHe;
        return arr;
    }

    public static String getSingleDouble(int sum, String... arr) {
        if (sum == 49) {
            return "和";
        }
        if (sum % 2 == 0) {
            return "双";
        } else {
            return "单";
        }
    }

    public static String getBigOrSmall(int num, int middleValue) {
        if (num <= middleValue) {
            return "小";
        } else {
            return "大";
        }
    }

    /**
     * SSC 判断龙虎和
     */
    public static String dragonTigerSum(int beginPosition, String... codes) {
        if (codes != null && beginPosition < codes.length) {
            int lhs = Integer.parseInt(codes[beginPosition - 1]);
            int rhs = Integer.parseInt(codes[codes.length - beginPosition]);
            if (lhs > rhs) {
                return "龙";
            } else if (lhs < rhs) {
                return "虎";
            } else {
                return "和";
            }
        } else {
            return "";
        }
    }
}
