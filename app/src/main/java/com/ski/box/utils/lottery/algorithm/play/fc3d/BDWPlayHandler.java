//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ski.box.utils.lottery.algorithm.play.fc3d;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/**
 * 3D
 * 三星组选六复式 6010202
 * 前二组选复式_3d 6020201
 * 后二组选复式_3d 6020301
 * 三星一码不定胆 6040101
 * 三星二码不定胆 6040102
 */
public class BDWPlayHandler implements ITicketPlayHandler {

    private int bdwNum;
    private int[] offsets;
    private String[] numArray;

    public BDWPlayHandler(int bdwNum, int[] offsets, String[] numArray) {
        this.bdwNum = bdwNum;
        this.offsets = offsets;
        this.numArray = numArray;
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = betNums.trim().split(" ");
            String[] fixedNums = TicketPlayUtils.getFixedAndSortedNums(nums);
            if (fixedNums.length >= this.bdwNum && fixedNums.length <= this.numArray.length && nums.length == fixedNums.length) {
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
        String[] nums = betNums.trim().split(" ");
        return TicketPlayUtils.getFixedNums(nums);
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = this.getBetNums(betNums);
        return nums == null ? 0 : bdwNum(nums.length, this.bdwNum);
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
}
