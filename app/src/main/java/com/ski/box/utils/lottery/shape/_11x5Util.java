package com.ski.box.utils.lottery.shape;

import com.ski.box.utils.lottery.LotteryNoUtil;

/**
 * Created by tom on 2020/11/21.
 */
public class _11x5Util {
    public static String[] get11X5_ShapeData(String[] numArr) {
        String[] arr = new String[4];
        int sum = LotteryNoUtil.calculationSum(numArr);
        String daXiao = getBigOrSmall(sum, 30, numArr);
        String danShuang = getSingleDouble(sum, numArr);
        String longHuHe = SSCUtil.dragonTigerSum(1, numArr);
        String weiDaXiao = getWeiDaXiao(sum, numArr);
        arr[0] = String.valueOf(sum);
        arr[1] = daXiao;
        arr[2] = danShuang;
//        arr[3] = weiDaXiao;
        arr[3] = longHuHe;
        return arr;
    }

    public static String getBigOrSmall(int sum, int value, String... arr) {
        if (sum == value) {
            return "和";
        } else if (sum > value) {
            return "大";
        } else {
            return "小";
        }
    }

    public static String getSingleDouble(int sum, String... arr) {
        if (sum % 2 == 0) {
            return "双";
        } else {
            return "单";
        }
    }

    public static String getWeiDaXiao(int sum, String... arr) {
        int weiShuInt = Integer.valueOf(sum) % 10;
        String weiShu = "尾大";
        if (weiShuInt >= 5) {
            weiShu = "尾大";
        } else {
            weiShu = "尾小";
        }
        return weiShu;
    }


}
