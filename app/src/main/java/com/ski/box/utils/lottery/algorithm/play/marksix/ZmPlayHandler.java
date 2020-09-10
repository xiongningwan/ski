package com.ski.box.utils.lottery.algorithm.play.marksix;

import android.text.TextUtils;

import com.ski.box.bean.lottery.IAllPlayCode;
import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.array.ArrayUtils;
import com.ski.box.utils.lottery.algorithm.utils.exception.GlobalServiceException;
import com.ski.box.utils.lottery.algorithm.utils.math.AlgorithmUtil;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

/**
 * 六合彩
 * 27040101,正码
 * 27040102,正码1
 * 27040103,正码2
 * 27040104,正码3
 * 27040105,正码4
 * 27040106,正码5
 * 27040107,正码6
 * 27040108, 三中二
 * 27040109, 三全中
 * 27040110, 二全中
 * 27040111, 四全中
 */
public class ZmPlayHandler implements ITicketPlayHandler {

    private String code;
    private String symbol = "";

    public ZmPlayHandler( String code, String symbol) {
        this.code = code;
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

            /* if (this.playId >= 27040101 && this.playId <= 27040107) {*/
            /** 27040101,正码 27040102,正码1   27040103,正码2   27040104,正码3 27040105,正码4  27040106,正码5  27040107,正码6**/
            if (code.equalsIgnoreCase(IAllPlayCode.正码)
                    || code.equalsIgnoreCase(IAllPlayCode.正码1)
                    || code.equalsIgnoreCase(IAllPlayCode.正码2)
                    || code.equalsIgnoreCase(IAllPlayCode.正码3)
                    || code.equalsIgnoreCase(IAllPlayCode.正码4)
                    || code.equalsIgnoreCase(IAllPlayCode.正码5)
                    || code.equalsIgnoreCase(IAllPlayCode.正码6)) {
                returnNum = strs.length;
                /** 27040108, 三中二 27040109, 三全中  27040110, 二全中 27040111, 四全中 **/
                /* (this.playId >= 27040108 && this.playId <= 27040111)*/
            } else if (this.code.equalsIgnoreCase(IAllPlayCode.三中二)
                    || code.equalsIgnoreCase(IAllPlayCode.三全中)
                    || code.equalsIgnoreCase(IAllPlayCode.二全中)
                    || code.equalsIgnoreCase(IAllPlayCode.四全中)) {
                /* 27040108, 三中二*/
                if (this.code.equalsIgnoreCase(IAllPlayCode.三中二)) {
                    returnNum = AlgorithmUtil.combination(strs.length, 3).intValue();
                }
                /*   27040109, 三全中*/
                if (this.code.equalsIgnoreCase(IAllPlayCode.三全中)) {
                    returnNum = AlgorithmUtil.combination(strs.length, 3).intValue();
                }
                /*  27040110, 二全中*/
                if (this.code.equalsIgnoreCase(IAllPlayCode.二全中)) {
                    returnNum = AlgorithmUtil.combination(strs.length, 2).intValue();
                }
                /*  27040111, 四全中*/
                if (this.code.equalsIgnoreCase(IAllPlayCode.四全中)) {
                    returnNum = AlgorithmUtil.combination(strs.length, 4).intValue();
                }
            }
            return returnNum;
        }
    }


    @Override
    public boolean validateBetNums(String betNums) {
        boolean flag = false;
        if (TextUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = StringUtils.split(betNums, symbol);
            String[] var5 = nums;
            int var6 = nums.length;
            for (int var7 = 0; var7 < var6; ++var7) {
                String num = var5[var7];
                flag = ArrayUtils.isExist(DING_WEI_DAN_OR_XUANHAO, num);
            }
        }
        return flag;
    }
}
