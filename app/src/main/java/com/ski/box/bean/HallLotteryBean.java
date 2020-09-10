package com.ski.box.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class HallLotteryBean implements MultiItemEntity {
    private String time;// 开奖时间
    private int ticketId; // 彩种
    private String ticketName;
    private int gMode;
    private String mobileLogoPath;
    private boolean isSelected;
    private int itemType;

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public int getgMode() {
        return gMode;
    }

    public void setgMode(int gMode) {
        this.gMode = gMode;
    }

    public String getMobileLogoPath() {
        return mobileLogoPath;
    }

    public void setMobileLogoPath(String mobileLogoPath) {
        this.mobileLogoPath = mobileLogoPath;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
