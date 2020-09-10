package com.ski.box.utils.lottery.algorithm.play.marksix;

import com.ski.box.bean.lottery.IAllPlayCode;
import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.Zodiac;
import com.ski.box.utils.lottery.algorithm.utils.array.ArrayUtils;
import com.ski.box.utils.lottery.algorithm.utils.exception.GlobalServiceException;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 六合彩 正特肖 ->连肖
 * 27080101,正特一肖
 * 27080102,二连肖
 * 27080103,三连肖
 * 27080104,四连肖
 * 27080105,五连肖
 * 27080107,总肖单双
 */
public class ZtxPlayHandler implements ITicketPlayHandler {
    private String code;

    public ZtxPlayHandler(String code) {
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
            //------------------------------------------------------------------
            if (IAllPlayCode.二连肖.equalsIgnoreCase(code)) {
                /**六合彩 二连肖**/
                return getLiuHeCaiErLianXiaoCount(betNums);
            } else if (IAllPlayCode.三连肖.equalsIgnoreCase(code)) {
                /**六合彩 三连肖**/
                return getLiuHeCaiSanLianXiaoCount(betNums);
            } else if (IAllPlayCode.四连肖.equalsIgnoreCase(code)) {
                /**六合彩 四连肖**/
                return getLiuHeCaiSiLianXiaoCount(betNums);
            } else if (IAllPlayCode.五连肖.equalsIgnoreCase(code)) {
                /**六合彩 五连肖**/
                return getLiuHeCaiWuLianXiaoCount(betNums);
            } else {
                /**六合彩 正特一肖**/
                return StringUtils.calculateBetNum(betNums);
            }
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
            if (IAllPlayCode.正特一肖.equalsIgnoreCase(code)) {
                String[] nums = StringUtils.split(betNums, ",");
                String[] var5 = nums;
                int var6 = nums.length;

                for (int var7 = 0; var7 < var6; ++var7) {
                    String num = var5[var7];
                    flag = true;
                    if (!ArrayUtils.isExist(SHENG_XIAO, num)) {
                        flag = false;
                        break;
                    }
                }
            } else if (IAllPlayCode.总肖单双.equalsIgnoreCase(code) && ArrayUtils.isExist(DAN_SHUANG, betNums)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 六合彩的二连肖注数计算
     *
     * @param splicingStr 拼接号的参数
     * @return false 常规计算,采用jar包, true ,自己计算,采用value值
     */
    private int getLiuHeCaiErLianXiaoCount(String splicingStr) {
        splicingStr = StringUtils.checkStringBetNum(splicingStr);
        String[] replace = splicingStr.split(",");
        List<Zodiac> list = new ArrayList<Zodiac>();
        for (int i = 0; i < replace.length; i++) {
            for (int j = i + 1; j < replace.length; j++) {
                Zodiac a = new Zodiac();
                a.setOne(replace[i]);
                a.setTwo(replace[j]);
                list.add(a);
            }
        }
        if (list.size() > 0) {
            StringBuffer ss = new StringBuffer();
            for (int o = 0; o < list.size(); o++) {
                ss.append(list.get(o).getOne() + "," + list.get(o).getTwo() + "/");
            }
//            ConfigStandardBetManager.getInstance().setBetNum(ss.toString());
        }
        return list.size();
    }

    /**
     * 六合彩的三连肖注数计算
     *
     * @param splicingStr 拼接号的参数
     * @return false 常规计算,采用jar包, true ,自己计算,采用value值
     */
    private int getLiuHeCaiSanLianXiaoCount(String splicingStr) {
        splicingStr = StringUtils.checkStringBetNum(splicingStr);
        String[] replace = splicingStr.split(",");
        List<Zodiac> list = new ArrayList<Zodiac>();
        for (int i = 0; i < replace.length; i++) {
            for (int j = i + 1; j < replace.length; j++) {
                for (int k = j + 1; k < replace.length; k++) {
                    Zodiac a = new Zodiac();
                    a.setOne(replace[i]);
                    a.setTwo(replace[j]);
                    a.setThree(replace[k]);
                    list.add(a);
                }
            }
        }
        if (list.size() > 0) {
            StringBuffer ss = new StringBuffer();
            for (int o = 0; o < list.size(); o++) {
                ss.append(list.get(o).getOne() + "," + list.get(o).getTwo() + "," + list.get(o).getThree() + "/");
            }
//            ConfigStandardBetManager.getInstance().setBetNum(ss.toString());
        }
        return list.size();
    }

    /**
     * 六合彩的四连肖注数计算
     *
     * @param splicingStr 拼接号的参数
     * @return false 常规计算,采用jar包, true ,自己计算,采用value值
     */
    private int getLiuHeCaiSiLianXiaoCount(String splicingStr) {
        splicingStr = StringUtils.checkStringBetNum(splicingStr);
        String[] replace = splicingStr.split(",");
        List<Zodiac> list = new ArrayList<Zodiac>();
        for (int i = 0; i < replace.length; i++) {
            for (int j = i + 1; j < replace.length; j++) {
                for (int k = j + 1; k < replace.length; k++) {
                    for (int h = k + 1; h < replace.length; h++) {
                        Zodiac a = new Zodiac();
                        a.setOne(replace[i]);
                        a.setTwo(replace[j]);
                        a.setThree(replace[k]);
                        a.setFour(replace[h]);
                        list.add(a);
                    }
                }
            }
        }
        if (list.size() > 0) {
            StringBuffer ss = new StringBuffer();
            for (int o = 0; o < list.size(); o++) {
                ss.append(list.get(o).getOne() + "," + list.get(o).getTwo() + "," + list.get(o).getThree() + "," + list.get(o).getFour() + "/");
            }
//            ConfigStandardBetManager.getInstance().setBetNum(ss.toString());
        }
        return list.size();
    }

    /**
     * 六合彩的五连肖注数计算
     *
     * @param splicingStr 拼接号的参数
     * @return false 常规计算,采用jar包, true ,自己计算,采用value值
     */
    private int getLiuHeCaiWuLianXiaoCount(String splicingStr) {
        splicingStr = StringUtils.checkStringBetNum(splicingStr);
        String[] replace = splicingStr.split(",");
        List<Zodiac> list = new ArrayList<Zodiac>();
        for (int i = 0; i < replace.length; i++) {
            for (int j = i + 1; j < replace.length; j++) {
                for (int k = j + 1; k < replace.length; k++) {
                    for (int h = k + 1; h < replace.length; h++) {
                        for (int g = h + 1; g < replace.length; g++) {
                            Zodiac a = new Zodiac();
                            a.setOne(replace[i]);
                            a.setTwo(replace[j]);
                            a.setThree(replace[k]);
                            a.setFour(replace[h]);
                            a.setFive(replace[g]);
                            list.add(a);
                        }
                    }
                }
            }
        }
        if (list.size() > 0) {
            StringBuffer ss = new StringBuffer();
            for (int o = 0; o < list.size(); o++) {
                ss.append(list.get(o).getOne() + "," + list.get(o).getTwo() + "," + list.get(o).getThree() + "," + list.get(o).getFour() + "," + list.get(o).getFive() + "/");
            }
//            ConfigStandardBetManager.getInstance().setBetNum(ss.toString());
        }
        return list.size();
    }


}
