package com.ski.box.utils.lottery;

import android.text.TextUtils;

import com.hwangjr.rxbus.RxBus;
import com.ski.box.bean.BetStatus;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.MkBetParamEntity;
import com.ski.box.bean.SelfProfileEntity;
import com.ski.box.bean.lottery.CodeExhibition;
import com.ski.box.bean.lottery.LotteryConstant;
import com.ski.box.bean.lottery.LotteryPlay;
import com.ski.box.bean.lottery.LotteryPlayEnd;
import com.ski.box.bean.lottery.LotteryPlayStart;
import com.ski.box.bean.lottery.LotteryPlaySub;
import com.ski.box.bean.lottery.RemoteLotteryPlay;
import com.ski.box.utils.lottery.algorithm.CalculationNumUtil;
import com.yb.core.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import static com.ski.box.ConstantValue.EVENT_BET_ODDS_CHANGE;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_PLAY_DAN;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_PLAY_FUSHI;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_PLAY_MODE_STANDARD;


/**
 * 数字处理，期号处理
 */
public class LotteryNoUtil {

    public static long getPlayIdPrefix() {
        // 145010101
        int mode = 1;
        int lotteryId = DataCenter.getInstance().getCurLotteryId();
        long id_Prefix = 0;
        if (lotteryId < 10) {
//            id_Prefix = mode * 100000000 + lotteryId * 1000000;
            id_Prefix = mode * 10000000 + lotteryId * 1000000;
        } else if (lotteryId < 100) {
            id_Prefix = mode * 100000000 + lotteryId * 1000000;

        } else if (lotteryId < 1000) {
            id_Prefix = mode * 100000000 + lotteryId * 100000;
        } else {
            id_Prefix = mode * 100000000 + lotteryId * 10000;
        }
        return id_Prefix;
    }

    public static long getPlayIdPrefix(int lotteryId) {
        // 145010101
        int mode = 1;
        long id_Prefix = 0;
        if (lotteryId < 10) {
//            id_Prefix = mode * 100000000 + lotteryId * 10000000;
            id_Prefix = mode * 10000000 + lotteryId * 1000000;
        } else if (lotteryId < 100) {
            id_Prefix = mode * 100000000 + lotteryId * 1000000;
        } else if (lotteryId < 1000) {
            id_Prefix = mode * 100000000 + lotteryId * 100000;
        } else {
            id_Prefix = mode * 100000000 + lotteryId * 10000;
        }
        return id_Prefix;
    }

