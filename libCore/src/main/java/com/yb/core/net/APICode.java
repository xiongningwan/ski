package com.yb.core.net;

public interface APICode {
    /**
     * 投注限红 报错
     */
    int BET_XIANHONG_ERROR = 10069;
    /*余额不足*/
    int INSUFFICIENT_BALANCE_ERROR = 10020;
    int KOU_KUAN__ERROR = 10006;


    //网关异常错误码
    /**
     * token不合法
     */
    int MKErrorCodes_1u = -1;
    /**
     * token为空
     */
    int MKErrorCodes_2u = -2;
    /**
     * sign不合法
     */
    int MKErrorCodes_3u = -3;
    /**
     * 没有权限
     */
    int MKErrorCodes_4u = -4;
    /**
     * 消息系统服务暂不可用
     */
    int MKErrorCodes_5u = -5;
    /**
     * 商户系统服务暂不可用
     */
    int MKErrorCodes_6u = -6;
    /**
     * 会员系统系统服务暂不可用
     */
    int MKErrorCodes_7u = -7;
    /**
     * 总控系统服务暂不可用
     */
    int MKErrorCodes_8u = -8;
    /**
     * 会员系统系统服务暂不可用
     */
    int MKErrorCodes_9u = -9;
    /**
     * 投注系统系统服务暂不可用
     */
    int MKErrorCodes_10u = -10;
    /**
     * 系统服务暂不可用
     */
    int MKErrorCodes_11u = -11;

    /**
     * 转账失败
     */
    int MKErrorCodes_500 = 500;
    /**
     * 操作失败
     */
    int MKErrorCodes_2001 = 2001;
    /**
     * 类型不能为空
     */
    int MKErrorCodes_10001 = 10001;

