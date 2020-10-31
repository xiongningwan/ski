package com.ski.box.mvp.presenter.money;

import android.content.Context;

import com.ski.box.bean.money.DepositBack;
import com.ski.box.bean.money.PayType;
import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.money.RechargeContract;
import com.ski.box.mvp.contract.money.RechargeDetailContract;
import com.ski.box.mvp.remote.MoneyModel;
import com.ski.box.mvp.remote.imodel.IMoneyModel;
import com.yb.core.base.RxPresenter;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class RechargeDetailPresenter extends RxPresenter<RechargeDetailContract.View> implements RechargeDetailContract.Presenter {
//    private IMoneyModel mMoneyModel;

    public RechargeDetailPresenter(Context context) {
        super(context);
       // mMoneyModel = new MoneyModel();
    }

}


