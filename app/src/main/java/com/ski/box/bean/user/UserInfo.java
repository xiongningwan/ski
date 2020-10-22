package com.ski.box.bean.user;

/**
 * Created by tom on 2020/10/23.
 */
public class UserInfo {
    /**
     * memberId : 1318161931562913883
     * memberAccount : water14
     * balance : 999915.6262
     * transferType : 2
     * merchantAccout : suba
     */

    private String memberId;
    private String memberAccount;
    private String balance;
    private int transferType;
    private String merchantAccout;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public int getTransferType() {
        return transferType;
    }

    public void setTransferType(int transferType) {
        this.transferType = transferType;
    }

    public String getMerchantAccout() {
        return merchantAccout;
    }

    public void setMerchantAccout(String merchantAccout) {
        this.merchantAccout = merchantAccout;
    }
}
