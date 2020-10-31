package com.ski.box.mvp.service;


import com.ski.box.bean.money.DepositBack;
import com.ski.box.bean.money.MoneyProgressData;
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
    // 提现
    @POST(IUrlMoney.PAY_WITHDRAW)
    @FormUrlEncoded
    Single<HttpResult<Object>> withdraw(@Field("memberCardNo") String memberCardNo, @Field("amt") String amt, @Field("fundPassword") String fundPassword);
    // 资金进度
    @GET(IUrlMoney.PAY_DW_ORDER_LIST)
    Single<HttpResult<MoneyProgressData>> dwOrderList(@Query("startDate") String startDate,
                                                      @Query("endDate") String endDate,
                                                      @Query("dwType") String dwType,
                                                      @Query("pageSize") int pageSize,
                                                      @Query("pageNum") int pageNum);


}
