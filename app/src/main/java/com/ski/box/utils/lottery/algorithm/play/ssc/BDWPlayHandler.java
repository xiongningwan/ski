package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/** 时时彩
 * 不定位  SSC
 * 五星组选120  1010201
 * 前四组选24    1020201
 * 后四组选24   1020401
 * 前三组选六    1030201
 * 中三组选六    1040201
 * 后三组选六    1050201
 * 前二组选复式  1060201
 * 后二组选复式  1070201
 * 前三一码不定胆 1090101
 * 前三二码不定胆 1090102
 * 中三一码不定胆 1090103
 * 中三二码不定胆 1090104
 * 后三一码不定胆 1090105
 * 后三二码不定胆 1090106
 * 前四一码不定胆 1090201
 * 前四二码不定胆 1090202
 * 前四三码不定胆 1090203
 * 后四一码不定胆 1090204
 * 后四二码不定胆 1090205
 * 后四三码不定胆 1090206
 * 五星一码不定胆 1090301
 * 五星二码不定胆 1090302
 * 五星三码不定胆 1090303
 */
public class BDWPlayHandler implements ITicketPlayHandler {

    private int bdwNum;
    private int[] offsets;
    private String[] numArray;
    private String symbol = "";

    public BDWPlayHandler(int bdwNum, int[] offsets, String[] numArray,String symbol) {
        this.bdwNum = bdwNum;
        this.offsets = offsets;
        this.numArray = numArray;
        this.symbol = symbol;
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = betNums.trim().split(symbol);
            String[] fixedNums = TicketPlayUtils.getFixedAndSortedNums(nums);
            if (fixedNums.length >= this.bdwNum && fixedNums.length <= this.numArray.length && nums.length == fixedNums.length) {
                String[] var4 = fixedNums;
                int var5 = fixedNums.length;

                for(int var6 = 0; var6 < var5; ++var6) {
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

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = this.getBetNums(betNums);
        return nums == null ? 0 : bdwNum(nums.length, this.bdwNum);
    }


    private static int bdwNum(int betNum, int bdwNum) {
        int upCount = 1;
        int downCount = 1;

        int a;
        for(a = 0; a < bdwNum; ++a) {
            upCount *= betNum - a;
        }

        for(a = 1; a <= bdwNum; ++a) {
            downCount *= a;
        }

        return upCount / downCount;
    }
}
