package com.ski.box.utils.lottery.algorithm.play.fc3d;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

/**
 * 3D
 * 三星直选跨度 6010104
 * 前二直选跨度_3d 6020104
 * 后二直选跨度_3d 6020304
 */
public class ZHKDPlayHandler implements ITicketPlayHandler {
    private static final int[] SANXING_BETS = new int[]{10, 54, 96, 126, 144, 150, 144, 126, 96, 54};
    private static final int[] ERXING_BETS = new int[]{10, 18, 16, 14, 12, 10, 8, 6, 4, 2};
    private int[] offsets;

    public ZHKDPlayHandler(int[] offsets) {
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

                for (int var6 = 0; var6 < var5; ++var6) {
                    String num = var4[var6];
                    if (!num.trim().matches("\\d{1}")) {
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

        for (int i = 0; i < nums.length; ++i) {
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

            for (int var6 = 0; var6 < var5; ++var6) {
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
