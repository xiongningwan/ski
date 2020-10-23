package com.ski.box.mvp.presenter;

import android.content.Context;

import com.ski.box.bean.DataCenter;
import com.ski.box.bean.user.MemberInfo;
import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.PersonalInfoContract;
import com.ski.box.mvp.contract.UpdateAliasContract;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.yb.core.base.BaseConsumer;
import com.yb.core.base.RxPresenter;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class UpdateAliasPresenter extends RxPresenter<UpdateAliasContract.View> implements UpdateAliasContract.Presenter {
    private IUserModel mUserModel;

    public UpdateAliasPresenter(Context context) {
        super(context);
        mUserModel = new UserModel();
    }


    @Override
    public void updateAlias(String alias) {
        Disposable disposable = mUserModel.memberUpdateAlias(new Consumer<Object>() {
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
        }, alias);
        addDisposable(disposable);
    }
}


