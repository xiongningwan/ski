package com.ski.box.bean;

public class PersonProfileEntity {
    /**
     * 选择的区间id
     */
    private String nextConfigId;
    /**
     * 倒计时提示音
     */
    private boolean countDownBeep;
    /**
     * 双面盘投注确认框
     */
    private boolean doubleBetConfirm;
    /**
     * 遗漏提醒
     */
    private boolean missRemind;
    /**
     * 快速投注确认框
     */
    private boolean quickConfirm;
    /**
     * 长龙提醒的彩种列表
     */
    private String longDragonTicketList;
    /**
     * 中奖提示音
     */
    private boolean winBeep;
    /**
     * 中奖弹框提示
     */
    private boolean winModal;
    /**
     * 长龙数字提醒提示
     */
    private boolean longDragonNumPrompt;
    /**
     * 长龙提醒的连出限制
     */
    private String longDragonLimit;
    /**
     * 筹码
     */
    private String chips;
    /**
     * 虚拟数字键盘开关
     */
    private boolean virtualKeyboard;

    // 冷热期数
    private int coldHotPeriod;
    // 冷热点击提示
    private boolean coldHotClickTip;

    public boolean isColdHotClickTip() {
        return coldHotClickTip;
    }

    public void setColdHotClickTip(boolean coldHotClickTip) {
        this.coldHotClickTip = coldHotClickTip;
    }

    public int getColdHotPeriod() {
        return coldHotPeriod;
    }

    public void setColdHotPeriod(int coldHotPeriod) {
        this.coldHotPeriod = coldHotPeriod;
    }

    public String getNextConfigId() {
        return nextConfigId;
    }

    public void setNextConfigId(String nextConfigId) {
        this.nextConfigId = nextConfigId;
    }

    public boolean isCountDownBeep() {
        return countDownBeep;
    }

    public void setCountDownBeep(boolean countDownBeep) {
        this.countDownBeep = countDownBeep;
    }

    public boolean isDoubleBetConfirm() {
        return doubleBetConfirm;
    }

    public void setDoubleBetConfirm(boolean doubleBetConfirm) {
        this.doubleBetConfirm = doubleBetConfirm;
    }

    public boolean isMissRemind() {
        return missRemind;
    }

    public void setMissRemind(boolean missRemind) {
        this.missRemind = missRemind;
    }

    public boolean isQuickConfirm() {
        return quickConfirm;
    }

    public void setQuickConfirm(boolean quickConfirm) {
        this.quickConfirm = quickConfirm;
    }

    public String getLongDragonTicketList() {
        return longDragonTicketList;
    }

    public void setLongDragonTicketList(String longDragonTicketList) {
        this.longDragonTicketList = longDragonTicketList;
    }

    public boolean isWinBeep() {
        return winBeep;
    }

    public void setWinBeep(boolean winBeep) {
        this.winBeep = winBeep;
    }

    public boolean isWinModal() {
        return winModal;
    }

    public void setWinModal(boolean winModal) {
        this.winModal = winModal;
    }

    public boolean isLongDragonNumPrompt() {
        return longDragonNumPrompt;
    }

    public void setLongDragonNumPrompt(boolean longDragonNumPrompt) {
        this.longDragonNumPrompt = longDragonNumPrompt;
    }

    public String getLongDragonLimit() {
        return longDragonLimit;
    }

    public void setLongDragonLimit(String longDragonLimit) {
        this.longDragonLimit = longDragonLimit;
    }

    public String getChips() {
        return chips;
    }

    public void setChips(String chips) {
        this.chips = chips;
    }

    public boolean isVirtualKeyboard() {
        return virtualKeyboard;
    }

    public void setVirtualKeyboard(boolean virtualKeyboard) {
        this.virtualKeyboard = virtualKeyboard;
    }
}
