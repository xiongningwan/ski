package com.ski.box.view.activity.old;

import java.util.List;

public class MerchantBean {
    public MerchantBean(int id, String merchantName) {
        this.id = id;
        this.merchantName = merchantName;
    }

    /**
     * id : 43
     * merchantAccount : bob
     * merchantName : bob
     * parantId : 0
     * environment : 4
     * merchantSon : []
     */

    private int id;
    private String merchantAccount;
    private String merchantName;
    private int parantId;
    private int environment;
    private List<?> merchantSon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMerchantAccount() {
        return merchantAccount;
    }

    public void setMerchantAccount(String merchantAccount) {
        this.merchantAccount = merchantAccount;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public int getParantId() {
        return parantId;
    }

    public void setParantId(int parantId) {
        this.parantId = parantId;
    }

    public int getEnvironment() {
        return environment;
    }

    public void setEnvironment(int environment) {
        this.environment = environment;
    }

    public List<?> getMerchantSon() {
        return merchantSon;
    }

    public void setMerchantSon(List<?> merchantSon) {
        this.merchantSon = merchantSon;
    }
}
