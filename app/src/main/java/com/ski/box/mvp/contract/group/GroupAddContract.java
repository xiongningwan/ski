package com.ski.box.mvp.contract.group;


import com.ski.box.bean.group.RebateKV;
import com.ski.box.bean.group.RebateScope;
import com.ski.box.bean.user.Bank;
import com.yb.core.base.BaseContract;

import java.util.List;

public interface GroupAddContract {
    interface View extends BaseContract.BaseView {
        void onSuccessResult(List<RebateKV> list);
        void onFailResult(String s);
        void onAddSuccessResult();
        void onAddFailResult(String s);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getRebateScope();
        void agentCreate(String memberAccount, String password, int prizeGroup);
    }
}
