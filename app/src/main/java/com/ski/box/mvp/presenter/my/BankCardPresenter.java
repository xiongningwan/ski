package com.ski.box.mvp.presenter.my;

import android.content.Context;

import com.ski.box.bean.user.BankCard;
import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.my.BankCardContract;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.yb.core.base.RxPresenter;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class BankCardPresenter extends RxPresenter<BankCardContract.View> implements BankCardContract.Presenter {
    private IUserModel mUserModel;

    public BankCardPresenter(Context context) {
        super(context);
        mUserModel = new UserModel();
    }

    @Override
    public void getBankCardList() {
        Disposable disposable = mUserModel.getBankCardList(new Consumer<List<BankCard>>() {
            @Override
            public void accept(List<BankCard> list) throws Exception {
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
}


