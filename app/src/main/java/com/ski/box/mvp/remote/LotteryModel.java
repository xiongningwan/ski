package com.ski.box.mvp.remote;


import com.google.gson.Gson;
import com.ski.box.R;
import com.ski.box.bean.DataCenter;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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
    public Disposable getTicketHeadInfo_responseData(Consumer s, Consumer e, String ticketId) {
        Single<Response<HttpResult<List<TicketLotteryTimeBean>>>> responseDataSingle = RetrofitHelper.getService(ILotteryService.class).getTicketHeadInfo_responseData(ticketId);

        Single<List<TicketLotteryTimeBean>> map =  responseDataSingle.map(new Function<Response<HttpResult<List<TicketLotteryTimeBean>>>, HttpResult<List<TicketLotteryTimeBean>>>() {
            @Override
            public HttpResult<List<TicketLotteryTimeBean>> apply(Response<HttpResult<List<TicketLotteryTimeBean>>> httpResultResponse) throws Exception {
                Headers headers = httpResultResponse.headers();
                String dateStr = headers.get("date");
                SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
                Date date = format.parse(dateStr);
                long serverTime = date.getTime();
                DataCenter.getInstance().setServerTime(serverTime);

                HttpResult<List<TicketLotteryTimeBean>> body = httpResultResponse.body();
                return body;
            }
        }).map(new HttpResultFunc<>(TYPE_LIST));
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
        Single<List<LotteryNumBean>> map = RetrofitHelper.getService(ILotteryService.class)
                .getLotteryNum(ticketId, num).map(new HttpResultFunc<>(TYPE_LIST));
        return toSubscribe(map, s, e);
    }
}
