package com.ski.box.bean;

import java.util.List;

/**
 * 读取用户设置
 */
public class SelfProfileEntity {
    /**
     * memberId : 1209718497005797463
     * selectConfigId : 29
     * nextConfigId : 0
     * delayed : true
     * delayedTime : 2020-01-22 14:32:17
     * longDragonLimit : 4
     * longDragonNumPrompt : true
     * countDownBeep : true
     * doubleBetConfirm : true
     * missRemind : true
     * quickConfirm : true
     * winBeep : true
     * winModal : true
     * chips :
     * virtualKeyboard : true
     * doubleLimitStatus : true
     * doubleBetRangeList : [{"configId":5,"doublePlayBetMin":1,"doublePlayBetMax":1002,"nextSelect":false,"current":false},{"configId":6,"doublePlayBetMin":50,"doublePlayBetMax":2000,"nextSelect":false,"current":false},{"configId":29,"doublePlayBetMin":100,"doublePlayBetMax":1000000,"nextSelect":false,"current":true}]
     * omits : [{"seriesId":1,"ticketIds":"1,3,8,9,45,55,56,57","playCodes":"zusanfushi,zuxuanhezhi,zuliufushi,dingweidan,qiansanyima,zhongsanyima,housanyima,qiansiyima,housiyima,wuxingyima,wuxinghezhi,longhuhe"},{"seriesId":2,"ticketIds":"4,5,11,14,15,46,58","playCodes":"qiansanbudingdan,dingweidan"},{"seriesId":3,"ticketIds":"27,59","playCodes":"tema,touweishu,texiao,texiaowuxing,temaxingtai,tehexingtai,teweixingtai,texiaoxingtai,quanbo"},{"seriesId":4,"ticketIds":"6,16,17","playCodes":"sanxingzuxuansan,sanxingzuxuanliu,baiweidingweidan,shiweidingweidan,geweidingweidan,sanxingyimabudingdan,longhuhe"},{"seriesId":5,"ticketIds":"26,47","playCodes":"shangxiapan,jioupan,hezhidaxiaodanshuang"},{"seriesId":6,"ticketIds":"18,48,54,67,68","playCodes":"qianwudingweidan,houwudingweidan,guanyahezhi"},{"seriesId":7,"ticketIds":"20,41,42,43,44,50,89","playCodes":"hezhidaxiaodanshuang,santonghaodanxuan,sanlianhaotongxuan,erbutonghaofuxuan,caiyigehao"},{"seriesId":8,"ticketIds":"40","playCodes":"zusanfushi,zuxuanhezhi,zuliufushi,dingweidan,qiansanyima,zhongsanyima,housanyima,qiansiyima,housiyima,wuxingyima,wuxinghezhi,longhuhe,zhixuanfushi"}]
     * longDragonTickets : [{"seriesId":1,"ticketIds":"1,3,8,9,45,55,56,57"},{"seriesId":2,"ticketIds":"4,5,11,14,15,46,58"},{"seriesId":3,"ticketIds":"27,59"},{"seriesId":4,"ticketIds":"6,16,17"},{"seriesId":5,"ticketIds":"26,47"},{"seriesId":6,"ticketIds":"18,48,54,67,68"},{"seriesId":7,"ticketIds":"20,41,42,43,44,50,89"},{"seriesId":8,"ticketIds":"40"}]
     */

    private long memberId;
    //选定的区间id
    private int selectConfigId;
    private int nextConfigId;
    //是否有锁定
    private boolean delayed = false;
    //如果锁定，锁定时间
    private String delayedTime;
    //长龙提醒的连出限制
    private int longDragonLimit; // 上限
    private int longDragonLimitMax; // 下限
    //长龙数字提醒是否开启
    private boolean longDragonNumPrompt;
    //倒计时提示音
    private boolean countDownBeep;
    //双面盘投注确认框
    private boolean doubleBetConfirm;
    //遗漏提醒
    private boolean missRemind;
    //快速投注确认框
    private boolean quickConfirm;
    //中奖提示音
    private boolean winBeep;
    //中奖弹框提示
    private boolean winModal;
    //骰宝筹码区间
    private String chips;
    //虚拟数字键盘开关  默认是true
    private boolean virtualKeyboard;
    //商户是否开启双面盘限红，如果不开启则投注不校验限红，玩家配置信息不显示限红配置
    private boolean doubleLimitStatus;
    //投注区间列表
    private List<DoubleBetRangeListBean> doubleBetRangeList;
    //遗漏选定列表
    private List<OmitsBean> omits;
    //长龙提醒的彩种列表
    private List<LongDragonTicketsBean> longDragonTickets;

    public int getLongDragonLimitMax() {
        return longDragonLimitMax;
    }

