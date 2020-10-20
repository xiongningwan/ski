package com.ski.box.mvp.remote.imodel;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public interface IHotColdMissModel {

    /**
     * 标准盘冷热遗漏
     * ticketId：彩种ID
     */
    Disposable getStandardHotColdData(Consumer s, String ticketId, String labelType);

    /**
     * 双面盘冷热遗漏
     * ticketId：彩种ID
     * labelOrLocationId：
     * planCount ： 奖期数
     */
    Disposable getDoubleTicketHotColdData(Consumer s, int ticketId);

    /**
     * 提醒配置信息
     * groupMode:1：遗漏提醒 2：长龙提醒
     */
    Disposable getLongDragonConfigInfo(Consumer s, Consumer e, String groupMode);

    /**
     * 提醒推送信息
     * groupMode:1：遗漏提醒 2：长龙提醒
     * threshold：过滤值，欲出率或长龙期数  星系
     * ticketIdList：彩种ID
     */
    Disposable getLongDragonPushInfo(Consumer s, Consumer e, String groupMode, String min, String max, List<Integer> ticketIdList);

}
