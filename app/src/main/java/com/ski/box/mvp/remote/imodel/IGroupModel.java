package com.ski.box.mvp.remote.imodel;


import com.ski.box.bean.PersonProfileEntity;
import com.ski.box.bean.SelfProfileEntity;
import com.ski.box.exception.CusConsumer;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import retrofit2.http.Field;
import retrofit2.http.Query;

public interface IGroupModel {
    Disposable getRebateScope(Consumer s, CusConsumer e);

    Disposable agentCreate(Consumer s,
                        CusConsumer e,
                        String memberAccount,
                        String password,
                        int prizeGroup);

    Disposable getInviteUrlList(Consumer s, CusConsumer e,int pageSize, int pageNum);

    Disposable inviteCreate(Consumer s,
                           CusConsumer e,
                           String inviteWord,
                            int memberRebate);
}
