package com.ski.box;

/**
 * 常量
 */

public interface ConstantValue {
    String EVENT_TYPE_ALL_LOTTERY_UPDATE = "event_type_all_lottery_update";
    String EVENT_BET_ODDS_CHANGE = "event_bet_odds_change"; // 双面盘六合彩自选不中发送修改后的赔率
    String EVENT_TYPE_BET_NO_CHECK_UPDATE = "event_type_bet_no_check_update"; // 更新底部主单提醒
    String EVENT_TYPE_CHANGE_OPEN_RESULT_HISTORY = "event_type_change_open_result_history"; //打开关闭投注历史页
    String EVENT_TYPE_CLOSE_RESULT_HISTORY = "event_type_change_open_result_history"; //关闭投注历史页
    String EVENT_BET_TOP_CHOSE_LOTTERY_DIALOG_START_OPEN = "event_bet_top_chose_lottery_dialog_start_open"; // 顶部选择彩种弹窗打开
    String EVENT_BET_ACTIVITY_FINISH = "event_bet_bet_activity_finish"; // 关闭投注页面
    String EVENT_TYPE_BALANCE_UPDATE = "event_type_balance_update"; // 余额设置
    String EVENT_TYPE_BET_SUCCESS = "event_type_balance_update"; // 投注成功
    String EVENT_REQUEST_BET_SUBMIT = "event_request_bet_submit"; // 提交投注
    String EVENT_GET_UNSETTLE_LIST = "event_get_unsettle_list";// 推送未结算注单
    String EVENT_CLEAN_XUAN_HAO_PAN = "EVENT_CLEAN_XUAN_HAO_PAN"; //清空选号盘
    String EVENT_CLEAN_XUAN_HAO_PAN_SECTION = "EVENT_CLEAN_XUAN_HAO_PAN_SECTION"; // 清空部分选号盘
    String EVENT_TYPE_BET_RiGHT_NOW_CLICK = "event_type_bet_right_now_click";
    String EVENT_BET_BOTTOM_QUICK_DOUBLE_ET_CHANGE = "event_bet_bottom_quick_double_change";
    String EVENT_OPEN_RESULT_UPDATE = "event_open_result_update"; // 最新开奖结果已经更新
    String EVENT_UPDATE_RECENT_NO = "event_recent_no_update"; // 最近未结注单
    // 100 期历史结果获取成功
    String EVENT_RESULT_HISTORY_LIST_UPDATE = "event_result_history_list_update";
    // 路子图左侧菜单点击
    String EVENT_ROAD_LEFT_BUTTON_CLICK = "event_road_left_button_click";
    String EVENT_TYPE_QUICK_BET_CLICK = "event_type_quick_bet_right_click";
    // 长龙列表子项 请求倒计时
    String EVENT_DRAGON_ITEM_REQUEST_COUNT_DOWN = "event_dragon_item_request_count_down";
    String EVENT_TYPE_LONG_DRAGON_BET_CLICK = "event_type_long_dragon_bet_click";
    String EVENT_TYPE_USER_NAME_NICK_NAME = "event_type_user_name_nick_name";
    String EVENT_TOKEN_DISABLE = "event_token_disable";
    String EVENT_FUND_PWD_UPDATE = "event_fund_pwd_update";
    String EVENT_UPDATE_HEAD_SUCCESS = "event_update_head_success";
    String EVENT_BIND_PHONE_SUCCESS = "event_bind_phone_success";
    String EVENT_BIND_EMAIL_SUCCESS = "event_bind_email_success";
    String EVENT_BIND_BANK_CARD_SUCCESS = "event_bind_bank_card_success";
    String EVENT_RECORD_CANCEL_SUCCESS = "event_record_cancel_success";
    String EVENT_GROUP_INVITE_URL_ADD_SUCCESS = "event_group_invite_url_add_success";
    // sp
    String SP_DOUBLE_QUICK_MONEY_DEFAULT_LIST = "double_default_money_list"; // 快捷金额 默认金额列表
    String SP_DOUBLE_QUICK_MONEY_DEFAULT_SELECTED = "double_default_money_selected"; // 快捷金额 默认选中金额

    int PAGE_SIZE = 10;
    int PAGE_NO = 1;

    String BASE_HOST = "https://web.k5615.com/sk/";
    String DEVICE = "3";

    int SUBMIT_MODE_BET = 0; //投注
    int SUBMIT_MODE_CHASE = 1;//追号

    /*投注来自立即投注按钮*/
    int BET_FROM_BET_RIGHT_NOW = 1;
    /*投注来自购彩蓝*/
    int BET_FROM_BASKET = 2;
    int BET_FROM_CHASE = 3;
    int BET_FROM_DRAGON = 4;

}
