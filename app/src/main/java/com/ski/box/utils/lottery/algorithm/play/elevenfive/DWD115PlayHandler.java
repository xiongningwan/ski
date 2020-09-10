package com.ski.box.utils.lottery.algorithm.play.elevenfive;


import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/**
 * 十一选五
 * 定位胆  4040101
 */
public class DWD115PlayHandler implements ITicketPlayHandler {
    private String[] numArray;
    private int[] offsets;

    public DWD115PlayHandler(int[] offsets) {
        this.numArray = NUMS_115;
        this.offsets = offsets;
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
            String[] nums = betNums.trim().split(",");

            String[] var3 = nums;
            int var4 = nums.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                String num = var3[var5];
                if (!"-".equals(num)) {
                    String[] subNums = num.split(" ");
                    String[] fixedSubNums = TicketPlayUtils.getFixedAndSortedNums(subNums);
                    if (subNums.length != fixedSubNums.length) {
                        return false;
                    }

                    String[] var9 = subNums;
                    int var10 = subNums.length;

                    for (int var11 = 0; var11 < var10; ++var11) {
                        String subNum = var9[var11];
                        if (Arrays.binarySearch(this.numArray, subNum) < 0) {
                            return false;
                        }
                    }
                }
            }
            return true;

           /* if (nums.length != this.offsets.length) {
                return false;
            } else {

            }*/
        }
    }

    @Override
    public String[] getBetNums(String betNums) {
        String[] nums = betNums.trim().split(",");
        String[] res = new String[nums.length];

        for (int i = 0; i < nums.length; ++i) {
            res[i] = nums[i].trim();
        }

        return res;
    }
}
