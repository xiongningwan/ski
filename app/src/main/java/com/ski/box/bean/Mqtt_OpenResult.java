package com.ski.box.bean;

/**
 * Created by tom on 2020/5/12.
 */
public class Mqtt_OpenResult {
    /**
     * ticketPlanNo : 20200509-179
     * openTime : 2020-05-10 03:59:09
     * openCode : 9,2,4,1,10,6,5,7,8,3
     * ticketId : 68
     * ticketName : 幸运飞艇
     */

    private String ticketPlanNo;
    private String openTime;
    private String openCode;
    private int ticketId;
    private String ticketName;

    public String getTicketPlanNo() {
        return ticketPlanNo;
    }

    public void setTicketPlanNo(String ticketPlanNo) {
        this.ticketPlanNo = ticketPlanNo;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getOpenCode() {
        return openCode;
    }

    public void setOpenCode(String openCode) {
        this.openCode = openCode;
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
}
