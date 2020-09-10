package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

/**
 * 时时彩
 * 五星直选组合  1010103
 * 前四直选组合  1020103
 * 后四直选组合  1020303
 * 前三直选组合  1030103
 * 中三直选组合  1040103
 * 后三直选组合  1050103
 */
public class ZUHEPlayHandler implements ITicketPlayHandler {
    private int[] offsets;

    public ZUHEPlayHandler(int[] offsets) {
        this.offsets = offsets;
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] strs = betNums.trim().split(",");
            if (strs.length != this.offsets.length) {
                return false;
            } else {
                String[] nums = this.getBetNums(betNums);
                int valLen = 0;
                String[] var5 = nums;
                int var6 = nums.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    String num = var5[var7];
                    if ("-".equals(num) || !StringUtils.isNotEmpty(num)) {
                        return false;
                    }

                    String[] bnums = num.split(" ");
                    String[] fixedBnums = TicketPlayUtils.getFixedAndSortedNums(bnums);
                    if (bnums.length != fixedBnums.length) {
                        return false;
                    }

                    String[] var11 = bnums;
                    int var12 = bnums.length;

                    for(int var13 = 0; var13 < var12; ++var13) {
                        String bn = var11[var13];
                        if (!bn.matches("\\d{1}")) {
                            return false;
                        }
                    }

                    ++valLen;
                }

                return valLen == this.offsets.length;
            }
        }
    }

    @Override
    public String[] getBetNums(String betNums) {
        String[] nums = betNums.trim().split(",");
        String[] res = new String[this.offsets.length];
        int idx = 0;

        for(int i = 0; i < nums.length; ++i) {
            if (!"-".equals(nums[i].trim())) {
                res[idx++] = nums[i].trim();
            }
        }

        return res;
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = this.getBetNums(betNums);
        if (nums == null) {
            return 0;
        } else {
            int betNum = this.offsets.length;
            String[] var4 = nums;
            int var5 = nums.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String num = var4[var6];
                betNum *= num.split(" ").length;
            }

            return betNum;
        }
    }
}
