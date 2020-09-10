package com.ski.box.utils.lottery.algorithm.play.pk10;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/**
 * PK10
 * 猜冠军直选复式 18010101
 */
public class ZHX1PK10PlayHandler implements ITicketPlayHandler {

    public ZHX1PK10PlayHandler() {

    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = this.getBetNums(betNums);
            if (nums.length > 10) {
                return false;
            } else {
                String[] var3 = nums;
                int var4 = nums.length;
                Arrays.sort(NUMS_PK10_old);
                for(int var5 = 0; var5 < var4; ++var5) {
                    String bet = var3[var5];
                    if (Arrays.binarySearch(NUMS_PK10_old, bet.trim()) < 0) {
                        return false;
                    }
                }

                return true;
            }
        }
    }

    @Override
    public String[] getBetNums(String betNums) {
        String[] nums = betNums.trim().split(" ");
        String[] res = new String[nums.length];

        for(int i = 0; i < nums.length; ++i) {
            res[i] = nums[i].trim();
        }

        return res;
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = this.getBetNums(betNums);
        return nums == null ? 0 : nums.length;
    }
}
