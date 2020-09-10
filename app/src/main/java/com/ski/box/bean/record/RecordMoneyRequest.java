package com.ski.box.bean.record;

/**
 * @Description: 追号记录列表 请求参数
 */
public class RecordMoneyRequest {
    private String betPlayId;//订单编号
    private String queryDate;
    private String startTime;
    private String endTime;
    private String pageSize = "20";
    private int pageNum = 1;
    private String tradeType;
    //账变类型 1资金转入，2资金转出，3投注，4投注返点，5派奖，6撤销派奖，7撤销返点，8撤单返款，9追号扣款，10当期追号返款，
    // 11撤销追号，12单期总利润超限扣款，13单期单挑利润超限扣款, 14单期总利润超限返款，16撤单返款

    public String getBetPlayId() {
        return betPlayId;
    }

    public void setBetPlayId(String betPlayId) {
        this.betPlayId = betPlayId;
    }

    public String getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(String queryDate) {
        this.queryDate = queryDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

}
