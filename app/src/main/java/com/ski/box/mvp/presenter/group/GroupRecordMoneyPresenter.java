package com.ski.box.mvp.presenter.group;

import android.content.Context;

import com.google.gson.Gson;
import com.ski.box.bean.FrontTradeTypesBean;
import com.ski.box.bean.record.RecordMoney;
import com.ski.box.bean.record.RecordMoneyRequest;
import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.RecordMoneyContract;
import com.ski.box.mvp.contract.group.GroupRecordMoneyContract;
import com.ski.box.mvp.remote.RecordModel;
import com.yb.core.base.RxPresenter;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class GroupRecordMoneyPresenter extends RxPresenter<GroupRecordMoneyContract.View> implements GroupRecordMoneyContract.Presenter {
    RecordModel mRecordModel;
    public GroupRecordMoneyPresenter(Context context) {
        super(context);
         mRecordModel = new RecordModel();
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
                mView.onError(throwable);
            }
        });
        addDisposable(disposable);
    }

    @Override
    public void getMoneyRecordData(RecordMoneyRequest mkMoneyRecordRequest) {
        Disposable disposable = mRecordModel.getMoneyRecordData(mkMoneyRecordRequest, new Consumer<RecordMoney>() {
            @Override
            public void accept(RecordMoney frontTradeTypesBeans) throws Exception {
                String s = new Gson().toJson(frontTradeTypesBeans);
                mView.onSuccessful(frontTradeTypesBeans);

            }
        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onError(throwable);
            }
        });
        addDisposable(disposable);
    }

}
