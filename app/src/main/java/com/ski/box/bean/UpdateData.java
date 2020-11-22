package com.ski.box.bean;

/**
 * Created by tom on 2020/11/22.
 */
public class UpdateData {
    private int code;
    private String msg;
    private UpdateBean data;

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

    public UpdateBean getData() {
        return data;
    }

    public void setData(UpdateBean data) {
        this.data = data;
    }
}
