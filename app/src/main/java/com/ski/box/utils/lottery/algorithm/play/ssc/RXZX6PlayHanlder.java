package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/**
 * 时时彩
 * 任四组选6 1150203
 */
public class RXZX6PlayHanlder implements ITicketPlayHandler {
    private int rxNum;

    public RXZX6PlayHanlder(int rxNum) {

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
                        nums = data[1].trim().split(" ");
                        String[] fixedNums = TicketPlayUtils.getFixedAndSortedNums(nums);
                        if (nums.length == fixedNums.length && nums.length >= 2) {
                            String[] var13 = nums;
                            int var14 = nums.length;

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
            String[] nums = data[1].trim().split(" ");
            return nums.length < 2 ? 0 : SIXING_MULTI[offsets.length - this.rxNum] * nums.length * (nums.length - 1) / 2;
        }
    }
}
