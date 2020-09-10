package com.ski.box.utils.lottery.algorithm.play.elevenfive;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/** 11选5
 * 任选胆拖二中二  4050301
 * 任选胆拖三中三  4050302
 * 任选胆拖四中四  4050303
 * 任选胆拖五中五  4050302
 * 任选胆拖六中五  4050305
 * 任选胆拖七中五  4050306
 * 任选胆拖八中五  4050307
 */
public class RXDT115PlayHandler implements ITicketPlayHandler {
    private int[] offsets;
    private String[] numArray;
    private int mNum;

    public RXDT115PlayHandler(int mNum) {
        this.offsets = OFFSETS_WUXIN;
        this.numArray = NUMS_115;
        this.mNum = mNum;
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = this.getBetNums(betNums);
        if (nums != null && nums.length == 2) {
            String[] dNum = nums[0].split(" ");
            String[] tNum = nums[1].split(" ");
            int betNum = 1;
            int desNum = 1;

            for(int i = 0; i < this.mNum - dNum.length; ++i) {
                betNum *= tNum.length - i;
                desNum *= i + 1;
            }

            return betNum / desNum;
        } else {
            return 0;
        }
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (betNums != null && !"".equals(betNums.trim())) {
            String[] nums = betNums.trim().split(",");
            if (nums.length != 2) {
                return false;
            } else {
                String[] dNums = nums[0].trim().split(" ");
                String[] fixDanNums = TicketPlayUtils.getFixedAndSortedNums(dNums);
                if (dNums.length == fixDanNums.length && dNums.length != 0 && dNums.length <= this.mNum - 1) {
                    String[] tNums = nums[1].trim().split(" ");
                    String[] fixTuoNums = TicketPlayUtils.getFixedAndSortedNums(tNums);
                    if (tNums.length == fixTuoNums.length && tNums.length != 0 && dNums.length + tNums.length <= this.numArray.length) {
                        Arrays.sort(tNums);
                        String[] var7 = dNums;
                        int var8 = dNums.length;

                        int var9;
                        String tNum;
                        for(var9 = 0; var9 < var8; ++var9) {
                            tNum = var7[var9];
                            if (Arrays.binarySearch(this.numArray, tNum) < 0 || Arrays.binarySearch(tNums, tNum) >= 0) {
                                return false;
                            }
                        }

                        var7 = tNums;
                        var8 = tNums.length;

                        for(var9 = 0; var9 < var8; ++var9) {
                            tNum = var7[var9];
                            if (Arrays.binarySearch(this.numArray, tNum) < 0) {
                                return false;
                            }
                        }

                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    @Override
    public String[] getBetNums(String betNums) {
        String[] nums = betNums.trim().split(",");
        String[] res = new String[nums.length];

        for(int i = 0; i < nums.length; ++i) {
            res[i] = nums[i].trim();
        }

        return res;
    }
}
