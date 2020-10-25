package com.yb.core.net;

/**
 * 模板
 *
 * @param <T>
 */
public class HttpResult<T> {

    private int code;
    private String msg;
    private T data;
    private Object extra;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", message='" + msg + '\'' +
                ", data=" + data +
                ", extra=" + extra +
                '}';
    }
}