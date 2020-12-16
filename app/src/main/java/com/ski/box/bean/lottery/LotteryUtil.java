package com.ski.box.bean.lottery;

import com.ski.box.R;
import com.ski.box.utils.lottery.ConfigurationUiUtils;
import com.yb.core.utils.TimeUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_11X5_FJ;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_11X5_GD;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_11X5_GX;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_11X5_JS;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_11X5_JX;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_11X5_SD;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_11X5_XY;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_3D_FC;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_3D_JS;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_3D_PL35;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_3D_XY;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_F1_CCL_BM;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_F1_CCL_F1;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_F1_CCL_F3;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_F1_CCL_GP2;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_F1_CCL_KBS;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_F1_CCL_KDC;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_F1_JJS_BM;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_F1_JJS_F1;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_F1_JJS_F3;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_F1_JJS_GP2;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_F1_JJS_KBS;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_F1_JJS_KDC;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_F1_SW_BM;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_F1_SW_F1;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_F1_SW_F3;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_F1_SW_GP2;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_F1_SW_KBS;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_F1_SW_KDC;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_K3_AH;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_K3_FJ;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_K3_GX;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_K3_JINAGSU;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_K3_JISU;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_K3_JL;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_K3_XY;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_KL8_BJ;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_KL8_JS;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_LHC_5F;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_LHC_JS;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_LHC_XG;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_LOW_FC3D;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_LOW_XGLHC;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_PK10_AZ;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_PK10_BJ;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_PK10_HLFT;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_PK10_JSFT;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_PK10_JSSC;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_PK10_METFT;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_PK10_XYFT;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_PL35_PL35;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_SSC_AZXY5;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_SSC_CQ;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_SSC_HLJ;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_SSC_HN5FC;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_SSC_JS;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_SSC_TJ;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_SSC_TX;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_SSC_XJ;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_SSC_XY5FC;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_ID_SSC_XYFFC;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_11X5;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_3D;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_F1_CCL;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_F1_JJS;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_F1_SW;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_K3;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_KL8;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_LHC;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_PK10;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_PL35;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_SSC;

public class LotteryUtil {
    private static Map<Integer, Integer> mLotteryIconMap = new HashMap<>();
    public static final List<String[]> shengXiaos = new ArrayList<>();
    public static final int nowIndex;
    public static final String[] shengXiao1 = new String[]{"鸡", "猴", "羊", "马", "蛇", "龙", "兔", "虎", "牛", "鼠", "猪", "狗"};
    public static final String[] shengXiao2 = new String[]{"狗", "鸡", "猴", "羊", "马", "蛇", "龙", "兔", "虎", "牛", "鼠", "猪"};
    public static final String[] shengXiao3 = new String[]{"猪", "狗", "鸡", "猴", "羊", "马", "蛇", "龙", "兔", "虎", "牛", "鼠"};
    public static final String[] shengXiao4 = new String[]{"鼠", "猪", "狗", "鸡", "猴", "羊", "马", "蛇", "龙", "兔", "虎", "牛"};
    public static final String[] shengXiao5 = new String[]{"牛", "鼠", "猪", "狗", "鸡", "猴", "羊", "马", "蛇", "龙", "兔", "虎"};
    public static final String[] shengXiao6 = new String[]{"虎", "牛", "鼠", "猪", "狗", "鸡", "猴", "羊", "马", "蛇", "龙", "兔"};
    public static final String[] shengXiao7 = new String[]{"兔", "虎", "牛", "鼠", "猪", "狗", "鸡", "猴", "羊", "马", "蛇", "龙"};
    public static final String[] shengXiao8 = new String[]{"龙", "兔", "虎", "牛", "鼠", "猪", "狗", "鸡", "猴", "羊", "马", "蛇"};
    public static final String[] shengXiao9 = new String[]{"蛇", "龙", "兔", "虎", "牛", "鼠", "猪", "狗", "鸡", "猴", "羊", "马"};
    public static final String[] shengXiao10 = new String[]{"马", "蛇", "龙", "兔", "虎", "牛", "鼠", "猪", "狗", "鸡", "猴", "羊"};
    public static final String[] shengXiao11 = new String[]{"羊", "马", "蛇", "龙", "兔", "虎", "牛", "鼠", "猪", "狗", "鸡", "猴"};
    public static final String[] shengXiao12 = new String[]{"猴", "羊", "马", "蛇", "龙", "兔", "虎", "牛", "鼠", "猪", "狗", "鸡"};

