package com.ski.box.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by tom on 2020/10/30.
 */
public class RecordResultBean implements MultiItemEntity {
    private int itemType;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
