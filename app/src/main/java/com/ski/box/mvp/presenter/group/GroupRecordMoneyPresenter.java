package com.ski.box.mvp.presenter.group;

import android.content.Context;

import com.google.gson.Gson;
import com.ski.box.bean.FrontTradeTypesBean;
import com.ski.box.bean.group.GroupMoneyData;
import com.ski.box.bean.record.RecordMoney;
import com.ski.box.bean.record.RecordMoneyRequest;
import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.RecordMoneyContract;
import com.ski.box.mvp.contract.group.GroupRecordMoneyContract;
import com.ski.box.mvp.remote.GroupModel;
import com.ski.box.mvp.remote.RecordModel;
import com.ski.box.mvp.remote.imodel.IGroupModel;
import com.ski.box.mvp.remote.imodel.IRecordModel;
import com.yb.core.base.RxPresenter;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class GroupRecordMoneyPresenter extends RxPresenter<GroupRecordMoneyContract.View> implements GroupRecordMoneyContract.Presenter {
    private IRecordModel mRecordModel;
    private IGroupModel mGroupModel;

    public GroupRecordMoneyPresenter(Context context) {
        super(context);
        mRecordModel = new RecordModel();
        mGroupModel = new GroupModel();
    }


    @Override
    public void getFrontTradeTypes() {
        Disposable disposable = mRecordModel.getFrontTradeTypes(new Consumer<List<FrontTradeTypesBean>>() {
            @Override
            public void accept(List<FrontTradeTypesBean> frontTradeTypesBeans) throws Exception {
                mView.onMoneyTypeSuccess(frontTradeTypesBeans);

            }
        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
               // mView.onError(throwable);
            }
        });
        addDisposable(disposable);
    }


    @Override
    public void getTeamTransList(String startDate, String endDate, String status, String memberAccount, int pageSize, int pageNum) {
        Disposable disposable = mGroupModel.getTeamTransList(new Consumer<GroupMoneyData>() {
            @Override
            public void accept(GroupMoneyData o) throws Exception {
                mView.onSuccessful(o);
            }
        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onFailResult(throwable.getMessage());
            }
        }, startDate, endDate, status, memberAccount, pageSize, pageNum);
        addDisposable(disposable);
    }

}
