package com.ski.box.bean;

public class QuickSetMoneyBean2 {
    private String money;
    private boolean isSelected;

    public QuickSetMoneyBean2() {
    }

    public QuickSetMoneyBean2(String money, boolean isSelected) {
        this.money = money;
        this.isSelected = isSelected;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
