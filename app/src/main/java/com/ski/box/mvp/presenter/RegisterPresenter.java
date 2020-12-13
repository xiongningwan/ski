package com.ski.box.mvp.presenter;

import android.content.Context;

import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.RegisterContract;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.ski.box.utils.SignUtil;
import com.yb.core.base.RxPresenter;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class RegisterPresenter extends RxPresenter<RegisterContract.View> implements RegisterContract.Presenter {
    private IUserModel mUserModel;

    public RegisterPresenter(Context context) {
        super(context);
        mUserModel = new UserModel();
    }





    @Override
    public void doRegister(String memberAccount, String password, String memberDomain) {
        Disposable disposable = mUserModel.register(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                mView.onRegisterSuccessResult(o);
            }
        }, new CusConsumer(){
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onRegisterFailResult("");
            }
        }, memberAccount, password, memberDomain);
        addDisposable(disposable);
    }

    @Override
    public void doRegisterCode(String memberAccount, String password, String reqCode) {
        Disposable disposable = mUserModel.registerCode(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                mView.onRegisterSuccessResult(o);
            }
        }, new CusConsumer(){
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onRegisterFailResult("");
            }
        }, memberAccount, password,  reqCode);
        addDisposable(disposable);
    }
}


