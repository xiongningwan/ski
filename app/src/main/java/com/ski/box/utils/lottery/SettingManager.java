package com.ski.box.utils.lottery;


import com.yb.core.utils.AppUtil;
import com.yb.core.utils.SPUtils;

import static com.yb.core.utils.AppUtil.getContext;

public class SettingManager {


    /**
     * 投注倒计时提示音
     */
    public static boolean isBettingCountdownTone() {
        return SPUtils.getBoolean(getContext(), "SETTING_HINT", true);
    }

    /**
     * 设置投注倒计时提示音
     */
    public static void setBettingCountdownTone(boolean isSelected) {
        SPUtils.putBoolean(getContext(), "SETTING_HINT", isSelected);
    }

    /**
     * 中奖提示音
     */
    public static boolean isWinningTone() {
        return SPUtils.getBoolean(getContext(), "SETTING_WIN_TIPS", true);
    }

    /**
     * 设置中奖提示音
     */
    public static void setWinningTone(boolean isSelected) {
        SPUtils.putBoolean(getContext(), "SETTING_WIN_TIPS", isSelected);
    }

    /**
     * 【冷热】说明在点击时提示
     */
    public static boolean isColdHotDescripionPrompt() {
        return SPUtils.getBoolean(getContext(), "SETTING_COLD_HOT", true);
    }

    /**
     * 设置【冷热】说明在点击时提示
     */
    public static void setColdHotDescripionPrompt(boolean isSelected) {
        SPUtils.putBoolean(getContext(), "SETTING_COLD_HOT", isSelected);
    }

    /**
     * 【遗漏】说明在点击时提示
     */
    public static boolean isMissingDescripionPrompt() {
        return SPUtils.getBoolean(getContext(), "SETTING_OMIT", true);
    }

    /**
     * 设置【遗漏】说明在点击时提示
     */
    public static void setMissingDescripionPrompt(boolean isSelected) {
        SPUtils.putBoolean(getContext(), "SETTING_OMIT", isSelected);
    }

    /**
     * 冷热统计期数
     */
    public static String getColdHotStatisticsPeriods() {
        return SPUtils.getString(getContext(), "COLD_HOT_STATISTICS_PERIODS", "100");
    }

    /**
     * 设置冷热统计期数
     */
    public static void setColdHotStatisticsPeriods(String periods) {
        SPUtils.putString(getContext(), "COLD_HOT_STATISTICS_PERIODS", periods);
    }

    /**
     * 立即投注弹出框确认
     */
    public static boolean isFastBetConfirmDialog() {
        return SPUtils.getBoolean(getContext(), "IS_NEED_FAST_BET_CONFIRM_TIPS", true);
    }

    /**
     * 设置立即投注弹出框确认 单式用
     */
    public static void setFastBetConfirmDialog(boolean isNeed) {
        SPUtils.putBoolean(getContext(), "IS_NEED_FAST_BET_CONFIRM_TIPS", isNeed);
    }


    /**
     * 双面盘标注盘公用 投注弹出框确认
     */
    public static boolean isBetConfirmDialog() {

        return SPUtils.getBoolean(getContext(), "IS_NEED_DOUBLE_BET_CONFIRM_TIPS", true);
    }

    /**
     * 双面盘标注盘公用 投注弹出框确认
     */
    public static void setBetConfirmDialog(boolean isNeed) {
        SPUtils.putBoolean(getContext(), "IS_NEED_DOUBLE_BET_CONFIRM_TIPS", isNeed);
    }

    /**
     * 双面盘玩法投注区间
     */
    public static String getDoubleBetAmountRange() {
        return SPUtils.getString(getContext(), "DOUBLE_BET_AMOUNT_RANGE", "");
    }

    /**
     * 设置双面盘玩法投注区间
     */
    public static void setDoubleBetAmountRange(String range) {
        SPUtils.putString(getContext(), "DOUBLE_BET_AMOUNT_RANGE", range);
    }

    /**
     * 长龙数字提醒
     */
    public static boolean isLongDragonNumPrompt() {
        return SPUtils.getBoolean(AppUtil.getContext(), "IS_LONG_DRAGON_NUM_PROMPT", true);
    }

    /**
     * 设置长龙数字提醒
     */
    public static void setLongDragonNumPrompt(boolean isSelected) {
        SPUtils.putBoolean(getContext(), "IS_LONG_DRAGON_NUM_PROMPT", isSelected);
    }

