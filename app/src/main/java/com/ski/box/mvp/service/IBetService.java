package com.ski.box.mvp.service;


import com.yb.core.net.HttpResult;
import com.yb.core.net.HttpResult_new;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * 投注接口
 */
public interface IBetService {

    /**
     * 标准盘投注
     */
    @POST(UrlConfig.URL_NORMAL_BET)
    @FormUrlEncoded
    Single<HttpResult<String>> standardBet(@FieldMap Map<String, Object> map);

    /**
     * 双面盘投注
     */
    @POST(UrlConfig.URL_DOUBLE_BET)
    @FormUrlEncoded
    Single<HttpResult<String>> doubleBet(@FieldMap Map<String, Object> map);

    /**
     * 标准盘 追号投注
     */
    @POST(UrlConfig.URL_BET_NORMAL_TRACE)
    @FormUrlEncoded
    Single<HttpResult<String>> standardTraceBet(@FieldMap Map<String, Object> map);

    /**
     * 双面盘 追号投注
     */
    @POST(UrlConfig.URL_BET_DOUBLE_TRACE)
    @FormUrlEncoded
    Single<HttpResult<String>> doubleTraceBet(@FieldMap Map<String, Object> map);




    /**
     * 是否出发单挑
     */
    @POST(UrlConfig.URL_IS_DAN_TIAO)
    @FormUrlEncoded
    Single<HttpResult<String>> isDanTiao(@FieldMap Map<String, Object> map);

}
