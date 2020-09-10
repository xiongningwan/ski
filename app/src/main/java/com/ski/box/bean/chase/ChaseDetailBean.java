package com.ski.box.bean.chase;

import java.io.Serializable;
import java.util.List;

/**
 * 追号记录详情
 */
public class ChaseDetailBean implements Serializable {

    /**
     * id : 1252867174578520072
     * ticketId : 59
     * ticketName : 六合5分彩
     * playId : 259050101
     * playName : 正码三中二
     * chaseBetNum : 01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18,19,20
     * chaseBetContent : 01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18,19,20
     * allPeriods : 10
     * donePeriods : 2
     * cancelPeriods : 0
     * allAmount : 11400
     * doneAmount : 2280
     * cancelAmount : 0
     * suspend : 1
     * betMode : 1
     * chaseStart : 20200422-190
     * chaseStatus : 1
     * chaseStatusDes : 追号中
     * groupMode : 2
     * winAmount : 2240
     * canCancel : true
     * chaseStartTime : 2020-04-22 15:49:46
     * chasePlanVoList : [{"id":"1252867174599491648","planNo":"20200422-190","planStatusCode":2,"planStatusDes":"已中奖","prizeStatusCode":0,"prizeStatusDes":"已中奖","betMultiple":1,"canCancel":false,"winAmount":1120,"betMoney":1140,"orderId":"1252867174633046104","betNums":0,"hasDetail":true,"win":true},{"id":"1252867174670794797","planNo":"20200422-191","planStatusCode":2,"planStatusDes":"已中奖","prizeStatusCode":0,"prizeStatusDes":"已中奖","betMultiple":1,"canCancel":false,"winAmount":1120,"betMoney":1140,"orderId":"1252867233944698908","betNums":0,"hasDetail":true,"win":true},{"id":"1252867174683377731","planNo":"20200422-192","planStatusCode":2,"planStatusDes":"待开奖","prizeStatusCode":0,"prizeStatusDes":"待开奖","betMultiple":1,"canCancel":true,"winAmount":0,"betMoney":1140,"orderId":"1252868471167909892","betNums":0,"hasDetail":true,"win":false},{"id":"1252867174687572021","planNo":"20200422-193","planStatusCode":1,"planStatusDes":"待投注","prizeStatusCode":0,"prizeStatusDes":"追号中","betMultiple":1,"canCancel":true,"winAmount":0,"betMoney":1140,"orderId":null,"betNums":0,"hasDetail":false,"win":false},{"id":"1252867174695960611","planNo":"20200422-194","planStatusCode":1,"planStatusDes":"待投注","prizeStatusCode":0,"prizeStatusDes":"追号中","betMultiple":1,"canCancel":true,"winAmount":0,"betMoney":1140,"orderId":null,"betNums":0,"hasDetail":false,"win":false},{"id":"1252867174704349225","planNo":"20200422-195","planStatusCode":1,"planStatusDes":"待投注","prizeStatusCode":0,"prizeStatusDes":"追号中","betMultiple":1,"canCancel":true,"winAmount":0,"betMoney":1140,"orderId":null,"betNums":0,"hasDetail":false,"win":false},{"id":"1252867174708543541","planNo":"20200422-196","planStatusCode":1,"planStatusDes":"待投注","prizeStatusCode":0,"prizeStatusDes":"追号中","betMultiple":1,"canCancel":true,"winAmount":0,"betMoney":1140,"orderId":null,"betNums":0,"hasDetail":false,"win":false},{"id":"1252867174716932152","planNo":"20200422-197","planStatusCode":1,"planStatusDes":"待投注","prizeStatusCode":0,"prizeStatusDes":"追号中","betMultiple":1,"canCancel":true,"winAmount":0,"betMoney":1140,"orderId":null,"betNums":0,"hasDetail":false,"win":false},{"id":"1252867174725320778","planNo":"20200422-198","planStatusCode":1,"planStatusDes":"待投注","prizeStatusCode":0,"prizeStatusDes":"追号中","betMultiple":1,"canCancel":true,"winAmount":0,"betMoney":1140,"orderId":null,"betNums":0,"hasDetail":false,"win":false},{"id":"1252867174729515080","planNo":"20200422-199","planStatusCode":1,"planStatusDes":"待投注","prizeStatusCode":0,"prizeStatusDes":"追号中","betMultiple":1,"canCancel":true,"winAmount":0,"betMoney":1140,"orderId":null,"betNums":0,"hasDetail":false,"win":false}]
     */

    private String id;
    private int ticketId;
    private String ticketName;
    private int playId;
    private String playName;
    private String chaseBetNum;
    private String chaseBetContent;
    private int allPeriods;
    private int donePeriods;
    private int cancelPeriods;
    private double allAmount;
    private double doneAmount;
    private double cancelAmount;
    private int suspend;
    private double betMode;
    private String chaseStart;
    private int chaseStatus;
    private String chaseStatusDes;
    private int groupMode;
    private double winAmount;
    private boolean canCancel;
    private String chaseStartTime;
    private List<ChasePlanVoListBean> chasePlanVoList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public int getPlayId() {
        return playId;
    }

    public void setPlayId(int playId) {
        this.playId = playId;
    }

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    public String getChaseBetNum() {
        return chaseBetNum;
    }

    public void setChaseBetNum(String chaseBetNum) {
        this.chaseBetNum = chaseBetNum;
    }

    public String getChaseBetContent() {

        return chaseBetContent;
    }

