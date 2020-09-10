package com.ski.box.bean.chase;


/**
 * 追号单是否可撤
 */
public class ChasePlanStatusEntity {
    /**
     * msg : 976198期可撤单
     * plan : 976198
     * status : 1
     */

    private String msg;
    private String currentPlanNo;
    private int status;//1:可撤；2：追号单状态不合法；3：不可撤

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCurrentPlanNo() {
        return currentPlanNo;
    }

    public void setCurrentPlanNo(String currentPlanNo) {
        this.currentPlanNo = currentPlanNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
