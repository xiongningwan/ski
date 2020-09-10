package com.ski.box.utils.lottery.algorithm.play.elevenfive;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/**
 * 11选五
 * 任选复式一中一   4050101
 * 任选复式二中二   4050102
 * 任选复式三中三   4050103
 * 任选复式四中四   4050102
 * 任选复式五中五  4050105
 * 任选复式六中五  4050106
 * 任选复式七中五  4050107
 * 任选复式八中五  4050108
 */
public class RXFS115PlayHandler implements ITicketPlayHandler {
    private int[] offsets;
    private String[] numArray;
    private int bdwNum;
    private String symbol = "";

    public RXFS115PlayHandler(int bdwNum,String symbol) {
        this.offsets = OFFSETS_WUXIN;
        this.numArray = NUMS_115;
        this.bdwNum = bdwNum;
        this.symbol = symbol;
    }

    private static int bdwNum(int betNum, int bdwNum) {
        int upCount = 1;
        int downCount = 1;

        int a;
        for (a = 0; a < bdwNum; ++a) {
            upCount *= betNum - a;
        }

        for (a = 1; a <= bdwNum; ++a) {
            downCount *= a;
        }
        return upCount / downCount;
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = this.getBetNums(betNums);
        return nums == null ? 0 : bdwNum(nums.length, this.bdwNum);
    }


    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = betNums.trim().split(symbol);
            String[] fixedNums = TicketPlayUtils.getFixedAndSortedNums(nums);
            if (fixedNums.length <= this.numArray.length && nums.length == fixedNums.length) {
                String[] var4 = fixedNums;
                int var5 = fixedNums.length;

                for (int var6 = 0; var6 < var5; ++var6) {
                    String num = var4[var6];
                    if (Arrays.binarySearch(this.numArray, num) < 0) {
                        return false;
                    }
                }

                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public String[] getBetNums(String betNums) {
        String[] nums = betNums.trim().split(symbol);
        return TicketPlayUtils.getFixedNums(nums);
    }
}
