package com.ski.box.mvp.presenter.my;

import android.content.Context;

import com.ski.box.bean.user.Bank;
import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.my.BankCardAddContract;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.yb.core.base.RxPresenter;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class BankCardAddPresenter extends RxPresenter<BankCardAddContract.View> implements BankCardAddContract.Presenter {
    private IUserModel mUserModel;

    public BankCardAddPresenter(Context context) {
        super(context);
        mUserModel = new UserModel();
    }


    @Override
    public void getBankList() {
        Disposable disposable = mUserModel.getBankList(new Consumer<List<Bank>>() {
            @Override
            public void accept(List<Bank> list) throws Exception {
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
    public void bindBank(String bankCode, String bankName, String bankSubName, String cardName, String cardNo, String cardNoSec) {
        Disposable disposable = mUserModel.bindBank(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                mView.onBindSuccessResult();
            }
        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onBindFailResult(throwable.getMessage());
            }
        }, bankCode, bankName, bankSubName, cardName, cardNo, cardNoSec);
        addDisposable(disposable);
    }
}


