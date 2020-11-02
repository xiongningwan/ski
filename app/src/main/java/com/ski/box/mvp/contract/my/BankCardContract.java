package com.ski.box.mvp.contract.my;


import com.ski.box.bean.user.Bank;
import com.ski.box.bean.user.BankCard;
import com.yb.core.base.BaseContract;

import java.util.List;

public interface BankCardContract {
    interface View extends BaseContract.BaseView {
        void onSuccessResult(List<BankCard> list);
        void onFailResult(String s);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getBankCardList();
    }
}
