package com.ski.box.utils.lottery.algorithm.play.marksix;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.array.ArrayUtils;
import com.ski.box.utils.lottery.algorithm.utils.exception.GlobalServiceException;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

/**
 * 六合彩
 * 27070103,自选不中
 */
public class LhcZiXuanBuZhongPlayHandler implements ITicketPlayHandler {

    private String symbol = "";

    public LhcZiXuanBuZhongPlayHandler(String symbol) {
        this.symbol = symbol;
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
            String[] strs = StringUtils.split(betNums, symbol);
            if (strs.length > 4) {
                returnNum = 1;
            }
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

        if (isBlankFlag) {
            String[] nums = StringUtils.split(betNums, symbol);
            String[] var5;
            int var6;
            int var7;
            String num;
            var5 = nums;
            var6 = nums.length;
            for (var7 = 0; var7 < var6; ++var7) {
                num = var5[var7];
                flag = ArrayUtils.isExist(DING_WEI_DAN_OR_XUANHAO, num);
            }
        }
        return flag;
    }

}
