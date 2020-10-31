package com.ski.box.mvp.presenter.money;

import android.content.Context;

import com.ski.box.bean.FrontTradeTypesBean;
import com.ski.box.bean.group.GroupMoneyData;
import com.ski.box.bean.money.MoneyProgressData;
import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.group.GroupRecordMoneyContract;
import com.ski.box.mvp.contract.group.MoneyProgressContract;
import com.ski.box.mvp.remote.GroupModel;
import com.ski.box.mvp.remote.MoneyModel;
import com.ski.box.mvp.remote.RecordModel;
import com.ski.box.mvp.remote.imodel.IGroupModel;
import com.ski.box.mvp.remote.imodel.IMoneyModel;
import com.ski.box.mvp.remote.imodel.IRecordModel;
import com.yb.core.base.RxPresenter;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MoneyProgressPresenter extends RxPresenter<MoneyProgressContract.View> implements MoneyProgressContract.Presenter {
    private IMoneyModel mMoneyModel;

    public MoneyProgressPresenter(Context context) {
        super(context);
        mMoneyModel = new MoneyModel();
    }

    @Override
    public void dwOrderList(String startDate, String endDate, String dwType, int pageSize, int pageNum) {
        Disposable disposable = mMoneyModel.dwOrderList(new Consumer<MoneyProgressData>() {
            @Override
            public void accept(MoneyProgressData o) throws Exception {
                mView.onSuccessful(o);
            }
        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onFailResult(throwable.getMessage());
            }
        }, startDate, endDate, dwType, pageSize, pageNum);
        addDisposable(disposable);
    }
}
