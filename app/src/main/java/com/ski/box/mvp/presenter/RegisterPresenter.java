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
    public void doRegister(String merchantAccount, String merchantId, String memberAccount, String password, String tester) {
        Disposable disposable = mUserModel.register(new Consumer<String>() {
            @Override
            public void accept(String str) {
                mView.onRegisterSuccessResult(str);
            }
        }, new CusConsumer(){
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onRegisterFailResult("");
            }
        },merchantAccount, merchantId, memberAccount, password, tester);
        addDisposable(disposable);
    }

    @Override
    public void doRegister(String memberAccount, String password, String tester) {
        Disposable disposable = mUserModel.register(new Consumer<String>() {
            @Override
            public void accept(String str) {
                mView.onRegisterSuccessResult(str);
            }
        }, new CusConsumer(){
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onRegisterFailResult("");
            }
        }, memberAccount, password, tester);
        addDisposable(disposable);
    }
}


