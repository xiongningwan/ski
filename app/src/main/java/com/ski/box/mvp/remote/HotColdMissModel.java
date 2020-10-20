package com.ski.box.mvp.remote;

import com.ski.box.bean.LongDragonPushInfoEntity;
import com.ski.box.bean.MkBettingRecordHistoryEntity;
import com.ski.box.bean.NoticeConfigInfoEntity;
import com.ski.box.mvp.remote.imodel.IHotColdMissModel;
import com.ski.box.mvp.service.IHotColdMissService;
import com.yb.core.base.BaseModel;
import com.yb.core.net.RetrofitHelper;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class HotColdMissModel extends BaseModel implements IHotColdMissModel {

    @Override
    public Disposable getStandardHotColdData(Consumer s, String ticketId, String labelType) {
        Single<Object> single = RetrofitHelper
                .getService(IHotColdMissService.class)
                .getStandardHotColdData(ticketId, labelType)
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s);
    }

    @Override
    public Disposable getDoubleTicketHotColdData(Consumer s, int ticketId) {
        Single<Object> single = RetrofitHelper
                .getService(IHotColdMissService.class)
                .getDoubleTicketHotColdData(String.valueOf(ticketId))
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s);
    }

    @Override
    public Disposable getLongDragonConfigInfo(Consumer s, Consumer e, String groupMode) {
        Single<List<NoticeConfigInfoEntity>> single = RetrofitHelper
                .getService(IHotColdMissService.class)
                .getLongDragonConfigInfo(groupMode)
                .map(new HttpResultFunc<List<NoticeConfigInfoEntity>>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable getLongDragonPushInfo(Consumer s, Consumer e, String groupMode, String min, String max, List<Integer> ticketIdList) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("groupMode", String.valueOf(groupMode));
        hashMap.put("thresholdLst[0]", min);
        hashMap.put("thresholdLst[1]", max);
        if (ticketIdList != null) {
            for (int i = 0; i < ticketIdList.size(); i++) {
                int id = ticketIdList.get(i);
                hashMap.put("ticketIdList[" + i + "]", String.valueOf(id));
            }
        }

        Single<List<LongDragonPushInfoEntity>> map = RetrofitHelper
                .getService(IHotColdMissService.class)
                .getLongDragonPushInfo(hashMap)
                .map(new HttpResultFunc<>());

        return toSubscribe(map, s, e);
    }

}
