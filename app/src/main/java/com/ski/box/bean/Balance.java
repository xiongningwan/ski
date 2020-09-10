package com.ski.box.bean;

/**
 * 查询余额（商户钱包）
 **/
public class Balance {

    private String merchantAccount;
    private String amount;
    private String minTransferLimit;
    private String maxTransferLimit;

    public String getMerchantAccount() {
        return merchantAccount;
    }

    public void setMerchantAccount(String merchantAccount) {
        this.merchantAccount = merchantAccount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMinTransferLimit() {
        return minTransferLimit;
    }

    public void setMinTransferLimit(String minTransferLimit) {
        this.minTransferLimit = minTransferLimit;
    }

    public String getMaxTransferLimit() {
        return maxTransferLimit;
    }

    public void setMaxTransferLimit(String maxTransferLimit) {
        this.maxTransferLimit = maxTransferLimit;
    }
}
