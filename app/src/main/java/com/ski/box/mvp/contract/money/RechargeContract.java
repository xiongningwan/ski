package com.ski.box.mvp.contract.money;


import com.ski.box.bean.money.DepositBack;
import com.ski.box.bean.money.PayType;
import com.ski.box.bean.user.Bank;
import com.ski.box.bean.user.UserInfo;
import com.yb.core.base.BaseContract;

import java.util.List;

public interface RechargeContract {
    interface View extends BaseContract.BaseView {
        void onUserInfoResult(UserInfo userInfo);
        void onUserInfoFailResult(String s);
        void onTypeResult(List<PayType> list);
        void onDepositSuccessResult(DepositBack bean);
        void onDepositFailResult(String s);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getBalance();
        void getPayType(int currency);
        void deposit(int channelCode, String cardName, String amt);
    }
}
