package com.ski.box.mvp.presenter.my;

import android.content.Context;

import com.ski.box.bean.DataCenter;
import com.ski.box.bean.user.MemberInfo;
import com.ski.box.mvp.contract.my.PersonalInfoContract;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.yb.core.base.BaseConsumer;
import com.yb.core.base.RxPresenter;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class PersonalInfoPresenter extends RxPresenter<PersonalInfoContract.View> implements PersonalInfoContract.Presenter {
    private IUserModel mUserModel;

    public PersonalInfoPresenter(Context context) {
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

}


