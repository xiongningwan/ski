package com.ski.box.mvp.contract;


import com.yb.core.base.BaseContract;

public interface UpdateAliasContract {
    interface View extends BaseContract.BaseView {
        void onSuccessResult();
        void onFailResult(String s);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void updateAlias(String alias);
    }
}
