package com.ski.box.mvp.contract.my;


import com.yb.core.base.BaseContract;

public interface BindEmailContract {
    interface View extends BaseContract.BaseView {
        void onSuccessResult();
        void onFailResult(String s);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void bindEmail(String email);
    }
}
