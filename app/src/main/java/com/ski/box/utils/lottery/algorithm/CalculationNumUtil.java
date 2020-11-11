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
        switch (play.getRemoteCode()) {
//            case "组选":
            case "zuxuan":
//                if ("前二组选".equals(codes.getPlayName())) {
                if ("qianerzuxuan".equals(codes.getRemoteSubCode())) {
                    handler = new ZXFS115PlayHandler(2, ITicketPlayHandler.OFFSETS_QIANER, " ");
//                } else if ("前三组选".equals(codes.getPlayName())) {
                } else if ("qiansanzuxuan".equals(codes.getRemoteSubCode())) {
                    handler = new ZXFS115PlayHandler(3, ITicketPlayHandler.OFFSETS_QIANSAN, " ");
                }
//            case "直选":
            case "zhixuan":
//                if ("前二直选".equals(codes.getPlayName())) {
                if ("qianerzhixuan".equals(codes.getRemoteSubCode())) {
                    handler = new ZHXFS115PlayHandler(ITicketPlayHandler.OFFSETS_QIANER, ",", " ");
//                } else if ("前三直选".equals(codes.getPlayName())) {
                } else if ("qiansanzhixuan".equals(codes.getRemoteSubCode())) {
                    handler = new ZHXFS115PlayHandler(ITicketPlayHandler.OFFSETS_QIANSAN, ",", " ");
                }
                break;
//            case "特码":
            case "tema":
//                if ("合肖".equals(codes.getPlayName())) {
                if ("hexiao".equals(codes.getRemoteSubCode())) {
                    codes.setBetTxt(codes.getBetTxt().replace(" ", ","));
                    codes.setShowTxt(codes.getShowTxt().replace(" ", ","));
                    handler = new LhcHeXiaoPlayHandler(SHENG_XIAO_CODE, ",");
                }
                break;
//            case "连肖连尾":
            case "lianxiaolianwei":
                codes.setBetTxt(codes.getBetTxt().replace(" ", ","));
                codes.setShowTxt(codes.getShowTxt().replace(" ", ","));
                if ("erlianxiao".equals(codes.getRemoteSubCode())) {
                    handler = new LhcLianXiaoPlayHandler(SHENG_XIAO_CODE, ",", 2);
                } else if ("sanlianxiao".equals(codes.getRemoteSubCode())) {
                    handler = new LhcLianXiaoPlayHandler(SHENG_XIAO_CODE, ",", 3);
                } else if ("silianxiao".equals(codes.getRemoteSubCode())) {
                    handler = new LhcLianXiaoPlayHandler(SHENG_XIAO_CODE, ",", 4);
                } else if ("wulianxiao".equals(codes.getRemoteSubCode())) {
                    handler = new LhcLianXiaoPlayHandler(SHENG_XIAO_CODE, ",", 5);
                } else if ("erlianwei".equals(codes.getRemoteSubCode())) {
                    handler = new LhcLianWeiPlayHandler(LIAN_WEI_CODE, ",", 2);
                } else if ("sanlianwei".equals(codes.getRemoteSubCode())) {
                    handler = new LhcLianWeiPlayHandler(LIAN_WEI_CODE, ",", 3);
                } else if ("silianwei".equals(codes.getRemoteSubCode())) {
                    handler = new LhcLianWeiPlayHandler(LIAN_WEI_CODE, ",", 4);
                } else if ("wulianwei".equals(codes.getRemoteSubCode())) {
                    handler = new LhcLianWeiPlayHandler(LIAN_WEI_CODE, ",", 5);
                }
                break;
//            case "连码":
            case "lianma":
                codes.setBetTxt(codes.getBetTxt().replace(" ", ","));
                codes.setShowTxt(codes.getShowTxt().replace(" ", ","));
                if ("sanzhonger".equals(codes.getRemoteSubCode())) {
                    handler = new ZmPlayHandler(IAllPlayCode.三中二, ",");
                } else if ("sanquanzhong".equals(codes.getRemoteSubCode())) {
                    handler = new ZmPlayHandler(IAllPlayCode.三全中, ",");
                } else if ("erquanzhong".equals(codes.getRemoteSubCode())) {
                    handler = new ZmPlayHandler(IAllPlayCode.二全中, ",");
                } else if ("siquanzhong".equals(codes.getRemoteSubCode())) {
                    handler = new ZmPlayHandler(IAllPlayCode.四全中, ",");
                } else if ("erzhongte".equals(codes.getRemoteSubCode())) {
                    handler = new LhcErZhongTePlayHandler(",");
                } else if ("techuang".equals(codes.getRemoteSubCode())) {
                    handler = new LhcTeChuanPlayHandler(",");
                }
                break;
//            case "自选不中":
            case "zixuanbuzhong":
                codes.setBetTxt(codes.getBetTxt().replace(" ", ","));
                codes.setShowTxt(codes.getShowTxt().replace(" ", ","));
                handler = new LhcZiXuanBuZhongPlayHandler(",");
                break;
//            case "二字定位":
            case "liangzidingwei":
                handler = new ZHXFSPlayHandler(ITicketPlayHandler.OFFSETS_HOUER, ",", " ");
                break;
//            case "三字定位":
            case "sanzidingwei":
                handler = new ZHXFSPlayHandler(ITicketPlayHandler.OFFSETS_HOUSAN, ",", " ");
                break;
//            case "组三":
            case "zusan":
                handler = new ZXZ3PlayDoubleHanlder(ITicketPlayHandler.OFFSETS_QIANER, ",", " ");
                break;
//            case "组六":
            case "zuliu":
                handler = new BDWPlayHandler(3, ITicketPlayHandler.OFFSETS_HOUSAN, ITicketPlayHandler.NUMS_SSC, " ");
                break;
//            case "任选":
            case "renxuan":
                if ("renxuanyi".equals(codes.getRemoteSubCode())) {
                    handler = new RXFS115PlayHandler(1, " ");
                } else if ("renxuaner".equals(codes.getRemoteSubCode())) {
                    handler = new RXFS115PlayHandler(2, " ");
                } else if ("renxuansan".equals(codes.getRemoteSubCode())) {
                    handler = new RXFS115PlayHandler(3, " ");
                } else if ("renxuansi".equals(codes.getRemoteSubCode())) {
                    handler = new RXFS115PlayHandler(4, " ");
                } else if ("renxuanwu".equals(codes.getRemoteSubCode())) {
                    handler = new RXFS115PlayHandler(5, " ");
                } else if ("renxuanliuzhongwu".equals(codes.getRemoteSubCode())) {
                    handler = new RXFS115PlayHandler(6, " ");
                } else if ("renxuanqizhongwu".equals(codes.getRemoteSubCode())) {
                    handler = new RXFS115PlayHandler(7, " ");
                } else if ("renxuanbazhongwu".equals(codes.getRemoteSubCode())) {
                    handler = new RXFS115PlayHandler(8, " ");
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + play.getRemoteCode());
        }

        try {
            if (handler != null && handler.validateBetNums(codes.getBetTxt())) {
                codes.setBetNum(handler.calculateBetNum(codes.getBetTxt()));
                if ("zusan".equals(play.getRemoteCode()) || "liangzidingwei".equals(play.getRemoteCode()) || "sanzidingwei".equals(play.getRemoteCode())) {
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
                if (isDoubleVerified(playStart.getRemoteCode())) {
                    String playName = playSub.getTitleSub();
                    if ("zuliu".equals(playSub.getRemoteCode())) playName = playStart.getTitle();
                    codes.setPlayName(playName);
                } else {//二字定位、三字定位、组三
                    codes.setPlayName(playStart.getTitle());
                }
                codeShowTxt.append(play.getName()).append(" ");
                codeBetTxt.append(play.getCode()).append(" ");
                codes.setOdds(getOdds(play.getOdds()));
                codes.setPlayItemId(play.getId());
                codes.setTag(play.getTag());
                codes.setRemoteCode(playStart.getRemoteCode());
                codes.setRemoteSubCode(playSub.getRemoteCode());
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

    private static boolean isDoubleVerified(String remoteCode) {
//        return remoteCode.equals("连肖连尾") || remoteCode.equals("特码") || remoteCode.equals("连码") || remoteCode.equals("自选不中") || remoteCode.equals("组选")
//                || remoteCode.equals("任选") || remoteCode.equals("组六") || remoteCode.equals("直选");
        return remoteCode.equals("lianxiaolianwei") || remoteCode.equals("tema") || remoteCode.equals("lianma") || remoteCode.equals("zixuanbuzhong") || remoteCode.equals("zuxuan")
                || remoteCode.equals("renxuan") || remoteCode.equals("zuliu") || remoteCode.equals("zhixuan");
    }



}