    public static long getPeriod2Long(String planId) {
        long period = 0;
        if (!TextUtils.isEmpty(planId)) {
            if (planId.contains("-")) {
                String tempPeriod = planId.replace("-", "");
                try {
                    period = Long.parseLong(tempPeriod);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    period = Long.parseLong(planId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return period;
    }

    /**
     * 期号的缩略格式
     *
     * @param issue
     * @return
     */
    public static String getShortIssue(String issue) {
        if (TextUtils.isEmpty(issue)) {
            return "-";
        }

        int seriesId = DataCenter.getInstance().getLotterySeriesId();
        if (seriesId == LotteryConstant.SER_ID_LHC) {
            String str = "-";
            if (issue.contains("-")) {
                String[] arr = issue.split("-");
                if (arr.length > 1 && arr[0].length() > 2) {
                    String s1 = arr[0].substring(arr[0].length() - 2);
                    str = s1 + arr[1];
                }

            } else {
                str = issue.substring(2);
            }
            return str;
        } else {
            if (issue.contains("-")) {
                String[] arr = issue.split("-");
                if (arr.length > 1 && arr[0].length() > 4) {
                    String s1 = arr[0].substring(arr[0].length() - 2);
                    issue = s1 + arr[1];
                }
            } else if (issue.length() > 8) {
                issue = issue.substring(6, 8) + issue.substring(8);
            }
            return issue;
        }


    }

    /**
     * paike
     *
     * @param money
     * @return
     */
    /*保留两位小数*/
    public static String keepTwoDecimal(String money) {
        String[] split = money.split("\\.");
        int length = split.length;
        if (length > 1) {
            String s = split[1];
            int length1 = s.length();
            String substring;
            if (length1 > 2) {
                substring = s.substring(0, 2);
            } else {
                substring = s;
            }
            return split[0] + substring;
        } else {
            return split[0];
        }
    }

    public static String[] getTime(long countDownTimes) {
        int hours = (int) (countDownTimes / 1000 / 3600);
        int minutes = (int) ((countDownTimes / 1000 / 60) % 60);
        int seconds = (int) (countDownTimes / 1000 % 60);
        String stringHour;
        String stringMinute;
        String stringSecond;
        if (hours < 10) {
            stringHour = String.format("0%d", hours);
        } else {
            stringHour = String.format("%d", hours);
        }
        if (minutes < 10) {
            stringMinute = String.format("0%d", minutes);
        } else {
            stringMinute = String.format("%d", minutes);
        }
        if (seconds < 10) {
            stringSecond = String.format("0%d", seconds);
        } else {
            stringSecond = String.format("%d", seconds);
        }
        String[] arr = new String[]{stringHour, stringMinute, stringSecond};
        return arr;
    }

    public static String getRangeStr(int min, int max) {
        String builder = String.format("%s～%s", min, max);
        return builder;
    }

    public static List<SelfProfileEntity.DoubleBetRangeListBean> getProfile(SelfProfileEntity selfProfile) {
        // 设置长龙最短期数
        // SettingManager.setLongDragonNumPeriods(selfProfile.getLongDragonLimit());

        // 限红
        List<SelfProfileEntity.DoubleBetRangeListBean> rangeList = new ArrayList<>();
        for (int i = 0; i < selfProfile.getDoubleBetRangeList().size(); i++) {
            SelfProfileEntity.DoubleBetRangeListBean range = selfProfile.getDoubleBetRangeList().get(i);
            if (range.isCurrent()) {
                //本地保存默认项
                String rangeStr = LotteryNoUtil.getRangeStr(range.getDoublePlayBetMin(), range.getDoublePlayBetMax());
                SettingManager.setDoubleBetAmountRange(rangeStr);
                if (selfProfile.isDelayed()) {
                    range.setLock(true);
                }
            }
            rangeList.add(range);
        }
        return rangeList;
    }


    /**
     * 设置特殊赔率
     *
     * @param playId
     * @param odd
     * @return
     */
    public static String[] setSpecialOdd(long playId, String odd) {
        String[] oddArr;
        if (!TextUtils.isEmpty(odd) && odd.contains(",")) {
            oddArr = odd.split(",");
        } else {
            oddArr = new String[]{odd};
        }
        return oddArr;
    }

    public static BetStatus getBetStatus(List<MkBetParamEntity.BetParamEntity> bets, int playMode) {
        BetStatus betStatus = new BetStatus();
        int zhuShu = 0;//注数
        if (bets == null) {
            return betStatus;
        }
        if (playMode == LOTTERY_PLAY_MODE_STANDARD) {
            /*标准盘*/
            double totalAmount = 0;
            for (MkBetParamEntity.BetParamEntity betParamEntity : bets) {
                totalAmount += betParamEntity.getBetAmount_s() * betParamEntity.getBetCount() * betParamEntity.getBetBeiShu();
                zhuShu += betParamEntity.getBetCount();
                betStatus.setStatus(LOTTERY_PLAY_FUSHI);
            }
            betStatus.setTotalAmount_s(totalAmount);
        } else {
            int totalAmount_d = 0;
            for (MkBetParamEntity.BetParamEntity betParamEntity : bets) {
                totalAmount_d +=  1.0 * betParamEntity.getBetAmount_d() * betParamEntity.getBetCount();
                zhuShu += betParamEntity.getBetCount();
                betStatus.setStatus(betParamEntity.isSingle() ? LOTTERY_PLAY_DAN : LOTTERY_PLAY_FUSHI);
            }
            betStatus.setTotalAmount(totalAmount_d);
        }

        betStatus.setZhuShu(zhuShu);
        return betStatus;
    }

    //1可赢= 输入金额 X 赔率 - 投注金额
    //（注：输入金额指用户在“金额输入框”内输入的金额；复式注单下的 投注金额，不等于输入金额，指该注单总的投注额；如出现负数，正常显示负数）
    //2、若该玩法有多个赔率，则取最大赔率
    public static String calculateWinMoney_d(MkBetParamEntity.BetParamEntity betParamEntity) {
        return LotteryWinMoneyUtil.calculateWinMoney_d(betParamEntity);
    }


    //双面盘注数算法
    public static MkBetParamEntity calculateBets_d(LotteryPlayStart play, int playPosition) {
        MkBetParamEntity entity = new MkBetParamEntity();
        entity = getParams_Double(play, playPosition);
        return entity;
    }

    /**
     * 双面盘
     *
     * @param play
     * @return
     */
    private static MkBetParamEntity getParams_Double(LotteryPlayStart play, int playPosition) {
        MkBetParamEntity entity = new MkBetParamEntity();
        entity.setClickEnable(true);
        List<MkBetParamEntity.BetParamEntity> bets = new ArrayList<>();
        entity.setBet(bets);
        //TODO：单选 多选
//        if ("双面".equals(play.getTitle()) || "选号".equals(play.getTitle()) || "前中后三".equals(play.getTitle()) || "冠亚和值".equals(play.getTitle())
//                || "特码".equals(play.getTitle()) || "正码".equals(play.getTitle()) || "正码特".equals(play.getTitle()) || "一肖总肖平特尾数".equals(play.getTitle())
//                || "总和".equals(play.getTitle()) || "一字组合".equals(play.getTitle()) || "二字组合".equals(play.getTitle()) || "三字组合".equals(play.getTitle())
//                || "一字定位".equals(play.getTitle()) || "二字和数".equals(play.getTitle()) || "三字和数".equals(play.getTitle()) || "跨度".equals(play.getTitle())
//                || "整合".equals(play.getTitle()) || "鱼虾蟹".equals(play.getTitle())) {
//            LotteryPlaySub playSub = play.getSubPlays().get(playPosition);
//            if ("合肖".equals(playSub.getTitleSub())) {
//                onComposeResult(bets, play, playPosition);
//            } else {
//                entity = getParams_DAN(play, playPosition);
//            }
//        } else if ("自选不中".equals(play.getTitle())) {
//            onComposeResultLimit(entity, bets, play, playPosition, 11);
//        } else {
//            onComposeResult(bets, play, playPosition);
//        }
        String remoteCode = play.getRemoteCode();

        if ("shuangmian".equals(remoteCode) || "danhao".equals(remoteCode) || "liangmian".equals(remoteCode) || "xuanhao".equals(remoteCode)
                || "qianzhonghousan".equals(remoteCode) || "guanyahezhi".equals(remoteCode) || "tema".equals(remoteCode) || "zhengma".equals(remoteCode)
                || "zhengmate".equals(remoteCode) || "yixiaozongxiaopingteweishu".equals(remoteCode) || "zonghe".equals(remoteCode) || "yizizuhe".equals(remoteCode)
                || "liangzizuhe".equals(remoteCode) || "sanzizuhe".equals(remoteCode) || "yizidingwei".equals(remoteCode) || "liangziheshu".equals(remoteCode)
                || "sanziheshu".equals(remoteCode) || "kuadu".equals(remoteCode) || "zhenghe".equals(remoteCode) || "yuxiaxietoubao".equals(remoteCode)) {
            LotteryPlaySub playSub = play.getSubPlays().get(playPosition);
            if ("hexiao".equals(playSub.getRemoteCode())) {
                onComposeResult(bets, play, playPosition);
            } else {
                entity = getParams_DAN(play, playPosition);
            }
        } else if ("zixuanbuzhong".equals(remoteCode)) {
            onComposeResultLimit(entity, bets, play, playPosition, 11);
        } else {
            onComposeResult(bets, play, playPosition);
        }
        return entity;
    }

    private static void onComposeResult(List<MkBetParamEntity.BetParamEntity> bets, LotteryPlayStart play, int playPosition) {
        CodeExhibition codes = CalculationNumUtil.getAttachCodesString_d(play, playPosition);
        MkBetParamEntity.BetParamEntity betParamEntity = new MkBetParamEntity.BetParamEntity();
        betParamEntity.setPlayId(String.valueOf(codes.getPlayItemId()));
        betParamEntity.setBetNum(codes.getBetTxt());
        betParamEntity.setContent(codes.getShowTxt());
        betParamEntity.setBetAmount_d(DataCenter.getInstance().getQuickMoney());
        betParamEntity.setBetCount(codes.getBetNum());

        /*fushi*/
        betParamEntity.setGroupName(codes.getPlayName());
        betParamEntity.setGroupCode(codes.getRemoteSubCode());
        betParamEntity.setSingle(false);
        betParamEntity.setPlayOdds(codes.getOdds());
        bets.add(betParamEntity);
    }

    private static void onComposeResult(CodeExhibition codes, List<MkBetParamEntity.BetParamEntity> bets, LotteryPlayStart play, int playPosition, RemoteLotteryPlay remoteLotteryPlay) {
        MkBetParamEntity.BetParamEntity betParamEntity = new MkBetParamEntity.BetParamEntity();
        betParamEntity.setPlayId(String.valueOf(remoteLotteryPlay.getId()));
        betParamEntity.setBetNum(codes.getBetTxt());
        betParamEntity.setContent(codes.getShowTxt());
        betParamEntity.setBetAmount_d(DataCenter.getInstance().getQuickMoney());
        betParamEntity.setBetCount(codes.getBetNum());

        /*fushi*/
        betParamEntity.setGroupName(codes.getPlayName());
        betParamEntity.setGroupCode(codes.getRemoteSubCode());
        betParamEntity.setSingle(false);
        betParamEntity.setPlayOdds(remoteLotteryPlay.getOdds());
        bets.add(betParamEntity);
    }

    /**
     * 选号个数限制
     *
     * @param bets
     * @param play
     * @param playPosition
     * @param limit
     */
    private static void onComposeResultLimit(MkBetParamEntity entity, List<MkBetParamEntity.BetParamEntity> bets, LotteryPlayStart play, int playPosition, int limit) {
        CodeExhibition codes = new CalculationNumUtil().getAttachCodesString_d(play, playPosition);
        int length = codes.getShowTxt().split(",").length;
        RemoteLotteryPlay remoteLotteryPlay = new RemoteLotteryPlay();
        if (length > limit) {
            length = limit;
            // 移除最后一个
            codes.setBetTxt(fixCodes(codes.getBetTxt()));
            codes.setShowTxt(fixCodes(codes.getShowTxt()));
            entity.setClickEnable(false);
            ToastUtil.showError("最多可选11球");
        } else {
            entity.setClickEnable(true);
        }

        List<RemoteLotteryPlay> list = DataCenter.getInstance().getZixuanbuzhongOdd_list();
        if (null != list && list.size() > length - 5) {
            /*通过RxBus发送修改的赔率*/
            String odd;
            if (length > 4) {
                remoteLotteryPlay = list.get(length - 5);
                odd = remoteLotteryPlay.getOdds();
            } else {
                odd = "/";
            }
            RxBus.get().post(EVENT_BET_ODDS_CHANGE, odd);
        }

        onComposeResult(codes, bets, play, playPosition, remoteLotteryPlay);
    }

    private static String fixCodes(String str) {
        if (!TextUtils.isEmpty(str) && str.contains(",")) {
            StringBuilder sb = new StringBuilder();
            String[] betArr = str.split(",");
            for (int i = 0; i < betArr.length; i++) {
                if (i < betArr.length - 1) {
                    sb.append(betArr[i]).append(",");
                }
            }

            if (sb.length() > 1) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }
        return str;
    }

    private static MkBetParamEntity getParams_DAN(LotteryPlayStart lotteryPlayStart, int playPosition) {
        MkBetParamEntity entity = new MkBetParamEntity();
        List<MkBetParamEntity.BetParamEntity> bets = new ArrayList<>();
        entity.setBet(bets);

        List<LotteryPlaySub> playSubs = lotteryPlayStart.getSubPlays();
        LotteryPlaySub playSub = playSubs.get(playPosition);
        List<LotteryPlayEnd> playEnds = playSub.getLotteryPlayEnds();

        for (LotteryPlayEnd playEnd : playEnds) {
            List<LotteryPlay> plays = playEnd.getLotteryPlays();

            for (LotteryPlay play : plays) {
                if (!play.isSelected()) {
                    continue;
                }
                MkBetParamEntity.BetParamEntity betParamEntity = new MkBetParamEntity.BetParamEntity();
                betParamEntity.setPlayId(String.valueOf(play.getId()));
//                betParamEntity.setBetNum(getCodeWay(play.getCode(), lotteryPlayStart.getTitle(), playSub.getTitleSub()));
                betParamEntity.setBetNum(play.getCode());
//                betParamEntity.setContent(danBetContent(lotteryPlayStart, playSub, playEnd, play));
                betParamEntity.setContent(play.getName());
                betParamEntity.setBetAmount_d(DataCenter.getInstance().getQuickMoney());
                betParamEntity.setBetCount(1);
                betParamEntity.setGroupName(play.getTag());
                betParamEntity.setSingle(true);
                betParamEntity.setPlayOdds(play.getOdds());
                bets.add(betParamEntity);
            }
        }
        return entity;
    }

    private static String danBetContent(LotteryPlayStart lotteryPlayStart, LotteryPlaySub playSub, LotteryPlayEnd playEnd, LotteryPlay play) {
        String content = play.getName();
        if ("yuxiaxietoubao".equals(lotteryPlayStart.getRemoteCode())) {
            if ("yuxiaxietoubao".equals(playSub.getRemoteCode())) {
                if ("yuxiaxietoubao".equals(play.getRemoteCodeUp())) {
                    if ("yu".equals(play.getRemoteCode())) {
                        content = "鱼";
                    } else if ("xia".equals(play.getRemoteCode())) {
                        content = "虾";
                    } else if ("hulu".equals(play.getRemoteCode())) {
                        content = "葫芦";
                    } else if ("jinxian".equals(play.getRemoteCode())) {
                        content = "金钱";
                    } else if ("xie".equals(play.getRemoteCode())) {
                        content = "蟹";
                    } else if ("ji".equals(play.getRemoteCode())) {
                        content = "鸡";
                    }
                }
            }
        }
        return content;
    }


    /**
     * 单式号码方式
     *
     * @param code
     * @return
     */
    private static String getCodeWay(String code, String oneLevel, String twoLevel) {
        StringBuffer playStr = new StringBuffer();
        if (!isVerifyOneTheory(oneLevel)) {
            if (!isVerifyTwoTheory(twoLevel)) {
                playStr.append(code.replace("", "|"));
                getCodeWay(code, playStr);
                return playStr.substring(0, playStr.length() - 1);
            } else {
                playStr.append(code);
                getCodeWay(playStr);
                return playStr.toString();
            }
        } else {
            playStr.append(code);
            getCodeWay(playStr);
            return playStr.toString();
        }
    }

    private static StringBuffer getCodeWay(StringBuffer playStr) {
        for (int i = 0; i < 4; i++) {
            playStr.insert(i, "|");
        }
        return playStr;
    }

    private static StringBuffer getCodeWay(String code, StringBuffer playStr) {
        for (int i = 0; i < 5 - (code.length() + 1); i++) {
            playStr.insert(i, "|");
        }
        return playStr;
    }

    /**
     * 一级
     *
     * @param title
     * @return
     */
    private static boolean isVerifyOneTheory(String title) {
        return title.equals("选号") || title.equals("冠亚和值") || title.equals("特码") || title.equals("正码")
                || title.equals("正码特") || title.equals("一肖总肖平特尾数") || title.equals("整合") || title.equals("二字和数")
                || title.equals("三字和数");
    }

    /**
     * 二级
     *
     * @param title
     * @return
     */
    private static boolean isVerifyTwoTheory(String title) {
        return title.equals("号码") || title.equals("特肖头尾数") || title.equals("合肖") || title.equals("正肖七色波")
                || title.equals("点数") || title.equals("长短牌");
    }


    /**
     * 计算总数
     *
     * @param arr
     * @return
     */
    public static int calculationSum(String... arr) {
        if (arr == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!TextUtils.isEmpty(arr[i])) {
                count += Integer.parseInt(arr[i]);
            }
        }
        return count;
    }
}
