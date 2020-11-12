package com.ski.box.mvp.remote.imodel;


import com.ski.box.exception.CusConsumer;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import retrofit2.http.Field;
import retrofit2.http.Query;

public interface IMoneyModel {
    Disposable getPayType(Consumer s,
                          CusConsumer e,
                          int currency);

    Disposable deposit(Consumer s, CusConsumer e, int channelCode, String cardName,String amt);

    Disposable getWithdrawRange(Consumer s, CusConsumer e);

    Disposable withdraw(Consumer s, CusConsumer e, long cardId, String amt, String fundPassword);

    Disposable dwOrderList(Consumer s, CusConsumer e,
                           String startDate,
                           String endDate,
                           String dwType,
                           int pageSize,
                           int pageNum);
}
