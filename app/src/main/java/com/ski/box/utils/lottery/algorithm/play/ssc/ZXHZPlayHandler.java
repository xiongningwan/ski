package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

/**
 * 时时彩
 * 前三组选和值 1030204
 * 中三组选和值 1040204
 * 后三组选和值 1050204
 * 前二组选和值 1060203
 * 后二组选和值 1070203
 */
public class ZXHZPlayHandler implements ITicketPlayHandler {
    private static final int[] SANXING_BETS = new int[]{0, 1, 2, 2, 4, 5, 6, 8, 10, 11, 13, 14, 14, 15, 15, 14, 14, 13, 11, 10, 8, 6, 5, 4, 2, 2, 1};
    private static final int[] ERXING_BETS = new int[]{0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 4, 4, 3, 3, 2, 2, 1, 1};

    private int[] offsets;

    public ZXHZPlayHandler( int[] offsets) {
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
                        if (Integer.parseInt(num.trim()) > SANXING_BETS.length || Integer.parseInt(num.trim()) < 1) {
                            return false;
                        }
                    } else if (this.offsets.length == 2 && (Integer.parseInt(num.trim()) > ERXING_BETS.length || Integer.parseInt(num.trim()) < 1)) {
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
        return TicketPlayUtils.getFixedNums(nums);
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
