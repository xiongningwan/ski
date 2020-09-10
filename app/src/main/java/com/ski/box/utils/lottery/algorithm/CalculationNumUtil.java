package com.ski.box.utils.lottery.algorithm;


import com.ski.box.bean.DataCenter;
import com.ski.box.bean.lottery.CodeExhibition;
import com.ski.box.bean.lottery.IAllPlayCode;
import com.ski.box.bean.lottery.LotteryPlay;
import com.ski.box.bean.lottery.LotteryPlayEnd;
import com.ski.box.bean.lottery.LotteryPlayStart;
import com.ski.box.bean.lottery.LotteryPlaySub;
import com.ski.box.utils.lottery.algorithm.play.ArithmeticHandler;
import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.elevenfive.RXFS115PlayHandler;
import com.ski.box.utils.lottery.algorithm.play.elevenfive.ZHXFS115PlayHandler;
import com.ski.box.utils.lottery.algorithm.play.elevenfive.ZXFS115PlayHandler;
import com.ski.box.utils.lottery.algorithm.play.fc3d.ZHXFSPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.fc3d.ZXZ3PlayDoubleHanlder;
import com.ski.box.utils.lottery.algorithm.play.marksix.LhcErZhongTePlayHandler;
import com.ski.box.utils.lottery.algorithm.play.marksix.LhcHeXiaoPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.marksix.LhcLianWeiPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.marksix.LhcLianXiaoPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.marksix.LhcTeChuanPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.marksix.LhcZiXuanBuZhongPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.marksix.ZmPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.BDWPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.exception.GlobalServiceException;

import java.util.List;

import static com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler.LIAN_WEI_CODE;
import static com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler.SHENG_XIAO_CODE;


public class CalculationNumUtil {

    public static final String TYPE_DOU_HAO = ",";
    public static final String TYPE_KONG_GE = " ";
    public static final String TYPE_KONG_ZIFUCHUAN = "";
    public static final String HENG_GANG = "-";
    public static final String KONG_ZHI = null;

    private ArithmeticHandler mArithmetic;

    public CalculationNumUtil() {

       // mArithmetic =  ArithmeticHandler.getInstance(getLotterySeriesId());
     //   mArithmetic.getHandler()
    }


    // 双面
    public static CodeExhibition getAttachCodesString_d(LotteryPlayStart play, int playPosition) {
        return getDoubleAttachCodesString(play, playPosition);
    }


