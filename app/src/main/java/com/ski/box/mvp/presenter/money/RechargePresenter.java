package com.ski.box.mvp.presenter.money;

import android.content.Context;

import com.ski.box.bean.money.DepositBack;
import com.ski.box.bean.money.PayType;
import com.ski.box.bean.user.UserInfo;
import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.money.RechargeContract;
import com.ski.box.mvp.remote.MoneyModel;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.mvp.remote.imodel.IMoneyModel;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.yb.core.base.BaseConsumer;
import com.yb.core.base.RxPresenter;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class RechargePresenter extends RxPresenter<RechargeContract.View> implements RechargeContract.Presenter {
    private IMoneyModel mMoneyModel;
    private IUserModel mUserModel;

    public RechargePresenter(Context context) {
        super(context);
        mMoneyModel = new MoneyModel();
        mUserModel = new UserModel();
    }

    @Override
    public void getBalance() {
        Disposable disposable = mUserModel.getMemberDetails(new Consumer<UserInfo>() {
            @Override
            public void accept(UserInfo bean) throws Exception {
                mView.onUserInfoResult(bean);
            }
        }, new BaseConsumer(false, false) {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onUserInfoFailResult(throwable.getMessage());
            }
        });
        addDisposable(disposable);
    }



    @Override
    public void getPayType(int currency) {
        Disposable disposable = mMoneyModel.getPayType(new Consumer<List<PayType>>() {
            @Override
            public void accept(List<PayType> list) throws Exception {
                mView.onTypeResult(list);
            }
        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
               // mView.onFailResult(throwable.getMessage());
            }
        }, currency);
        addDisposable(disposable);
    }

    @Override
    public void deposit(int channelCode, String amt) {
        Disposable disposable = mMoneyModel.deposit(new Consumer<DepositBack>() {
            @Override
            public void accept(DepositBack depositBack) throws Exception {
                mView.onDepositSuccessResult(depositBack);
            }
        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onDepositFailResult(throwable.getMessage());
            }
        }, channelCode, amt);
        addDisposable(disposable);
    }
}


