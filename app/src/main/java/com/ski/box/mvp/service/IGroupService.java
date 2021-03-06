package com.ski.box.mvp.service;


import com.ski.box.bean.Balance;
import com.ski.box.bean.SelfProfileEntity;
import com.ski.box.bean.group.GroupBetData;
import com.ski.box.bean.group.GroupMemberData;
import com.ski.box.bean.group.GroupMoneyData;
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
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
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
            @Field("memberRebate") int memberRebate);

    // 推广链接删除
    @DELETE("promen/agent/invite/{inviteCode}")
    Single<HttpResult<Object>> inviteDelete(
            @Path("inviteCode") String inviteCode);

    // 团队管理列表
    @GET(UrlConfig.URL_GROUP_TEAM_MEMBER_LIST)
    Single<HttpResult<GroupMemberData>> getTeamMemberList(
            @Query("memberAccount") String memberAccount,
            @Query("pageSize") int pageSize,
            @Query("pageNum") int pageNum);

    // 团队投注记录
    @GET(UrlConfig.URL_GROUP_TEAM_ORDER_LIST)
    Single<HttpResult<GroupBetData>> getTeamOrderList(
            @Query("startDate") String startDate,
            @Query("endDate") String endDate,
            @Query("ticketId") String ticketId,
            @Query("status") String status,
            @Query("memberAccount") String memberAccount,
            @Query("pageSize") int pageSize,
            @Query("pageNum") int pageNum);

    // 团队账变记录
    @GET(UrlConfig.URL_GROUP_TEAM_TRANS_LIST)
    Single<HttpResult<GroupMoneyData>> getTeamTransList(
            @Query("queryDate") String queryDate,
            @Query("transType") String transType,
            @Query("memberAccount") String memberAccount,
            @Query("pageSize") int pageSize,
            @Query("pageNum") int pageNum);
}
