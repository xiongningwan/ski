package com.ski.box.mvp.presenter.my;

import android.content.Context;

import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.my.UpdateLoginPwdContract;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.yb.core.base.RxPresenter;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class UpdateLoginPwdPresenter extends RxPresenter<UpdateLoginPwdContract.View> implements UpdateLoginPwdContract.Presenter {
    private IUserModel mUserModel;

    public UpdateLoginPwdPresenter(Context context) {
        super(context);
        mUserModel = new UserModel();
    }


    @Override
    public void updateLoginPwd(String loginPwd, String loginPwdNew) {
        Disposable disposable = mUserModel.updateLoginPwd(new Consumer<Object>() {
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
        }, loginPwd, loginPwdNew);
        addDisposable(disposable);
    }
}