    public void setChaseBetContent(String chaseBetContent) {
        this.chaseBetContent = chaseBetContent;
    }

    public int getAllPeriods() {
        return allPeriods;
    }

    public void setAllPeriods(int allPeriods) {
        this.allPeriods = allPeriods;
    }

    public int getDonePeriods() {
        return donePeriods;
    }

    public void setDonePeriods(int donePeriods) {
        this.donePeriods = donePeriods;
    }

    public int getCancelPeriods() {
        return cancelPeriods;
    }

    public void setCancelPeriods(int cancelPeriods) {
        this.cancelPeriods = cancelPeriods;
    }

    public double getAllAmount() {
        return allAmount;
    }

    public void setAllAmount(double allAmount) {
        this.allAmount = allAmount;
    }

    public double getDoneAmount() {
        return doneAmount;
    }

    public void setDoneAmount(double doneAmount) {
        this.doneAmount = doneAmount;
    }

    public double getCancelAmount() {
        return cancelAmount;
    }

    public void setCancelAmount(double cancelAmount) {
        this.cancelAmount = cancelAmount;
    }

    public int getSuspend() {
        return suspend;
    }

    public void setSuspend(int suspend) {
        this.suspend = suspend;
    }

    public double getBetMode() {
        return betMode;
    }

    public void setBetMode(double betMode) {
        this.betMode = betMode;
    }

    public String getChaseStart() {
        return chaseStart;
    }

    public void setChaseStart(String chaseStart) {
        this.chaseStart = chaseStart;
    }

    public int getChaseStatus() {
        return chaseStatus;
    }

    public void setChaseStatus(int chaseStatus) {
        this.chaseStatus = chaseStatus;
    }

    public String getChaseStatusDes() {
        return chaseStatusDes;
    }

    public void setChaseStatusDes(String chaseStatusDes) {
        this.chaseStatusDes = chaseStatusDes;
    }

    public int getGroupMode() {
        return groupMode;
    }

    public void setGroupMode(int groupMode) {
        this.groupMode = groupMode;
    }

    public double getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(double winAmount) {
        this.winAmount = winAmount;
    }

    public boolean isCanCancel() {
        return canCancel;
    }

    public void setCanCancel(boolean canCancel) {
        this.canCancel = canCancel;
    }

    public String getChaseStartTime() {
        return chaseStartTime;
    }

    public void setChaseStartTime(String chaseStartTime) {
        this.chaseStartTime = chaseStartTime;
    }

    public List<ChasePlanVoListBean> getChasePlanVoList() {
        return chasePlanVoList;
    }

    public void setChasePlanVoList(List<ChasePlanVoListBean> chasePlanVoList) {
        this.chasePlanVoList = chasePlanVoList;
    }

    public static class ChasePlanVoListBean {
        /**
         * id : 1252867174599491648
         * planNo : 20200422-190
         * planStatusCode : 2
         * planStatusDes : 已中奖
         * prizeStatusCode : 0
         * prizeStatusDes : 已中奖
         * betMultiple : 1
         * canCancel : false
         * winAmount : 1120
         * betMoney : 1140
         * orderId : 1252867174633046104
         * betNums : 0
         * hasDetail : true
         * win : true
         */

        private String id;
        private String planNo;
        private int planStatusCode;
        private String planStatusDes;
        private int prizeStatusCode;
        private String prizeStatusDes;
        private int betMultiple;
        private boolean canCancel;
        private double winAmount;
        private double betMoney;
        private String orderId;
        private int betNums;
        private boolean hasDetail;
        private boolean win;
        private boolean check;

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPlanNo() {
            return planNo;
        }

        public void setPlanNo(String planNo) {
            this.planNo = planNo;
        }

        public int getPlanStatusCode() {
            return planStatusCode;
        }

        public void setPlanStatusCode(int planStatusCode) {
            this.planStatusCode = planStatusCode;
        }

        public String getPlanStatusDes() {
            return planStatusDes;
        }

        public void setPlanStatusDes(String planStatusDes) {
            this.planStatusDes = planStatusDes;
        }

        public int getPrizeStatusCode() {
            return prizeStatusCode;
        }

        public void setPrizeStatusCode(int prizeStatusCode) {
            this.prizeStatusCode = prizeStatusCode;
        }

        public String getPrizeStatusDes() {
            return prizeStatusDes;
        }

        public void setPrizeStatusDes(String prizeStatusDes) {
            this.prizeStatusDes = prizeStatusDes;
        }

        public int getBetMultiple() {
            return betMultiple;
        }

        public void setBetMultiple(int betMultiple) {
            this.betMultiple = betMultiple;
        }

        public boolean isCanCancel() {
            return canCancel;
        }

        public void setCanCancel(boolean canCancel) {
            this.canCancel = canCancel;
        }

        public double getWinAmount() {
            return winAmount;
        }

        public void setWinAmount(double winAmount) {
            this.winAmount = winAmount;
        }

        public double getBetMoney() {
            return betMoney;
        }

        public void setBetMoney(double betMoney) {
            this.betMoney = betMoney;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getBetNums() {
            return betNums;
        }

        public void setBetNums(int betNums) {
            this.betNums = betNums;
        }

        public boolean isHasDetail() {
            return hasDetail;
        }

        public void setHasDetail(boolean hasDetail) {
            this.hasDetail = hasDetail;
        }

        public boolean isWin() {
            return win;
        }

        public void setWin(boolean win) {
            this.win = win;
        }
    }
}
