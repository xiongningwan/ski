package com.ski.box.mvp.contract.group;


import com.ski.box.bean.money.MoneyProgressData;
import com.yb.core.base.BaseContract;

public interface MoneyProgressContract {

    interface View extends BaseContract.BaseView {
        void onSuccessful(MoneyProgressData o);
        void onFailResult(String s);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void dwOrderList(String startDate, String endDate, String dwType, int pageSize, int pageNum);
    }

}
