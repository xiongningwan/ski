package com.ski.box.utils.lottery.algorithm.play.kuai3;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 快三
 * 二同号单选复式  43050102
 */
public class Kuai3ETHDXFSHandler implements ITicketPlayHandler {

    public Kuai3ETHDXFSHandler() {
    }

    @Override
    public int calculateBetNum(String betNums) {
        if (betNums == null) {
            return 0;
        } else {
            String[] nums = betNums.trim().split(",");
            if (nums.length == 2) {
                String preSegment = nums[0];
                String[] preFixSegment = preSegment.split(" ");
                String variable = nums[1];
                String[] fixedVarible = variable.split(" ");
                List preFixList = new ArrayList();
                List fixedFixList = new ArrayList();
                int i = 0, j = 0;
                for (i = 0; i < preFixSegment.length; ++i) {
                    if ("11".equals(preFixSegment[i])) {
                        preFixList.add("1");
                    } else if ("22".equals(preFixSegment[i])) {
                        preFixList.add("2");
                    } else if ("33".equals(preFixSegment[i])) {
                        preFixList.add("3");
                    } else if ("44".equals(preFixSegment[i])) {
                        preFixList.add("4");
                    } else if ("55".equals(preFixSegment[i])) {
                        preFixList.add("5");
                    } else if ("66".equals(preFixSegment[i])) {
                        preFixList.add("6");
                    }
                }
                for (j = 0; j < fixedVarible.length; ++j) {
                    if ("1".equals(fixedVarible[j])) {
                        fixedFixList.add("1");
                    } else if ("2".equals(fixedVarible[j])) {
                        fixedFixList.add("2");
                    } else if ("3".equals(fixedVarible[j])) {
                        fixedFixList.add("3");
                    } else if ("4".equals(fixedVarible[j])) {
                        fixedFixList.add("4");
                    } else if ("5".equals(fixedVarible[j])) {
                        fixedFixList.add("5");
                    } else if ("6".equals(fixedVarible[j])) {
                        fixedFixList.add("6");
                    }
                }
                i = 0;
                j = 0;
                int notAlike = 0;
                for (i = 0; i < preFixList.size(); ++i) {
                    for (j = 0; j < fixedFixList.size(); ++j) {
                        if (!preFixList.get(i).equals(fixedFixList.get(j))) {
                            ++notAlike;
                        }
                    }
                }
                return notAlike;
            }
            return 0;
        }
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = betNums.trim().split(",");
            if (nums.length == 2 && nums[0].length() >= 1 && nums[1].length() >= 1) {
                String[] preNums = nums[0].split(" ");
                new ArrayList();
                String[] lastNums = nums[1].split(" ");

                for (int k = 0; k < lastNums.length; ++k) {
                    Integer number = Integer.valueOf(lastNums[k]);
                    if (number < 1 || number > 6) {
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
        String[] nums = betNums.trim().split(",");
        return TicketPlayUtils.getFixedNums(nums);
    }
}
