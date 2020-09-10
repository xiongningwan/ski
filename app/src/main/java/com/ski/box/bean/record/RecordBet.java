package com.ski.box.bean.record;

import java.io.Serializable;
import java.util.List;

/**
 * 四大列表 投注记录 返回ben对象
 */
public class RecordBet implements Serializable {


    /**
     * total : 14
     * totalPage : 1
     * pageSize : 20
     * currentPage : 1
     * list : [{"orderId":"1252527973630738487","betTime":"2020-04-21 17:21:54","playId":"246020901","playCode":null,"playName":"前二组选","betContent":"01 02","winAmount":0,"betMoney":10,"ticketId":46,"ticketName":"极速11选5","canCancel":false,"seriesId":2,"seriesCode":null,"odd":"{\"1\":\"40.000\"}","betPrize":"{\"1\":\"400.0000\"}","ticketResult":"05,09,10,11,08","solo":false,"chaseOrder":false,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1042","betStatus":2,"betStatusDes":"未中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":null,"nextOrderId":"1252525271966285826","win":false,"displayZuHe":false,"betNum":"01 02"},{"orderId":"1252525271966285826","betTime":"2020-04-21 17:11:10","playId":"267010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":0,"betMoney":50,"ticketId":67,"ticketName":"极速赛车","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"110.2500\"}","ticketResult":"3,5,6,10,4,8,9,7,2,1","solo":false,"chaseOrder":false,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1032","betStatus":2,"betStatusDes":"未中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252527973630738487","nextOrderId":"1252523446387081268","win":false,"displayZuHe":false,"betNum":"da"},{"orderId":"1252523446387081268","betTime":"2020-04-21 17:03:55","playId":"267010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":22.05,"betMoney":10,"ticketId":67,"ticketName":"极速赛车","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"22.0500\"}","ticketResult":"8,6,2,3,9,7,10,5,4,1","solo":false,"chaseOrder":true,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1025","betStatus":5,"betStatusDes":"已中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252525271966285826","nextOrderId":"1252523194812727368","win":true,"displayZuHe":false,"betNum":"da"},{"orderId":"1252523194812727368","betTime":"2020-04-21 17:02:55","playId":"267010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":22.05,"betMoney":10,"ticketId":67,"ticketName":"极速赛车","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"22.0500\"}","ticketResult":"9,3,4,7,6,2,8,1,10,5","solo":false,"chaseOrder":true,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1024","betStatus":5,"betStatusDes":"已中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252523446387081268","nextOrderId":"1252522943150293088","win":true,"displayZuHe":false,"betNum":"da"},{"orderId":"1252522943150293088","betTime":"2020-04-21 17:01:55","playId":"267010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":22.05,"betMoney":10,"ticketId":67,"ticketName":"极速赛车","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"22.0500\"}","ticketResult":"10,2,5,6,1,8,9,4,3,7","solo":false,"chaseOrder":true,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1023","betStatus":5,"betStatusDes":"已中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252523194812727368","nextOrderId":"1252522691584327716","win":true,"displayZuHe":false,"betNum":"da"},{"orderId":"1252522691584327716","betTime":"2020-04-21 17:00:55","playId":"267010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":0,"betMoney":10,"ticketId":67,"ticketName":"极速赛车","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"22.0500\"}","ticketResult":"2,7,9,1,10,3,4,6,5,8","solo":false,"chaseOrder":true,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1022","betStatus":2,"betStatusDes":"未中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252522943150293088","nextOrderId":"1252522439888339015","win":false,"displayZuHe":false,"betNum":"da"},{"orderId":"1252522439888339015","betTime":"2020-04-21 16:59:55","playId":"267010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":0,"betMoney":10,"ticketId":67,"ticketName":"极速赛车","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"22.0500\"}","ticketResult":"1,6,7,9,3,10,4,8,2,5","solo":false,"chaseOrder":true,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1021","betStatus":2,"betStatusDes":"未中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252522691584327716","nextOrderId":"1252522210963226654","win":false,"displayZuHe":false,"betNum":"da"},{"orderId":"1252522210963226654","betTime":"2020-04-21 16:59:00","playId":"267010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":22.05,"betMoney":10,"ticketId":67,"ticketName":"极速赛车","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"22.0500\"}","ticketResult":"10,7,6,3,4,5,9,2,1,8","solo":false,"chaseOrder":true,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1020","betStatus":5,"betStatusDes":"已中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252522439888339015","nextOrderId":"1252521936446029917","win":true,"displayZuHe":false,"betNum":"da"},{"orderId":"1252521936446029917","betTime":"2020-04-21 16:57:55","playId":"267010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":0,"betMoney":10,"ticketId":67,"ticketName":"极速赛车","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"22.0500\"}","ticketResult":"1,10,9,2,5,3,6,8,4,7","solo":false,"chaseOrder":true,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1019","betStatus":2,"betStatusDes":"未中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252522210963226654","nextOrderId":"1252521684922007608","win":false,"displayZuHe":false,"betNum":"da"},{"orderId":"1252521684922007608","betTime":"2020-04-21 16:56:55","playId":"267010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":22.05,"betMoney":10,"ticketId":67,"ticketName":"极速赛车","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"22.0500\"}","ticketResult":"5,9,3,1,4,2,6,8,10,7","solo":false,"chaseOrder":true,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1018","betStatus":5,"betStatusDes":"已中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252521936446029917","nextOrderId":"1252521435000209442","win":true,"displayZuHe":false,"betNum":"da"},{"orderId":"1252521435000209442","betTime":"2020-04-21 16:55:55","playId":"267010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":0,"betMoney":10,"ticketId":67,"ticketName":"极速赛车","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"22.0500\"}","ticketResult":"7,4,5,3,2,6,9,10,1,8","solo":false,"chaseOrder":true,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1017","betStatus":2,"betStatusDes":"未中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252521684922007608","nextOrderId":"1252521375545950247","win":false,"displayZuHe":false,"betNum":"da"},{"orderId":"1252521375545950247","betTime":"2020-04-21 16:55:41","playId":"267010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":22.05,"betMoney":10,"ticketId":67,"ticketName":"极速赛车","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"22.0500\"}","ticketResult":"3,9,6,1,2,8,10,5,4,7","solo":false,"chaseOrder":true,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-1016","betStatus":5,"betStatusDes":"已中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252521435000209442","nextOrderId":"1252512310019227658","win":true,"displayZuHe":false,"betNum":"da"},{"orderId":"1252512310019227658","betTime":"2020-04-21 16:19:39","playId":"258010101","playCode":null,"playName":"总和大小","betContent":"大","winAmount":19.6,"betMoney":10,"ticketId":58,"ticketName":"幸运11选5","canCancel":false,"seriesId":2,"seriesCode":null,"odd":"{\"1\":\"1.960\"}","betPrize":"{\"1\":\"19.6000\"}","ticketResult":"05,11,09,06,08","solo":false,"chaseOrder":false,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-196","betStatus":5,"betStatusDes":"已中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252521375545950247","nextOrderId":"1252495991928520736","win":true,"displayZuHe":false,"betNum":"da"},{"orderId":"1252495991928520736","betTime":"2020-04-21 15:14:49","playId":"248010101","playCode":null,"playName":"冠亚和大小","betContent":"大","winAmount":0,"betMoney":10,"ticketId":48,"ticketName":"极速飞艇","canCancel":false,"seriesId":6,"seriesCode":null,"odd":"{\"1\":\"2.205\"}","betPrize":"{\"1\":\"22.0500\"}","ticketResult":"1,6,4,5,7,10,9,3,8,2","solo":false,"chaseOrder":false,"groupMode":2,"singleGame":false,"ticketPlanNo":"20200421-0915","betStatus":2,"betStatusDes":"未中奖","betMultiple":1,"betModel":1,"betNums":1,"chaseId":null,"preOrderId":"1252512310019227658","nextOrderId":"1252527973630738487","win":false,"displayZuHe":false,"betNum":"da"}]
     */

