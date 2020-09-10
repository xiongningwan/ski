package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.bean.lottery.IAllPlayCode;
import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

/**
 * 时时彩 P3P5
 * 三星和值大小单双 11100208
 */
public class HZDXDSPlayHandler2 implements ITicketPlayHandler {
    private String code;
    private String[] numArray;

    public HZDXDSPlayHandler2(String code, String[] numArray) {
        this.code = code;
        this.numArray = numArray;
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
      /*      String[] numsDigit = betNums.trim().split(",");
            boolean dup = false;
            for (String str : numsDigit) {
                String[] nums = str.trim().split(" ");
                if (nums.length > 4) {
                    dup = false;
                    return dup;
                } else {
                    String[] fixedNums = TicketPlayUtils.getFixedAndSortedNums(nums);
                    if (nums.length != fixedNums.length) {
                        dup = false;
                        return dup;
                    } else {
                        String[] var4 = fixedNums;
                        int var5 = fixedNums.length;

                        for (int var6 = 0; var6 < var5; ++var6) {
                            String num = var4[var6];
                            if (Arrays.binarySearch(this.numArray, num) < 0) {
                                dup = false;
                                return dup;
                            }
                        }
                        dup = true;
                        return dup;
                    }
                }
            }
            return dup;*/
            return true;
        }
    }

    @Override
    public String[] getBetNums(String betNums) {
        String[] nums = betNums.trim().split(" ");
        return TicketPlayUtils.getFixedNums(nums);
    }


    @Override
    public int calculateBetNum(String betNums) {
        if (IAllPlayCode.三星和值大小单双.equalsIgnoreCase(code)){
             return  getHeZhiDaXiaoDanShuang(betNums);
        }else{
            String[] numsDigit = betNums.trim().split(",");
            int num = 0;
            for (String str : numsDigit) {
                String[] nums = this.getBetNums(str);
                num += nums.length;
            }
            return num;
        }
    }

    private int getHeZhiDaXiaoDanShuang(String splicingStr) {
        int count = 0;
        String[] split = splicingStr.split(",");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (!"-".equalsIgnoreCase(s)) {
                String[] s1 = s.split(" ");
                count += s1.length;
            }
        }
        return count;
    }


}
