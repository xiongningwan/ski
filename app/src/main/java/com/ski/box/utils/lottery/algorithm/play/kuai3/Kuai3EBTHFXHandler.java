package com.ski.box.utils.lottery.algorithm.play.kuai3;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

/**
 * 快三
 * 二不同号复选 43060101
 */
public class Kuai3EBTHFXHandler implements ITicketPlayHandler {

    public Kuai3EBTHFXHandler() {
    }

    @Override
    public String[] getBetNums(String betNums) {
        return new String[0];
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = betNums.split(",");
        String[] preFixSegment = nums[0].split(" ");
        int num = preFixSegment.length;
        return num * (num - 1) / 2;
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = betNums.trim().split(" ");

            for (int i = 0; i < nums.length; ++i) {
                Integer num = Integer.valueOf(nums[i]);
                if (num < 1 || num > 6) {
                    return false;
                }
            }
            return true;
        }
    }
}
