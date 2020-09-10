package com.ski.box.mvp.remote;


import com.ski.box.bean.SystemConfig;
import com.ski.box.mvp.remote.imodel.ISysModel;
import com.ski.box.mvp.service.ISysService;
import com.yb.core.base.BaseModel;
import com.yb.core.net.RetrofitHelper;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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
}
