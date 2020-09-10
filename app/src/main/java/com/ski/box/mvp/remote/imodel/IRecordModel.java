package com.ski.box.mvp.remote.imodel;


import com.ski.box.bean.chase.ChaseRecordRequest;
import com.ski.box.bean.record.ProfitLossRequest;
import com.ski.box.bean.record.RecordBetRequest;
import com.ski.box.bean.record.RecordMoneyRequest;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public interface IRecordModel {
    /**
     * 获取近期投注记录
     * memberId：暂时传空
     * status：0
     */
    Disposable getBettingRecordTop(Consumer s, String memberId, String status);

    Disposable getBetRecordData(RecordBetRequest mkBettingRecordRequest, Consumer s, Consumer e);
    Disposable getChaseNumsData(ChaseRecordRequest chaseRecordRequest, Consumer s, Consumer e);
    /**获取账单筛选类型*/
    Disposable getFrontTradeTypes(Consumer s, Consumer e);

    Disposable getMoneyRecordData(RecordMoneyRequest moneyRecordRequest, Consumer s, Consumer e);
    Disposable getDailyProfitLoss(ProfitLossRequest profitLossRequest, Consumer s, Consumer e);


    /**投注撤单*/
    Disposable betOrderCancel(String chaseId,Consumer s, Consumer e);

}
