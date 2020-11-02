package com.ski.box.mvp.contract.my;


import com.yb.core.base.BaseContract;

public interface UpdateHeadContract {
    interface View extends BaseContract.BaseView {
        void onSuccessResult();
        void onFailResult(String s);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void memberUpdateProfile(String profile);
    }
}
