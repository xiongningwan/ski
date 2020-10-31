package com.ski.box.bean.money;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tom on 2020/10/31.
 */
public class DepositBack implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.orderId);
        dest.writeString(this.amt);
        dest.writeString(this.bankName);
        dest.writeString(this.platformCardNo);
        dest.writeString(this.platformCardName);
        dest.writeString(this.depositMsg);
        dest.writeString(this.createAt);
        dest.writeString(this.expireAt);
    }

    public DepositBack() {
    }

    protected DepositBack(Parcel in) {
        this.orderId = in.readString();
        this.amt = in.readString();
        this.bankName = in.readString();
        this.platformCardNo = in.readString();
        this.platformCardName = in.readString();
        this.depositMsg = in.readString();
        this.createAt = in.readString();
        this.expireAt = in.readString();
    }

    public static final Parcelable.Creator<DepositBack> CREATOR = new Parcelable.Creator<DepositBack>() {
        @Override
        public DepositBack createFromParcel(Parcel source) {
            return new DepositBack(source);
        }

        @Override
        public DepositBack[] newArray(int size) {
            return new DepositBack[size];
        }
    };
}