    /**
     * 注单号不能为空
     */
    int MKErrorCodes_10002 = 10002;
    /**
     * 修改人不能为空
     */
    int MKErrorCodes_10003 = 10003;
    /**
     * 取消订单出错
     */
    int MKErrorCodes_10004 = 10004;
    /**
     * 用户自己撤单只支持单条操作
     */
    int MKErrorCodes_10005 = 10005;
    /**
     * 调用钱包操作失败
     */
    int MKErrorCodes_10006 = 10006;
    /**
     * 订单未查询到
     */
    int MKErrorCodes_10007 = 10007;
    /**
     * 订单状态不合法
     */
    int MKErrorCodes_10008 = 10008;
    /**
     * 不可撤单，已过允许撤单时间
     */
    int MKErrorCodes_10009 = 10009;
    /**
     * 撤单失败
     */
    int MKErrorCodes_10010 = 10010;
    /**
     * 批量撤单失败
     */
    int MKErrorCodes_10011 = 10011;
    /**
     * 撤单类型异常
     */
    int MKErrorCodes_10012 = 10012;
    /**
     * 奖期信息不存在
     */
    int MKErrorCodes_10013 = 10013;
    /**
     * 获取奖期信息出错
     */
    int MKErrorCodes_10014 = 10014;
    /**
     * 存在多期都在销售
     */
    int MKErrorCodes_10015 = 10015;
    /**
     * 彩种不存在或已禁用
     */
    int MKErrorCodes_10016 = 10016;
    /**
     * 投注模式不合法
     */
    int MKErrorCodes_10017 = 10017;
    /**
     * 注数不合法
     */
    int MKErrorCodes_10018 = 10018;
    /**
     * 总金额不合法
     */
    int MKErrorCodes_10019 = 10019;
    /**
     * 余额不足
     */
    int MKErrorCodes_10020 = 10020;
    /**
     * 玩法已停用
     */
    int MKErrorCodes_10021 = 10021;
    /**
     * 投注号码异常
     */
    int MKErrorCodes_10022 = 10022;
    /**
     * 奖期已停售
     */
    int MKErrorCodes_10023 = 10023;
    /**
     * 追号单不存在
     */
    int MKErrorCodes_10024 = 10024;
    /**
     * 注单不存在
     */
    int MKErrorCodes_10025 = 10025;
    /**
     * 投注模式或注数不合法
     */
    int MKErrorCodes_10026 = 10026;
    /**
     * 您的注单投注金额小于玩法投注区间启动金额，无法进行投注。
     */
    int MKErrorCodes_10027 = 10027;
    /**
     * 您累计的注单投注金额大于当期玩法下投注项区间封顶金额，无法进行投注。
     */
    int MKErrorCodes_10028 = 10028;
    /**
     * 双面玩法投注失败，请联系客服咨询。
     */
    int MKErrorCodes_10029 = 10029;
    int MKErrorCodes_10030 = 10030;
    int MKErrorCodes_10031 = 10031;
    int MKErrorCodes_10032 = 10032;
    int MKErrorCodes_10033 = 10033;
    int MKErrorCodes_10034 = 10034;
    int MKErrorCodes_10035 = 10035;
    int MKErrorCodes_10036 = 10036;
    int MKErrorCodes_10037 = 10037;
    int MKErrorCodes_10038 = 10038;
    int MKErrorCodes_10039 = 10039;
    int MKErrorCodes_10040 = 10040;
    int MKErrorCodes_10041 = 10041;
    int MKErrorCodes_10042 = 10042;
    int MKErrorCodes_10043 = 10043;
    int MKErrorCodes_10044 = 10044;
    int MKErrorCodes_10045 = 10045;
    int MKErrorCodes_10046 = 10046;
    int MKErrorCodes_10047 = 10047;
    int MKErrorCodes_10048 = 10048;
    int MKErrorCodes_10049 = 10049;
    int MKErrorCodes_10050 = 10050;
    int MKErrorCodes_10051 = 10051;
    int MKErrorCodes_10052 = 10052;
    int MKErrorCodes_10053 = 10053;
    int MKErrorCodes_10054 = 10054;
    int MKErrorCodes_10055 = 10055;
    int MKErrorCodes_10056 = 10056;
    int MKErrorCodes_10057 = 10057;
    int MKErrorCodes_10058 = 10058;
    int MKErrorCodes_10059 = 10059;
    int MKErrorCodes_10060 = 10060;
    int MKErrorCodes_10061 = 10061;
    int MKErrorCodes_10062 = 10062;
    int MKErrorCodes_10063 = 10063;
    int MKErrorCodes_10064 = 10064;
    int MKErrorCodes_10065 = 10065;
    int MKErrorCodes_10066 = 10066;
    int MKErrorCodes_10067 = 10067;
    int MKErrorCodes_10068 = 10068;
    int MKErrorCodes_10069 = 10069;
    int MKErrorCodes_10070 = 10070;
    int MKErrorCodes_10071 = 10071;
    int MKErrorCodes_10072 = 10072;
    int MKErrorCodes_10073 = 10073;
    int MKErrorCodes_10074 = 10074;
    int MKErrorCodes_10075 = 10075;
    int MKErrorCodes_10076 = 10076;
    int MKErrorCodes_10077 = 10077;
    int MKErrorCodes_10078 = 10078;
    int MKErrorCodes_10079 = 10079;
    int MKErrorCodes_10080 = 10080;
    int MKErrorCodes_10081 = 10081;
    int MKErrorCodes_10082 = 10082;
    int MKErrorCodes_10083 = 10083;
    int MKErrorCodes_10084 = 10084;
    int MKErrorCodes_10085 = 10085;
    int MKErrorCodes_10086 = 10086;
    int MKErrorCodes_10087 = 10087;
    int MKErrorCodes_10088 = 10088;
    int MKErrorCodes_10089 = 10089;
    int MKErrorCodes_10090 = 10090;
    int MKErrorCodes_10091 = 10091;
    int MKErrorCodes_10092 = 10092;
    int MKErrorCodes_10093 = 10093;
    int MKErrorCodes_10094 = 10094;
    int MKErrorCodes_10095 = 10095;
    int MKErrorCodes_10096 = 10096;
    int MKErrorCodes_10097 = 10097;
    int MKErrorCodes_10098 = 10098;
    int MKErrorCodes_10099 = 10099;
    int MKErrorCodes_10100 = 10100;
    int MKErrorCodes_10101 = 10101;
    int MKErrorCodes_10102 = 10102;
}
