package com.ski.box.utils.lottery.algorithm.play.marksix;

import com.ski.box.bean.lottery.IAllPlayCode;
import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.exception.GlobalServiceException;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/**
 * 六合彩 特码  27010101,特码
 */
public class TmPlayHandler implements ITicketPlayHandler {

    private String code;

    public TmPlayHandler( String code) {
        this.code = code;
    }

    @Override
    public String[] getBetNums(String betNums) {
        return new String[0];
    }

    @Override
    public int calculateBetNum(String betNums) throws GlobalServiceException {
        int returnNum = 0;
        if (StringUtils.isBlank(betNums)) {
            return returnNum;
        } else {
            String[] strs = StringUtils.split(betNums, ",");
             returnNum = strs.length;
            return returnNum;
        }
    }

    @Override
    public boolean validateBetNums(String betNums) {
        boolean flag = false;
        boolean isBlankFlag = false;
        if (StringUtils.isNotBlank(betNums)) {
            isBlankFlag = true;
        }

        String[] nums = StringUtils.split(betNums, ",");
        int var6;
        byte var7;
        String touweishu;
        if (isBlankFlag && this.code .equalsIgnoreCase(IAllPlayCode.特码) ) {
            var6 = nums.length;
            var7 = 0;
            if (var7 < var6) {
                touweishu = nums[var7];
                flag = Arrays.asList(DING_WEI_DAN_OR_XUANHAO).contains(touweishu);
            }
        }
        return flag;
    }
}
