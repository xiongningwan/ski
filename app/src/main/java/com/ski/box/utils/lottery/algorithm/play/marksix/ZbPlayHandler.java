package com.ski.box.utils.lottery.algorithm.play.marksix;

import com.ski.box.bean.lottery.IAllPlayCode;
import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.exception.GlobalServiceException;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

/**
 * 六合彩
 * 27060101,正波
 */
public class ZbPlayHandler implements ITicketPlayHandler {
    private String code;

    public ZbPlayHandler( String code) {
        this.code = code;
    }

    @Override
    public String[] getBetNums(String betNums) {
        return new String[0];
    }

    @Override
    public int calculateBetNum(String betNums) throws GlobalServiceException {
        int returnNum = 0;
        if (StringUtils.isEmpty(betNums)) {
            return returnNum;
        } else {
            return StringUtils.calculateBetNum(betNums);
        }
    }

    @Override
    public boolean validateBetNums(String betNums) {
        boolean flag = false;
        if (StringUtils.isEmpty(betNums)) {
            return false;
        }else{
            /** 27060101,正波 **/
            if ( this.code.contains(IAllPlayCode.正波)) {
                flag = true;
            }
        }
        return flag;
    }
}
