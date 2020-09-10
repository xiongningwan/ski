package com.ski.box.utils.lottery.algorithm.play.pk10;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/**
 * 时时彩
 * 冠亚和值大小单双  18050102
 */
public class HZDXDSPK10PlayHandler implements ITicketPlayHandler {

    private static String[] options = new String[]{"0", "1", "2", "3"};

    public HZDXDSPK10PlayHandler() {
    }

    @Override
    public String[] getBetNums(String betNums) {
        String[] nums = betNums.trim().split(",");
        String[] res = new String[nums.length];

        for(int i = 0; i < nums.length; ++i) {
            if (StringUtils.isBlank(nums[i])) {
                res[i] = "-";
            } else {
                res[i] = nums[i].trim();
            }
        }

        return res;
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] bets = betNums.split(" ");
            String[] var3 = bets;
            int var4 = bets.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String bet = var3[var5];
                if (Arrays.binarySearch(options, bet.trim()) < 0) {
                    return false;
                }
            }

            return true;
        }
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = this.getBetNums(betNums);
        if (nums == null) {
            return 0;
        } else {
            int betNum = 0;

            for(int i = 0; i < nums.length; ++i) {
                if (!"-".equals(nums[i])) {
                    String[] valid = nums[i].split(" ");
                    betNum += valid.length;
                }
            }

            return betNum;
        }
    }
}
