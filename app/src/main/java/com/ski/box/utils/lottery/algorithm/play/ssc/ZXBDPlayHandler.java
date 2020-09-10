package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/** 时时彩
 * 前三组选包胆  1030207
 * 中三组选包胆  1040207
 * 后三组选包胆  1050207
 * 前二组选包胆  1060204
 * 后二组选包胆  1070204
 */
public class ZXBDPlayHandler implements ITicketPlayHandler {
    private static final int[] BETS_SSC = new int[]{0, 1, 9, 54};
    private static final int[] BETS_115 = new int[]{0, 1, 9, 54};
    private int[] offsets;
    private String[] numArray;

    public ZXBDPlayHandler(int[] offsets, String[] numArray) {
        this.offsets = offsets;
        this.numArray = numArray;
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            return Arrays.binarySearch(this.numArray, betNums.trim()) >= 0;
        }
    }

    @Override
    public String[] getBetNums(String betNums) {
        String[] res = new String[]{betNums.trim()};
        return res;
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = this.getBetNums(betNums);
        if (nums == null) {
            return 0;
        } else if (NUMS_SSC.equals(this.numArray)) {
            return BETS_SSC[this.offsets.length];
        } else {
            return NUMS_115.equals(this.numArray) ? BETS_115[this.offsets.length] : 0;
        }
    }
}
