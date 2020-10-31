package com.ski.box.mvp.presenter.money;

import android.content.Context;

import com.ski.box.bean.money.DepositBack;
import com.ski.box.bean.money.PayType;
import com.ski.box.bean.user.BankCard;
import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.money.RechargeContract;
import com.ski.box.mvp.contract.money.WithdrawContract;
import com.ski.box.mvp.remote.MoneyModel;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.mvp.remote.imodel.IMoneyModel;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.yb.core.base.RxPresenter;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class WithdrawPresenter extends RxPresenter<WithdrawContract.View> implements WithdrawContract.Presenter {
    private IMoneyModel mMoneyModel;
    private IUserModel mUserModel;

    public WithdrawPresenter(Context context) {
        super(context);
        mMoneyModel = new MoneyModel();
        mUserModel = new UserModel();
    }


    @Override
    public void getBankCardList() {
        Disposable disposable = mUserModel.getBankCardList(new Consumer<List<BankCard>>() {
            @Override
            public void accept(List<BankCard> list) throws Exception {
                mView.onTypeResult(list);
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
    public void withdraw(String memberCardNo, String amt, String fundPassword) {
        Disposable disposable = mMoneyModel.withdraw(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                mView.onSuccessResult();
            }
        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onFailResult(throwable.getMessage());
            }
        }, memberCardNo, amt, fundPassword);
        addDisposable(disposable);
    }
}


