package com.ski.box.utils.lottery.shape;

import com.ski.box.utils.lottery.LotteryNoUtil;

/**
 * Created by tom on 2020/11/21.
 */
public class K3Util {
    /**
     * 总和点数，总和大小，总和单双，5对龙虎
     *
     * @param numArr 0-22 为小, 23-45 为大
     * @return
     */
    public static String[] getK3_ShapeData(String[] numArr) {
        String[] arr = new String[2];
        int sum = LotteryNoUtil.calculationSum(numArr);
        arr[0] = String.valueOf(sum);
        arr[1] = getBigOrSmall(numArr, sum,10);
//        arr[2] = getSingleDouble(sum);
        return arr;
    }


    public static   String getBigOrSmall(String[] numArr ,int sum, int middleValue) {
        if (numArr.length!=3){
            return "";
        }
        if (numArr[0].equalsIgnoreCase(numArr[1] )&& numArr[1].equalsIgnoreCase(numArr[2])){
            return "豹子";
        }else{
            return  getBigOrSmall(sum, middleValue);
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
