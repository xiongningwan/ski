package com.ski.box.bean;

public class QuickSetMoneyBean {
    String money;
    boolean red;
    int color;

    public QuickSetMoneyBean(String s, boolean b) {
        this.money = s;
        this.red = b;
    }

    public QuickSetMoneyBean(String money, int color) {
        this.money = money;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public boolean isRed() {
        return red;
    }

    public void setRed(boolean red) {
        this.red = red;
    }
}
