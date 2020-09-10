package com.ski.box.utils.lottery.algorithm.play.kuaile8;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.exception.GlobalServiceException;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 趣味
 * 上下盘  26020101
 * 奇偶盘  26030101
 * 趣味和值大小单双 26040101
 */
public class QWPlayHandler implements ITicketPlayHandler {
    private Integer choose;

    public QWPlayHandler(Integer choose) {
        this.choose = choose;
    }

    @Override
    public String[] getBetNums(String betNums) {
        String[] nums = betNums.split(",");
        return leftAddZeor(nums);
    }


    @Override
    public int calculateBetNum(String betNums) throws GlobalServiceException {
        String[] betNumArray = betNums.split(" ");
        return betNumArray.length;
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (betNums != null && !"".equals(betNums)) {
            String[] betNumArray = betNums.split(" ");
            int arrayleng = betNumArray.length;
            boolean validate = false;
            if (this.choose.equals(1001)) {
                validate = Arrays.stream(betNumArray).allMatch((x) -> {
                    return "101".equals(x) || "102".equals(x) || "103".equals(x);
                });
            } else if (this.choose.equals(2001)) {
                validate = Arrays.stream(betNumArray).allMatch((x) -> {
                    return "201".equals(x) || "202".equals(x) || "203".equals(x);
                });
            } else {
                if (!this.choose.equals(3001)) {
                    return false;
                }

                validate = Arrays.stream(betNumArray).allMatch((x) -> {
                    return "301".equals(x) || "302".equals(x) || "303".equals(x) || "304".equals(x);
                });
            }

            return Stream.of(betNumArray).distinct().count() == (long) arrayleng && validate;
        } else {
            return false;
        }
    }

    public static String[] leftAddZeor(String[] nums) {
        String[] numsArray = new String[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            String str = nums[i];
            if (nums[i].length() == 1) {
                str = "0" + nums[i];
            }
            numsArray[i] = str;
        }
        return numsArray;
    }

}
