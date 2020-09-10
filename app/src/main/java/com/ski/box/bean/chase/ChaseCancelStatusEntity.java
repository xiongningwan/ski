package com.ski.box.bean.chase;


/**
 * 追号单某期是否可撤
 */
public class ChaseCancelStatusEntity {

    /**
     * msg : 976198期可撤单
     * plan : 976198
     * status : 1
     */

    private String msg;
    private String plan;
    private int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
