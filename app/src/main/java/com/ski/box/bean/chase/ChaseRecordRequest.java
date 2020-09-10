package com.ski.box.bean.chase;

/**
 * @Description: 追号记录列表请求参数
 */
public class ChaseRecordRequest {
    private String startDate;
    private String endDate;
    private String ticketId;
    private String playId;
    //status状态：1-待开奖，2-未中奖
    private String status;//追号状态1-待开奖,2-未中奖,3-已中奖,4-挂起,5-已结算
    private int pageSize=20;//每页显示条数
    private int pageNum;//当前是第几页
    private String diskSurface;//区分玩法，0 标准盘；1 双面盘；空全部
    private String isLow; //查询低频彩 1 低频彩；空全部
    private String isSingle; //是否单挑
    /**
     * KENO的请求参数
     */
    private int seriesId;

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public String getIsLow() {
        return isLow;
    }

    public void setIsLow(String isLow) {
        this.isLow = isLow;
    }

    public String getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(String isSingle) {
        this.isSingle = isSingle;
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

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getPlayId() {
        return playId;
    }

    public void setPlayId(String playId) {
        this.playId = playId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getDiskSurface() {
        return diskSurface;
    }

    public void setDiskSurface(String diskSurface) {
        this.diskSurface = diskSurface;
    }
}
