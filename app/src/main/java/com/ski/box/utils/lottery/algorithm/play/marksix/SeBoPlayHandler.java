package com.ski.box.utils.lottery.algorithm.play.marksix;

import android.text.TextUtils;

import com.ski.box.bean.lottery.IAllPlayCode;
import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.exception.GlobalServiceException;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

/**
 * 六合彩
 *  27030101,色波
 *  27030102,半波
 */
public class SeBoPlayHandler implements ITicketPlayHandler {
    private String code;

    public SeBoPlayHandler( String code) {
        this.code = code;
    }

    @Override
    public String[] getBetNums(String betNums) {
        return new String[0];
    }

    @Override
    public int calculateBetNum(String betNums) throws GlobalServiceException {
        if (TextUtils.isEmpty(betNums)){
            return 0;
        }else{
            String replace = betNums.replace(",", " ");
            String[] str = replace.split(" ");
            return str.length;
        }
    }

    @Override
    public boolean validateBetNums(String betNums) {
        boolean flag = false;
        if (StringUtils.isEmpty(betNums)) {
          return false;
        }else{
                 /**27030101,色波**/
            if (this.code.equalsIgnoreCase(IAllPlayCode.色波)) {
                    flag = true;
                /**27030102,半波**/
            } else if (this.code .equalsIgnoreCase(IAllPlayCode.半波)) {
                flag = true;
            }
        }
        return flag;
    }

}
