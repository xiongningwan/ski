package com.ski.box.bean.user;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by tom on 2020/10/26.
 */
public class BankCard implements MultiItemEntity {
    /**
     * bankCode : 1001
     * bankName : 招商银行
     * cardNo : 8765432187655
     * cardName : 李某某
     * currency : 704
     * createAt : null
     */

    private int bankCode;
    private String bankName;
    private String cardNo = "";
    private String cardName;
    private String currency;
    private Object createAt;
    private int itemType;

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public int getBankCode() {
        return bankCode;
    }

    public void setBankCode(int bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Object getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Object createAt) {
        this.createAt = createAt;
    }
}
