package com.ski.box.mvp.service;

/**
 * Created by tom on 2020/10/31.
 */
public interface IUrlMoney {
    // 支付方式
    String PAY_TYPE = "promen/wallet/platform/channel";
    // 充值
    String PAY_DEPOSIT = "promen/wallet/deposit";
    // 提现
    String PAY_WITHDRAW = "promen/wallet/withdraw";
    // 资金进度
    String PAY_DW_ORDER_LIST = "promen/wallet/dwOrderList";
}
