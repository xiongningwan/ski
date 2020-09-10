package com.ski.box.bean.record;

import java.io.Serializable;

public class CheDanErrorBean implements Serializable {


    /**
     * code : 10035
     * message : 正在处理中,请稍等...
     * type : 2
     */

    private int code;
    private String message;
    private int type;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
