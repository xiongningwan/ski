package com.ski.box.mvp.presenter.my;

import android.content.Context;

import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.my.UpdateHeadContract;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.yb.core.base.RxPresenter;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class UpdateHeadPresenter extends RxPresenter<UpdateHeadContract.View> implements UpdateHeadContract.Presenter {
    private IUserModel mUserModel;

    public UpdateHeadPresenter(Context context) {
        super(context);
        mUserModel = new UserModel();
    }



    @Override
    public void memberUpdateProfile(String profile) {
        Disposable disposable = mUserModel.memberUpdateProfile(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                mView.onSuccessResult();
            }
        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
            }
        }, profile);
        addDisposable(disposable);
    }
}


