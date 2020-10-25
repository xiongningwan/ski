package com.yb.core.base;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yb.core.net.ApiException;
import com.yb.core.net.CustomHttpException;
import com.yb.core.net.ErrorResult;
import com.yb.core.net.HttpCode;
import com.yb.core.net.IErrorType;
import com.yb.core.utils.AppUtil;
import com.yb.core.utils.ToastUtil;
import com.yb.core.utils.ToastUtil;
import com.zy.core.R;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.functions.Consumer;

public class BaseConsumer implements Consumer<Throwable> {
    private boolean isDoDefaultLogic = true;//处理默认逻辑

    private boolean isDoDefaultToast = true;//处理默认的toast

    public BaseConsumer() {
    }

    public BaseConsumer(boolean isDoDefaultLogic, boolean isDoDefaultToast) {
        this.isDoDefaultLogic = isDoDefaultLogic;
        this.isDoDefaultToast = isDoDefaultToast;
    }

    @Override
    public void accept(Throwable throwable) throws Exception {
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
            doOtherException(throwable);
        }
    }

    private void doHttpException(ErrorResult errorResult) {
        if (isDoDefaultLogic) {

        }
        if (isDoDefaultToast) {
            ToastUtil.showError(errorResult.getMessage());
        }
    }

    private void doApiException(ErrorResult errorResult) {
        if (isDoDefaultLogic) {

        }

        if (isDoDefaultToast) {
            ToastUtil.showError(errorResult.getMessage());

        }
    }

    private void doOtherException(Throwable e) {
        Log.e("OkHttp", "doOtherException: error = " + e.getMessage());
        if (isDoDefaultLogic) {

        }

        if (isDoDefaultToast) {
            toastError(e);
        }
    }

    private void toastError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            ToastUtil.showError(R.string.ybcp_Network_Connection_timeout);
        } else if (e instanceof ConnectException) {
            ToastUtil.showError(R.string.ybcp_no_network);
        } else if (e instanceof UnknownHostException) {
            ToastUtil.showError(R.string.ybcp_no_address_associated_with_hostname);
        } else if (e instanceof java.lang.IllegalStateException) {
            ToastUtil.showError(R.string.ybcp_cant_parse_illegalStateException);
        } else {
            if (!TextUtils.isEmpty(e.getMessage()) && e.getMessage().contains("java.lang.IllegalStateException")) {
                return;
            }
            ToastUtil.showError(e.getMessage());
        }
    }
}
