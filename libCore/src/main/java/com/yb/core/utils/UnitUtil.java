package com.yb.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnitUtil {
    /**
     * 把大的数值以转换成小数值
     *
     * @param number 原数值
     * @return double
     */
    public static String numberToSmall(double number) {
        String transform = get2Decimal(number, true);
        if (number >= 10000) {
            transform = get2Decimal(number / 10000, true);
        } else if (number >= 100000000) {
            transform = get2Decimal(number / 100000000, true);
        }

        return transform;

    }

    /**
     * @param num 原数值
     * @return 返回保留两位小数的数值，如果保留的数值以0结尾，将会自动去掉0
     */
    public static String get2Decimal(String num) {
        if (isNumeric(num)) {
            return get2Decimal(Double.valueOf(num));
        } else {
            return num;
        }
    }

    public static String get2Decimal(double num) {
        return get2Decimal(num, false);
    }

    public static String get2Decimal(double num, boolean isReplace0) {
        DecimalFormat df = new DecimalFormat("0.##");
        if (!isReplace0) {
            df = new DecimalFormat("0.00");
        }
        return df.format(num);
    }

    /**
     * @param num 原数值
     * @return 返回保留一位小数的数值，
     */
    public static String get1Decimal(double num) {
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(num);
    }

    /**
     * @param num   原数值
     * @param floor 是否四舍五入
     * @return 返回保留三位小数的数值，
     */
    public static String get3Decimal(double num, boolean floor) {
        DecimalFormat df = new DecimalFormat("0.000");
        if (!floor) {
            df.setRoundingMode(RoundingMode.DOWN);
        }
        return df.format(num);
    }

    /**
     * @param num 原数值
     * @return 返回保留三位小数的数值，
     */
    public static String get3Decimal(double num) {
        return get3Decimal(num, false);
    }

    /**
     * @param num   原数值
     * @param floor 是否四舍五入
     * @return 返回保留四位小数的数值，
     */
    public static String get4Decimal(double num, boolean floor) {
        DecimalFormat df = new DecimalFormat("0.0000");
        if (!floor) {
            df.setRoundingMode(RoundingMode.DOWN);
        }
        return df.format(num);
    }

    /**
     * @param num 原数值
     * @return 返回保留四位小数的数值，
     */
    public static String get4Decimal(double num) {
        return get4Decimal(num, false);
    }

    /**
     * @param num 原数值
     * @return 返回保留两位小数的数值，如果保留的数值以0结尾，将会自动去掉0
     */
    public static String get0Decimal(double num) {
        return get0Decimal(num, false);
    }

    public static String get0Decimal(double num, boolean floor) {
        DecimalFormat df = new DecimalFormat("0");
        if (!floor) {
            df.setRoundingMode(RoundingMode.DOWN);
        }
        return df.format(num);
    }

    private static String getCH(int input) {
        String sd = "";
        switch (input) {
            case 1:
                sd = "一";
                break;
            case 2:
                sd = "二";
                break;
            case 3:
                sd = "三";
                break;
            case 4:
                sd = "四";
                break;
            case 5:
                sd = "五";
                break;
            case 6:
                sd = "六";
                break;
            case 7:
                sd = "七";
                break;
            case 8:
                sd = "八";
                break;
            case 9:
                sd = "九";
                break;
            default:
                break;
        }
        return sd;
    }

    /**
     * 去除以0结尾的小数
     *
     * @param p
     * @return
     */
    public static String getReplace0Decimal(double p) {
        DecimalFormat df = new DecimalFormat("0.###");
        df.setRoundingMode(RoundingMode.DOWN);
        return df.format(p);
    }

    public static String getDecimalDouble(double p) {
        DecimalFormat df = new DecimalFormat("#.00");//#,###.00
        return df.format(p);
    }

    /*判断字符是否是数字*/
    public static boolean isNumeric(String str) {
        // 该正则表达式可以匹配所有的数字 包括负数
        Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toPlainString();
        } catch (Exception e) {
            return false;
        }

        Matcher isNum = pattern.matcher(bigStr);
        return isNum.matches();
    }
}

