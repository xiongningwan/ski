package com.ski.box.mvp.presenter.group;

import android.content.Context;

import com.ski.box.bean.group.RebateKV;
import com.ski.box.bean.group.RebateScope;
import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.group.GroupAddContract;
import com.ski.box.mvp.remote.GroupModel;
import com.ski.box.mvp.remote.imodel.IGroupModel;
import com.yb.core.base.RxPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class GroupInviteUrlAddPresenter extends RxPresenter<GroupAddContract.View> implements GroupAddContract.Presenter {
    private IGroupModel mGroupModel;

    public GroupInviteUrlAddPresenter(Context context) {
        super(context);
        mGroupModel = new GroupModel();
    }

    @Override
    public void getRebateScope() {
        Disposable disposable = mGroupModel.getRebateScope(new Consumer<RebateScope>() {
            @Override
            public void accept(RebateScope rebateScope) throws Exception {
                List<RebateKV> list = new ArrayList<>();
                for(int i = rebateScope.getMaxRebate(); i >= rebateScope.getBaseRebate(); i--) {
                    RebateKV rebateKV = new RebateKV();
                    rebateKV.setRebate(i);
                    float f = (i - rebateScope.getBaseRebate())*100f / 2000;
                    String percent = String.format("%.2f", f) + "%";
                    rebateKV.setPercent(percent);
                    list.add(rebateKV);
                }
                mView.onSuccessResult(list);
            }
        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onFailResult(throwable.getMessage());
            }
        });
        addDisposable(disposable);
    }

    @Override
    public void agentCreate(String memberAccount, String password, int prizeGroup) {
        Disposable disposable = mGroupModel.agentCreate(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                mView.onAddSuccessResult();
            }
        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onAddFailResult(throwable.getMessage());
            }
        }, memberAccount, password, prizeGroup);
        addDisposable(disposable);
    }
}


