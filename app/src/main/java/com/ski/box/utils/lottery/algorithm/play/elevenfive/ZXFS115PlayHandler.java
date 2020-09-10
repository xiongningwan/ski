package com.ski.box.utils.lottery.algorithm.play.elevenfive;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/**
 * 11选五
 * 前三组选复式_115  4020201
 * 前二组选复式_115  4020201
 */
public class ZXFS115PlayHandler implements ITicketPlayHandler {
    private String[] numArray;
    private int bdwNum;
    private int[] offsets;
    private String symbol = "";

    public ZXFS115PlayHandler(int bdwNum, int[] offsets,String symbol) {
        this.numArray = NUMS_115;
        this.bdwNum = bdwNum;
        this.offsets = offsets;
        this.symbol = symbol;
    }


    private static int bdwNum(int betNum, int bdwNum) {
        int upCount = 1;
        int downCount = 1;

        for (int i = 0; i < bdwNum; ++i) {
            upCount *= betNum - i;
            downCount *= i + 1;
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
