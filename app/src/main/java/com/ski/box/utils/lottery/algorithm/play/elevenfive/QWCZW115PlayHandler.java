package com.ski.box.utils.lottery.algorithm.play.elevenfive;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/**
 * 11选5
 * 趣味猜中位
 */
public class QWCZW115PlayHandler implements ITicketPlayHandler {
    private int[] offsets;
    private String[] numArray;

    public QWCZW115PlayHandler() {
        this.offsets = OFFSETS_ZHONGWEI;
        this.numArray = NUMS_115_QW_CZW;

    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = this.getBetNums(betNums);
        if (nums == null) {
            return 0;
        } else {
            int betNum = 0;
            String[] var4 = nums;
            int var5 = nums.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                String num = var4[var6];
                if (!"-".equals(num) && StringUtils.isNotEmpty(num)) {
                    betNum += num.split(" ").length;
                }
            }

            return betNum;
        }
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = betNums.trim().split(" ");
            String[] fixedNums = TicketPlayUtils.getFixedAndSortedNums(nums);
            if (fixedNums.length <= this.numArray.length && nums.length == fixedNums.length) {
                String[] var4 = fixedNums;
                int var5 = fixedNums.length;

                for (int var6 = 0; var6 < var5; ++var6) {
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
}
