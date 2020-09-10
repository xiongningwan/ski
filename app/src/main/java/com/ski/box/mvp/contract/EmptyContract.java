package com.ski.box.mvp.contract;


import com.yb.core.base.BaseContract;

public interface EmptyContract {
    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.BasePresenter<View> {

    }
}
