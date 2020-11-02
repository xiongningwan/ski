package com.ski.box.mvp.contract.my;


import com.ski.box.bean.user.Bank;
import com.ski.box.bean.user.BankCard;
import com.yb.core.base.BaseContract;

import java.util.List;

public interface BankCardAddContract {
    interface View extends BaseContract.BaseView {
        void onSuccessResult(List<Bank>list);
        void onFailResult(String s);
        void onBindSuccessResult();
        void onBindFailResult(String s);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getBankList();
        void bindBank(String bankCode, String bankName, String bankSubName, String cardName, String cardNo, String cardNoSec);
    }
}