    /**
     * 双面盘 计算注数
     *
     * @param play
     * @param playPosition
     * @return
     */
    private static CodeExhibition getDoubleAttachCodesString(LotteryPlayStart play, int playPosition) {
        CodeExhibition codes = getDoubleAppendString(play, playPosition);
        ITicketPlayHandler handler = null;
        switch (play.getTitle()) {
            case "组选":
                if ("前二组选".equals(codes.getPlayName())) {
                    handler = new ZXFS115PlayHandler(2, ITicketPlayHandler.OFFSETS_QIANER, " ");
                } else if ("前三组选".equals(codes.getPlayName())) {
                    handler = new ZXFS115PlayHandler(3, ITicketPlayHandler.OFFSETS_QIANSAN, " ");
                }
            case "直选":
                if ("前二直选".equals(codes.getPlayName())) {
                    handler = new ZHXFS115PlayHandler(ITicketPlayHandler.OFFSETS_QIANER, ",", " ");
                } else if ("前三直选".equals(codes.getPlayName())) {
                    handler = new ZHXFS115PlayHandler(ITicketPlayHandler.OFFSETS_QIANSAN, ",", " ");
                }
                break;
            case "特码":
                if ("合肖".equals(codes.getPlayName())) {
                    codes.setBetTxt(codes.getBetTxt().replace(" ", ","));
                    codes.setShowTxt(codes.getShowTxt().replace(" ", ","));
                    handler = new LhcHeXiaoPlayHandler(SHENG_XIAO_CODE, ",");
                }
                break;
            case "连肖连尾":
                codes.setBetTxt(codes.getBetTxt().replace(" ", ","));
                codes.setShowTxt(codes.getShowTxt().replace(" ", ","));
                if ("二连肖".equals(codes.getPlayName())) {
                    handler = new LhcLianXiaoPlayHandler(SHENG_XIAO_CODE, ",", 2);
                } else if ("三连肖".equals(codes.getPlayName())) {
                    handler = new LhcLianXiaoPlayHandler(SHENG_XIAO_CODE, ",", 3);
                } else if ("四连肖".equals(codes.getPlayName())) {
                    handler = new LhcLianXiaoPlayHandler(SHENG_XIAO_CODE, ",", 4);
                } else if ("五连肖".equals(codes.getPlayName())) {
                    handler = new LhcLianXiaoPlayHandler(SHENG_XIAO_CODE, ",", 5);
                } else if ("二连尾".equals(codes.getPlayName())) {
                    handler = new LhcLianWeiPlayHandler(LIAN_WEI_CODE, ",", 2);
                } else if ("三连尾".equals(codes.getPlayName())) {
                    handler = new LhcLianWeiPlayHandler(LIAN_WEI_CODE, ",", 3);
                } else if ("四连尾".equals(codes.getPlayName())) {
                    handler = new LhcLianWeiPlayHandler(LIAN_WEI_CODE, ",", 4);
                } else if ("五连尾".equals(codes.getPlayName())) {
                    handler = new LhcLianWeiPlayHandler(LIAN_WEI_CODE, ",", 5);
                }
                break;
            case "连码":
                codes.setBetTxt(codes.getBetTxt().replace(" ", ","));
                codes.setShowTxt(codes.getShowTxt().replace(" ", ","));
                if ("三中二".equals(codes.getPlayName())) {
                    handler = new ZmPlayHandler(IAllPlayCode.三中二, ",");
                } else if ("三全中".equals(codes.getPlayName())) {
                    handler = new ZmPlayHandler(IAllPlayCode.三全中, ",");
                } else if ("二全中".equals(codes.getPlayName())) {
                    handler = new ZmPlayHandler(IAllPlayCode.二全中, ",");
                } else if ("四全中".equals(codes.getPlayName())) {
                    handler = new ZmPlayHandler(IAllPlayCode.四全中, ",");
                } else if ("二中特".equals(codes.getPlayName())) {
                    handler = new LhcErZhongTePlayHandler(",");
                } else if ("特串".equals(codes.getPlayName())) {
                    handler = new LhcTeChuanPlayHandler(",");
                }
                break;
            case "自选不中":
                codes.setBetTxt(codes.getBetTxt().replace(" ", ","));
                codes.setShowTxt(codes.getShowTxt().replace(" ", ","));
                handler = new LhcZiXuanBuZhongPlayHandler(",");
                break;
            case "二字定位":
                handler = new ZHXFSPlayHandler(ITicketPlayHandler.OFFSETS_HOUER, ",", " ");
                break;
            case "三字定位":
                handler = new ZHXFSPlayHandler(ITicketPlayHandler.OFFSETS_HOUSAN, ",", " ");
                break;
            case "组三":
                handler = new ZXZ3PlayDoubleHanlder(ITicketPlayHandler.OFFSETS_QIANER, ",", " ");
                break;
            case "组六":
                handler = new BDWPlayHandler(3, ITicketPlayHandler.OFFSETS_HOUSAN, ITicketPlayHandler.NUMS_SSC, " ");
                break;
            case "任选":
                if ("任选一".equals(codes.getPlayName())) {
                    handler = new RXFS115PlayHandler(1, " ");
                } else if ("任选二".equals(codes.getPlayName())) {
                    handler = new RXFS115PlayHandler(2, " ");
                } else if ("任选三".equals(codes.getPlayName())) {
                    handler = new RXFS115PlayHandler(3, " ");
                } else if ("任选四".equals(codes.getPlayName())) {
                    handler = new RXFS115PlayHandler(4, " ");
                } else if ("任选五".equals(codes.getPlayName())) {
                    handler = new RXFS115PlayHandler(5, " ");
                } else if ("任选六中五".equals(codes.getPlayName())) {
                    handler = new RXFS115PlayHandler(6, " ");
                } else if ("任选七中五".equals(codes.getPlayName())) {
                    handler = new RXFS115PlayHandler(7, " ");
                } else if ("任选八中五".equals(codes.getPlayName())) {
                    handler = new RXFS115PlayHandler(8, " ");
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + play.getTitle());
        }

        try {
            if (handler != null && handler.validateBetNums(codes.getBetTxt())) {
                codes.setBetNum(handler.calculateBetNum(codes.getBetTxt()));
                if (play.getTitle().equals("组三") || play.getTitle().equals("二字定位") || play.getTitle().equals("三字定位")) {
                    String str = codes.getBetTxt().replace(",", "|");
                    codes.setBetTxt(str);
                    codes.setShowTxt(str);
                }
//                else if (play.getTitle().equals("直选")) {
//                    codes.setBetTxt(getCodeWay(codes.getBetTxt(), "|", true));
//                }
//                else {
//                    codes.setBetTxt(codes.getBetTxt());
//                }
                codes.setBetTxt(codes.getBetTxt());
            }
        } catch (GlobalServiceException e) {
            codes.setBetNum(0);
        }
        return codes;
    }

