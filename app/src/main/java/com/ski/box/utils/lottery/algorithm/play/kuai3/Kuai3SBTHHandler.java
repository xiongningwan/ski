package com.ski.box.utils.lottery.algorithm.play.kuai3;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 快三
 * 三不同号单选 43040101
 */
public class Kuai3SBTHHandler implements ITicketPlayHandler {

    public Kuai3SBTHHandler() {
    }

    @Override
    public String[] getBetNums(String betNums) {
        return new String[0];
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = betNums.trim().split(" ");
        List list = new Vector();
        String[] var4 = nums;
        int var5 = nums.length;

        for (int var6 = 0; var6 < var5; ++var6) {
            String s = var4[var6];
            list.add(s);
        }
        return list.size();
    }


    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = betNums.trim().split(" ");
            List validNums = new ArrayList();

            for (int i = 0; i < nums.length; ++i) {
                String[] var5 = ITicketPlayHandler.OFFSETS_KUAI3_3BTH;
                int var6 = var5.length;

                for (int var7 = 0; var7 < var6; ++var7) {
                    String s = var5[var7];
                    if (nums[i].equals(s)) {
                        validNums.add(s);
                    }
                }
            }

            if (nums.length != validNums.size()) {
                return false;
            } else {
                return true;
            }
        }
    }
}