    public void setLongDragonLimitMax(int longDragonLimitMax) {
        this.longDragonLimitMax = longDragonLimitMax;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public int getSelectConfigId() {
        return selectConfigId;
    }

    public void setSelectConfigId(int selectConfigId) {
        this.selectConfigId = selectConfigId;
    }

    public int getNextConfigId() {
        return nextConfigId;
    }

    public void setNextConfigId(int nextConfigId) {
        this.nextConfigId = nextConfigId;
    }

    public boolean isDelayed() {
        return delayed;
    }

    public void setDelayed(boolean delayed) {
        this.delayed = delayed;
    }

    public String getDelayedTime() {
        return delayedTime;
    }

    public void setDelayedTime(String delayedTime) {
        this.delayedTime = delayedTime;
    }

    public int getLongDragonLimit() {
        return longDragonLimit;
    }

    public void setLongDragonLimit(int longDragonLimit) {
        this.longDragonLimit = longDragonLimit;
    }

    public boolean isLongDragonNumPrompt() {
        return longDragonNumPrompt;
    }

    public void setLongDragonNumPrompt(boolean longDragonNumPrompt) {
        this.longDragonNumPrompt = longDragonNumPrompt;
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

    public boolean isDoubleLimitStatus() {
        return doubleLimitStatus;
    }

    public void setDoubleLimitStatus(boolean doubleLimitStatus) {
        this.doubleLimitStatus = doubleLimitStatus;
    }

    public List<DoubleBetRangeListBean> getDoubleBetRangeList() {
        return doubleBetRangeList;
    }

    public void setDoubleBetRangeList(List<DoubleBetRangeListBean> doubleBetRangeList) {
        this.doubleBetRangeList = doubleBetRangeList;
    }

    public List<OmitsBean> getOmits() {
        return omits;
    }

    public void setOmits(List<OmitsBean> omits) {
        this.omits = omits;
    }

    public List<LongDragonTicketsBean> getLongDragonTickets() {
        return longDragonTickets;
    }

    public void setLongDragonTickets(List<LongDragonTicketsBean> longDragonTickets) {
        this.longDragonTickets = longDragonTickets;
    }

    public static class DoubleBetRangeListBean {
        /**
         * configId : 5
         * doublePlayBetMin : 1
         * doublePlayBetMax : 1002
         * nextSelect : false
         * current : false
         */

        //投注区间id
        private int configId;
        //最小投注金额
        private int doublePlayBetMin;
        //最大投注金额
        private int doublePlayBetMax;
        //是否延迟选定
        private boolean nextSelect;
        //是否当前选定
        private boolean current;

        private boolean isLock;

        public boolean isLock() {
            return isLock;
        }

        public void setLock(boolean lock) {
            isLock = lock;
        }

        public int getConfigId() {
            return configId;
        }

        public void setConfigId(int configId) {
            this.configId = configId;
        }

        public int getDoublePlayBetMin() {
            return doublePlayBetMin;
        }

        public void setDoublePlayBetMin(int doublePlayBetMin) {
            this.doublePlayBetMin = doublePlayBetMin;
        }

        public int getDoublePlayBetMax() {
            return doublePlayBetMax;
        }

        public void setDoublePlayBetMax(int doublePlayBetMax) {
            this.doublePlayBetMax = doublePlayBetMax;
        }

        public boolean isNextSelect() {
            return nextSelect;
        }

        public void setNextSelect(boolean nextSelect) {
            this.nextSelect = nextSelect;
        }

        public boolean isCurrent() {
            return current;
        }

        public void setCurrent(boolean current) {
            this.current = current;
        }
    }

    public static class OmitsBean {
        /**
         * seriesId : 1
         * ticketIds : 1,3,8,9,45,55,56,57
         * playCodes : zusanfushi,zuxuanhezhi,zuliufushi,dingweidan,qiansanyima,zhongsanyima,housanyima,qiansiyima,housiyima,wuxingyima,wuxinghezhi,longhuhe
         */

        //彩系id
        private int seriesId;
        //遗漏彩种,逗号分隔
        private String ticketIds;
        //遗漏玩法code,逗号分隔
        private String playCodes;

        public int getSeriesId() {
            return seriesId;
        }

        public void setSeriesId(int seriesId) {
            this.seriesId = seriesId;
        }

        public String getTicketIds() {
            return ticketIds;
        }

        public void setTicketIds(String ticketIds) {
            this.ticketIds = ticketIds;
        }

        public String getPlayCodes() {
            return playCodes;
        }

        public void setPlayCodes(String playCodes) {
            this.playCodes = playCodes;
        }
    }

    public static class LongDragonTicketsBean {
        /**
         * seriesId : 1
         * ticketIds : 1,3,8,9,45,55,56,57
         */

        //彩系id
        private int seriesId;
        //长龙彩种,逗号分隔
        private String ticketIds;

        public int getSeriesId() {
            return seriesId;
        }

        public void setSeriesId(int seriesId) {
            this.seriesId = seriesId;
        }

        public String getTicketIds() {
            return ticketIds;
        }

        public void setTicketIds(String ticketIds) {
            this.ticketIds = ticketIds;
        }
    }

}
