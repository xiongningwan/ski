package com.ski.box.bean.chase;

import java.io.Serializable;
import java.util.List;

/**
 * 追号记录列表
 */
public class ChaseListEntity implements Serializable {
    /**
     * total : 1
     * totalPage : 1
     * pageSize : 20
     * currentPage : 1
     * list : [{"chaseId":"1252521375508201562","preChaseId":"","nextChaseId":"","playName":"冠亚和大小","ticketName":"极速赛车","allPeriods":10,"donePeriods":10,"cancelPeriods":0,"groupMode":2,"winPeriods":6,"allAmount":100,"doneAmount":100,"cancelAmount":0,"winAmount":132.3,"chaseModel":1,"suspend":1,"chaseStart":"20200421-1016","chaseStatus":2,"chaseStatusDes":"已完成","canCancel":false,"chaseStartTime":"2020-04-21 16:55:41"}]
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
         * chaseId : 1252521375508201562
         * preChaseId :
         * nextChaseId :
         * playName : 冠亚和大小
         * ticketName : 极速赛车
         * allPeriods : 10
         * donePeriods : 10
         * cancelPeriods : 0
         * groupMode : 2
         * winPeriods : 6
         * allAmount : 100
         * doneAmount : 100
         * cancelAmount : 0
         * winAmount : 132.3
         * chaseModel : 1
         * suspend : 1
         * chaseStart : 20200421-1016
         * chaseStatus : 2
         * chaseStatusDes : 已完成
         * canCancel : false
         * chaseStartTime : 2020-04-21 16:55:41
         */

        private String chaseId;
        private String preChaseId;
        private String nextChaseId;
        private String playName;
        private String ticketName;
        private int allPeriods;
        private int donePeriods;
        private int cancelPeriods;
        private int groupMode;
        private int winPeriods;
        private float allAmount;
        private float doneAmount;
        private double cancelAmount;
        private double winAmount;
        private int chaseModel;
        private int suspend;
        private String chaseStart;
        private int chaseStatus;
        private String chaseStatusDes;
        private boolean canCancel;
        private String chaseStartTime;

        public String getChaseId() {
            return chaseId;
        }

        public void setChaseId(String chaseId) {
            this.chaseId = chaseId;
        }

        public String getPreChaseId() {
            return preChaseId;
        }

        public void setPreChaseId(String preChaseId) {
            this.preChaseId = preChaseId;
        }

        public String getNextChaseId() {
            return nextChaseId;
        }

        public void setNextChaseId(String nextChaseId) {
            this.nextChaseId = nextChaseId;
        }

        public String getPlayName() {
            return playName;
        }

        public void setPlayName(String playName) {
            this.playName = playName;
        }

        public String getTicketName() {
            return ticketName;
        }

