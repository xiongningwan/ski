package com.ski.box.utils.lottery.algorithm.play.elevenfive;


import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/**
 * 11选五
 * 不定胆_115 4030101
 */
public class BDW115PlayHandler implements ITicketPlayHandler {
    private int bdwNum = 1;
    private String[] numsArr;


    public BDW115PlayHandler() {
        this.numsArr = NUMS_115;
    }

    private static int bdwNum(int betNum, int bdwNum) {
        int upCount = 1;
        int downCount = 1;
        int a;
        for (a = 0; a < bdwNum; ++a) {
            upCount *= betNum - a;
        }

        for (a = 1; a <= bdwNum; ++a) {
            downCount *= a;
        }

        return upCount / downCount;
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = this.getBetNums(betNums);
        return nums == null ? 0 : bdwNum(nums.length, this.bdwNum);
    }


    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = betNums.trim().split(" ");
            String[] fixedNums = TicketPlayUtils.getFixedAndSortedNums(nums);
            if (fixedNums.length <= this.numsArr.length && nums.length == fixedNums.length) {
                String[] var4 = fixedNums;
                int var5 = fixedNums.length;

                for (int var6 = 0; var6 < var5; ++var6) {
                    String num = var4[var6];
                    if (Arrays.binarySearch(this.numsArr, num) < 0) {
                        return false;
                    }
                }

                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public String[] getBetNums(String betNums) {
        String[] nums = betNums.trim().split(" ");
        return TicketPlayUtils.getFixedNums(nums);
    }
}
