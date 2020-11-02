package com.ski.box.mvp.contract.my;


import com.yb.core.base.BaseContract;

public interface UpdateFundPwdContract {
    interface View extends BaseContract.BaseView {
        void onSuccessResult();
        void onFailResult(String s);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void updateFundPwd(String fundPwd, String fundPwdNew);
    }
}