    /**
     * 双面盘 组装号码
     *
     * @param playStart
     * @param playPosition
     * @return
     */
    private static CodeExhibition getDoubleAppendString(LotteryPlayStart playStart, int playPosition) {
        CodeExhibition codes = new CodeExhibition();
        List<LotteryPlaySub> playSubs = playStart.getSubPlays();
        LotteryPlaySub playSub = playSubs.get(playPosition);

        StringBuffer codeBetTxt = new StringBuffer();
        StringBuffer codeShowTxt = new StringBuffer();
        for (LotteryPlayEnd playEnd : playSub.getLotteryPlayEnds()) {
            if (codeBetTxt.length() > 0 && codeShowTxt.length() > 0) {
                codeBetTxt.replace(codeBetTxt.length() - 1, codeBetTxt.length(), "");
                codeBetTxt.append(",");
                codeShowTxt.replace(codeShowTxt.length() - 1, codeShowTxt.length(), "");
                codeShowTxt.append(",");
            }
            for (LotteryPlay play : playEnd.getLotteryPlays()) {
                if (!play.isSelected()) {
                    continue;
                }
                if (isDoubleVerified(playStart.getTitle())) {
                    String playName = playSub.getTitleSub();
                    if ("组六_副".equals(playName)) playName = "组六";
                    codes.setPlayName(playName);
                } else {//二字定位、三字定位、组三
                    codes.setPlayName(playStart.getTitle());
                }
                codeShowTxt.append(play.getName()).append(" ");
                codeBetTxt.append(play.getCode()).append(" ");
                codes.setOdds(getOdds(play.getOdds()));
                codes.setPlayItemId(play.getId());
                codes.setTag(play.getTag());
            }
        }
        if (codeBetTxt.length() > 0 && codeShowTxt.length() > 0) {
            codes.setShowTxt(codeShowTxt.delete(codeShowTxt.length() - 1, codeShowTxt.length()).toString());
            codes.setBetTxt(codeBetTxt.delete(codeBetTxt.length() - 1, codeBetTxt.length()).toString());
        }
        return codes;
    }


    private static String getOdds(String odds) {
        int index = odds.indexOf("～");
        if (index != -1) {
            String[] oddsStr = odds.split("～");
            return oddsStr[1];
        }
        return odds;
    }

    private static boolean isDoubleVerified(String title) {
        return title.equals("连肖连尾") || title.equals("特码") || title.equals("连码") || title.equals("自选不中") || title.equals("组选")
                || title.equals("任选") || title.equals("组六") || title.equals("直选");
    }



}