    static {
        shengXiaos.add(shengXiao1);
        shengXiaos.add(shengXiao2);
        shengXiaos.add(shengXiao3);
        shengXiaos.add(shengXiao4);
        shengXiaos.add(shengXiao5);
        shengXiaos.add(shengXiao6);
        shengXiaos.add(shengXiao7);
        shengXiaos.add(shengXiao8);
        shengXiaos.add(shengXiao9);
        shengXiaos.add(shengXiao10);
        shengXiaos.add(shengXiao11);
        shengXiaos.add(shengXiao12);
        nowIndex = TimeUtils.getNowIndex();
    }


    public static boolean isLowTicket(int ticketId) {
        ;
        switch (ticketId) {
            case LOTTERY_ID_LOW_XGLHC:
            case LOTTERY_ID_LOW_FC3D:
            case LOTTERY_ID_3D_PL35:
                return true;
        }
        return false;
    }


    /**
     * 获取根据彩种id彩系id
     *
     * @param lotteryId
     * @return
     */
    public static int getSerIdByLotteryId(int lotteryId) {
        int serId = 0;
        switch (lotteryId) {
            case LOTTERY_ID_PK10_JSSC:
            case LOTTERY_ID_PK10_XYFT:
            case LOTTERY_ID_PK10_BJ:
            case LOTTERY_ID_PK10_AZ:
            case LOTTERY_ID_PK10_JSFT:
            case LOTTERY_ID_PK10_HLFT:
            case LOTTERY_ID_PK10_METFT:
                serId = SER_ID_PK10;
                break;
            case LOTTERY_ID_SSC_CQ:
            case LOTTERY_ID_SSC_XJ:
            case LOTTERY_ID_SSC_TJ:
            case LOTTERY_ID_SSC_HLJ:
            case LOTTERY_ID_SSC_AZXY5:
            case LOTTERY_ID_SSC_JS:
            case LOTTERY_ID_SSC_XYFFC:
            case LOTTERY_ID_SSC_XY5FC:
            case LOTTERY_ID_SSC_HN5FC:
            case LOTTERY_ID_SSC_TX:
                serId = SER_ID_SSC;
                break;
            case LOTTERY_ID_LHC_XG:
            case LOTTERY_ID_LHC_JS:
            case LOTTERY_ID_LHC_5F:
                serId = SER_ID_LHC;
                break;
            case LOTTERY_ID_11X5_GD:
            case LOTTERY_ID_11X5_SD:
            case LOTTERY_ID_11X5_JX:
            case LOTTERY_ID_11X5_FJ:
            case LOTTERY_ID_11X5_GX:
            case LOTTERY_ID_11X5_JS:
            case LOTTERY_ID_11X5_XY:
                serId = SER_ID_11X5;
                break;
            case LOTTERY_ID_K3_JL:
            case LOTTERY_ID_K3_XY:
            case LOTTERY_ID_K3_JINAGSU:
            case LOTTERY_ID_K3_AH:
            case LOTTERY_ID_K3_GX:
            case LOTTERY_ID_K3_FJ:
            case LOTTERY_ID_K3_JISU:
                serId = SER_ID_K3;
                break;
            case LOTTERY_ID_3D_FC:
            case LOTTERY_ID_3D_XY:
            case LOTTERY_ID_3D_JS:
                serId = SER_ID_3D;
                break;
            case LOTTERY_ID_PL35_PL35:
                serId = SER_ID_PL35;
                break;
            case LOTTERY_ID_KL8_BJ:
            case LOTTERY_ID_KL8_JS:
                serId = SER_ID_KL8;
                break;
            case LOTTERY_ID_F1_JJS_KDC:
            case LOTTERY_ID_F1_JJS_KBS:
            case LOTTERY_ID_F1_JJS_BM:
            case LOTTERY_ID_F1_JJS_F1:
            case LOTTERY_ID_F1_JJS_F3:
            case LOTTERY_ID_F1_JJS_GP2:
                serId = SER_ID_F1_JJS;
                break;
            case LOTTERY_ID_F1_CCL_KDC:
            case LOTTERY_ID_F1_CCL_KBS:
            case LOTTERY_ID_F1_CCL_BM:
            case LOTTERY_ID_F1_CCL_F1:
            case LOTTERY_ID_F1_CCL_F3:
            case LOTTERY_ID_F1_CCL_GP2:
                serId = SER_ID_F1_CCL;
                break;
            case LOTTERY_ID_F1_SW_KDC:
            case LOTTERY_ID_F1_SW_KBS:
            case LOTTERY_ID_F1_SW_BM:
            case LOTTERY_ID_F1_SW_F1:
            case LOTTERY_ID_F1_SW_F3:
            case LOTTERY_ID_F1_SW_GP2:
                serId = SER_ID_F1_SW;
                break;
        }

        return serId;
    }

