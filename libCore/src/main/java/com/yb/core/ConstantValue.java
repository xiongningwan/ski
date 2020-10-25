package com.yb.core;

/**
 * 常量
 */

public interface ConstantValue {

    /**
     * 接口失败，抛出异常
     * 页面有刷新或者加载更多，或者页面要在抛出异常处理一些事物
     */
    String EVENT_TYPE_NETWORK_EXCEPTION = "EVENT_TYPE_NETWORK_EXCEPTION";
    String EVENT_TYPE_REFRESH_CONFLICT = "EVENT_TYPE_REFRESH_CONFLICT";

    String APP_TYPE = "2"; // 2安卓　

    String CONTENT_TYPE_JSON = "Content-Type: application/json;charset=UTF-8";
}
