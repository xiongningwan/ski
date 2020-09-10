package com.ski.box.mvp.contract;


import android.app.ProgressDialog;

import com.ski.box.view.activity.LoginActivity;
import com.yb.core.base.BaseContract;

public interface LoginContract {
    interface View extends BaseContract.BaseView {
        void onLoginResult(String str);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void doLogin(String environment,
                     String merchantId,
                     String account,
                     String password,
                     int loginType,
                     String timestamp);
        void doLogin2(ProgressDialog mLoading, String environment,
                      String merchantId,
                      String account,
                      String password,
                      int loginType,
                      String timestamp);
    }
}