    /**
     * 根据不同的数字计算他的生肖
     *
     * @param code
     * @return
     */
    public static String getLHCSX(int code) {
        if (code >= 12) {
            code = code % 12;
        }
        return  " " + shengXiaos.get(nowIndex)[code] + " ";
    }

    public static String getCurrentLHCSX(int code, int year) {
        if (code >= 12) {
            code = code % 12;
        }
        int currentIndex = getCurrentIndex(year);
        return shengXiaos.get(currentIndex)[code];
    }

    public static int getCurrentIndex(int year) {
        return year % 12;
    }


    /**
     * 根据传入的年获取生肖
     *
     * @param year
     * @return
     */
    public static String getYear_SX(Integer year) {
        String[] years = new String[]{
                "鼠", "牛", "虎", "兔",
                "龙", "蛇", "马", "羊",
                "猴", "鸡", "狗", "猪"
        };
        if (year < 1900) {
            return "未知";
        }

        Integer start = 1900;
        return years[(year - start) % years.length];
    }

    /*判断字符是否是数字*/
    public static boolean isNumeric(String str) {
        // 该正则表达式可以匹配所有的数字 包括负数
        Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toPlainString();
        } catch (Exception e) {
            return false;
        }

        Matcher isNum = pattern.matcher(bigStr);
        return isNum.matches();
    }



    /**
     * 六合彩双面盘方块背景
     *
     * @param code
     * @return
     */
    public static Integer getLHCSquareBackgroudResource(int code) {
        if (ConfigurationUiUtils.red.contains(code)) {
            return R.drawable.ski_bg_lhc_num_red_selector;
        } else if (ConfigurationUiUtils.blue.contains(code)) {
            return R.drawable.ski_bg_lhc_num_blue_selector;
        } else if (ConfigurationUiUtils.green.contains(code)) {
            return R.drawable.ski_bg_lhc_num_green_selector;
        }
        return R.drawable.ski_bet_content_btn_item_selector;
    }

    /**
     * 六合彩球型未选中的背景
     *
     * @param code
     * @return
     */
    public static Integer getLHCBallBackgroundResource(int code) {
        if (ConfigurationUiUtils.red.contains(code)) {
            return R.drawable.ski_circle_lhc_num_red_selector;
        } else if (ConfigurationUiUtils.green.contains(code)) {
            return R.drawable.ski_circle_lhc_num_green_selector;
        } else if (ConfigurationUiUtils.blue.contains(code)) {
            return R.drawable.ski_circle_lhc_num_blue_selector;
        }
        return R.drawable.ski_circle_lhc_num_red_selector;
    }

    private static Map<String, String> doubleSideMutelIteMap = new HashMap<>();
    public static ArrayList analyzingCode = new ArrayList() {
    };

    /**
     * 双面盘互斥选项映射
     */
    public static Map<String, String> getDoubleSideMutelIteMap() {
        return doubleSideMutelIteMap;
    }

    /**
     * 互斥选项映射初始化
     */
    static {
        doubleSideMutelIteMap.put("大", "小");
        doubleSideMutelIteMap.put("单", "双");
        doubleSideMutelIteMap.put("质", "合");
        doubleSideMutelIteMap.put("前多", "后多");
        doubleSideMutelIteMap.put("合大", "合小");
        doubleSideMutelIteMap.put("合单", "合双");
        doubleSideMutelIteMap.put("天肖", "地肖");
        doubleSideMutelIteMap.put("前肖", "后肖");
        doubleSideMutelIteMap.put("家肖", "野肖");
        doubleSideMutelIteMap.put("尾大", "尾小");
        doubleSideMutelIteMap.put("单多", "双多");
        doubleSideMutelIteMap.put("龙", "虎");
        doubleSideMutelIteMap.put("总肖单", "总肖双");
        /**3D  两面  **/
        doubleSideMutelIteMap.put("和尾质", "和尾合");
        doubleSideMutelIteMap.put("和尾小", "和尾大");

        /*投注筛选不符合金额区间的玩法*/
        analyzingCode.add("大");
        analyzingCode.add("小");
        analyzingCode.add("单");
        analyzingCode.add("双");

        /*龙虎和*/
        analyzingCode.add("龙");
        analyzingCode.add("虎");
        analyzingCode.add("和");
        /*质合*/
        analyzingCode.add("质");
        analyzingCode.add("合");

        /* 11选5特殊部分 尾大 尾小*/
        analyzingCode.add("尾大");
        analyzingCode.add("尾小");

        /*快乐彩 特殊部分 前后多 单双多*/
        analyzingCode.add("前多");
        analyzingCode.add("后多");
        analyzingCode.add("单多");
        analyzingCode.add("双多");

        /*六合彩 特殊部分*/
        analyzingCode.add("合大");
        analyzingCode.add("合小");
        analyzingCode.add("合单");
        analyzingCode.add("合双");
        analyzingCode.add("天肖");
        analyzingCode.add("地肖");
        analyzingCode.add("前肖");
        analyzingCode.add("后肖");
        analyzingCode.add("家野");
        analyzingCode.add("野肖");
        analyzingCode.add("红波");
        analyzingCode.add("蓝波");
        analyzingCode.add("绿波");
    }

    static {
        // ssc
        mLotteryIconMap.put(1, R.mipmap.ski_icon_ssc_1);
        mLotteryIconMap.put(3, R.mipmap.ski_icon_ssc_3);
        mLotteryIconMap.put(8, R.mipmap.ski_icon_ssc_8);
        mLotteryIconMap.put(9, R.mipmap.ski_icon_ssc_9);
        mLotteryIconMap.put(45, R.mipmap.ski_icon_ssc_45);
        mLotteryIconMap.put(55, R.mipmap.ski_icon_ssc_55);
        mLotteryIconMap.put(56, R.mipmap.ski_icon_ssc_56);
        mLotteryIconMap.put(57, R.mipmap.ski_icon_ssc_57);
        // 11x5
        mLotteryIconMap.put(4, R.mipmap.ski_icon_11x5_4);
        mLotteryIconMap.put(5, R.mipmap.ski_icon_11x5_5);
        mLotteryIconMap.put(11, R.mipmap.ski_icon_11x5_11);
        mLotteryIconMap.put(46, R.mipmap.ski_icon_11x5_46);
        mLotteryIconMap.put(58, R.mipmap.ski_icon_11x5_58);
        // lhc
        mLotteryIconMap.put(27, R.mipmap.ski_icon_lhc_27);
        mLotteryIconMap.put(59, R.mipmap.ski_icon_lhc_59);
        // 3d
        mLotteryIconMap.put(6, R.mipmap.ski_icon_3d_6);
        mLotteryIconMap.put(16, R.mipmap.ski_icon_3d_16);
        mLotteryIconMap.put(17, R.mipmap.ski_icon_3d_17);
        // kl8
        mLotteryIconMap.put(26, R.mipmap.ski_icon_kl8_26);
        mLotteryIconMap.put(47, R.mipmap.ski_icon_kl8_47);
        // pk10
        mLotteryIconMap.put(18, R.mipmap.ski_icon_pk10_18);
        mLotteryIconMap.put(48, R.mipmap.ski_icon_pk10_48);
        mLotteryIconMap.put(67, R.mipmap.ski_icon_pk10_67);
        mLotteryIconMap.put(68, R.mipmap.ski_icon_pk10_68);
        // k3
        mLotteryIconMap.put(20, R.mipmap.ski_icon_k3_20);
        mLotteryIconMap.put(41, R.mipmap.ski_icon_k3_41);
        mLotteryIconMap.put(42, R.mipmap.ski_icon_k3_42);
        mLotteryIconMap.put(43, R.mipmap.ski_icon_k3_43);
        mLotteryIconMap.put(50, R.mipmap.ski_icon_k3_50);
        mLotteryIconMap.put(89, R.mipmap.ski_icon_k3_89);
        // pl35
        mLotteryIconMap.put(40, R.mipmap.ski_icon_pl35_40);
    }

    public static int getLotteryIcon(int ticket) {
        Integer value = mLotteryIconMap.get(ticket);
        int res= 0;
        if(value == null) {
            res = R.mipmap.ski_home_icon_lottery_txffc;
        } else {
            res = value;
        }
        return res;
    }


}
