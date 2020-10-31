package com.ski.box.bean.money;

/**
 * Created by tom on 2020/10/31.
 */
public class PayType {
    /**
     * channelCode : 3
     * channelName : 网银支付
     * minAmt : 300.0000
     * maxAmt : 30000.0000
     */

    private int channelCode;
    private String channelName;
    private String minAmt;
    private String maxAmt;

    public int getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(int channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getMinAmt() {
        return minAmt;
    }

    public void setMinAmt(String minAmt) {
        this.minAmt = minAmt;
    }

    public String getMaxAmt() {
        return maxAmt;
    }

    public void setMaxAmt(String maxAmt) {
        this.maxAmt = maxAmt;
    }
}
