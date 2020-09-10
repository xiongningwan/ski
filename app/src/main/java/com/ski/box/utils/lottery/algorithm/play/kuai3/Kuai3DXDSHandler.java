package com.ski.box.utils.lottery.algorithm.play.kuai3;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

/**
 * 快三
 * 和值大小单双  43010101
 */
public class Kuai3DXDSHandler implements ITicketPlayHandler {

    public Kuai3DXDSHandler() {
    }

    @Override
    public String[] getBetNums(String betNums) {
        String[] nums = betNums.trim().split(",");
        return nums;
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = betNums.split(" ");
        return nums == null ? 0 : nums.length;
    }


    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = betNums.split(" ");
            return nums.length >= 1 && nums.length <= 4;
        }
    }

}
