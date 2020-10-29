package com.ski.box.mvp.remote;


import com.ski.box.bean.group.RebateScope;
import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.remote.imodel.IGroupModel;
import com.ski.box.mvp.service.IGroupService;
import com.yb.core.base.BaseModel;
import com.yb.core.net.RetrofitHelper;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class GroupModel extends BaseModel implements IGroupModel {

    @Override
    public Disposable getRebateScope(Consumer s,CusConsumer e) {
        Single<RebateScope> single = RetrofitHelper
                .getService(IGroupService.class)
                .getRebateScope()
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable agentCreate(Consumer s, CusConsumer e, String memberAccount, String password, int prizeGroup) {
        Single<Object> single = RetrofitHelper
                .getService(IGroupService.class)
                .agentCreate(memberAccount, password, prizeGroup)
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }
}
