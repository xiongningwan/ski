package com.ski.box.utils.lottery.algorithm.play.marksix;

import com.ski.box.bean.lottery.IAllPlayCode;
import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.exception.GlobalServiceException;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

/**
 * 六合彩
 * 27050101,正肖
 */
public class ZxPlayHandler implements ITicketPlayHandler {

    private String code;

    public ZxPlayHandler( String code) {
        this.code = code;
    }

    @Override
    public String[] getBetNums(String betNums) {
        return new String[0];
    }

    @Override
    public int calculateBetNum(String betNums) throws GlobalServiceException {
        if (StringUtils.isEmpty(betNums)) {
            return 0;
        } else {
            return StringUtils.calculateBetNum(betNums);
        }
    }

    @Override
    public boolean validateBetNums(String betNums) {
        boolean flag = false;

        if (StringUtils.isEmpty(betNums)) {
            return false;
        }else {
            /**27050101  正肖**/
            if (IAllPlayCode.正肖.equalsIgnoreCase(code) ) {
                flag = true;
            }
        }
        return flag;
    }
}
