package com.ski.box.mvp.service;

import com.ski.box.bean.chase.ChaseCancelStatusEntity;
import com.ski.box.bean.chase.ChaseDetailBean;
import com.ski.box.bean.chase.ChaseListEntity;
import com.ski.box.bean.FrontTradeTypesBean;
import com.ski.box.bean.chase.ChasePlanStatusEntity;
import com.ski.box.bean.record.RecordBet;
import com.ski.box.bean.record.RecordBetDetail;
import com.ski.box.bean.record.RecordMoney;
import com.ski.box.bean.record.RecordRecent;
import com.ski.box.bean.record.RecordProfit;
import com.yb.core.net.HttpResult;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRecordService {

    /**
     * 获取近期投注记录
     * memberId 暂时传空
     * status 0
     */
    @POST(UrlConfig.URL_ORDERS_TOP)
    @FormUrlEncoded
    Single<HttpResult<RecordRecent>> getBettingRecordTop(
            @Field("status") String status);

    /**
     * 每日盈亏列表
     */
    @POST(UrlConfig.URL_DAILY_PROFIT)
    @FormUrlEncoded
    Single<HttpResult<RecordProfit>> getDailyProfitLossList(
            @FieldMap HashMap<String, String> hashMap);

    /**
     * 资金记录->帐变类型
     */
    @POST(UrlConfig.URL_MONEY_RECORD_TRADE_TYPES)
    Single<HttpResult<List<FrontTradeTypesBean>>> getFrontTradeTypes();

    /**
     * 资金记录列表
     * tradeType：//帐变类型 1资金转入，2资金转出，3投注，4投注返点，5派奖，6撤销派奖，7撤销返点，8撤单返款，9追号扣款，10当期追号返款，11撤销追号，12单期总利润超限扣款，13单期单挑利润超限扣款, 14单期总利润超限返款，16撤单返款
     */
    @GET(UrlConfig.URL_MONEY_RECORD)
    Single<HttpResult<RecordMoney>> getMoneyRecordList(
            @Query("queryDate") String queryDate,
            @Query("tradeType") String tradeType,
            @Query("pageSize") String pageSize,
            @Query("pageNum") String pageNum);


    /**
     * 投注记录列表
     * status：1-待开奖,2-未中奖,3-已中奖,4-挂起,5-已结算
     */
    @GET(UrlConfig.URL_ORDER_LIST)
    Single<HttpResult<RecordBet>> getBetRecordList(
            @Query("ticketId") String ticketId,
            @Query("status") String status,
            @Query("isLow") String isLow,
            @Query("startDate") String startDate,
            @Query("endDate") String endDate,
            @Query("pageNum") String pageNum,
            @Query("pageSize") String pageSize);

    /**
     * 投注记录详情
     * isTrace:是否是追号单 1 代表追号单 0 为注单
     */
    @POST(UrlConfig.URL_ORDER_DETAIL)
    @FormUrlEncoded
    Single<HttpResult<RecordBetDetail>> getBettingRecordDetail(
            @Field("orderId") String orderId);

    /**
     * 投注撤单
     */
    @POST(UrlConfig.URL_GENERAL_BET_CANCLE)
    @FormUrlEncoded
    Single<HttpResult<Object>> betCancle(
            @Field("orderId") String orderId);

    /**
     * 追号记录列表
     * status：追号状态1:进行中;2:已完成;3:已撤单
     */
    @POST(UrlConfig.URL_TRACE_LIST)
    @FormUrlEncoded
    Single<HttpResult<ChaseListEntity>> getChaselist(
            @FieldMap HashMap<String, String> hashMap);

    /**
     * 追号记录详情
     */
    @POST(UrlConfig.URL_TRACE_DETAIL)
    @FormUrlEncoded
    Single<HttpResult<ChaseDetailBean>> getChaseNumDetail(
            @Field("chaseId") String chaseId);

    /**
     * 追号单是否可撤 根据单号撤单
     */
    @POST(UrlConfig.URL_CHASE_PLAN_STATUS)
    @FormUrlEncoded
    Single<HttpResult<ChasePlanStatusEntity>> cancelChaseOrderById(
            @Field("chaseId") String chaseId);

    /**
     * 追号单某期是否可撤 根据选择的日期撤单
     */
    @POST(UrlConfig.URL_CHASE_CANCEL_STATUS)
    @FormUrlEncoded
    Single<HttpResult<ChaseCancelStatusEntity>> cancelChaseOrderByData(
            @Field("chaseId") String chaseId,
            @Field("planNo") String planNo);

    /**
     * 追号:  这是是撤单
     */
    @POST(UrlConfig.URL_CHASE_BET_CANCLE)
    @FormUrlEncoded
    Single<HttpResult<Object>> chaseNumOrderCancle(
            @FieldMap HashMap<String, String> hashMap);

}
