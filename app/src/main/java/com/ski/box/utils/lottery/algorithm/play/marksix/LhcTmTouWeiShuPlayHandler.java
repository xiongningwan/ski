package com.ski.box.utils.lottery.algorithm.play.marksix;

import com.ski.box.bean.lottery.IAllPlayCode;
import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.exception.GlobalServiceException;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

/**
 * 特码头尾数   27010102, 特码头尾数
 */
public class LhcTmTouWeiShuPlayHandler implements ITicketPlayHandler {

    private String code;

    public LhcTmTouWeiShuPlayHandler(String code) {
        this.code = code;
    }

    @Override
    public String[] getBetNums(String betNums) {
        return new String[0];
    }

    @Override
    public int calculateBetNum(String betNums) throws GlobalServiceException {
        String replace = betNums.replace(",", " ");
        String[] strs = replace.split(" ");
        return strs.length;

    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        }
        if (IAllPlayCode.特码头尾数.equalsIgnoreCase(code)) {
            return true;
        }
        return false;
    }
}
