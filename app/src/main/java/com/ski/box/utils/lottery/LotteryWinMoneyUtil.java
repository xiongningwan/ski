package com.ski.box.utils.lottery;

import android.text.TextUtils;

import com.ski.box.bean.DataCenter;
import com.ski.box.bean.MkBetParamEntity;
import com.ski.box.bean.lottery.LotteryConstant;
import com.ski.box.bean.lottery.LotteryPlay;
import com.ski.box.bean.lottery.LotteryPlayEnd;
import com.ski.box.bean.lottery.LotteryPlayStart;
import com.ski.box.bean.lottery.LotteryPlaySub;
import com.ski.box.bean.lottery.LotteryUtil;
import com.ski.box.utils.lottery.algorithm.utils.math.AlgorithmUtil;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 可嬴金额
 */
public class LotteryWinMoneyUtil {
    //1可赢= 输入金额 X 赔率 - 投注金额
    //（注：输入金额指用户在“金额输入框”内输入的金额；复式注单下的 投注金额，不等于输入金额，指该注单总的投注额；如出现负数，正常显示负数）
    //2、若该玩法有多个赔率，则取最大赔率
    private static DecimalFormat mDecimalFormat;

    public static String calculateWinMoney_d(MkBetParamEntity.BetParamEntity betParamEntity) {
        long amount = betParamEntity.getBetAmount_d();
        long count = betParamEntity.getBetCount();
        String odd = betParamEntity.getPlayOdds();
        boolean isSingle = betParamEntity.isSingle();
        String groupName = betParamEntity.getGroupName();
        String betNum = betParamEntity.getBetNum();
        String showText = betParamEntity.getContent();

        if (TextUtils.isEmpty(odd)) {
            odd = "0";
        }
        double odd_F = Double.parseDouble(odd);
        double win_d = getWin_d(isSingle, groupName, betNum, showText, amount, count, odd_F);
        if (mDecimalFormat == null) {
            mDecimalFormat = new DecimalFormat("#0.###");
        }
        String win_s = mDecimalFormat.format(win_d);
        return win_s;
    }

    private static double getWin_d(boolean isSingle, String groupName, String betNum, String showText, long amount, long count, double odd_F) {
        if (!isSingle) {
            int combineCount = 0;
            int n = 1; // 用户选中号码个数
            if (!TextUtils.isEmpty(betNum)) {
                if (betNum.contains(" ")) {
                    String[] arr = betNum.split(" ");
                    n = arr.length;
                } else if (betNum.contains(",")) {
                    String[] arr = betNum.split(",");
                    n = arr.length;
                }
            }
            int serId = DataCenter.getInstance().getLotterySeriesId();
            switch (serId) {
                case LotteryConstant.SER_ID_11X5:
                    if ("任选一".equals(groupName) || "任选二".equals(groupName) || "任选三".equals(groupName) || "任选四".equals(groupName)) {
                        if (n > 5) {
                            n = 5;
                        }
                        int m = 0;
                        if ("任选一".equals(groupName)) {
                            m = 1;
                        } else if ("任选二".equals(groupName)) {
                            m = 2;
                        } else if ("任选三".equals(groupName)) {
                            m = 3;
                        } else if ("任选四".equals(groupName)) {
                            m = 4;
                        }
                        combineCount = AlgorithmUtil.combination(n, m).intValue();
                    } else if ("任选六中五".equals(groupName) || "任选七中五".equals(groupName) || "任选八中五".equals(groupName)) {
                        int m = 0;
                        if ("任选六中五".equals(groupName)) {
                            m = 1;
                        } else if ("任选七中五".equals(groupName)) {
                            m = 2;
                        } else if ("任选八中五".equals(groupName)) {
                            m = 3;
                        }
                        combineCount = AlgorithmUtil.combination(n - 5, m).intValue();
                    } else if ("前二组选".equals(groupName) || "前三组选".equals(groupName) || "前二直选".equals(groupName) || "前三直选".equals(groupName)
                            || "任选五".equals(groupName)) {
                        return amount * odd_F - amount * count;
                    }

                    break;

                case LotteryConstant.SER_ID_3D:
                    if ("二字定位".equals(groupName) || "三字定位".equals(groupName) || "组三".equals(groupName) || "组六".equals(groupName)) {
                        return amount * odd_F - amount * count;
                    }
                    break;
                case LotteryConstant.SER_ID_LHC:
                    if ("二连肖".equals(groupName) || "三连肖".equals(groupName) || "四连肖".equals(groupName) || "五连肖".equals(groupName)) {
                        if (isHasBenMing(showText)) {
                            if (n > 7) {
                                combineCount = getLHC_LX(n, groupName);
                            } else {
                                return getLHC_LX_BM(n, count, groupName, amount, odd_F);
                            }
                        } else {
                            combineCount = getLHC_LX(n, groupName);
                        }

                    } else if ("二连尾".equals(groupName) || "三连尾".equals(groupName) || "四连尾".equals(groupName) || "五连尾".equals(groupName)) {
                        if (isHasBenMing(showText)) {
                            return getLHC_LX_BM(n, count, groupName, amount, odd_F);
                        } else {
                            combineCount = getLHC_LX(n, groupName);
                        }

                } else if ("二全中".equals(groupName) || "三全中".equals(groupName) || "四全中".equals(groupName)) {
                        if (n > 6) {
                            n = 6;
                        }
                        int m = 0;
                        if ("二全中".equals(groupName)) {
                            m = 2;
                        } else if ("三全中".equals(groupName)) {
                            m = 3;
                        } else if ("四全中".equals(groupName)) {
                            m = 4;
                        }
                        combineCount = AlgorithmUtil.combination(n, m).intValue();
                    } else if ("二中特".equals(groupName)) {
                        if (n >= 2 && n <= 6) {
                            combineCount = AlgorithmUtil.combination(n, 2).intValue();
                        } else {
                            if (n > 7) {
                                n = 7;
                            }
                            combineCount = AlgorithmUtil.combination(6, 2).intValue();
                            double otherOdd = getOtherOdd("lianma", "erzhongte");
                            return amount * otherOdd * 6 + amount * odd_F * combineCount - amount * count;
                        }
                    } else if ("特串".equals(groupName)) {
                        if (n > 7) {
                            n = 7;
                        }
                        combineCount = n - 1;
                    } else if ("三中二".equals(groupName)) {

                        if (n >= 3 && n <= 6) {
                            combineCount = AlgorithmUtil.combination(n, 3).intValue();
                        } else {
                            combineCount = AlgorithmUtil.combination(6, 2).intValue();
                            double otherOdd = getOtherOdd("lianma", "sanzhonger");
                            return amount * odd_F * 20 + amount * otherOdd * (n - 6) * combineCount - amount * count;
                        }
                    } else if ("自选不中".equals(groupName)) {
                        return amount * count * odd_F - amount * count;

                    } else if("合肖".equals(groupName)) {
                        return amount * count * odd_F - amount * count;
                    }

                    break;
            }
            return amount * odd_F * combineCount - amount * count;
        }
        return amount * count * odd_F - amount * count;
    }

