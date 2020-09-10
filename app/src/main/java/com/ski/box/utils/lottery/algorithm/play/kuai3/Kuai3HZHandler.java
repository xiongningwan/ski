package com.ski.box.utils.lottery.algorithm.play.kuai3;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

/**
 * 快三
 * 和值  43020101
 */
public class Kuai3HZHandler implements ITicketPlayHandler {

    public Kuai3HZHandler() {
    }

    @Override
    public String[] getBetNums(String betNums) {
        return new String[0];
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = betNums.split(",");
        if (nums == null) {
            return 0;
        } else {
            int betNum = 0;
            for (int i = 0; i < nums.length; ++i) {
                if (!"-".equals(nums[i])) {
                    String[] valid = nums[i].split(" ");
                    betNum += valid.length;
                }
            }
            return betNum;
        }
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = betNums.split(" ");
            if (nums.length >= 0) {
                if (nums[0].split(" ").length > 16) {
                    return false;
                }
                String[] var3 = nums;
                int var4 = nums.length;
                for (int var5 = 0; var5 < var4; ++var5) {
                    String num = var3[var5];
                    Integer i = Integer.valueOf(num);
                    if (i > 18 || i < 3) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

}