    /**
     * 长龙最短期数
     */
    public static String getLongDragonNumPeriods() {
        return SPUtils.getString(getContext(), "LONG_DRAGON_NUM_PERIODS", "4");
    }

    /**
     * 设置长龙最短期数
     */
    public static void setLongDragonNumPeriods(String periods) {
        SPUtils.putString(getContext(), "LONG_DRAGON_NUM_PERIODS", periods);
    }

    /**
     * 长龙最大期数
     */
    public static String getLongDragonLimitMax() {
        return SPUtils.getString(getContext(), "LONG_DRAGON_LIMIT_MAX", "0");
    }

    /**
     * 设置长龙最大期数
     * @param periods
     */
    public static void setLongDragonLimitMax(String periods) {
        SPUtils.putString(getContext(), "LONG_DRAGON_LIMIT_MAX", periods);
    }

    /**
     * 长龙自定义保存的彩种id
     */
    public static String getLongDragonCustomLotteryList() {
        return SPUtils.getString(getContext(), "LONG_DRAGON_CUSTOM_LOTTERY_LIST");
    }

    /**
     * 长龙自定义保存的彩种id
     * @param data
     */
    public static void setLongDragonCustomLotteryList(String data) {
        SPUtils.putString(getContext(), "LONG_DRAGON__CUSTOM_LOTTERY_LIST", data);
    }

    /**
     * 长龙提醒，当前选中的按钮 0.当前彩种，1.当前彩系 ，2.自定义彩种
     */
    public static int getLongDragonCheckButtonId(int lotteryId) {
        return SPUtils.getInt(getContext(), "LONG_DRAGON_CHECK_BUTTOM_ID"+lotteryId,0);
    }

    /**
     * 长龙提醒，当前选中的按钮 0.当前彩种，1.当前彩系 ，2.自定义彩种
     * @param id
     */
    public static void setLongDragonCheckButtonId(int id,int lotteryId) {
        SPUtils.putInt(getContext(), "LONG_DRAGON_CHECK_BUTTOM_ID"+lotteryId, id);
    }


    /**
     * 设置长龙数字提醒的数量
     */
    public static void setLongDragonNum(int num) {
        SPUtils.putInt(getContext(), "LONG_DRAGON_REMIND_NUM", num);
    }

    /**
     * 获取长龙数字提醒的数量
     */
    public static int getLongDragonNum() {
        return SPUtils.getInt(getContext(), "LONG_DRAGON_REMIND_NUM", 0);
    }

    /**
     * 设置遗漏提醒彩种选择文字
     */
    public static void setMissingLotteryText(String text) {
        SPUtils.putString(getContext(), "MISSING_REMIND_LOTTERY_TEXT", text);
    }

    /**
     * 获取遗漏提醒彩种选择文字
     */
    public static String getMissingLotteryText() {
        return SPUtils.getString(getContext(), "MISSING_REMIND_LOTTERY_TEXT", "请编辑彩种");
    }

    /**
     * 开启更多玩法
     * @return
     */
    public static boolean getMorePlayNo(){
        return SPUtils.getBoolean(getContext(), "MORE_PLAY_NO", false);
    }

    public static void setMorePlayNo(boolean isOpen){
        SPUtils.putBoolean(getContext(), "MORE_PLAY_NO", isOpen);
    }

    /**
     * 获取玩法ID
     * @return
     */
    public static int getStandardPlayId() {
        return SPUtils.getInt(getContext(), "TICKET_PLAY_ID", 0);
    }

    public static void setStandardPlayId(int playId) {
        SPUtils.putInt(getContext(), "TICKET_PLAY_ID", playId);
    }

    public static int getStandardGroupPosition() {
        return SPUtils.getInt(getContext(), "TICKET_PLAY_GROUP_POS", 0);
    }

    public static void setStandardGroupPosition(int pos) {
        SPUtils.putInt(getContext(), "TICKET_PLAY_GROUP_POS", pos);
    }

    /**
     * 记录双面盘选择的Fragment
     * @return
     */
    public static int getRememberDoubleSideCheckFragmentId(int lotteryId) {
        return SPUtils.getInt(getContext(), "Remember_DoubleSide_Check_FragmentId"+lotteryId, 0);
    }

    /**
     * 记录双面盘选择的Fragment
     * @return
     */
    public static void setRememberDoubleSideCheckFragmentId(int pos,int lotteryId) {
        SPUtils.putInt(getContext(), "Remember_DoubleSide_Check_FragmentId"+lotteryId, pos);
    }
}
