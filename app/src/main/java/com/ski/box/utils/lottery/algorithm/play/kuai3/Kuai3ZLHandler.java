package com.ski.box.utils.lottery.algorithm.play.kuai3;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

/**
 * 快三
 * 杂六 43040401
 */
public class Kuai3ZLHandler implements ITicketPlayHandler {

    public Kuai3ZLHandler() {
    }

    @Override
    public String[] getBetNums(String betNums) {
        return new String[0];
    }

    @Override
    public int calculateBetNum(String betNums) {
        int index = 0;
        String str = betNums.replace(",", " ");
        String[] nums = str.split(" ");
        for (int i = 0; i < nums.length; i++) {
            String num = nums[i];
            if (!"-".equalsIgnoreCase(num)) {
                index += 1;
            }
        }
        return index;
    }


    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            if (betNums.contains(",")) {
                return true;
            }
        }
        return false;
    }
}
