package com.ski.box.bean;

import java.io.Serializable;

public class MkBaseEntity implements Serializable {
    /**
     * code : 0
     * msg : ok
     */
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
