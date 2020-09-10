package com.ski.box.utils;

import java.math.BigDecimal;

public class DecimalSetUtils {
    /*有小数点 保留小数点后面3位数 并且去0,没有 小数点 去0*/
    public static String setMoneySaveThree(String money) {
        String[] split = money.split("\\.");
        if (split.length==2) {
            String xiaoshu = split[1];
            double integer = Double.valueOf(xiaoshu);
            if (integer == 0) {
                return split[0];
            }
            if (xiaoshu.length() > 3) {
                String substring = split[1].substring(0, 3);
                money = split[0] + "." + substring;
            }
        }
        money=new BigDecimal(money).stripTrailingZeros().toPlainString();
        return money;
    }

    /*不预留小数，超过小数点后4位截断*/
    public static String setMoneySaveFour(String money) {

        String[] split = money.split("\\.");
        if (split.length==2) {
            String xiaoshu = split[1];
            double integer = Double.valueOf(xiaoshu);
            if (integer == 0) {
                return split[0];
            }

            if (xiaoshu.length() > 4) {
                String substring = split[1].substring(0, 4);
                money = split[0] + "." + substring;
            }

        }
        money=new BigDecimal(money).stripTrailingZeros().toPlainString();

        return money;
    }

    public static String setMoneyLeastTwo(String money) {
        String[] split = money.split("\\.");
        if (split.length == 2) {
            String xiaoshu = split[1];
            int length = xiaoshu.length();
            if (length > 3) {
                money =split[0]+"." +xiaoshu.substring(0, 3);
            } else if ((length < 2)) {
                money=split[0]+"." +xiaoshu + "0";
            }


        } else {
            money=split[0]+ ".00";
        }
        return money;
    }

    public static String setMoneyLeastTwoFour(String money) {
        String[] split = money.split("\\.");
        if (split.length == 2) {
            String xiaoshu = split[1];
            int length = xiaoshu.length();
            if (length == 2||length == 4) {
            }else if (length < 2||length < 4){
                money = split[0] + "." + xiaoshu + "0";
            } else if (length>4) {
                money =split[0]+"." +xiaoshu.substring(0, 4);
            }
        }
        return money;
    }
}
