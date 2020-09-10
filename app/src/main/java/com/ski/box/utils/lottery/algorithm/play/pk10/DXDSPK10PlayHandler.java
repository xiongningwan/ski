package com.ski.box.utils.lottery.algorithm.play.pk10;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/**
 * PK10
 * 前五大小单双 18050101
 */
public class DXDSPK10PlayHandler implements ITicketPlayHandler {
    private static String[] options = new String[]{"0", "1", "2", "3"};

    public DXDSPK10PlayHandler() {
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
            String[] betNumArray = betNums.split(",");
                String[] var3 = betNumArray;
                int var4 = betNumArray.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    String number = var3[var5];
                    if (!StringUtils.equals("-", number)) {
                        String[] bets = number.split(" ");
                        String[] var8 = bets;
                        int var9 = bets.length;

                        for(int var10 = 0; var10 < var9; ++var10) {
                            String bet = var8[var10];
                            if (Arrays.binarySearch(options, bet.trim()) < 0) {
                                return false;
                            }
                        }
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
