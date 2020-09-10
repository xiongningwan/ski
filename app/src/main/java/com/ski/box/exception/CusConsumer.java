package com.ski.box.exception;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.yb.core.base.BaseConsumer;
import com.yb.core.net.ApiException;
import com.yb.core.net.CustomHttpException;
import com.yb.core.net.ErrorResult;
import com.yb.core.net.IErrorType;

public class CusConsumer extends BaseConsumer {
    private boolean isDoDefaultLogic = true;//处理默认逻辑

    private boolean isDoDefaultToast = true;//处理默认的toast

    public CusConsumer() {
    }

    public CusConsumer(boolean isDoDefaultLogic, boolean isDoDefaultToast) {
        this.isDoDefaultLogic = isDoDefaultLogic;
        this.isDoDefaultToast = isDoDefaultToast;
    }

    @Override
    public void accept(Throwable throwable) throws Exception {
        super.accept(throwable);
        if (throwable == null) {
            return;
        }

        String error = throwable.getMessage();
        if (TextUtils.isEmpty(error)) {
            return;
        }
        if (throwable instanceof CustomHttpException) {
            ErrorResult errorResult = new Gson().fromJson(error, ErrorResult.class);
            if (IErrorType.ERROR_TYPE_HTTP == errorResult.getType()) {
                // http 异常
                doHttpException(errorResult);
            }
        } else if (throwable instanceof ApiException) {
            ErrorResult errorResult = new Gson().fromJson(error, ErrorResult.class);
            if (IErrorType.ERROR_TYPE_API == errorResult.getType()) {
                //api 异常
                doApiException(errorResult);
            }
        } else {
            // 其他异常
            doOtherException(error);
        }
    }


    private void doHttpException(ErrorResult errorResult) {
        if (isDoDefaultLogic) {

        }

        if (isDoDefaultToast) {

        }
    }

    private void doApiException(ErrorResult errorResult) {
        if (isDoDefaultLogic) {

        }

        if (isDoDefaultToast) {

        }
    }

    private void doOtherException(String error) {
        if (isDoDefaultLogic) {

        }

        if (isDoDefaultToast) {

        }
    }
}