    private int total;
    private int totalPage;
    private int pageSize;
    private int currentPage;
    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable{
        /**
         * orderId : 1252527973630738487
         * betTime : 2020-04-21 17:21:54
         * playId : 246020901
         * playCode : null
         * playName : 前二组选
         * betContent : 01 02
         * winAmount : 0
         * betMoney : 10
         * ticketId : 46
         * ticketName : 极速11选5
         * canCancel : false
         * seriesId : 2
         * seriesCode : null
         * odd : {"1":"40.000"}
         * betPrize : {"1":"400.0000"}
         * ticketResult : 05,09,10,11,08
         * solo : false
         * chaseOrder : false
         * groupMode : 2
         * singleGame : false
         * ticketPlanNo : 20200421-1042
         * betStatus : 2
         * betStatusDes : 未中奖
         * betMultiple : 1
         * betModel : 1
         * betNums : 1
         * chaseId : null
         * preOrderId : null
         * nextOrderId : 1252525271966285826
         * win : false
         * displayZuHe : false
         * betNum : 01 02
         */

        private String orderId;
        private String betTime;
        private String playId;
        private Object playCode;
        private String playName;
        private String betContent;
        private double winAmount;
        private float betMoney;
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
        private float betModel;
        private int betNums;
        private Object chaseId;
        private Object preOrderId;
        private String nextOrderId;
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

        public void setWinAmount(int winAmount) {
            this.winAmount = winAmount;
        }

        public float getBetMoney() {
            return betMoney;
        }

        public void setBetMoney(int betMoney) {
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

        public float getBetModel() {
            return betModel;
        }

        public void setBetModel(float betModel) {
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

        public String getNextOrderId() {
            return nextOrderId;
        }

        public void setNextOrderId(String nextOrderId) {
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
