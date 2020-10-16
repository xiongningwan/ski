package com.ski.box.mvp.contract;


import android.app.ProgressDialog;

import com.yb.core.base.BaseContract;

public interface RegisterContract {
    interface View extends BaseContract.BaseView {
        void onRegisterSuccessResult(String str);

        void onRegisterFailResult(String str);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void doRegister(String merchantAccount,
                        String merchantId,
                        String memberAccount,
                        String password,
                        String tester);

        void doRegister(String memberAccount,
                        String password,
                        String tester);
    }
}
