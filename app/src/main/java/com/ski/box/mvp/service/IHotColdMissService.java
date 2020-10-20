package com.ski.box.mvp.service;

import com.ski.box.bean.LongDragonPushInfoEntity;
import com.ski.box.bean.MkBettingRecordHistoryEntity;
import com.ski.box.bean.NoticeConfigInfoEntity;
import com.yb.core.net.HttpResult;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 冷热遗漏相关
 */
public interface IHotColdMissService {

    /**
     * 标准盘冷热遗漏
     * ticketId：彩种ID
     */
    @GET(UrlConfig.URL_HOT_COLD_MISS)
    Single<HttpResult<Object>> getStandardHotColdData(
            @Query("ticketId") String ticketId,
            @Query("labelType") String labelType);

    /**
     * 双面盘冷热遗漏
     * ticketId：彩种ID
     * labelOrLocationId：
     * planCount ： 奖期数
     */
    @GET(UrlConfig.URL_DOUBLE_HOT_COLD_MISS)
    Single<HttpResult<Object>> getDoubleTicketHotColdData(
            @Query("ticketId") String ticketId);

    /**
     * 提醒配置信息
     * surface:1：遗漏提醒 2：长龙提醒
     */
    @POST(UrlConfig.URL_LONG_DRAGON_CONFIG_INFO)
    @FormUrlEncoded
    Single<HttpResult<List<NoticeConfigInfoEntity>>> getLongDragonConfigInfo(
            @Field("groupMode") String groupMode);

    /**
     * 提醒推送信息
     * groupMode:1：遗漏提醒 2：长龙提醒
     * threshold：过滤值，欲出率或长龙期数  星系
     * ticketIdList：彩种ID
     */
    @POST(UrlConfig.URL_LONG_DRAGON_PUSH_INFO)
    @FormUrlEncoded
    Single<HttpResult<List<LongDragonPushInfoEntity>>> getLongDragonPushInfo(@FieldMap Map<String, Object> map);
//    Single<HttpResult<List<LongDragonPushInfoEntity>>> getLongDragonPushInfo(
//            @Field("groupMode") String groupMode,
//            @Field("threshold") String threshold,
//            @FieldMap("ticketIdList") String ticketIdList);


}
