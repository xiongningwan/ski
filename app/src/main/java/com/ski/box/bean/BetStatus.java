package com.ski.box.bean;

public class BetStatus {
    private int status; // 0 单式  1 组合
    private long totalAmount;
    private double totalAmount_s;
    private int zhuShu = 0;

    public BetStatus() {
    }

    public BetStatus(int status, int totalAmount, int zhuShu) {
        this.status = status;
        this.totalAmount = totalAmount;
        this.zhuShu = zhuShu;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount_s() {
        return totalAmount_s;
    }

    public void setTotalAmount_s(double totalAmount_s) {
        this.totalAmount_s = totalAmount_s;
    }

    public int getZhuShu() {
        return zhuShu;
    }

    public void setZhuShu(int zhuShu) {
        this.zhuShu = zhuShu;
    }
}
