package com.yb.core.net;

import com.google.gson.Gson;
import com.hwangjr.rxbus.RxBus;


/**
 * 200	请求成功
 * 201  资源已经创建
 * 401  未授权 token 失效
 * 403  禁止
 * 404  找不到资源
 * 460  请求被服务器防火墙拦截
 */

public class CustomHttpException extends RuntimeException {
    public CustomHttpException(int code, String s) {
        HttpResult httpResult = new Gson().fromJson(s, HttpResult.class);
        if (httpResult == null) {
            httpResult = new HttpResult();
        }
        ErrorResult errorResult = new ErrorResult();
        errorResult.setType(IErrorType.ERROR_TYPE_HTTP);
        errorResult.setCode(code);
        errorResult.setMessage(httpResult.getMessage());
        switch (code) {
            case HttpCode.BAD_REQUEST:
                errorResult.setMessage("错误的请求");
                break;
            case HttpCode.UNAUTHORIZED:
                if (APICode.MKErrorCodes_1u == httpResult.getCode()) {
                    errorResult.setMessage("token disable");
                } else if (APICode.MKErrorCodes_2u == httpResult.getCode()) {
                    errorResult.setMessage("token disable");
                } else {
                    errorResult.setMessage("token disable");
                }
                RxBus.get().post("event_token_disable", "");
                break;
            case HttpCode.FORBIDDEN:
                errorResult.setMessage("禁止访问");
                break;
            case HttpCode.NOT_FOUND:
                errorResult.setMessage("找不到相关资源");
                break;
            case HttpCode.METHOD_NOT_ALLOWED:
                errorResult.setMessage("方法不被允许");
                break;
            case HttpCode.REQUEST_TIMEOUT:
                errorResult.setMessage("请求超时");
                break;
            case HttpCode.CONFLICT:
                errorResult.setMessage("请求冲突");
                break;
            case HttpCode.INTERNAL_SERVER_ERROR:
                errorResult.setMessage("服务器代码错误");
                break;
            case HttpCode.BAD_GATEWAY:
                errorResult.setMessage("请求无响应");
                break;
            case HttpCode.SERVICE_UNAVAILABLE:
                errorResult.setMessage("服务器维护或者过载");
                break;
            case HttpCode.GATEWAY_TIMEOUT:
                errorResult.setMessage("网关超时");
                break;

        }

        String errorJson = new Gson().toJson(errorResult);
        throw new CustomHttpException(errorJson);
    }

    public CustomHttpException(String message) {
        super(message);
    }
}
