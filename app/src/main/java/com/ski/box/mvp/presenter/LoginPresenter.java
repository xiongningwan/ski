package com.ski.box.mvp.presenter;

import android.content.Context;

import com.ski.box.bean.user.LoginInfo;
import com.ski.box.bean.user.User;
import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.LoginContract;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.yb.core.base.RxPresenter;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {
    private IUserModel mUserModel;

    public LoginPresenter(Context context) {
        super(context);
        mUserModel = new UserModel();
    }


    @Override
    public void doLogin(String memberAccount, String password) {
        Disposable disposable = mUserModel.login(new Consumer<LoginInfo>() {
            @Override
            public void accept(LoginInfo loginInfo) {
                mView.onLoginSuccessResult(loginInfo);
            }
        },new CusConsumer(){
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onLoginFailResult("");
            }
        },memberAccount, password);
        addDisposable(disposable);
    }

}


