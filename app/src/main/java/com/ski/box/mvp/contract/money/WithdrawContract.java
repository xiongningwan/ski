package com.ski.box.mvp.contract.money;


import com.ski.box.bean.user.BankCard;
import com.ski.box.bean.user.MemberInfo;
import com.ski.box.bean.user.UserInfo;
import com.yb.core.base.BaseContract;

import java.util.List;

public interface WithdrawContract {
    interface View extends BaseContract.BaseView {
        void onUserInfoResult(UserInfo userInfo);
        void onUserInfoFailResult(String s);
        void onTypeResult(List<BankCard> list);
        void onSuccessResult();
        void onFailResult(String s);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getBalance();
        void getBankCardList();
        void withdraw(String memberCardNo, String amt, String fundPassword);
    }
}
