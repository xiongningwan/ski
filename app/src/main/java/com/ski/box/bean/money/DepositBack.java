package com.ski.box.bean.money;

/**
 * Created by tom on 2020/10/31.
 */
public class DepositBack {
    /**
     * orderId : 1322455432656064561
     * amt : 11111
     * bankName : 招商银行
     * platformCardNo : 6225000100023333
     * platformCardName : 关羽
     * depositMsg : 3617
     * createAt : 2020-10-31 16:28:39
     * expireAt : 2020-10-31 16:38:39
     */

    private String orderId;
    private String amt;
    private String bankName;
    private String platformCardNo;
    private String platformCardName;
    private String depositMsg;
    private String createAt;
    private String expireAt;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getPlatformCardNo() {
        return platformCardNo;
    }

    public void setPlatformCardNo(String platformCardNo) {
        this.platformCardNo = platformCardNo;
    }

    public String getPlatformCardName() {
        return platformCardName;
    }

    public void setPlatformCardName(String platformCardName) {
        this.platformCardName = platformCardName;
    }

    public String getDepositMsg() {
        return depositMsg;
    }

    public void setDepositMsg(String depositMsg) {
        this.depositMsg = depositMsg;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(String expireAt) {
        this.expireAt = expireAt;
    }
}
