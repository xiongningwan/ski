package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/**
 * 时时彩
 * 一帆风顺 1110101
 * 好事成双 1110102
 * 三星报喜 1110103
 * 四季发财 1110104
 */
public class QWTSPlayHandler implements ITicketPlayHandler {
    private String[] numArray;

    public QWTSPlayHandler( String[] numArray) {
        this.numArray = numArray;
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = this.getBetNums(betNums);
            if (nums.length > this.numArray.length) {
                return false;
            } else {
                String[] fixedNums = TicketPlayUtils.getFixedAndSortedNums(nums);
                if (fixedNums.length != nums.length) {
                    return false;
                } else {
                    String[] var4 = fixedNums;
                    int var5 = fixedNums.length;

                    for(int var6 = 0; var6 < var5; ++var6) {
                        String num = var4[var6];
                        if (Arrays.binarySearch(this.numArray, num) < 0) {
                            return false;
                        }
                    }

                    return true;
                }
            }
        }
    }

    @Override
    public String[] getBetNums(String betNums) {
        String[] nums = betNums.trim().split(" ");
        return TicketPlayUtils.getFixedNums(nums);
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = this.getBetNums(betNums);
        return nums == null ? 0 : nums.length;
    }
}
