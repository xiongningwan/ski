package com.ski.box.mvp.service;

/**
 * time: 2019/10/10
 * author: Dominica
 * describe:
 **/
public final class UrlConfig {

    //--------------------merchant-start--------------------

    /**
     * 注册
     */
    public static final String URL_REGISTER = "promen/member/register/direct";

    /**
     * 登录
     */
    public static final String URL_LOG_IN = "promen/member/login";
    /**
     * 登出
     */
    public static final String URL_LOG_OUT = "promen/member/logout";
    /**
     * 修改用户信息
     */
    public static final String URL_MEM_UPDATE = "promen/member/update/base";
    /**
     * 更新登录密码
     */
    public static final String URL_MEM_UPDATE_LOGIN_PWD = "promen/member/update/loginPwd";
    /**
     * 更新资金密码
     */
    public static final String URL_MEM_UPDATE_FUND_PWD = "promen/member/update/fundPwd";
    /**
     * 绑定手机号
     */
    public static final String URL_MEM_UPDATE_PHONE = "promen/member/update/phone";
    /**
     * 绑定邮箱
     */
    public static final String URL_MEM_UPDATE_EMAIL = "promen/member/update/email";
    /**
     * 银行卡列表
     */
    public static final String URL_MEM_BANK_CARDS = "promen/card/query";
    /**
     * 银行列表
     */
    public static final String URL_MEM_BANK_LIST = "promen/card/platform/bankList";
    /**
     * 银行列表
     */
    public static final String URL_MEM_BANK_BIND = "promen/card/bind";


    //----------------------boracay-start-------------------

    /**
     * 每日盈亏列表
     * POST
     */

    public static final String URL_DAILY_PROFIT = "coron/ticketmod/dailyProfit";

    /**
     * 用户信息--用户余额
     * POST
     */
    public static final String URL_USER_INFO = "botanic/member/front/userInfo";

    /**
     * 资金记录->账变类型
     * POST
     */
    public static final String URL_MONEY_RECORD_TRADE_TYPES = "botanic/member/front/getFrontTradeTypes";

    /**
     * 资金记录列表
     * POST
     */
    public static final String URL_MONEY_RECORD = "botanic/member/front/queryBalanceRecords";

    /**
     * 遗漏彩系,彩种,玩法保存接口
     * POST
     */
    public static final String URL_OMIT_SAVE = "boracay/member/front/omit/save";
    /**
     * 遗漏删除玩法接口
     * POST
     */
    public static final String URL_OMIT_DELETE = "boracay/member/front/omit/delete";
    /**
     * 读取用户设置
     */
    public static final String URL_USER_READ = "botanic/member/front/userConf";
    /**
     * 写入用户设置
     */
    public static final String URL_USER_WRITE = "botanic/member/front/save";

    /**
     * 双面盘，长龙提醒自定义彩种保存
     */
    public static final String URL_USER_WRITE_DOUBLE = "boracay/member/front/omit/save/2";

    /**
     * 获取钱包模式：1，无钱包，2，有钱包
     */
    public static final String URL_MERCHANT_TYPE = "boracay/api/nofreemember/getMerchantTransferType";

    //----------------------boracay-end---------------------

    //----------------------wallet-start--------------------

    /**
     * 查询余额（商户钱包）
     * POST
     */
    public static final String URL_MEMBER_BALANCE_BY_MERCHANT = "wallet/front/getMemberBalanceByMerchant";

    /**
     * 转账接口（平台钱包—商户钱包）
     * POST
     */
    public static final String URL_TRANSFER_ON_MEMBER_AND_MERCHANT = "wallet/front/transferOnMemberAndMerchant";

    //-----------------------wallet-end----------------------

    //-----------------------coron-start---------------------

    /**
     * 投注
     * POST
     */
    public static final String URL_NORMAL_BET = "coron/order/normal/create";

    /**
     * 彩种历史开奖号码
     * POST
     */
    public static final String URL_TICKET_SOURCE_RESULT_LIST = "cashew/api/ticketSourceResult/ticketSourceResultList.json";

    /**
     * 投注记录列表
     * POST
     */
    public static final String URL_ORDER_LIST = "cashew/ticketmod/orderList";

    /**
     * 投注记录详情
     * POST
     */
    public static final String URL_ORDER_DETAIL = "coron/ticketmod/orderDetail";