        public void setTicketName(String ticketName) {
            this.ticketName = ticketName;
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

        public int getGroupMode() {
            return groupMode;
        }

        public void setGroupMode(int groupMode) {
            this.groupMode = groupMode;
        }

        public int getWinPeriods() {
            return winPeriods;
        }

        public void setWinPeriods(int winPeriods) {
            this.winPeriods = winPeriods;
        }

        public float getAllAmount() {
            return allAmount;
        }

        public void setAllAmount(float allAmount) {
            this.allAmount = allAmount;
        }

        public float getDoneAmount() {
            return doneAmount;
        }

        public void setDoneAmount(float doneAmount) {
            this.doneAmount = doneAmount;
        }

        public double getCancelAmount() {
            return cancelAmount;
        }

        public void setCancelAmount(double cancelAmount) {
            this.cancelAmount = cancelAmount;
        }

        public double getWinAmount() {
            return winAmount;
        }

        public void setWinAmount(double winAmount) {
            this.winAmount = winAmount;
        }

        public int getChaseModel() {
            return chaseModel;
        }

        public void setChaseModel(int chaseModel) {
            this.chaseModel = chaseModel;
        }

        public int getSuspend() {
            return suspend;
        }

        public void setSuspend(int suspend) {
            this.suspend = suspend;
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
    }

    /*
        *//**
         * total : 3
         * totalPage : 1
         * pageSize : 10
         * currentPage : 1
         * list : [{"orderId":"1177465123313287228","chaseNum":"XJSSC201909271408005DI9","nextOrderId":"1177408897430847570","preOrderId":"","methodname":"后二直选复式","startPlayId":"20190927-13","totalIssue":10,"traceIssue":1,"cancelIssue":0,"totalPrize":"60.0000","winPrize":"0.0000","state":0,"canCancel":true,"diskSurface":"0","chaseStarttime":"2019-09-27 14:08:56","ticketName":"新疆时时彩","ticketId":3,"winPeriods":0},{"orderId":"1177408897430847570","chaseNum":"KL82019092710250055SY","nextOrderId":"1177408781642891328","preOrderId":"1177465123313287228","methodname":"任选1","startPlayId":"976198","totalIssue":10,"traceIssue":1,"cancelIssue":0,"totalPrize":"20.0000","winPrize":"0.0000","state":0,"canCancel":true,"diskSurface":"0","chaseStarttime":"2019-09-27 10:25:31","ticketName":"北京快乐八","ticketId":26,"winPeriods":0},{"orderId":"1177408781642891328","chaseNum":"KL82019092710250055SL","nextOrderId":"","preOrderId":"1177408897430847570","methodname":"任选1","startPlayId":"976198","totalIssue":10,"traceIssue":1,"cancelIssue":0,"totalPrize":"20.0000","winPrize":"0.0000","state":0,"canCancel":true,"diskSurface":"0","chaseStarttime":"2019-09-27 10:25:03","ticketName":"北京快乐八","ticketId":26,"winPeriods":1}]
         *//*

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

        public static class ListBean implements  Serializable{

            private String chaseId;
            private String orderId;
            @SerializedName("nextChaseId")
            private String nextOrderId;//查询下一个订单id
            @SerializedName("preChaseId")
            private String preOrderId;//查询上一个订单id
            @SerializedName("playName")
            private String methodname;//玩法名称
            private String startPlayId;//开始奖期
            @SerializedName("allPeriods")
            private int totalIssue;//追号总期数
            @SerializedName("donePeriods")
            private int traceIssue;//已完成期数
            @SerializedName("cancelPeriods")
            private int cancelIssue;//已撤销期数
            @SerializedName("allAmount")
            private String totalPrize;//总金额
            private String winAmount;//中奖金额
            //            private String doneAmount;//中奖金额
            private String cancelAmount;//撤销金额
            private int state;
            private boolean canCancel;
            @SerializedName("groupMode")
            private String diskSurface;
            @SerializedName("chaseStartTime")
            private String chaseStarttime;
            @SerializedName("ticketName")
            private String ticketName;
            private int ticketId;
            private int winPeriods;

            private int chaseModel;//1-翻倍追号,2-利润率追号
            private int suspend;//追号规则:1-无,2-追中即停,3-不中即停
            private int chaseStatus;
            private String chaseStatusDes;
            private String chaseStart;

            public String getChaseStart() {
                return chaseStart;
            }
            public String getCancelAmount() {
                return cancelAmount;
            }

            public String getChaseId() {
                return chaseId;
            }

            public void setChaseId(String chaseId) {
                this.chaseId = chaseId;
            }

            public void setCancelAmount(String cancelAmount) {
                this.cancelAmount = cancelAmount;
            }

            public int getChaseModel() {
                return chaseModel;
            }

            public void setChaseModel(int chaseModel) {
                this.chaseModel = chaseModel;
            }

            public int getSuspend() {
                return suspend;
            }

            public void setSuspend(int suspend) {
                this.suspend = suspend;
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

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }



            public String getNextOrderId() {
                return nextOrderId;
            }

            public void setNextOrderId(String nextOrderId) {
                this.nextOrderId = nextOrderId;
            }

            public String getPreOrderId() {
                return preOrderId;
            }

            public void setPreOrderId(String preOrderId) {
                this.preOrderId = preOrderId;
            }

            public String getMethodname() {
                return methodname;
            }

            public void setMethodname(String methodname) {
                this.methodname = methodname;
            }

            public String getStartPlayId() {
                return startPlayId;
            }

            public void setStartPlayId(String startPlayId) {
                this.startPlayId = startPlayId;
            }

            public int getTotalIssue() {
                return totalIssue;
            }

            public void setTotalIssue(int totalIssue) {
                this.totalIssue = totalIssue;
            }

            public int getTraceIssue() {
                return traceIssue;
            }

            public void setTraceIssue(int traceIssue) {
                this.traceIssue = traceIssue;
            }

            public int getCancelIssue() {
                return cancelIssue;
            }

            public void setCancelIssue(int cancelIssue) {
                this.cancelIssue = cancelIssue;
            }

            public String getTotalPrize() {
                return totalPrize;
            }

            public void setTotalPrize(String totalPrize) {
                this.totalPrize = totalPrize;
            }

            public String getWinAmount() {
                return winAmount;
            }

            public void setWinAmount(String winAmount) {
                this.winAmount = winAmount;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public boolean isCanCancel() {
                return canCancel;
            }

            public void setCanCancel(boolean canCancel) {
                this.canCancel = canCancel;
            }

            public String getDiskSurface() {
                return diskSurface;
            }

            public void setDiskSurface(String diskSurface) {
                this.diskSurface = diskSurface;
            }

            public String getChaseStarttime() {
                return chaseStarttime;
            }

            public void setChaseStarttime(String chaseStarttime) {
                this.chaseStarttime = chaseStarttime;
            }

            public String getTicketName() {
                return ticketName;
            }

            public void setTicketName(String ticketName) {
                this.ticketName = ticketName;
            }

            public int getTicketId() {
                return ticketId;
            }

            public void setTicketId(int ticketId) {
                this.ticketId = ticketId;
            }

            public int getWinPeriods() {
                return winPeriods;
            }

            public void setWinPeriods(int winPeriods) {
                this.winPeriods = winPeriods;
            }
        }*/




}
