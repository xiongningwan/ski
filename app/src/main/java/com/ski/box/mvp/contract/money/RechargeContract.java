package com.ski.box.mvp.contract.money;


import com.ski.box.bean.money.DepositBack;
import com.ski.box.bean.money.PayType;
import com.ski.box.bean.user.Bank;
import com.yb.core.base.BaseContract;

import java.util.List;

public interface RechargeContract {
    interface View extends BaseContract.BaseView {
        void onTypeResult(List<PayType> list);
        void onDepositSuccessResult(DepositBack bean);
        void onDepositFailResult(String s);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getPayType(int currency);
        void deposit(int channelCode, String amt);
    }
}
