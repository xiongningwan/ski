package com.ski.box.mvp.contract.group;


import com.ski.box.bean.group.InviteUrl;
import com.ski.box.bean.group.RebateKV;
import com.ski.box.exception.CusConsumer;
import com.yb.core.base.BaseContract;

import java.util.List;

import io.reactivex.functions.Consumer;

public interface GroupInviteUrlAddContract {
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
