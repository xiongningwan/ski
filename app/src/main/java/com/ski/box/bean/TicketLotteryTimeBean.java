package com.ski.box.bean;

/**
 * 获取头部倒计时 和最新期号
 */
public class TicketLotteryTimeBean {

    /**
     * ticketId : 67
     * sale : 0
     * startTime : 1580363335000
     * endTime : 1580363395000
     * planId : 20200130-0830
     * isLow : 0
     */

    private int ticketId;
    private int sale;
    private long startTime;
    private long endTime;
    private String planId;
    private long waitingTime;
    private int isLow;
    private String beforePlanNo;

    public String getBeforePlanNo() {
        return beforePlanNo;
    }

    public void setBeforePlanNo(String beforePlanNo) {
        this.beforePlanNo = beforePlanNo;
    }

    public long getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(long waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public int getIsLow() {
        return isLow;
    }

    public void setIsLow(int isLow) {
        this.isLow = isLow;
    }
}
