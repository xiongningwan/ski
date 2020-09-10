package com.ski.box.utils.lottery.algorithm.play.kuai3;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**快三
 * 二同号单选单式 43050101
 */
public class Kuai3ETHDXDSHandler implements ITicketPlayHandler {

    public Kuai3ETHDXDSHandler() {

    }
    @Override
    public String[] getBetNums(String betNums) {
        return new String[0];
    }
    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = betNums.trim().split(" ");
        int i = nums.length;
        List list = new Vector();
        String[] var5 = nums;
        int var6 = nums.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            String s = var5[var7];
            list.add(s);
        }

        return list.size();
    }

    @Override
    public boolean validateBetNums(String betNums) {
        String[] nums = betNums.trim().split(" ");
        if (nums.length <= 30 && nums.length >= 1) {
            List list = new ArrayList();
            if ("1".equals(nums[0]) & nums.length == 1) {
                return true;
            } else {
                for(int i = 0; i < nums.length; ++i) {
                    String[] var5 = ITicketPlayHandler.OFFSETS_KUAI3_2THDXDS;
                    int var6 = var5.length;

                    for(int var7 = 0; var7 < var6; ++var7) {
                        String s = var5[var7];
                        if (nums[i].equals(s)) {
                            list.add(nums[i]);
                        }
                    }
                }

                return list.size() == nums.length;
            }
        } else {
            return false;
        }
    }


}