    // 六合彩可嬴金额连肖连尾
    private static int getLHC_LX(int n, String groupName) {
        int combineCount = 0;
        if (n > 7) {
            n = 7;
        }
        int m = 0;
        if ("二连肖".equals(groupName) || "二连尾".equals(groupName)) {
            m = 2;
        } else if ("三连肖".equals(groupName) || "三连尾".equals(groupName)) {
            m = 3;
        } else if ("四连肖".equals(groupName) || "四连尾".equals(groupName)) {
            m = 4;
        } else if ("五连肖".equals(groupName) || "五连尾".equals(groupName)) {
            m = 5;
        }
        combineCount = AlgorithmUtil.combination(n, m).intValue();
        return combineCount;
    }

    // 包含本命
    private static double getLHC_LX_BM(int n, long count, String groupName, long amount, double odd_F) {
        double keying = 0;
        int combineCount = 0;
        int combineCount_bm = 0;
        String startCode = "lianxiaolianwei" , subCode = "", playRemoteCodeUp = "", playRemoteCode ="";;
        if (n > 7) {
            n = 7;
        }
        int m = 0;
        if ("二连肖".equals(groupName) || "二连尾".equals(groupName)) {
            m = 2;
            if ("二连肖".equals(groupName)) {
                subCode = "erlianxiao";
                playRemoteCodeUp = "zhengtemaerlianxiaohb";
                playRemoteCode = "renxuanerxiao";
            } else {
                subCode = "erlianwei";
                playRemoteCodeUp = "zhengtemaerlianweihl";
                playRemoteCode = "renxuanerwei";
            }

        } else if ("三连肖".equals(groupName) || "三连尾".equals(groupName)) {
            m = 3;
            if ("三连肖".equals(groupName)) {
                subCode = "sanlianxiao";
                playRemoteCodeUp = "zhengtemasanlianxiaohb";
                playRemoteCode = "rexuansanxiao";
            } else {
                subCode = "sanlianwei";
                playRemoteCodeUp = "zhengtemasanlianweihl";
                playRemoteCode = "renxuansanwei";
            }
        } else if ("四连肖".equals(groupName) || "四连尾".equals(groupName)) {
            m = 4;
            if ("四连肖".equals(groupName)) {
                subCode = "silianxiao";
                playRemoteCodeUp = "zhengtemasilianxiaohb";
                playRemoteCode = "renxuansixiao";
            } else {
                subCode = "silianwei";
                playRemoteCodeUp = "zhengtemasilianweihl";
                playRemoteCode = "renxuansiwei";
            }
        } else if ("五连肖".equals(groupName) || "五连尾".equals(groupName)) {
            m = 5;
            if ("五连肖".equals(groupName)) {
                subCode = "wulianxiao";
                playRemoteCodeUp = "zhengtemawulianxiaohb";
                playRemoteCode = "renxuanwuxiao";
            } else {
                subCode = "wulianwei";
                playRemoteCodeUp = "zhengtemawulianweihl";
                playRemoteCode = "renxuanwuwei";
            }
        }

        double otherOdd = getWinOddParse(getWinOdd_BM(startCode, subCode, playRemoteCodeUp, playRemoteCode));
        combineCount_bm = AlgorithmUtil.combination(n - 1, m - 1).intValue();
        combineCount = AlgorithmUtil.combination(n - 1, m).intValue();
        keying = amount * otherOdd * combineCount_bm + amount * odd_F * combineCount - amount * count;

        return keying;
    }

