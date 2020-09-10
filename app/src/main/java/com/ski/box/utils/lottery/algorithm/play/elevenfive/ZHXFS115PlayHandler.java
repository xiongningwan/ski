package com.ski.box.utils.lottery.algorithm.play.elevenfive;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/**
 * 11选五
 * 前三直选复式_115  4010101
 * 前二直选复式_115  4020101
 */
public class ZHXFS115PlayHandler implements ITicketPlayHandler {

    private int[] offsets;
    private String groupSymbol = "";
    private String symbol = "";

    public ZHXFS115PlayHandler(int[] offsets, String groupSymbol, String symbol) {
        this.offsets = offsets;
        this.groupSymbol = groupSymbol;
        this.symbol = symbol;
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = this.getBetNums(betNums);
        if (nums == null) {
            return 0;
        } else {
            int betNum = 0;
            String[] var4;
            int var5;
            int var6;
            String num;
            String[] var8;
            int var9;
            int var10;
            String num1;
            if (nums.length == 2) {
                var4 = nums[0].split(symbol);
                var5 = var4.length;

                for (var6 = 0; var6 < var5; ++var6) {
                    num = var4[var6];
                    var8 = nums[1].split(symbol);
                    var9 = var8.length;

                    for (var10 = 0; var10 < var9; ++var10) {
                        num1 = var8[var10];
                        if (!num.trim().equals(num1.trim())) {
                            ++betNum;
                        }
                    }
                }
            } else if (nums.length == 3) {
                var4 = nums[0].split(symbol);
                var5 = var4.length;

                for (var6 = 0; var6 < var5; ++var6) {
                    num = var4[var6];
                    var8 = nums[1].split(symbol);
                    var9 = var8.length;

                    for (var10 = 0; var10 < var9; ++var10) {
                        num1 = var8[var10];
                        String[] var12 = nums[2].split(symbol);
                        int var13 = var12.length;

                        for (int var14 = 0; var14 < var13; ++var14) {
                            String num2 = var12[var14];
                            if (!num.trim().equals(num1.trim()) && !num.trim().equals(num2.trim()) && !num1.trim().equals(num2.trim())) {
                                ++betNum;
                            }
                        }
                    }
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
            String[] nums = betNums.trim().split(groupSymbol);
            if (nums.length < this.offsets.length) {
                return false;
            } else {
                int fixLen = 0;
                String[] var4 = nums;
                int var5 = nums.length;

                for (int var6 = 0; var6 < var5; ++var6) {
                    String num = var4[var6];
                    if (!"-".equals(num)) {
                        String[] subNums = num.split(symbol);
                        String[] fixedSubNums = TicketPlayUtils.getFixedAndSortedNums(subNums);
                        if (subNums.length != fixedSubNums.length) {
                            return false;
                        }

                        String[] var10 = subNums;
                        int var11 = subNums.length;

                        for (int var12 = 0; var12 < var11; ++var12) {
                            String subNum = var10[var12];
                            if (Arrays.binarySearch(NUMS_115, subNum) < 0) {
                                return false;
                            }
                        }

                        ++fixLen;
                    }
                }

                return this.offsets.length == fixLen;
            }
        }
    }

    @Override
    public String[] getBetNums(String betNums) {
        String[] nums = betNums.trim().split(groupSymbol);
        String[] res = new String[this.offsets.length];
        int idx = 0;

        for (int i = 0; i < nums.length; ++i) {
            if (!"-".equals(nums[i].trim())) {
                res[idx++] = nums[i].trim();
            }
        }

        return res;
    }
}
