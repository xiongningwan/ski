package com.ski.box.utils.lottery.algorithm.play.marksix;

import com.ski.box.bean.lottery.IAllPlayCode;
import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.exception.GlobalServiceException;
import com.ski.box.utils.lottery.algorithm.utils.math.AlgorithmUtil;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

/**
 * 六合彩
 * 1.27020101,合肖
 * 2.27020102,特肖
 * 127020103,特肖五行
 * 27020104,特码形态
 * 27020105,特和形态
 * 27020106,特尾形态
 * 27020107,特肖形态
 */
public class TxPlayhandler implements ITicketPlayHandler {
    private String code;

    public TxPlayhandler(String code) {
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
            String replace = betNums.replace(",", " ");
            String[] strs = replace.split(" ");
            if (this.code.equalsIgnoreCase(IAllPlayCode.合肖)) {
                returnNum = AlgorithmUtil.combination(strs.length, 2).intValue();
            }
            if (IAllPlayCode.特肖.equalsIgnoreCase(code)
                    || IAllPlayCode.特肖五行.equalsIgnoreCase(code)
                    || IAllPlayCode.特码形态.equalsIgnoreCase(code)
                    || IAllPlayCode.特和形态.equalsIgnoreCase(code)
                    || IAllPlayCode.特尾形态.equalsIgnoreCase(code)
                    || IAllPlayCode.特肖形态.equalsIgnoreCase(code)) {
                returnNum = strs.length;
            }
            return returnNum;
        }
    }

    @Override
    public boolean validateBetNums(String nums) {
        if (StringUtils.isEmpty(nums)) {
            return false;
        } else {
            return true;
        }
    }
}
