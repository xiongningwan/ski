package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/**
 * 时时彩
 * 前三组选三 1030201
 * 中三组选三 1040201
 * 后三组选三 1050201
 */
public class ZXZ3PlayHanlder implements ITicketPlayHandler {
    private int[] offsets;
    private String[] numArray;

    public ZXZ3PlayHanlder( int[] offsets, String[] numArray) {
        this.offsets = offsets;
        this.numArray = numArray;
    }
    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = betNums.trim().split(" ");
            String[] fixedNums = TicketPlayUtils.getFixedAndSortedNums(nums);
            if (fixedNums.length >= 2 && fixedNums.length <= this.numArray.length && nums.length == fixedNums.length) {
                String[] var4 = fixedNums;
                int var5 = fixedNums.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    String num = var4[var6];
                    if (Arrays.binarySearch(this.numArray, num) < 0) {
                        return false;
                    }
                }

                return true;
            } else {
                return false;
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
        return nums.length * (nums.length - 1);
    }
}
