package com.ski.box.bean.record;

import java.util.List;

/**
 * 每日盈亏列表
 */
public class RecordProfit {
    /**
     * totalDayTotalProfit : -203775.019
     * total : 9
     * totalRebateTotalMoney : 0
     * totalPage : 1
     * totalSoloDeductAmount : 0
     * pageSize : 20
     * totalWinTotalMoney : 87867.181
     * currentPage : 1
     * totalProfitRebateAmount : 0
     * list : [{"date":"2020-02-06","betTotalMoney":1000,"winTotalMoney":0,"rebateTotalMoney":0,"dayTotalProfit":-1000,"soloDeductAmount":0,"profitDeductAmount":0,"profitRebateAmount":0},{"date":"2020-02-05","betTotalMoney":100000,"winTotalMoney":0,"rebateTotalMoney":0,"dayTotalProfit":-100000,"soloDeductAmount":0,"profitDeductAmount":0,"profitRebateAmount":0},{"date":"2020-02-04","betTotalMoney":810,"winTotalMoney":911.37,"rebateTotalMoney":0,"dayTotalProfit":50.17,"soloDeductAmount":0,"profitDeductAmount":51.2,"profitRebateAmount":0},{"date":"2020-02-03","betTotalMoney":24119,"winTotalMoney":25435.5,"rebateTotalMoney":0,"dayTotalProfit":1316.5,"soloDeductAmount":0,"profitDeductAmount":0,"profitRebateAmount":0},{"date":"2020-02-01","betTotalMoney":8839,"winTotalMoney":10830.662,"rebateTotalMoney":0,"dayTotalProfit":1991.662,"soloDeductAmount":0,"profitDeductAmount":0,"profitRebateAmount":0},{"date":"2020-01-31","betTotalMoney":156622,"winTotalMoney":46988.849,"rebateTotalMoney":0,"dayTotalProfit":-109633.151,"soloDeductAmount":0,"profitDeductAmount":2.8,"profitRebateAmount":0},{"date":"2020-01-30","betTotalMoney":0,"winTotalMoney":3700.8,"rebateTotalMoney":0,"dayTotalProfit":3700.8,"soloDeductAmount":0,"profitDeductAmount":0,"profitRebateAmount":0},{"date":"2020-01-29","betTotalMoney":0,"winTotalMoney":0,"rebateTotalMoney":0,"dayTotalProfit":0,"soloDeductAmount":0,"profitDeductAmount":0,"profitRebateAmount":0},{"date":"2020-01-28","betTotalMoney":201,"winTotalMoney":0,"rebateTotalMoney":0,"dayTotalProfit":-201,"soloDeductAmount":0,"profitDeductAmount":0,"profitRebateAmount":0}]
     * totalBetTotalMoney : 291591
     * totalProfitDeductAmount : 54
     */

    private double totalDayTotalProfit;
    private int total;
    private int totalRebateTotalMoney;
    private int totalPage;
    private int totalSoloDeductAmount;
    private int pageSize;
    private double totalWinTotalMoney;
    private int currentPage;
    private int totalProfitRebateAmount;
    private int totalBetTotalMoney;
    private double totalProfitDeductAmount;
    private List<ListBean> list;

    public double getTotalDayTotalProfit() {
        return totalDayTotalProfit;
    }

    public void setTotalDayTotalProfit(double totalDayTotalProfit) {
        this.totalDayTotalProfit = totalDayTotalProfit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalRebateTotalMoney() {
        return totalRebateTotalMoney;
    }

    public void setTotalRebateTotalMoney(int totalRebateTotalMoney) {
        this.totalRebateTotalMoney = totalRebateTotalMoney;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalSoloDeductAmount() {
        return totalSoloDeductAmount;
    }

    public void setTotalSoloDeductAmount(int totalSoloDeductAmount) {
        this.totalSoloDeductAmount = totalSoloDeductAmount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public double getTotalWinTotalMoney() {
        return totalWinTotalMoney;
    }

    public void setTotalWinTotalMoney(double totalWinTotalMoney) {
        this.totalWinTotalMoney = totalWinTotalMoney;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalProfitRebateAmount() {
        return totalProfitRebateAmount;
    }

    public void setTotalProfitRebateAmount(int totalProfitRebateAmount) {
        this.totalProfitRebateAmount = totalProfitRebateAmount;
    }

    public int getTotalBetTotalMoney() {
        return totalBetTotalMoney;
    }

    public void setTotalBetTotalMoney(int totalBetTotalMoney) {
        this.totalBetTotalMoney = totalBetTotalMoney;
    }

    public double getTotalProfitDeductAmount() {
        return totalProfitDeductAmount;
    }

    public void setTotalProfitDeductAmount(double totalProfitDeductAmount) {
        this.totalProfitDeductAmount = totalProfitDeductAmount;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }



    public static class ListBean {
        private String date;
        private double betTotalMoney;
        private double winTotalMoney;
        private double rebateTotalMoney;
        private double dayTotalProfit;
        private String ticketName;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public double getBetTotalMoney() {
            return betTotalMoney;
        }

        public void setBetTotalMoney(double betTotalMoney) {
            this.betTotalMoney = betTotalMoney;
        }

        public double getWinTotalMoney() {
            return winTotalMoney;
        }

        public void setWinTotalMoney(double winTotalMoney) {
            this.winTotalMoney = winTotalMoney;
        }

        public double getRebateTotalMoney() {
            return rebateTotalMoney;
        }

        public void setRebateTotalMoney(double rebateTotalMoney) {
            this.rebateTotalMoney = rebateTotalMoney;
        }

        public double getDayTotalProfit() {
            return dayTotalProfit;
        }

        public void setDayTotalProfit(double dayTotalProfit) {
            this.dayTotalProfit = dayTotalProfit;
        }

        public String getTicketName() {
            return ticketName;
        }

        public void setTicketName(String ticketName) {
            this.ticketName = ticketName;
        }
    }











}
