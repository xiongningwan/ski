package com.ski.box.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 获取 开奖号码列表
 */
public class LotteryNumBean implements MultiItemEntity {

    /**
     * issue : 20200130-0984
     * code : 6 1 2 5 4 7 9 10 3 8
     * codeStyle : null
     */

    private String issue;
    private String code;
    private String zuSan;
    private String zuLiu;
    private String openTime;
    private int ticketId;
    private String ticketName;
    private String[]  shapeArr;
    private int itemType;



    public LotteryNumBean() {
    }

    public LotteryNumBean(String issue, String code, int ticketId) {
        this.issue = issue;
        this.code = code;
        this.ticketId = ticketId;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
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

    public String getZuSan() {
        return zuSan;
    }

    public void setZuSan(String zuSan) {
        this.zuSan = zuSan;
    }

    public String getZuLiu() {
        return zuLiu;
    }

    public void setZuLiu(String zuLiu) {
        this.zuLiu = zuLiu;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String[] getShapeArr() {
        return shapeArr;
    }

    public void setShapeArr(String[] shapeArr) {
        this.shapeArr = shapeArr;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
