package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/**
 * 时时彩
 * 前二大小单双组合 1100101
 * 前三大小单双组合 1100102
 * 后二大小单双组合 1100103
 * 后三大小单双组合 1100104
 */
public class DXDSPlayHandler implements ITicketPlayHandler {
    private String[] numArray;
    private int[] offsets;

    public DXDSPlayHandler(String[] numArray, int[] offsets) {
        this.numArray = numArray;
        this.offsets = offsets;
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = betNums.trim().split(",");
            if (nums.length != this.offsets.length) {
                return false;
            } else {
                String[] fixedNums = TicketPlayUtils.getFixedAndSortedNums(nums);
                String[] var4 = fixedNums;
                int var5 = fixedNums.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    String num = var4[var6];
                    String[] array = TicketPlayUtils.stringtoArray(num.replaceAll(" ", ""));
                    String[] noRepearted = TicketPlayUtils.getFixedAndSortedNums(array);
                    if (noRepearted.length != array.length) {
                        return false;
                    }

                    for(int i = 0; i < array.length; ++i) {
                        if (Arrays.binarySearch(this.numArray, array[i]) < 0) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
    }

    @Override
    public String[] getBetNums(String betNums) {
        String[] nums = betNums.trim().split(",");
        return TicketPlayUtils.getFixedNums(nums);
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = betNums.split(",");
        if (nums == null) {
            return 0;
        } else {
            int betNum = 1;
            String[] var4 = nums;
            int var5 = nums.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String num = var4[var6];
                betNum *= num.replaceAll(" ", "").length();
            }

            return betNum;
        }
    }
}
