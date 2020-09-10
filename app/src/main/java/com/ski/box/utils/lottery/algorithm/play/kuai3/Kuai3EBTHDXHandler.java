package com.ski.box.utils.lottery.algorithm.play.kuai3;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 快三
 * 二不同号单选 43060102
 */
public class Kuai3EBTHDXHandler implements ITicketPlayHandler {

    public Kuai3EBTHDXHandler() {
    }

    @Override
    public String[] getBetNums(String betNums) {
        return new String[0];
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = betNums.split(" ");
        List list = new ArrayList();
        String[] var4 = nums;
        int var5 = nums.length;

        for (int var6 = 0; var6 < var5; ++var6) {
            String num = var4[var6];
            list.add(num);
        }

        return list.size();
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = betNums.trim().split(" ");
            List list = new ArrayList();

            for (int i = 0; i < nums.length; ++i) {
                String[] var5 = ITicketPlayHandler.OFFSETS_KUAI3_2BTHDX;
                int var6 = var5.length;

                for (int var7 = 0; var7 < var6; ++var7) {
                    String s = var5[var7];
                    if (nums[i].equals(s)) {
                        list.add(nums[i]);
                    }
                }
            }

            if (nums.length != list.size()) {
                return false;
            } else {
                return nums.length >= 1 && nums.length <= 15;
            }
        }
    }
}
