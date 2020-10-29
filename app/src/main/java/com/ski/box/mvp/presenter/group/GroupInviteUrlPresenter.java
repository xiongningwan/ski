package com.ski.box.mvp.presenter.group;

import android.content.Context;

import com.ski.box.bean.group.InviteData;
import com.ski.box.bean.group.InviteUrl;
import com.ski.box.bean.group.RebateKV;
import com.ski.box.bean.group.RebateScope;
import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.group.GroupAddContract;
import com.ski.box.mvp.contract.group.GroupInviteUrlContract;
import com.ski.box.mvp.remote.GroupModel;
import com.ski.box.mvp.remote.imodel.IGroupModel;
import com.yb.core.base.RxPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class GroupInviteUrlPresenter extends RxPresenter<GroupInviteUrlContract.View> implements GroupInviteUrlContract.Presenter {
    private IGroupModel mGroupModel;

    public GroupInviteUrlPresenter(Context context) {
        super(context);
        mGroupModel = new GroupModel();
    }


    @Override
    public void getInviteUrlList(int pageSize, int pageNum) {
        Disposable disposable = mGroupModel.getInviteUrlList(new Consumer<InviteData>() {
            @Override
            public void accept(InviteData o) throws Exception {
                mView.onSuccessResult(o);
            }
        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onFailResult(throwable.getMessage());
            }
        }, pageSize,  pageNum);
        addDisposable(disposable);
    }

}


