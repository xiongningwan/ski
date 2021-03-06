package com.ski.box.mvp.contract;


import com.ski.box.bean.user.LoginInfo;
import com.ski.box.bean.user.User;
import com.yb.core.base.BaseContract;

public interface LoginContract {
    interface View extends BaseContract.BaseView {
        void onLoginSuccessResult(LoginInfo loginInfo);
        void onLoginFailResult(String str);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void doLogin(String memberAccount, String password);
    }
}
