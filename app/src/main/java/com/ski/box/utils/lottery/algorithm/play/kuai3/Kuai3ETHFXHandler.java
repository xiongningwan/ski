package com.ski.box.utils.lottery.algorithm.play.kuai3;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * 快三
 * 二同号复选 43050201
 */
public class Kuai3ETHFXHandler implements ITicketPlayHandler {


    public Kuai3ETHFXHandler() {
    }

    @Override
    public String[] getBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return new String[0];
        } else {
            String[] nums = betNums.trim().split(" ");
            return nums;
        }
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = betNums.split(" ");
        int i = nums.length;
        List list = new Vector();
        String[] var5 = nums;
        int var6 = nums.length;

        for (int var7 = 0; var7 < var6; ++var7) {
            String s = var5[var7];
            list.add(s);
        }

        return list.size();
    }

    @Override
    public boolean validateBetNums(String betNums) {
        String[] nums = this.getBetNums(betNums);
        String[] sort = TicketPlayUtils.getFixedAndSortedNums(nums);
        if (nums.length != sort.length) {
            return false;
        } else {
            String[] var4 = nums;
            int var5 = nums.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                String num = var4[var6];
                if (Arrays.binarySearch(ITicketPlayHandler.OFFSETS_KUAI3_2THDXFS, num.trim()) < 0) {
                    return false;
                }
            }

            return true;
        }
    }

}
