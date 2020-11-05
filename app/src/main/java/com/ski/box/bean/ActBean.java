package com.ski.box.bean;

/**
 * Created by tom on 2020/11/5.
 */
public class ActBean {
    /**
     * activityCode : qdscj
     * activityName : 签到送彩金
     * activityDesc : 签到送彩金的描述
     * startDate : 2020-11-01 00:00:00
     * endDate : 2020-12-31 23:59:59
     */

    private String activityCode;
    private String activityName;
    private String activityDesc;
    private String startDate;
    private String endDate;

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
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
}
