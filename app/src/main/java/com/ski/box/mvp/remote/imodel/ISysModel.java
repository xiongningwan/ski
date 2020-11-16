package com.ski.box.mvp.remote.imodel;


import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import retrofit2.http.Query;

public interface ISysModel {
    Disposable getSysConfig(Consumer s, Consumer e);

    Disposable checkVersion(Consumer s, Consumer e, String version, String mobType);

    Disposable getNoticeList(Consumer s,  Consumer e, int pageNum, int pageSize);

    Disposable sendCode(Consumer s,  Consumer e, String mobile);
}
