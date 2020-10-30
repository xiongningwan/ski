package com.ski.box.mvp.presenter.group;

import android.content.Context;

import com.ski.box.bean.group.GroupMemberData;
import com.ski.box.bean.group.InviteData;
import com.ski.box.bean.group.RebateKV;
import com.ski.box.bean.group.RebateScope;
import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.group.GroupInviteUrlContract;
import com.ski.box.mvp.contract.group.GroupManageContract;
import com.ski.box.mvp.remote.GroupModel;
import com.ski.box.mvp.remote.imodel.IGroupModel;
import com.ski.box.utils.ActivityUtil;
import com.yb.core.base.RxPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class GroupManagePresenter extends RxPresenter<GroupManageContract.View> implements GroupManageContract.Presenter {
    private IGroupModel mGroupModel;

    public GroupManagePresenter(Context context) {
        super(context);
        mGroupModel = new GroupModel();
    }


    @Override
    public void getTeamMemberList(String memberAccount, int pageSize, int pageNum) {
        Disposable disposable = mGroupModel.getTeamMemberList(new Consumer<GroupMemberData>() {
            @Override
            public void accept(GroupMemberData o) throws Exception {
                mView.onSuccessResult(o);
            }
        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onFailResult(throwable.getMessage());
            }
        }, memberAccount, pageSize, pageNum);
        addDisposable(disposable);
    }
}


