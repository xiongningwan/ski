package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/**
 * 时时彩
 *  任二直选和值  1120103
 *  任三直选和值  1140103
 */
public class RXZHHZPlayHandler implements ITicketPlayHandler {
    private int rxNum;

    public RXZHHZPlayHandler(int rxNum) {
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
                if (offsets.length == fixedOffsets.length && offsets.length >= this.rxNum && offsets.length <= 5) {
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
                        if (nums.length != fixedNums.length) {
                            return false;
                        } else {
                            String[] var13 = nums;
                            int var14 = nums.length;

                            for(int var10 = 0; var10 < var14; ++var10) {
                                String num = var13[var10];
                                if (this.rxNum == 3) {
                                    if (Integer.parseInt(num.trim()) >= SANXING_ZHHZ.length || Integer.parseInt(num.trim()) < 0) {
                                        return false;
                                    }
                                } else if (this.rxNum == 2 && (Integer.parseInt(num.trim()) >= ERXING_ZHHZ.length || Integer.parseInt(num.trim()) < 0)) {
                                    return false;
                                }
                            }

                            return true;
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
            String[] nums = data[1].trim().split(" ");
            String[] res = new String[nums.length];

            for(int i = 0; i < nums.length; ++i) {
                res[i] = nums[i].trim();
            }

            return res;
        }
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] data = betNums.split("\\|");
        if (data.length != 2) {
            return 0;
        } else {
            String[] nums = data[1].trim().split(" ");
            if (nums == null) {
                return 0;
            } else {
                String[] offsets = data[0].trim().split(",");
                int betNum = 0;
                String[] var6 = nums;
                int var7 = nums.length;

                for(int var8 = 0; var8 < var7; ++var8) {
                    String num = var6[var8];
                    if (this.rxNum == 3) {
                        betNum += SANXING_ZHHZ[Integer.parseInt(num)] * SANXING_MULTI[offsets.length - this.rxNum];
                    } else if (this.rxNum == 2) {
                        betNum += ERXING_ZHHZ[Integer.parseInt(num)] * ERXING_MULTI[offsets.length - this.rxNum];
                    }
                }

                return betNum;
            }
        }
    }
}
