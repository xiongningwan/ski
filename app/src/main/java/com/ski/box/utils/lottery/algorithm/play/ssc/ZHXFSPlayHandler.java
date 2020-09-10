package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

/**
 * 时时彩
 * 五星直选复式 1010101
 * 前四直选复式 1020101
 * 后四直选复式 1020301
 * 前三直选复式 1030101
 * 中三直选复式 1040101
 * 后三直选复式 1050101
 * 前二直选复式 1060101
 * 后二直选复式 1070101
 */
public class ZHXFSPlayHandler implements ITicketPlayHandler {
    private int[] offsets;

    public ZHXFSPlayHandler(int[] offsets) {
        this.offsets = offsets;
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            int valLen = 0;
            String[] nums = betNums.trim().split(",");
            String[] var4 = nums;
            int var5 = nums.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String num = var4[var6];
                if (!"-".equals(num)) {
                    String[] bnums = num.split(" ");
                    String[] fixedBnums = TicketPlayUtils.getFixedAndSortedNums(bnums);
                    if (bnums.length != fixedBnums.length) {
                        return false;
                    }

                    String[] var10 = bnums;
                    int var11 = bnums.length;

                    for(int var12 = 0; var12 < var11; ++var12) {
                        String bn = var10[var12];
                        if (!bn.matches("\\d{1}")) {
                            return false;
                        }
                    }

                    ++valLen;
                }
            }

            return valLen == this.offsets.length;
        }
    }

    @Override
    public String[] getBetNums(String betNums) {
        String[] nums = betNums.trim().split(",");
        String[] res = new String[this.offsets.length];
        int idx = 0;

        for(int i = 0; i < nums.length; ++i) {
            if (!"-".equals(nums[i].trim())) {
                res[idx++] = nums[i].replaceAll(" ", "").trim();
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
            int betNum = 1;
            String[] var4 = nums;
            int var5 = nums.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String num = var4[var6];
                betNum *= num.length();
            }

            return betNum;
        }
    }
}
