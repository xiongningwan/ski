package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

/**
 * 時時彩
 * 牛牛 1200101
 */
public class NIUPlayHandler implements ITicketPlayHandler {
    private String[] numArray;

    public NIUPlayHandler(String[] numArray) {
        this.numArray = numArray;
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = betNums.trim().split(" ");
            if (nums.length > this.numArray.length) {
                return false;
            } else {
                String[] fixedNums = TicketPlayUtils.getFixedAndSortedNums(nums);
                String[] var4 = fixedNums;
                int var5 = fixedNums.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    String num = var4[var6];
                    int target = Integer.valueOf(num);
                    if (target < 0 || target > 14) {
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
        return TicketPlayUtils.getFixedNums(nums);
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = this.getBetNums(betNums);
        return nums == null ? 0 : nums.length;
    }
}
