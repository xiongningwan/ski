package com.yb.core.net;


import com.google.gson.Gson;

public class ApiException_new extends ApiException {
    public ApiException_new(HttpResult_new httpResult) {
        this(getApiExceptionMessage(httpResult));
    }

    public ApiException_new(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     *
     * @return
     */
    private static String getApiExceptionMessage(HttpResult_new httpResult) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setType(IErrorType.ERROR_TYPE_API);
        errorResult.setCode(-1);
        errorResult.setMessage(String.valueOf(httpResult.getData()));
        String msg = new Gson().toJson(errorResult);
        return msg;
    }
}
