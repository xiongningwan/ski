package com.ski.box.mvp.remote;


import com.ski.box.bean.WithdrawRange;
import com.ski.box.bean.money.DepositBack;
import com.ski.box.bean.money.MoneyProgressData;
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
import retrofit2.http.Field;

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
    public Disposable deposit(Consumer s, CusConsumer e, int channelCode, String cardName, String amt) {
        Single<DepositBack> single = RetrofitHelper
                .getService(IMoneyService.class)
                .deposit(channelCode, cardName, amt)
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable getWithdrawRange(Consumer s, CusConsumer e) {
        Single<WithdrawRange> single = RetrofitHelper
                .getService(IMoneyService.class)
                .getWithdrawRange()
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable withdraw(Consumer s, CusConsumer e, long cardId, String amt, String fundPassword) {
        Single<Object> single = RetrofitHelper
                .getService(IMoneyService.class)
                .withdraw(cardId, amt, fundPassword)
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable dwOrderList(Consumer s, CusConsumer e, String startDate, String endDate, String dwType, int pageSize, int pageNum) {
        Single<MoneyProgressData> single = RetrofitHelper
                .getService(IMoneyService.class)
                .dwOrderList(startDate, endDate, dwType, pageSize, pageNum)
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

}
