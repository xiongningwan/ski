package com.ski.box.utils.lottery.algorithm.play.kuaile8;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.algorithm.BinomialCoeff;
import com.ski.box.utils.lottery.algorithm.utils.exception.GlobalServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * 快乐彩
 * 任选
 * "26010101",IAllPlayCode.任选一
 * "26010102",IAllPlayCode.任选二
 * "26010103",IAllPlayCode.任选三
 * "26010104",IAllPlayCode.任选四
 * "26010105",IAllPlayCode.任选五
 * "26010106",IAllPlayCode.任选六
 * "26010107",IAllPlayCode.任选七
 */
public class ZXPlayHandler implements ITicketPlayHandler {
    private Integer choose;
    private String code;


    public ZXPlayHandler(Integer choose, String code) {
        this.choose = choose;
        this.code = code;
    }


    @Override
    public String[] getBetNums(String betNums) {
        String[] nums = betNums.split(",");
        String numsTop = "-";
        String numbsboot = "-";
        if (!"-".equals(nums[0])) {
            numsTop = betNums.substring(0, betNums.indexOf(","));
        }
        if (!"-".equals(nums[1])) {
            numbsboot = betNums.substring(betNums.indexOf(",") + 1);
        }
        List<String> list = new ArrayList();
        String[] newStr;
        String[] var8;
        int var9;
        int var10;
        String s;
        if (numsTop.indexOf("-") == -1) {
            newStr = numsTop.split(" ");
            var8 = newStr;
            var9 = newStr.length;

            for(var10 = 0; var10 < var9; ++var10) {
                s = var8[var10];
                list.add(s);
            }
        }

        if (numbsboot.indexOf("-") == -1) {
            newStr = numbsboot.split(" ");
            var8 = newStr;
            var9 = newStr.length;

            for(var10 = 0; var10 < var9; ++var10) {
                s = var8[var10];
                list.add(s);
            }
        }

        newStr = new String[list.size()];

        for(int i = 0; i < newStr.length; ++i) {
            newStr[i] = list.get(i);
        }
        return leftAddZeor(newStr);
    }
    @Override
    public int calculateBetNum(String betNums) throws GlobalServiceException {
        if (this.choose <= 7) {
            String[] betNumArray = this.getBetNums(betNums);
            System.out.println(betNumArray.length);
            if (this.choose > betNumArray.length) {
                throw new GlobalServiceException("不符合组选规则");
            } else {
                Long value = BinomialCoeff.compute2(betNumArray.length, this.choose);
                if (value > 2147483647L) {
                    throw new GlobalServiceException("计算值过大");
                } else {
                    return value.intValue();
                }
            }
        } else {
            throw new GlobalServiceException("无此玩法");
        }
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (betNums != null && !"".equals(betNums)) {
            String[] betNumArray = this.getBetNums(betNums);
            int arrayleng = betNumArray.length;
            String[] var4 = betNumArray;
            int var5 = betNumArray.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String str = var4[var6];

                try {
                    if (Integer.parseInt(str) > 80) {
                        return false;
                    }
                } catch (Exception var9) {
                    return false;
                }
            }

            return Stream.of(betNumArray).distinct().count() == (long) arrayleng;
        } else {
            return false;
        }
    }

    public static String[] leftAddZeor(String[] nums) {
        String[] numsArray = new String[nums.length];

        for(int i = 0; i < nums.length; ++i) {
            String str = nums[i];
            if (nums[i].length() == 1) {
                str = String.format("0%s", nums[i]);
            }
            numsArray[i] = str;
        }
        return numsArray;
    }

}
