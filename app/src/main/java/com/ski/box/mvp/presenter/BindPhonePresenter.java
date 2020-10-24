package com.ski.box.mvp.presenter;

import android.content.Context;

import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.BindPhoneContract;
import com.ski.box.mvp.contract.UpdateAliasContract;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.yb.core.base.RxPresenter;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class BindPhonePresenter extends RxPresenter<BindPhoneContract.View> implements BindPhoneContract.Presenter {
    private IUserModel mUserModel;

    public BindPhonePresenter(Context context) {
        super(context);
        mUserModel = new UserModel();
    }


    @Override
    public void bindPhone(String phone) {
        Disposable disposable = mUserModel.bindPhone(new Consumer<Object>() {
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
        }, phone);
        addDisposable(disposable);
    }
}


