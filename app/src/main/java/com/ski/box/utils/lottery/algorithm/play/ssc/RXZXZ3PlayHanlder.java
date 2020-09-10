package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/**
 * 时时彩
 * 任三组选三复式 1140201
 */
public class RXZXZ3PlayHanlder implements ITicketPlayHandler {
    private int rxNum;

    public RXZXZ3PlayHanlder(int rxNum) {
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
                if (offsets.length == fixedOffsets.length && offsets.length >= this.rxNum) {
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
                        nums = data[1].trim().split(" ");
                        String[] fixedNums = TicketPlayUtils.getFixedAndSortedNums(nums);
                        if (nums.length == fixedNums.length && fixedNums.length >= 2 && fixedNums.length <= 10) {
                            String[] var13 = fixedNums;
                            int var14 = fixedNums.length;

                            for(int var10 = 0; var10 < var14; ++var10) {
                                String num = var13[var10];
                                if (!num.matches("\\d{1}")) {
                                    return false;
                                }
                            }

                            return true;
                        } else {
                            return false;
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
        String[] data = betNums.split("\\|");
        if (data.length != 2) {
            return null;
        } else {
            String[] nums = data[1].trim().split(",");
            return TicketPlayUtils.getFixedNums(nums);
        }
    }

    public String[] getOpenNums(String openNums) {
        return TicketPlayUtils.getOpenNums(openNums, OFFSETS_WUXIN);
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] data = betNums.split("\\|");
        if (data.length != 2) {
            return 0;
        } else {
            String[] offsets = data[0].trim().split(",");
            String[] nums = data[1].trim().split(" ");
            if (this.rxNum == 3) {
                return nums.length * (nums.length - 1) * SANXING_MULTI[offsets.length - this.rxNum];
            } else {
                if (this.rxNum == 2) {
                }

                return nums.length * (nums.length - 1);
            }
        }
    }

}
