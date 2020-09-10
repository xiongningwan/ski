package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

/**
 * 时时彩
 * 前四组选6  1020203
 * 后四组选6   1020403
 */
public class ZX6PlayHanlder implements ITicketPlayHandler {
    private int[] offsets;

    public ZX6PlayHanlder( int[] offsets) {
        this.offsets = offsets;
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (!StringUtils.isEmpty(betNums) && betNums.replaceAll(" ", "").trim().matches("(?!\\d*?(\\d)\\d*?\\1)\\d{2,10}")) {
            String[] nums = betNums.trim().split(" ");
            String[] fixedNums = TicketPlayUtils.getFixedAndSortedNums(nums);
            if (nums.length == fixedNums.length && nums.length >= 2 && nums.length <= 10) {
                String[] var4 = nums;
                int var5 = nums.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    String num = var4[var6];
                    if (!num.trim().matches("\\d{1}")) {
                        return false;
                    }
                }

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String[] getBetNums(String betNums) {
        String[] nums = betNums.trim().split(" ");
        String[] res = new String[nums.length];

        for(int i = 0; i < nums.length; ++i) {
            res[i] = nums[i].trim();
        }

        return res;
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = this.getBetNums(betNums);
        return nums.length * (nums.length - 1) / 2;
    }
}
