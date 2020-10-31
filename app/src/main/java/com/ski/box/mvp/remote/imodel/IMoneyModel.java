package com.ski.box.mvp.remote.imodel;


import com.ski.box.exception.CusConsumer;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public interface IMoneyModel {
    Disposable getPayType(Consumer s,
                           CusConsumer e,
                          int currency);

    Disposable deposit(Consumer s, CusConsumer e, int channelCode, String amt);
}
