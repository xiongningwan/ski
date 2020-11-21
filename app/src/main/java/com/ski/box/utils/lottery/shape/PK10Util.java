package com.ski.box.utils.lottery.shape;

import com.ski.box.utils.lottery.LotteryNoUtil;

/**
 * Created by tom on 2020/11/21.
 */
public class PK10Util {
    /**
     * 冠亚和，冠亚和大小，冠亚和单双，5对龙虎
     *
     * @param numArr 0-22 为小, 23-45 为大
     * @return
     */
    public static String[] getPK10_ShapeData(String[] numArr) {
        String[] arr = new String[8];
        // 冠亚和
        int sum = Integer.parseInt(numArr[0]) + Integer.parseInt(numArr[1]);
        arr[0] = String.valueOf(sum);
        arr[1] = getBigOrSmall(sum,11);
        arr[2] = getSingleDouble(sum);
        arr[3] = dragonTigerSum(1, numArr);
        arr[4] = dragonTigerSum(2, numArr);
        arr[5] = dragonTigerSum(3, numArr);
        arr[6] = dragonTigerSum(4, numArr);
        arr[7] = dragonTigerSum(5, numArr);
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
