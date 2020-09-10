package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/**
 * 时时彩
 * 定位胆 1080101
 */
public class DWDPlayHandler implements ITicketPlayHandler {

    private int[] offsets;
    private String[] numArray;

    public DWDPlayHandler(int[] offsets, String[] numArray) {
        this.offsets = offsets;
        this.numArray = numArray;
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = betNums.trim().split(",");
            if (nums.length != this.offsets.length) {
                return false;
            } else {
                int fixLen = 0;
                String[] var4 = nums;
                int var5 = nums.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    String num = var4[var6];
                    if (!"-".equals(num)) {
                        String[] subNums = num.split(" ");
                        String[] fixedSubNums = TicketPlayUtils.getFixedAndSortedNums(subNums);
                        if (subNums.length != fixedSubNums.length) {
                            return false;
                        }
                        String[] var10 = subNums;
                        int var11 = subNums.length;

                        for(int var12 = 0; var12 < var11; ++var12) {
                            String subNum = var10[var12];
                            if (Arrays.binarySearch(this.numArray, subNum) < 0) {
                                return false;
                            }
                        }
                    } else {
                        ++fixLen;
                    }
                }
                return fixLen != nums.length;
            }
        }
    }
    @Override
    public String[] getBetNums(String betNums) {
        String[] nums = betNums.trim().split(",");
        String[] res = new String[nums.length];
        for(int i = 0; i < nums.length; ++i) {
            res[i] = nums[i].trim();
        }
        return res;
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = this.getBetNums(betNums);
        if (nums == null) {
            return 0;
        } else {
            int betNum = 0;
            String[] var4 = nums;
            int var5 = nums.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String num = var4[var6];
                if (!"-".equals(num) && StringUtils.isNotEmpty(num)) {
                    betNum += num.split(" ").length;
                }
            }
            return betNum;
        }
    }
}
