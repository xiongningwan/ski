package com.ski.box.mvp.presenter;

import android.content.Context;

import com.ski.box.bean.DataCenter;
import com.ski.box.bean.user.LoginInfo;
import com.ski.box.bean.user.MemberInfo;
import com.ski.box.bean.user.UserInfo;
import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.LoginContract;
import com.ski.box.mvp.contract.PersonalContract;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.yb.core.base.BaseConsumer;
import com.yb.core.base.RxPresenter;
import com.yb.core.utils.StringUtils;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class PersonalPresenter extends RxPresenter<PersonalContract.View> implements PersonalContract.Presenter {
    private IUserModel mUserModel;

    public PersonalPresenter(Context context) {
        super(context);
        mUserModel = new UserModel();
    }


    @Override
    public void getMemberInfo() {
        Disposable disposable = mUserModel.getMemberInfo(new Consumer<MemberInfo>() {
            @Override
            public void accept(MemberInfo bean) throws Exception {
                if (bean != null) {
                    DataCenter.getInstance().setUser(bean);
                    mView.onMemberInfoResult(bean);
                }
            }
        }, new BaseConsumer(false, false) {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onMemberInfoFailResult(throwable.getMessage());
            }
        });
        addDisposable(disposable);
    }

    @Override
    public void logout() {
        Disposable disposable = mUserModel.logout(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                    mView.onLogoutResult(o);
            }
        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onLogoutFailResult(throwable.getMessage());
            }
        });
        addDisposable(disposable);
    }
}


