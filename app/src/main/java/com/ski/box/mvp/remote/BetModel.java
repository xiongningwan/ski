package com.ski.box.mvp.remote;

import android.util.Log;

import com.google.gson.Gson;
import com.ski.box.bean.MkBetParamEntity;
import com.ski.box.bean.lottery.LotterySer;
import com.ski.box.mvp.remote.imodel.IBetModel;
import com.ski.box.mvp.service.IBetService;
import com.ski.box.mvp.service.ILotteryService;
import com.yb.core.base.BaseModel;
import com.yb.core.net.RetrofitHelper;
import com.yb.core.utils.LogUtils;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class BetModel extends BaseModel implements IBetModel {
    @Override
    public Disposable getTickettypelist(Consumer s) {
        Single<List<LotterySer>> single = RetrofitHelper
                .getService(ILotteryService.class)
                .getTicketTypelist()
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s);
    }

    @Override
    public Disposable doubleBet(Consumer s, Consumer e, MkBetParamEntity entity) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("ticketId", entity.getTicketId());
        hashMap.put("planNo", entity.getPlanNo());

        List<MkBetParamEntity.BetParamEntity> betParamList = entity.getBet();
        for (int i = 0; i < betParamList.size(); i++) {
            MkBetParamEntity.BetParamEntity betParam = betParamList.get(i);
            hashMap.put("bet[" + i + "].playId", betParam.getPlayId());
            hashMap.put("bet[" + i + "].betNum", betParam.getBetNum());
            hashMap.put("bet[" + i + "].betCount", betParam.getBetCount());
            hashMap.put("bet[" + i + "].content", betParam.getContent());
            hashMap.put("bet[" + i + "].betAmount", betParam.getBetAmount_d() * betParam.getBetCount());
        }

        Single<String> map = RetrofitHelper.getService(IBetService.class)
                .doubleBet(hashMap).map(new HttpResultFunc<>());

        return toSubscribe(map, s, e);
    }

    @Override
    public Disposable doubleTraceBet(Consumer s,Consumer e, MkBetParamEntity entity) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("ticketId", String.valueOf(entity.getTicketId()));
        hashMap.put("isSuppend", String.valueOf(entity.getBuyMode()));
        hashMap.put("totalAmount", String.valueOf(entity.getTotalAmount()));
        hashMap.put("chaseMode", "1");

        List<MkBetParamEntity.BetParamEntity> betParamList = entity.getBet();
        List<MkBetParamEntity.ChaseParamEntity> traceParamList = entity.getChase();

        if (betParamList == null || betParamList.size() == 0) {
            return null;
        }
        if (traceParamList == null || traceParamList.size() == 0) {
            return null;
        }

        for (int i = 0; i < betParamList.size(); i++) {
            MkBetParamEntity.BetParamEntity betParam = betParamList.get(i);
            hashMap.put("bet[" + i + "].playItemId", betParam.getPlayId());
            hashMap.put("bet[" + i + "].betNum", betParam.getBetNum());
            hashMap.put("bet[" + i + "].betCount", String.valueOf(betParam.getBetCount()));
            boolean single = betParam.isSingle();
            if (single) {
                /*单式玩法*/
                hashMap.put("bet[" + i + "].betAmount", String.valueOf(betParam.getBetAmount_d()));

            } else {
                /*复试玩法*/
                hashMap.put("bet[" + i + "].betAmount", String.valueOf(betParam.getBetAmount_d()*betParam.getBetCount()));

            }
            hashMap.put("bet[" + i + "].content", betParam.getContent());
        }

        for (int i = 0; i < traceParamList.size(); i++) {
            MkBetParamEntity.ChaseParamEntity traceParam = traceParamList.get(i);
            hashMap.put("trace[" + i + "].planNo", traceParam.getPlanId());
            hashMap.put("trace[" + i + "].betMultiple", String.valueOf(traceParam.getTimes()));
        }
        String s1 = new Gson().toJson(hashMap);
        Single<Object> map = RetrofitHelper.getService(IBetService.class)
                .doubleTraceBet(hashMap).map(new HttpResultFunc<>());

        return toSubscribe(map, s,e);
    }
}
