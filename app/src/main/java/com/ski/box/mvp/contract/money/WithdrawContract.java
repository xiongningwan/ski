package com.ski.box.mvp.contract.money;


import com.ski.box.bean.WithdrawRange;
import com.ski.box.bean.user.BankCard;
import com.ski.box.bean.user.MemberInfo;
import com.ski.box.bean.user.UserInfo;
import com.yb.core.base.BaseContract;

import java.util.List;

public interface WithdrawContract {
    interface View extends BaseContract.BaseView {
        void onUserInfoResult(UserInfo userInfo);
        void onUserInfoFailResult(String s);
        void onRangeResult(WithdrawRange withdrawRange);
        void onTypeResult(List<BankCard> list);
        void onSuccessResult();
        void onFailResult(String s);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getBalance();
        void getWithdrawRange();
        void getBankCardList();
        void withdraw(long cardId, String amt, String fundPassword);
    }
}
