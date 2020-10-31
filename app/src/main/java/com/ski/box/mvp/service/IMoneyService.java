package com.ski.box.mvp.service;


import com.ski.box.bean.money.DepositBack;
import com.ski.box.bean.money.PayType;
import com.yb.core.net.HttpResult;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * 用户接口
 */
public interface IMoneyService {
    // 支付方式
    @GET(IUrlMoney.PAY_TYPE)
    Single<HttpResult<List<PayType>>> getPayType(@Query("currency") int currency);
    // 充值
    @POST(IUrlMoney.PAY_DEPOSIT)
    @FormUrlEncoded
    Single<HttpResult<DepositBack>> deposit(@Field("channelCode") int channelCode, @Field("amt") String amt);


}
