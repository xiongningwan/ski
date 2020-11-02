package com.ski.box.mvp.contract.my;


import com.yb.core.base.BaseContract;

public interface UpdateLoginPwdContract {
    interface View extends BaseContract.BaseView {
        void onSuccessResult();
        void onFailResult(String s);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void updateLoginPwd(String loginPwd, String loginPwdNew);
    }
}
