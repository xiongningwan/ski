package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

/**
 * 时时彩
 * 前三直选和值 1030104
 * 中三直选和值  1040104
 * 后三直选和值  1050104
 * 前二直选和值  1060103
 * 后二直选和值  1070103
 */
public class ZHHZPlayHandler implements ITicketPlayHandler {
    private static final int[] SANXING_BETS = new int[]{1, 3, 6, 10, 15, 21, 28, 36, 45, 55, 63, 69, 73, 75, 75, 73, 69, 63, 55, 45, 36, 28, 21, 15, 10, 6, 3, 1};
    private static final int[] ERXING_BETS = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    private int[] offsets;

    public ZHHZPlayHandler( int[] offsets) {
        this.offsets = offsets;
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = betNums.trim().split(" ");
            String[] fixedNums = TicketPlayUtils.getFixedAndSortedNums(nums);
            if (nums.length != fixedNums.length) {
                return false;
            } else {
                String[] var4 = nums;
                int var5 = nums.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    String num = var4[var6];
                    if (!num.matches("\\d+") || num.matches("0\\d+")) {
                        return false;
                    }

                    if (this.offsets.length == 3) {
                        if (Integer.parseInt(num.trim()) >= SANXING_BETS.length || Integer.parseInt(num.trim()) < 0) {
                            return false;
                        }
                    } else if (this.offsets.length == 2 && (Integer.parseInt(num.trim()) >= ERXING_BETS.length || Integer.parseInt(num.trim()) < 0)) {
                        return false;
                    }
                }

                return true;
            }
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
        if (nums == null) {
            return 0;
        } else {
            int betNum = 0;
            String[] var4 = nums;
            int var5 = nums.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String num = var4[var6];
                if (this.offsets.length == 3) {
                    betNum += SANXING_BETS[Integer.parseInt(num)];
                } else if (this.offsets.length == 2) {
                    betNum += ERXING_BETS[Integer.parseInt(num)];
                }
            }

            return betNum;
        }
    }
}
