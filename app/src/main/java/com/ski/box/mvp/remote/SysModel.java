package com.ski.box.mvp.remote;


import com.ski.box.bean.NoticeData;
import com.ski.box.bean.SystemConfig;
import com.ski.box.mvp.remote.imodel.ISysModel;
import com.ski.box.mvp.service.ISysService;
import com.yb.core.base.BaseModel;
import com.yb.core.net.RetrofitHelper;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import retrofit2.http.Query;

public class SysModel extends BaseModel implements ISysModel {


    @Override
    public Disposable getSysConfig(Consumer s, Consumer e) {
        Single<SystemConfig> single = RetrofitHelper
                .getService(ISysService.class)
                .getSysConfig()
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable checkVersion(Consumer s, Consumer e, String version, String mobType) {
        Single<Object> single = RetrofitHelper
                .getService(ISysService.class)
                .checkVersion(version, mobType)
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable getNoticeList(Consumer s, Consumer e, int pageNum, int pageSize) {
        Single<NoticeData> single = RetrofitHelper
                .getService(ISysService.class)
                .getNoticeList(pageNum, pageSize)
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable sendCode(Consumer s, Consumer e, String mobile) {
        Single<Object> single = RetrofitHelper
                .getService(ISysService.class)
                .sendCode(mobile)
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

}
