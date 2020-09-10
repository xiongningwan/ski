package com.ski.box.mvp.remote;


import com.google.gson.Gson;
import com.ski.box.bean.DateBean;
import com.ski.box.bean.LotteryNumBean;
import com.ski.box.bean.TicketLotteryTimeBean;
import com.ski.box.bean.lottery.LotterySer;
import com.ski.box.bean.lottery.RemoteLotteryPlay;
import com.ski.box.mvp.remote.imodel.ILotteryModel;
import com.ski.box.mvp.service.ILotteryService;
import com.ski.box.mvp.service.IRecordService;
import com.yb.core.base.BaseModel;
import com.yb.core.net.HttpResult;
import com.yb.core.net.RetrofitHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Response;


public class LotteryModel extends BaseModel implements ILotteryModel {
    @Override
    public Disposable getTickettypelist(Consumer s) {
        Single<List<LotterySer>> single = RetrofitHelper
                .getService(ILotteryService.class)
                .getTicketTypelist()
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s);
    }

    @Override
    public Disposable getTicketPlayType(Consumer s, Consumer e, int ticketId, int mode) {
        Single<List<RemoteLotteryPlay>> single = RetrofitHelper
                .getService(ILotteryService.class)
                .getTicketPlayType(ticketId, mode)
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable getGetHeadTicketInfo(Consumer s, Consumer e, String ticketId) {
        Single<List<TicketLotteryTimeBean>> map = RetrofitHelper.getService(ILotteryService.class)
                .getTicketHeadInfo(ticketId).map(new HttpResultFunc<>(TYPE_LIST));
        return toSubscribe(map, s, e);
    }

    @Override
    public Disposable getServiceTime(Consumer s, Consumer e) {
        Single<Response<DateBean>> map = RetrofitHelper.getService(ILotteryService.class)
                .getServiceData();
        return toSubscribe(map, s, e);
    }

    /**
     * 历史开奖结果
     *
     * @param s
     * @param ticketId
     * @param num
     * @return
     */
    @Override
    public Disposable getLotteryNumHistory(Consumer s, Consumer e, String ticketId, String num) {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("ticketId", ticketId);
        paramsMap.put("num", num);
        String s1 = new Gson().toJson(paramsMap);

        RequestBody requestBody = RequestBody.create(s1, MediaType.parse("Content-Type, application/json"));
        Single<List<LotteryNumBean>> map = RetrofitHelper.getService(ILotteryService.class)
                .getLotteryNum(requestBody).map(new HttpResultFunc<>(TYPE_LIST));
        return toSubscribe(map, s, e);
    }
}
