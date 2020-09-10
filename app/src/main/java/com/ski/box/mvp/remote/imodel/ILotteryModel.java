package com.ski.box.mvp.remote.imodel;


import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public interface ILotteryModel {
    /**
     * 获取所有彩种
     */
    Disposable getTickettypelist(Consumer s);

    /**
     * 玩法种类列表
     */
    Disposable getTicketPlayType(Consumer s, Consumer e, int ticketId, int mode);

    /**
     * 获取头部倒计时 和最新期号
     */
    Disposable getGetHeadTicketInfo(Consumer s, Consumer e, String ticketId);

    /**
     * 获取后台服务器时间
     */
    Disposable getServiceTime(Consumer s, Consumer e);

    /**
     * 获取 开奖号码 列表
     */
    Disposable getLotteryNumHistory(Consumer s, Consumer e, String ticketId, String num);


}
