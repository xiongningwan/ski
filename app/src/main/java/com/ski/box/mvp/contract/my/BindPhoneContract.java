package com.ski.box.mvp.contract.my;


import com.yb.core.base.BaseContract;

public interface BindPhoneContract {
    interface View extends BaseContract.BaseView {
        void onCodeSendSuccessResult(String s);
        void onSuccessResult();
        void onFailResult(String s);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void sendCode(String mobile);
        void bindPhone(String phone, String code);
    }
}
