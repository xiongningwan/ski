package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/**
 * 时时彩
 * 任四组选12 1150202
 */
public class RXZX12PlayHanlder implements ITicketPlayHandler {
    private int rxNum;

    public RXZX12PlayHanlder(int rxNum) {
        this.rxNum = rxNum;
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] data = betNums.split("\\|");
            if (data.length != 2) {
                return false;
            } else {
                String[] offsets = data[0].trim().split(",");
                String[] fixedOffsets = TicketPlayUtils.getFixedAndSortedNums(offsets);
                if (offsets.length == fixedOffsets.length && offsets.length >= this.rxNum && offsets.length <= OFFSETS_WUXIN.length) {
                    StringBuilder ofsb = new StringBuilder();
                    String[] nums = fixedOffsets;
                    int var7 = fixedOffsets.length;

                    for(int var8 = 0; var8 < var7; ++var8) {
                        String offset = nums[var8];
                        if (offset.length() != 1) {
                            return false;
                        }

                        if (Arrays.binarySearch(OFFSETS_WUXIN, Integer.valueOf(offset)) < 0) {
                            return false;
                        }

                        ofsb.append(offset).append(",");
                    }

                    if (!data[0].trim().equals(ofsb.substring(0, ofsb.length() - 1))) {
                        return false;
                    } else {
                        nums = data[1].trim().split(",");
                        if (nums.length != 2) {
                            return false;
                        } else {
                            String[] moreNums = nums[0].trim().split(" ");
                            String[] fixedMoreNums = TicketPlayUtils.getFixedAndSortedNums(moreNums);
                            String[] fewerNums = nums[1].trim().split(" ");
                            String[] fixedFewerNums = TicketPlayUtils.getFixedAndSortedNums(fewerNums);
                            if (moreNums.length == fixedMoreNums.length && fewerNums.length == fixedFewerNums.length && moreNums.length >= 1 && moreNums.length <= 10 && fewerNums.length >= 1 && fewerNums.length <= 10) {
                                String[] var11 = moreNums;
                                int var12 = moreNums.length;

                                int var13;
                                String fnum;
                                for(var13 = 0; var13 < var12; ++var13) {
                                    fnum = var11[var13];
                                    if (!fnum.matches("\\d{1}")) {
                                        return false;
                                    }
                                }

                                var11 = fewerNums;
                                var12 = fewerNums.length;

                                for(var13 = 0; var13 < var12; ++var13) {
                                    fnum = var11[var13];
                                    if (!fnum.matches("\\d{1}")) {
                                        return false;
                                    }
                                }

                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                } else {
                    return false;
                }
            }
        }
    }

    @Override
    public String[] getBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return null;
        } else {
            String[] data = betNums.split("\\|");
            if (data.length != 2) {
                return null;
            } else {
                String[] nums = data[1].trim().split(" ");
                return nums;
            }
        }
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] data = betNums.split("\\|");
        if (data.length != 2) {
            return 0;
        } else {
            String[] offsets = data[0].trim().split(",");
            String[] nums = data[1].trim().split(",");
            if (nums.length != 2) {
                return 0;
            } else {
                String[] moreNums = TicketPlayUtils.getFixedNums(nums[0].trim().split(" "));
                String[] fewerNums = TicketPlayUtils.getFixedNums(nums[1].trim().split(" "));
                int in = 0;
                Arrays.sort(fewerNums);
                String[] var8 = moreNums;
                int var9 = moreNums.length;

                for(int var10 = 0; var10 < var9; ++var10) {
                    String m = var8[var10];
                    if (Arrays.binarySearch(fewerNums, m) > -1) {
                        ++in;
                    }
                }

                return SIXING_MULTI[offsets.length - this.rxNum] * (fewerNums.length * (fewerNums.length - 1) * (moreNums.length - in) / 2 + (fewerNums.length - 1) * (fewerNums.length - 2) * in / 2);
            }
        }
    }
}
