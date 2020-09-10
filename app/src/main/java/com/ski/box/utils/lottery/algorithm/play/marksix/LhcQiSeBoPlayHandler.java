package com.ski.box.utils.lottery.algorithm.play.marksix;


import com.ski.box.bean.lottery.IAllPlayCode;
import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.exception.GlobalServiceException;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

/**
 * 六合彩
 * 127090101,七色波
 */
public class LhcQiSeBoPlayHandler implements ITicketPlayHandler {

    private String code;

    public LhcQiSeBoPlayHandler(String code) {
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
        } else {
            if (IAllPlayCode.七色波.equalsIgnoreCase(code)) {
                flag = true;
            }
        }
        return flag;
    }

}
