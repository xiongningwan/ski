package com.ski.box.utils.lottery.algorithm.play.pk10;


import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * PK10
 * 龙虎  18030101
 */
public class LHPK10PlayHandler implements ITicketPlayHandler {
    private static String[] options = new String[]{"0", "1"};

    public LHPK10PlayHandler( ) {

    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
                return true;
     }
    }

    @Override
    public String[] getBetNums(String betNums) {
        return betNums.split(",");
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = this.getBetNums(betNums);
        if (nums == null) {
            return 0;
        } else {
            AtomicInteger counter = new AtomicInteger(0);


            for (String num : Arrays.asList(nums)) {
                if (!"-".equals(num)) {
                    if (num.split(" ").length > 1) {
                        counter.set(counter.get() + 2);
                    } else {
                        counter.getAndIncrement();
                    }
                }
            }
            return counter.get();
        }
    }


}
