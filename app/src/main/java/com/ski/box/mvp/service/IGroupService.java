package com.ski.box.mvp.service;


import com.ski.box.bean.Balance;
import com.ski.box.bean.SelfProfileEntity;
import com.ski.box.bean.group.InviteData;
import com.ski.box.bean.group.InviteUrl;
import com.ski.box.bean.group.RebateScope;
import com.ski.box.bean.user.Bank;
import com.ski.box.bean.user.BankCard;
import com.ski.box.bean.user.LoginInfo;
import com.ski.box.bean.user.MemberInfo;
import com.ski.box.bean.user.UserInfo;
import com.yb.core.net.HttpResult;

import java.util.List;

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
public interface IGroupService {
    // 代理返奖率范围
    @GET(UrlConfig.URL_GROUP_REBATE_SCOPE)
    Single<HttpResult<RebateScope>> getRebateScope();

    // 创建下级代理
    @POST(UrlConfig.URL_GROUP_AGENT_CREATE)
    @FormUrlEncoded
    Single<HttpResult<Object>> agentCreate(
            @Field("memberAccount") String memberAccount,
            @Field("password") String password,
            @Field("prizeGroup") int prizeGroup);

    // 推广链接
    @GET(UrlConfig.URL_GROUP_INVITE_URL)
    Single<HttpResult<InviteData>> getInviteUrlList(
            @Query("pageSize") int pageSize,
            @Query("pageNum") int pageNum);

    // 创建推广链接
    @POST(UrlConfig.URL_GROUP_INVITE_CREATE)
    @FormUrlEncoded
    Single<HttpResult<Object>> inviteCreate(
            @Field("inviteWord") String inviteWord,
            @Field("memberRebate") String memberRebate);
}
