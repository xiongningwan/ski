package com.ski.box.mvp.remote.imodel;


import com.ski.box.bean.PersonProfileEntity;
import com.ski.box.bean.SelfProfileEntity;
import com.ski.box.exception.CusConsumer;
import com.yb.core.base.BaseConsumer;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import retrofit2.http.Field;

public interface IUserModel {

    Disposable register(Consumer s,
                        CusConsumer e,
                        String memberAccount,
                        String password);

    /**
     * 登录
     */
    Disposable login(Consumer s,
                     CusConsumer e,
                     String memberAccount,
                     String password);

    Disposable logout(Consumer s,
                      CusConsumer e);

    Disposable memberUpdateAlias(Consumer s,
                                 CusConsumer e,
                                 String alias);

    Disposable updateLoginPwd(Consumer s,
                              CusConsumer e,
                              String loginPwd,
                              String loginPwdNew);

    Disposable updateFundPwd(Consumer s,
                              CusConsumer e,
                              String fundPwd,
                              String fundPwdNew);

    Disposable bindPhone(Consumer s,
                              CusConsumer e,
                              String mobile);

    Disposable getMemberDetails(Consumer s, Consumer e);

    Disposable getMemberInfo(Consumer s, Consumer e);

    Disposable getSelfProfile(Consumer s);

    Disposable saveSelfProfile(Consumer s, PersonProfileEntity entity);

    Disposable saveLongDragonLimit(Consumer s, Consumer e, String longDragonLimit, String longDragonLimitMax);

    Disposable saveLongDragonTicketList(Consumer s, Consumer e, List<SelfProfileEntity.LongDragonTicketsBean> longDragonTicketList);

    Disposable getMemberBalanceByMerchant(Consumer s);

    Disposable gettransferOnMemberAndMerchant(Consumer s);

    Disposable getMerchantTransferType(Consumer s);

    Disposable getMerchantNoticeList(Consumer s, int pageSize, int num);
}