    /**
     * 投注撤单
     * POST
     */
    public static final String URL_GENERAL_BET_CANCLE = "cashew/ticketmod/memberBetCancel";

    /**
     * 双面盘投注
     * POST
     */
    public static final String URL_DOUBLE_BET = "cashew/order/double/create";

    /**
     * 追号 可追奖期列表
     * POST
     */
    public static final String URL_TRACE_ISSUES = "coron/ticketmod/traceIssues";

    /**
     * 追号 追号记录列表
     * POST
     */
    public static final String URL_TRACE_LIST = "coron/ticketmod/traceList";

    /**
     * 追号 追号记录详情
     * POST
     */
    public static final String URL_TRACE_DETAIL = "coron/ticketmod/traceDetail";

    /**
     * 追号 追号单是否可撤 根据单号撤单
     * POST
     */
    public static final String URL_CHASE_PLAN_STATUS = "coron/ticketmod/chase/canCancelByChaseId";

    /**
     * 追号 追号单某期是否可撤 根据选择的日期撤单
     * POST
     */
    public static final String URL_CHASE_CANCEL_STATUS = "coron/ticketmod/chase/canCancelByPlanNo";

    /**
     * 追号 撤单
     * POST
     */
    public static final String URL_CHASE_BET_CANCLE = "coron/ticketmod/chase/memberBetCancel";

    /**
     * 标准盘追号投注
     * POST
     */
    public static final String URL_BET_NORMAL_TRACE = "coron/order/normal/chase";

    /**
     * 双面盘追号投注
     * POST
     */
    public static final String URL_BET_DOUBLE_TRACE = "coron/order/double/chase";

    /**
     * 获取近期投注记录
     * <p>
     * GET
     */
    public static final String URL_ORDERS_TOP = "cashew/ticketmod/ordersTop";

    /**
     * 标椎盘冷热遗漏
     * GET
     */
//    public static final String URL_HOT_COLD_MISS = "coron/ticketmod/hotCold";
    public static final String URL_HOT_COLD_MISS = "coron/ticketmod/v2/queryHotMiss";

    /**
     * 双面盘冷热遗漏
     * GET
     */
    public static final String URL_DOUBLE_HOT_COLD_MISS = "coron/ticketmod/doubleHotColdMiss";

    /**
     * 提醒配置信息
     * 1：遗漏提醒
     * 2：长龙提醒
     * GET
     */
    public static final String URL_LONG_DRAGON_CONFIG_INFO = "coron/ticketmod/remindCfg";

    /**
     * 提醒推送信息
     * 1：遗漏提醒
     * 2：长龙提醒
     * GET
     */
    public static final String URL_LONG_DRAGON_PUSH_INFO = "cashew/ticketmod/remindTop";

    /**
     * 服务器时间
     * GET
     */
    public static final String URL_GET_SERVICE_TIME = "https://date.bobcp.vip/date";

    /**
     * Keno 分页获取订单列表
     */
    public static final String URL_KENO_ORDER_LIST = "/coron/ticketmod/kenoOrderList";

    /**
     * Keno 获取特色详情
     */
    public static final String URL_KENO_ORDER_DETAIL = "coron/ticketmod/kenoOrderDetail";

    /**
     * 走势图
     */
    public static final String URL_TICKECT_TREND_CHART = "coron/trendGraph/chart";

    /**
     * 新历史开奖数据
     */
    public static final String URL_TICKECT_TREND_CHART_DATA = "coron/trendGraph/chart/history";

    /**
     * 通过投注项得到关联的投注信息
     */
    public static final String URL_REMIND_DETAIL_BET = "coron/ticketmod/remindDetailBet";

    /**
     * 当前销售奖期
     */
    public static final String URL_CURRENT_SALE_ISSUE = "cashew/ticketmod/currentSaleIssue.json";

    /**
     * 彩系彩种列表
     * POST
     */
    public static final String URL_TICKET_TYPE_LIST = "cashew/ticketmod/tickettypelist.json";

    /**
     * 获取彩种设置信息
     * POST
     */
    public static final String URL_TICKET_CFG = "coron/ticketmod/ticketcfg.json";

    /**
     * 热门彩种
     * GET
     */
    public static final String URL_HOT_TICKET = "coron/homepage/topBanner";

