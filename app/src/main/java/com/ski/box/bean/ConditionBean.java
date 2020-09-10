package com.ski.box.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ConditionBean implements MultiItemEntity {
    private String name;
    private String isLow;
    private String startDate;
    private String endDate;
    private String status;
    private int itemType;
    private String tradeType;
    private boolean isSelected;

    public ConditionBean() {
    }

    public ConditionBean(String name, String condition) {
        this.name = name;
        this.isLow = condition;
    }

    public ConditionBean(String name, String status, boolean isSelected) {
        this.name = name;
        this.status = status;
        this.isSelected = isSelected;
    }

    public ConditionBean(String name, int itemType, String tradeType, boolean isSelected) {
        this.name = name;
        this.itemType = itemType;
        this.tradeType = tradeType;
        this.isSelected = isSelected;
    }

    public ConditionBean(String name, String startDate, String endDate, boolean isSelected) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isSelected = isSelected;
    }

    public ConditionBean(String name, String startDate, String endDate, String condition, boolean isSelected) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isLow = condition;
        this.isSelected = isSelected;
    }


    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsLow() {
        return isLow;
    }

    public void setIsLow(String isLow) {
        this.isLow = isLow;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

}
