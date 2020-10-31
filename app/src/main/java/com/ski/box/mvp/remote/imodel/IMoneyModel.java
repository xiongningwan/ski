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

    Disposable deposit(Consumer s, CusConsumer e, int channelCode, String amt);

    Disposable withdraw(Consumer s, CusConsumer e, String memberCardNo, String amt, String fundPassword);

    Disposable dwOrderList(Consumer s, CusConsumer e,
                           String startDate,
                           String endDate,
                           String dwType,
                           int pageSize,
                           int pageNum);
}
