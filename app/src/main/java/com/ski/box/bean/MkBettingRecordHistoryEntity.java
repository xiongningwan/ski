package com.ski.box.bean;

import java.util.List;

/**
 * 近期投注记录
 * Keno 订单列表
 */
public class MkBettingRecordHistoryEntity {

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * orderId : 1252979103686983712
             * betTime : 2020-04-22 23:14:32
             * playId : 267020101
             * playCode : null
             * playName : 冠军定位胆
             * betContent : 1
             * winAmount : 0.0
             * betMoney : 10.0
             * ticketId : 67
             * ticketName : 极速赛车
             * canCancel : true
             * seriesId : 6
             * seriesCode : null
             * odd : {"1":"9.850"}
             * betPrize : {"1":"98.5000"}
             * ticketResult :
             * solo : false
             * chaseOrder : false
             * groupMode : 2
             * singleGame : false
             * ticketPlanNo : 20200422-1395
             * betStatus : 1
             * betStatusDes : 待开奖
             * betMultiple : 1
             * betModel : 1.0
             * betNums : 1
             * chaseId : null
             * preOrderId : null
             * nextOrderId : null
             * win : false
             * displayZuHe : false
             * betNum : 1
             */

            private String orderId;
            private String betTime;
            private String playId;
            private Object playCode;
            private String playName;
            private String betContent;
            private double winAmount;
            private String betMoney;
            private int ticketId;
            private String ticketName;
            private boolean canCancel;
            private int seriesId;
            private Object seriesCode;
            private String odd;
            private String betPrize;
            private String ticketResult;
            private boolean solo;
            private boolean chaseOrder;
            private int groupMode;
            private boolean singleGame;
            private String ticketPlanNo;
            private int betStatus;
            private String betStatusDes;
            private int betMultiple;
            private double betModel;
            private int betNums;
            private Object chaseId;
            private Object preOrderId;
            private Object nextOrderId;
            private boolean win;
            private boolean displayZuHe;
            private String betNum;

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getBetTime() {
                return betTime;
            }

            public void setBetTime(String betTime) {
                this.betTime = betTime;
            }

            public String getPlayId() {
                return playId;
            }

            public void setPlayId(String playId) {
                this.playId = playId;
            }

            public Object getPlayCode() {
                return playCode;
            }

            public void setPlayCode(Object playCode) {
                this.playCode = playCode;
            }

            public String getPlayName() {
                return playName;
            }

            public void setPlayName(String playName) {
                this.playName = playName;
            }

            public String getBetContent() {
                return betContent;
            }

            public void setBetContent(String betContent) {
                this.betContent = betContent;
            }

            public double getWinAmount() {
                return winAmount;
            }

            public void setWinAmount(double winAmount) {
                this.winAmount = winAmount;
            }

            public String getBetMoney() {
                return betMoney;
            }

            public void setBetMoney(String betMoney) {
                this.betMoney = betMoney;
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

            public boolean isCanCancel() {
                return canCancel;
            }

            public void setCanCancel(boolean canCancel) {
                this.canCancel = canCancel;
            }

            public int getSeriesId() {
                return seriesId;
            }

            public void setSeriesId(int seriesId) {
                this.seriesId = seriesId;
            }

            public Object getSeriesCode() {
                return seriesCode;
            }

            public void setSeriesCode(Object seriesCode) {
                this.seriesCode = seriesCode;
            }

            public String getOdd() {
                return odd;
            }

            public void setOdd(String odd) {
                this.odd = odd;
            }

            public String getBetPrize() {
                return betPrize;
            }

            public void setBetPrize(String betPrize) {
                this.betPrize = betPrize;
            }

            public String getTicketResult() {
                return ticketResult;
            }

            public void setTicketResult(String ticketResult) {
                this.ticketResult = ticketResult;
            }

            public boolean isSolo() {
                return solo;
            }

            public void setSolo(boolean solo) {
                this.solo = solo;
            }

            public boolean isChaseOrder() {
                return chaseOrder;
            }

            public void setChaseOrder(boolean chaseOrder) {
                this.chaseOrder = chaseOrder;
            }

            public int getGroupMode() {
                return groupMode;
            }

            public void setGroupMode(int groupMode) {
                this.groupMode = groupMode;
            }

            public boolean isSingleGame() {
                return singleGame;
            }

            public void setSingleGame(boolean singleGame) {
                this.singleGame = singleGame;
            }

            public String getTicketPlanNo() {
                return ticketPlanNo;
            }

            public void setTicketPlanNo(String ticketPlanNo) {
                this.ticketPlanNo = ticketPlanNo;
            }

            public int getBetStatus() {
                return betStatus;
            }

            public void setBetStatus(int betStatus) {
                this.betStatus = betStatus;
            }

            public String getBetStatusDes() {
                return betStatusDes;
            }

            public void setBetStatusDes(String betStatusDes) {
                this.betStatusDes = betStatusDes;
            }

            public int getBetMultiple() {
                return betMultiple;
            }

            public void setBetMultiple(int betMultiple) {
                this.betMultiple = betMultiple;
            }

            public double getBetModel() {
                return betModel;
            }

            public void setBetModel(double betModel) {
                this.betModel = betModel;
            }

            public int getBetNums() {
                return betNums;
            }

            public void setBetNums(int betNums) {
                this.betNums = betNums;
            }

            public Object getChaseId() {
                return chaseId;
            }

            public void setChaseId(Object chaseId) {
                this.chaseId = chaseId;
            }

            public Object getPreOrderId() {
                return preOrderId;
            }

            public void setPreOrderId(Object preOrderId) {
                this.preOrderId = preOrderId;
            }

            public Object getNextOrderId() {
                return nextOrderId;
            }

            public void setNextOrderId(Object nextOrderId) {
                this.nextOrderId = nextOrderId;
            }

            public boolean isWin() {
                return win;
            }

            public void setWin(boolean win) {
                this.win = win;
            }

            public boolean isDisplayZuHe() {
                return displayZuHe;
            }

            public void setDisplayZuHe(boolean displayZuHe) {
                this.displayZuHe = displayZuHe;
            }

            public String getBetNum() {
                return betNum;
            }

            public void setBetNum(String betNum) {
                this.betNum = betNum;
            }
        }

}
