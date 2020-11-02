package com.ski.box.mvp.presenter.my;

import android.content.Context;

import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.my.BindEmailContract;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.yb.core.base.RxPresenter;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class BindEmailPresenter extends RxPresenter<BindEmailContract.View> implements BindEmailContract.Presenter {
    private IUserModel mUserModel;

    public BindEmailPresenter(Context context) {
        super(context);
        mUserModel = new UserModel();
    }



    @Override
    public void bindEmail(String email) {
        Disposable disposable = mUserModel.bindEmail(new Consumer<Object>() {
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
        }, email);
        addDisposable(disposable);
    }
}


