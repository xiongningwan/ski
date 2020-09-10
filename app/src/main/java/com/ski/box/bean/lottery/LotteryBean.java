package com.ski.box.bean.lottery;


import java.util.List;

public class LotteryBean {
    private int ticketId;
    private String ticketName;
    private String path;
    private String code;
    private List<LotteryPlayStart> plays;
    private boolean isSelected;

    public LotteryBean() {
    }

    public LotteryBean(int ticketId, String ticketName, String path, String code) {
        this.ticketId = ticketId;
        this.ticketName = ticketName;
        this.path = path;
        this.code = code;
    }

    public LotteryBean(int ticketId, String ticketName, String path, String code, List<LotteryPlayStart> plays) {
        this.ticketId = ticketId;
        this.ticketName = ticketName;
        this.path = path;
        this.code = code;
        this.plays = plays;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<LotteryPlayStart> getPlays() {
        return plays;
    }

    public void setPlays(List<LotteryPlayStart> plays) {
        this.plays = plays;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }



}
