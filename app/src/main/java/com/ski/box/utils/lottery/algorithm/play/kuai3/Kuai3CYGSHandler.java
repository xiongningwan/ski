package com.ski.box.utils.lottery.algorithm.play.kuai3;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.List;
import java.util.Vector;

/**
 * 快三
 * 猜一个号 43070101
 */
public class Kuai3CYGSHandler implements ITicketPlayHandler {

    public Kuai3CYGSHandler() {
    }

    @Override
    public String[] getBetNums(String betNums) {
        return new String[0];
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = betNums.split(" ");
        List list = new Vector();

        for (int i = 0; i < nums.length; ++i) {
            list.add(nums[i]);
        }

        return list.size();
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = betNums.split(" ");
            if (nums.length <= 6 && nums.length >= 1) {
                for (int i = 0; i < nums.length; ++i) {
                    int num = Integer.valueOf(nums[i]);
                    if (num < 1 || num > 6) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    }
}
