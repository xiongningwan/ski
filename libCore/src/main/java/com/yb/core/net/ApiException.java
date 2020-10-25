package com.yb.core.net;


import com.google.gson.Gson;

public class ApiException extends RuntimeException {
    public ApiException(HttpResult httpResult) {
        this(getApiExceptionMessage(httpResult));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     *
     * @return
     */
    private static String getApiExceptionMessage(HttpResult httpResult) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setType(IErrorType.ERROR_TYPE_API);
        errorResult.setCode(httpResult.getCode());
        errorResult.setMessage(httpResult.getMessage());
        String msg = new Gson().toJson(errorResult);
        return msg;
    }
}
