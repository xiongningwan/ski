package com.ski.box.mvp.service;


import com.ski.box.bean.DateBean;
import com.ski.box.bean.LotteryNumBean;
import com.ski.box.bean.TicketLotteryTimeBean;
import com.ski.box.bean.lottery.LotterySer;
import com.ski.box.bean.lottery.RemoteLotteryPlay;
import com.yb.core.net.HttpResult;

import java.util.List;

import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.yb.core.ConstantValue.CONTENT_TYPE_JSON;

/**
 * 彩票信息接口
 */
public interface ILotteryService {

    /**
     * 获取所有彩种
     */
    @GET(UrlConfig.URL_TICKET_TYPE_LIST)
    Single<HttpResult<List<LotterySer>>> getTicketTypelist();

    /**
     * 玩法种类列表
     * get
     */
    @GET(UrlConfig.URL_TICKET_PLAY_LIST)
    @Headers({CONTENT_TYPE_JSON})
    Single<HttpResult<List<RemoteLotteryPlay>>> getTicketPlayType(
            @Query("ticketId") int ticketId,
            @Query("mode") int mode);

    /**
     * 获取头部倒计时 和最新期号
     * get
     */
    @GET(UrlConfig.URL_CURRENT_SALE_ISSUE)
    Single<HttpResult<List<TicketLotteryTimeBean>>> getTicketHeadInfo(
            @Query("ticketIds") String ticketId);

    @GET(UrlConfig.URL_CURRENT_SALE_ISSUE)
    Single<Response<HttpResult<List<TicketLotteryTimeBean>>>> getTicketHeadInfo_responseData(@Query("ticketIds") String ticketId);

    /**
     * 获取后台时间 用于倒计时
     * get
     */
    @GET(UrlConfig.URL_GET_SERVICE_TIME)
    Single<Response<DateBean>> getServiceData();

    /**
     * 获取 开奖号码列表
     */
    @GET(UrlConfig.URL_TICKET_SOURCE_RESULT_LIST)
    Single<HttpResult<List<LotteryNumBean>>> getLotteryNum(@Query("ticketId") String ticketId, @Query("num") String num);
}
