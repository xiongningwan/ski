package com.ski.box.mvp.contract.money;


import com.ski.box.bean.user.BankCard;
import com.yb.core.base.BaseContract;

import java.util.List;

public interface WithdrawContract {
    interface View extends BaseContract.BaseView {
        void onTypeResult(List<BankCard> list);
        void onSuccessResult();
        void onFailResult(String s);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getBankCardList();
        void withdraw(String memberCardNo, String amt, String fundPassword);
    }
}
