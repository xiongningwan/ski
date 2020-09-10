package com.ski.box.bean;


/**
 * 资金记录的帐变类型
 */
public class FrontTradeTypesBean extends MkBaseEntity {
    /**
     * name : 转入
     * type : 1
     * tradeType : 1
     */

    private String name;
    private int type;
    private int tradeType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTradeType() {
        return tradeType;
    }

    public void setTradeType(int tradeType) {
        this.tradeType = tradeType;
    }
}
