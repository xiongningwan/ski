package com.ski.box.utils.lottery.algorithm.play.pk10;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 时时彩
 * 冠亚和值 18060101
 * 前三和值 18060102
 */
public class HZPK10PlayHandler implements ITicketPlayHandler {
    private int[] offsets;
    private int hzType;

    public HZPK10PlayHandler( int hzType, int[] offsets) {
        this.offsets = offsets;
        this.hzType = hzType;
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isBlank(betNums)) {
            return false;
        } else {
            String[] nums = betNums.split(" ");
            AtomicReference<Boolean> flag = new AtomicReference(true);
            for (String num : Arrays.asList(nums)) {
                if (this.hzType == 1) {
                    if (Integer.valueOf(num) > 19 || Integer.valueOf(num) < 3) {
                        flag.set(false);
                    }
                } else if (this.hzType == 2 && (Integer.valueOf(num) > 27 || Integer.valueOf(num) < 6)) {
                    flag.set(false);
                }
            }
            return flag.get();
        }
    }

    @Override
    public String[] getBetNums(String betNums) {
        return betNums.split(" ");
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = this.getBetNums(betNums);
        return nums == null ? 0 : nums.length;
    }
}