    /**
     * 最近常玩
     * GET
     */
    public static final String URL_RECENT_TICKET = "coron/member/front/recent/play/query";

    /**
     * 玩法种类列表
     * POST
     */
    public static final String URL_TICKET_PLAY_LIST = "cashew/ticketmod/ticketplaylist.json";

    //-----------------------coron-end---------------------


    /**
     * 系统配置信息：系统时间地址、 websocket地址、静态资源地址
     */
    public static final String URL_SYSTEM_CONFIG = "cashew/system/config/query";

    /**
     * 更新
     */
    public static final  String UPDATE_URL = "boracay/api/mobTerminal/checkVersion";
    /**
     * 公告
     */
    public static final  String NOTICE_LIST = "promen/notice/list";
    public static final  String SYS_SEND_CODE = "promen/member/mobile/sendCode";

    //-----------------------玩法说明-start----------------

    /**
     * 玩法说明的基础路径
     */
    public static final String PLAY_NAME_INTRODUCE_FILE_BASE_PATH = "https://test-json.emkcp.com:8006";


    /**
     * 十一选五
     */
    public static final String PLAY_NAME_INTRODUCE_SYXW = "app/SYXW_app.json";

    /**
     * 快三
     */
    public static final String PLAY_NAME_INTRODUCE_KS = "app/K3_app.json";

    /**
     * 快乐彩
     */
    public static final String PLAY_NAME_INTRODUCE_KLC = "app/KLC_app.json";
    /**
     * 六合彩
     */
    public static final String PLAY_NAME_INTRODUCE_LHC = "app/LHC_app.json";

    /**
     * 六合彩 当前生肖
     */
    public static final String PLAY_NAME_INTRODUCE_LHC_ZODIAC = "coron/api/ticketSourceResult/getElementResultsList.json";

    /**
     * 时时彩
     */
    public static final String PLAY_NAME_INTRODUCE_SSC = "app/SSC_app.json";

    /**
     * P3P5
     */
    public static final String PLAY_NAME_INTRODUCE_P3P5 = "app/P3P5_app.json";
    /**
     * PK10
     */
    public static final String PLAY_NAME_INTRODUCE_PK10 = "app/PK10_app.json";

    /**
     * SD
     */
    public static final String PLAY_NAME_INTRODUCE_SD = "app/SD_app.json";

    //-----------------------玩法说明-end------------------

    /**
     * 公告列表
     * GET
     */
    public static final String URL_NOTICE_LIST = "boracay/bulletin/merchant/list";
    /**
     * 标注盘最小限制金额
     */
    public static final String URL_MIN_AMOUNT = "coron/merSystem/config/getNormalOrderMmixQuota";

    /**
     * 等待倒计时
     */
    public static final  String  URL_WAIT_TIME="cashew/ticketmod/currentSaleIssue.json";

    /**
     * 是否出发单挑
     */

    public static final  String URL_IS_DAN_TIAO="coron/order/normal/solo";

    /**
     * 单期盈利上限接口
     */

    public static final  String URL_WIN_MONEY_UP="boracay/member/front/profit/list";

    // 用户详细信息
    public static final  String URL_USER_MEMBER_INFO ="promen/member/memberInfo";

    // 代理返奖率范围
    public static final  String URL_GROUP_REBATE_SCOPE ="promen/agent/rebateScope";
    // 创建下级代理
    public static final  String URL_GROUP_AGENT_CREATE ="promen/member/agent/create";
    // 推广链接
    public static final  String URL_GROUP_INVITE_URL ="promen/agent/inviteUrl";
    // 创建推广链接
    public static final  String URL_GROUP_INVITE_CREATE ="promen/agent/invite/create";
    // 推广链接删除
    public static final  String URL_GROUP_INVITE_DELETE ="promen/agent/invite/";
    // 团队管理列表
    public static final  String URL_GROUP_TEAM_MEMBER_LIST ="promen/team/memberList";
    // 团队投注记录
    public static final  String URL_GROUP_TEAM_ORDER_LIST ="promen/team/orderList";
    // 团队账变记录
    public static final  String URL_GROUP_TEAM_TRANS_LIST ="promen/team/transList";
    // 活动优惠列表
    public static final  String URL_ACTIVITY_LIST ="promen/activity/list";


}
