package com.ski.box.utils.lottery.algorithm.play.fc3d;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/**
 * 3D
 * 三星组选包胆  6010205
 * 前二组选包胆_3d 6020204
 * 后二组选包胆_3d 6020404
 */
public class ZXBDPlayHandler implements ITicketPlayHandler {
    private static final int[] FC_3D = new int[]{0, 1, 9, 54};
    private int[] offsets;
    private String[] numArray;
    private boolean isSpecial;

    public ZXBDPlayHandler(int[] offsets, String[] numArray, boolean isSpecial) {
        this.offsets = offsets;
        this.numArray = numArray;
        this.isSpecial = isSpecial;
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
        } else {
            return NUMS_FC3D.equals(this.numArray) ? FC_3D[this.offsets.length] : 0;
        }
    }
}
