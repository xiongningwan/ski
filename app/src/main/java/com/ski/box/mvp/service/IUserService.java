package com.ski.box.mvp.service;


import com.ski.box.bean.Balance;
import com.ski.box.bean.MemberDetailEntity;
import com.ski.box.bean.SelfProfileEntity;
import com.ski.box.bean.user.LoginInfo;
import com.ski.box.bean.user.MemberInfo;
import com.ski.box.bean.user.User;
import com.ski.box.bean.user.UserInfo;
import com.yb.core.net.HttpResult;

import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.yb.core.ConstantValue.CONTENT_TYPE_JSON;


/**
 * 用户接口
 */
public interface IUserService {
    /**
     * 注册
     */

    @POST(UrlConfig.URL_REGISTER)
    @FormUrlEncoded
    Single<HttpResult<Object>> register(
            @Field("memberAccount") String memberAccount,
            @Field("password") String password);

    /**
     * 登录
     */
    @POST(UrlConfig.URL_LOG_IN)
    @FormUrlEncoded
    Single<HttpResult<LoginInfo>> login(
            @Field("memberAccount") String memberAccount,
            @Field("password") String password);
    /**
     * 登出
     */
    @GET(UrlConfig.URL_LOG_OUT)
    Single<HttpResult<Object>> logout();
    /**
     * 修改用户信息
     */
    @POST(UrlConfig.URL_MEM_UPDATE)
    @FormUrlEncoded
    Single<HttpResult<Object>> memberUpdateAlias(@Field("alias") String alias);
    /**
     * 更新登录密码
     */
    @POST(UrlConfig.URL_MEM_UPDATE_LOGIN_PWD)
    @FormUrlEncoded
    Single<HttpResult<Object>> updateLoginPwd(@Field("loginPwd") String loginPwd,@Field("loginPwdNew") String loginPwdNew);

    /**
     * 用户信息--用户余额
     *
     * @return
     */
    @GET(UrlConfig.URL_USER_INFO)
    Single<HttpResult<UserInfo>> getMemberDetails();

    @GET(UrlConfig.URL_USER_MEMBER_INFO)
    Single<HttpResult<MemberInfo>> getMemberInfo();

    /**
     * 读取用户设置
     *
     * @return
     */
    @GET(UrlConfig.URL_USER_READ)
    Single<HttpResult<SelfProfileEntity>> getSelfProfile();

    /**
     * 写入用户设置
     *
     * @param nextConfigId
     * @param countDownBeep
     * @param doubleBetConfirm
     * @param missRemind
     * @param quickConfirm
     * @param longDragonTicketList
     * @param winBeep
     * @param winModal
     * @param longDragonNumPrompt
     * @param longDragonLimit
     * @param chips
     * @param virtualKeyboard
     * @return
     */
    @POST(UrlConfig.URL_USER_WRITE)
    @FormUrlEncoded
    Single<HttpResult<Object>> saveSelfProfile(
            @Field("nextConfigId") String nextConfigId,
            @Field("countDownBeep") boolean countDownBeep,
            @Field("doubleBetConfirm") boolean doubleBetConfirm,
            @Field("missRemind") boolean missRemind,
            @Field("quickConfirm") boolean quickConfirm,
            @Field("longDragonTicketList") String longDragonTicketList,
            @Field("winBeep") boolean winBeep,
            @Field("winModal") boolean winModal,
            @Field("longDragonNumPrompt") boolean longDragonNumPrompt,
            @Field("longDragonLimit") String longDragonLimit,
            @Field("chips") String chips,
            @Field("virtualKeyboard") boolean virtualKeyboard);


    @POST(UrlConfig.URL_USER_WRITE)
    @FormUrlEncoded
    Single<HttpResult<Object>> saveLongDragonLimit(
            @Field("longDragonLimit") String longDragonLimit,
            @Field("longDragonLimitMax") String longDragonLimitMax);

    @Headers({CONTENT_TYPE_JSON})
    @POST(UrlConfig.URL_USER_WRITE_DOUBLE)
    Single<HttpResult<Object>> saveLongDragonTicketList(@Body RequestBody re);

    /**
     * 查询余额（商户钱包）
     *
     * @return
     */
    @POST(UrlConfig.URL_MEMBER_BALANCE_BY_MERCHANT)
    Single<HttpResult<Balance>> getMemberBalanceByMerchant();

    /**
     * 转账接口（平台钱包—商户钱包）
     *
     * @return
     */
    @POST(UrlConfig.URL_TRANSFER_ON_MEMBER_AND_MERCHANT)
    Single<HttpResult<Object>> gettransferOnMemberAndMerchant();

    /**
     * 获取钱包类型：1，无钱包，2，有钱包
     *
     * @return
     */
    @GET(UrlConfig.URL_MERCHANT_TYPE)
    Single<HttpResult<Object>> getMerchantTransferType();


    @GET(UrlConfig.URL_NOTICE_LIST)
    Single<HttpResult<Object>>  getMerchantNoticeList(
            @Query("pageSize") int pageSize,
            @Query("pageNum") int pageNum);

}
