package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/** 时时彩
 * 五星和值大小单双 1100201
 */
public class HZDXDSPlayHandler implements ITicketPlayHandler {
    private String[] numArray;
    private int[] offsets;

    public HZDXDSPlayHandler(String[] numArray, int[] offsets) {
        this.numArray = numArray;
        this.offsets = offsets;
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = betNums.trim().split(" ");
            if (nums.length > 4) {
                return false;
            } else {
                String[] fixedNums = TicketPlayUtils.getFixedAndSortedNums(nums);
                if (nums.length != fixedNums.length) {
                    return false;
                } else {
                    String[] var4 = fixedNums;
                    int var5 = fixedNums.length;

                    for(int var6 = 0; var6 < var5; ++var6) {
                        String num = var4[var6];
                        if (Arrays.binarySearch(this.numArray, num) < 0) {
                            return false;
                        }
                    }

                    return true;
                }
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
