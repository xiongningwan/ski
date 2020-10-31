package com.ski.box.mvp.remote;


import com.ski.box.bean.money.DepositBack;
import com.ski.box.bean.money.PayType;
import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.remote.imodel.IMoneyModel;
import com.ski.box.mvp.service.IMoneyService;
import com.yb.core.base.BaseModel;
import com.yb.core.net.RetrofitHelper;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MoneyModel extends BaseModel implements IMoneyModel {



    @Override
    public Disposable getPayType(Consumer s, CusConsumer e, int currency) {
        Single<List<PayType>> single = RetrofitHelper
                .getService(IMoneyService.class)
                .getPayType(currency)
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable deposit(Consumer s, CusConsumer e, int channelCode, String amt) {
        Single<DepositBack> single = RetrofitHelper
                .getService(IMoneyService.class)
                .deposit(channelCode, amt)
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

}