    private static boolean isHasBenMing(String betNum) {
        List<String> list = new ArrayList<>();
        if (!TextUtils.isEmpty(betNum)) {
            if (betNum.contains(" ")) {
                String[] arr = betNum.split(" ");
                for (String s : arr) {
                    list.add(s);
                }
            } else if (betNum.contains(",")) {
                String[] arr = betNum.split(",");
                for (String s : arr) {
                    list.add(s);
                }
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        try {
            String tYear = sdf.format(new Date());
            int tYearInt = Integer.parseInt(tYear);
            // 今年生肖
            String bm_SX = LotteryUtil.getYear_SX(tYearInt);
            if (list.contains(bm_SX) || list.contains("0尾")) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }


    private static String getWinOdd(String startCode, String subCode) {
        String odd = "0";
        int lotteryId = DataCenter.getInstance().getCurLotteryId();
        int mode = LotteryConstant.LOTTERY_PLAY_MODE_DOUBLE;
        List<LotteryPlayStart> lotteryPlayStarts = DataCenter.getInstance().getRemotePlay(lotteryId, mode);
        for (int i = 0; i < lotteryPlayStarts.size(); i++) {
            LotteryPlayStart lotteryPlayStart = lotteryPlayStarts.get(i);
            List<LotteryPlaySub> lotteryPlaySubs = lotteryPlayStart.getSubPlays();
            if (!startCode.equals(lotteryPlayStart.getRemoteCode())) {
                continue;
            }
            for (int j = 0; j < lotteryPlaySubs.size(); j++) {
                LotteryPlaySub lotteryPlaySub = lotteryPlaySubs.get(j);
                if (subCode.equals(lotteryPlaySub.getRemoteCode())) {
                    odd = lotteryPlaySub.getOdds();
                }
            }
        }
        return odd;
    }

    private static double getWinOddParse(String odd) {
        double odd_F = 0;
        if (TextUtils.isEmpty(odd)) {
            odd = "0";
        }
        try {
            odd_F = Double.parseDouble(odd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return odd_F;
    }

    private static double getOtherOdd(String startCode, String subCode) {
        String odd = getWinOdd(startCode, subCode);
        double odd_F = 0;
        double odd_F_1 = 0;
        double odd_F_2 = 0;
        if (!TextUtils.isEmpty(odd) && odd.contains(",")) {
            String[] oddArr = odd.split(",");
            if (2 == oddArr.length) {
                odd_F_1 = getWinOddParse(oddArr[0]);
                odd_F_2 = getWinOddParse(oddArr[1]);
            }
        }
        if ("lianma".equals(startCode)) {
            if ("sanzhonger".equals(subCode)) {
                odd_F = Math.min(odd_F_1, odd_F_2);
            } else if ("erzhongte".equals(subCode)) {
                odd_F = Math.min(odd_F_1, odd_F_2);
            }
        }

        return odd_F;
    }

    // 本命赔率
    private static String getWinOdd_BM(String startCode, String subCode, String playRemoteCodeUp, String playRemoteCode) {
        String odd = "0";
        int lotteryId = DataCenter.getInstance().getCurLotteryId();
        int mode = LotteryConstant.LOTTERY_PLAY_MODE_DOUBLE;
        List<LotteryPlayStart> lotteryPlayStarts = DataCenter.getInstance().getRemotePlay(lotteryId, mode);
        for (int i = 0; i < lotteryPlayStarts.size(); i++) {
            LotteryPlayStart lotteryPlayStart = lotteryPlayStarts.get(i);
            List<LotteryPlaySub> lotteryPlaySubs = lotteryPlayStart.getSubPlays();
            if (!startCode.equals(lotteryPlayStart.getRemoteCode())) {
                continue;
            }
            for (int j = 0; j < lotteryPlaySubs.size(); j++) {
                LotteryPlaySub lotteryPlaySub = lotteryPlaySubs.get(j);
                List<LotteryPlayEnd> ends = lotteryPlaySub.getLotteryPlayEnds();
                if (!subCode.equals(lotteryPlaySub.getRemoteCode())) {
                    continue;
                }
                for (int k = 0; k < ends.size(); k++) {
                    LotteryPlayEnd lotteryPlayEnd = ends.get(k);
                    List<LotteryPlay> lotteryPlays = lotteryPlayEnd.getLotteryPlays();
                    for (int m = 0; m < lotteryPlays.size(); m++) {
                        LotteryPlay lotteryPlay = lotteryPlays.get(m);
                        if (playRemoteCodeUp.equals(lotteryPlay.getRemoteCodeUp()) && playRemoteCode.equals(lotteryPlay.getRemoteCode())) {
                            odd = lotteryPlay.getOdds();
                        }

                    }
                }
            }
        }
        return odd;
    }

}
