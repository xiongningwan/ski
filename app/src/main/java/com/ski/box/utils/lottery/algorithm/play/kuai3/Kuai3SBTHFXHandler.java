package com.ski.box.utils.lottery.algorithm.play.kuai3;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 快三
 * 三不同号复选 43040102
 */
public class Kuai3SBTHFXHandler implements ITicketPlayHandler {

    public Kuai3SBTHFXHandler() {
    }

    @Override
    public String[] getBetNums(String betNums) {
        return new String[0];
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = betNums.trim().split(" ");
        new Vector();
        if (nums.length == 3) {
            return 1;
        } else if (nums.length < 3) {
            return 0;
        } else {
            for (int i = 0; i < nums.length; ++i) {
            }
            return nums.length * (nums.length - 1) * (nums.length - 2) / 6;
        }
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = betNums.trim().split(" ");
            if (nums.length >= 3 && nums.length <= 6) {
                List validNums = new ArrayList();

                for (int i = 0; i < nums.length; ++i) {
                    String[] var5 = ITicketPlayHandler.OFFSETS_KUAI3_3BTHFX;
                    int var6 = var5.length;

                    for (int var7 = 0; var7 < var6; ++var7) {
                        String s = var5[var7];
                        if (nums[i].equals(s)) {
                            validNums.add(s);
                        }
                    }
                }

                if (nums.length != validNums.size()) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }
    }
}
