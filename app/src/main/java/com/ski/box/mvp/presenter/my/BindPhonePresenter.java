package com.ski.box.mvp.presenter.my;

import android.content.Context;

import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.my.BindPhoneContract;
import com.ski.box.mvp.remote.SysModel;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.mvp.remote.imodel.ISysModel;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.yb.core.base.RxPresenter;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class BindPhonePresenter extends RxPresenter<BindPhoneContract.View> implements BindPhoneContract.Presenter {
    private IUserModel mUserModel;
    private ISysModel mSysModel;

    public BindPhonePresenter(Context context) {
        super(context);
        mSysModel = new SysModel();
        mUserModel = new UserModel();
    }


    @Override
    public void sendCode(String mobile) {
        Disposable disposable = mSysModel.sendCode(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                mView.onCodeSendSuccessResult("短信发送成功");
            }
        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
              //  mView.onFailResult(throwable.getMessage());
            }
        }, mobile);
        addDisposable(disposable);
    }

    @Override
    public void bindPhone(String phone, String code) {
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
        }, phone, code);
        addDisposable(disposable);
    }
}


