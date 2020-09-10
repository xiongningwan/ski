package com.ski.box.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class TopGameBean implements MultiItemEntity {
    // 彩系
    private int ticketSeriesId;
    private String ticketSeriesName;
    // 彩种
    private int ticketId;
    private String ticketName;
    private int gMode;
    private String mobileLogoPath;
    private boolean isSelected;
    private int itemType;
    /**
     * 当前彩种所在的索引
     */
    private int lotteryIndex;
    /**
     * 当前彩系所包含的彩种个数
     */
    private int lotteryCount;

    /**
     * 单期盈利上限
     *
     * @return
     */
    private double periodMaxProfit;
    private double soloMaxProfit;

//1000.0
    public String getPeriodMaxProfit() {
        if (periodMaxProfit==0){
            return  "--";
        }
        String s = String.valueOf(periodMaxProfit);
        if (s.contains(".")){
            int i1 = s.indexOf(".");
            double i = Double.valueOf(s.substring(0,i1)) / 10000;
            double integer = Double.valueOf("0." + s.substring(i1+1));
            return (i+integer)+"万";
        }else{
            return (periodMaxProfit / 10000) + "万";
        }
    }

    public void setPeriodMaxProfit(double periodMaxProfit) {
        this.periodMaxProfit = periodMaxProfit;
    }

    public double getSoloMaxProfit() {
        return soloMaxProfit;
    }

    public void setSoloMaxProfit(double soloMaxProfit) {
        this.soloMaxProfit = soloMaxProfit;
    }

    public int getTicketSeriesId() {
        return ticketSeriesId;
    }

    public void setTicketSeriesId(int ticketSeriesId) {
        this.ticketSeriesId = ticketSeriesId;
    }

    public String getTicketSeriesName() {
        return ticketSeriesName;
    }

    public void setTicketSeriesName(String ticketSeriesName) {
        this.ticketSeriesName = ticketSeriesName;
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

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public int getLotteryIndex() {
        return lotteryIndex;
    }

    public void setLotteryIndex(int lotteryIndex) {
        this.lotteryIndex = lotteryIndex;
    }

    public int getLotteryCount() {
        return lotteryCount;
    }

    public void setLotteryCount(int lotteryCount) {
        this.lotteryCount = lotteryCount;
    }
}
