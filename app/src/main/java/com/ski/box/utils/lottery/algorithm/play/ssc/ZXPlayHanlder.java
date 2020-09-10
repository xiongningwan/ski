package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/**
 * 时时彩
 * 五星组选60 1010202
 * 五星组选30 1010203
 * 五星组选20 1010204
 * 五星组选10 1010205
 * 五星组选5 1010206
 * 前四组选12 1020202
 * 前四组选4 1020204
 * 后四组选12 1020402
 * 后四组选4 1020404
 */
public class ZXPlayHanlder implements ITicketPlayHandler {
    private int[] offsets;
    private int moreNumCount;
    private int moreNumTimes;
    private int fewerNumCount;
    private int fewerNumTimes;
    private String[] numArray;

    public ZXPlayHanlder( int moreNumCount, int moreNumTimes, int fewerNumCount, int fewerNumTimes, int[] offsets, String[] numArray) {
        this.offsets = offsets;
        this.moreNumCount = moreNumCount;
        this.moreNumTimes = moreNumTimes;
        this.fewerNumCount = fewerNumCount;
        this.fewerNumTimes = fewerNumTimes;
        this.numArray = numArray;
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = betNums.trim().split(",");
            if (nums.length != 2) {
                return false;
            } else {
                String[] moreNums = nums[0].trim().split(" ");
                String[] fixedMoreNums = TicketPlayUtils.getFixedAndSortedNums(moreNums);
                String[] fewerNums = nums[1].trim().split(" ");
                String[] fixedFewerNums = TicketPlayUtils.getFixedAndSortedNums(fewerNums);
                if (moreNums.length == fixedMoreNums.length && fewerNums.length == fixedFewerNums.length && moreNums.length >= this.moreNumCount && moreNums.length <= this.numArray.length && fewerNums.length >= this.fewerNumCount && fewerNums.length <= this.numArray.length) {
                    String[] var7 = moreNums;
                    int var8 = moreNums.length;

                    int var9;
                    String fnum;
                    for(var9 = 0; var9 < var8; ++var9) {
                        fnum = var7[var9];
                        if (Arrays.binarySearch(this.numArray, fnum) < 0) {
                            return false;
                        }
                    }

                    var7 = fewerNums;
                    var8 = fewerNums.length;

                    for(var9 = 0; var9 < var8; ++var9) {
                        fnum = var7[var9];
                        if (Arrays.binarySearch(this.numArray, fnum) < 0) {
                            return false;
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    @Override
    public String[] getBetNums(String betNums) {
        return betNums.trim().split(",");
    }


    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = this.getBetNums(betNums);
        String[] main = null;
        String[] tail = null;
        if (this.moreNumCount >= this.fewerNumCount) {
            main = TicketPlayUtils.getFixedNums(nums[0].trim().split(" "));
            tail = TicketPlayUtils.getFixedNums(nums[1].trim().split(" "));
        } else {
            main = TicketPlayUtils.getFixedNums(nums[1].trim().split(" "));
            tail = TicketPlayUtils.getFixedNums(nums[0].trim().split(" "));
        }

        int in = 0;
        Arrays.sort(main);
        String[] var6 = tail;
        int mainINfix = tail.length;

        int der;
        for(der = 0; der < mainINfix; ++der) {
            String t = var6[der];
            if (Arrays.binarySearch(main, t) > -1) {
                ++in;
            }
        }

        int mainNIfix = 1;
        mainINfix = 1;
        der = 1;

        for(int i = 0; i < Math.max(this.moreNumCount, this.fewerNumCount); ++i) {
            mainNIfix *= main.length - i;
            mainINfix *= main.length - i - 1;
            der *= i + 1;
        }

        return mainNIfix * (tail.length - in) / der + mainINfix * in / der;
    }

}
