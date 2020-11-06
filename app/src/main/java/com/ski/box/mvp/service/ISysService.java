package com.ski.box.mvp.service;


import com.ski.box.bean.NoticeData;
import com.ski.box.bean.SystemConfig;
import com.yb.core.net.HttpResult;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;



/**
 * 系统配置接口
 */
public interface ISysService {
    /**
     * 登录
     */
    @GET(UrlConfig.URL_SYSTEM_CONFIG)
    Single<HttpResult<SystemConfig>> getSysConfig();

    /**
     * 更新
     */
    @GET(UrlConfig.UPDATE_URL)
    Single<HttpResult<Object>> checkVersion(@Query("version") String version,
                                            @Query("mobType") String mobType);
    /**
     * 公告
     */
    @GET(UrlConfig.NOTICE_LIST)
    Single<HttpResult<NoticeData>> getNoticeList(@Query("pageNum") int pageNum,
                                                 @Query("pageSize") int pageSize);
}
