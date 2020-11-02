package com.ski.box.mvp.remote;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.ski.box.bean.FrontTradeTypesBean;
import com.ski.box.bean.chase.ChaseListEntity;
import com.ski.box.bean.chase.ChaseRecordRequest;
import com.ski.box.bean.record.ProfitLossRequest;
import com.ski.box.bean.record.RecordBet;
import com.ski.box.bean.record.RecordBetRequest;
import com.ski.box.bean.record.RecordMoney;
import com.ski.box.bean.record.RecordMoneyRequest;
import com.ski.box.bean.record.RecordProfit;
import com.ski.box.bean.record.RecordRecent;
import com.ski.box.mvp.remote.imodel.IRecordModel;
import com.ski.box.mvp.service.IRecordService;
import com.yb.core.base.BaseModel;
import com.yb.core.net.RetrofitHelper;
import com.yb.core.utils.TimeUtils;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RecordModel extends BaseModel implements IRecordModel {

    @Override
    public Disposable getBettingRecordTop(Consumer s, String memberId, String status) {
        Single<RecordRecent> single = RetrofitHelper
                .getService(IRecordService.class)
                .getBettingRecordTop(/*memberId,*/ status)
                .map(new HttpResultFunc<RecordRecent>());
        return toSubscribe(single, s);
    }

    @Override
    public Disposable getBetRecordData(RecordBetRequest request, Consumer s, Consumer e) {
        Single<RecordBet> map = RetrofitHelper.getService(IRecordService.class)
                .getBetRecordList(
                        request.getTicketId(),
                        request.getStatus(),
                        request.getStartDate(),
                        request.getEndDate(),
                        String.valueOf(request.getPageNum()),
                        request.getPageSize()
                ).map(new HttpResultFunc<>());
        return toSubscribe(map, s, e);
    }

    @Override
    public Disposable getChaseNumsData(ChaseRecordRequest request, Consumer s, Consumer e) {
        HashMap<String, String> requestResult = new HashMap<>();
        requestResult.put("pageNum", request.getPageNum() + "");
        requestResult.put("pageSize", request.getPageSize() + "");
        if (request.getPlayId() != null) {
            requestResult.put("playId", request.getPlayId());
        }
        if (request.getDiskSurface() != null) {
            requestResult.put("diskSurface", request.getDiskSurface());
        }
        if (request.getStatus() != null) {
            requestResult.put("status", request.getStatus());
        }
        if (request.getTicketId() != null) {
            requestResult.put("ticketId", request.getTicketId());
        }
        if (request.getIsLow() != null) {
            requestResult.put("isLow", request.getIsLow());
        }
        if (request.getStartDate() != null && !"0".equals(request.getStartDate())) {
            requestResult.put("startDate", request.getStartDate());
        }
        if (request.getEndDate() != null) {
            requestResult.put("endDate", request.getEndDate());
        }
        if (request.getIsSingle() != null) {
            requestResult.put("isSingle", request.getIsSingle());
        }
        Single<ChaseListEntity> map = RetrofitHelper.getService(IRecordService.class)
                .getChaselist(requestResult).map(new HttpResultFunc<>());

/*        JSONObject requestResult = new JSONObject();
        try {
            requestResult.put("pageNum", request.getPageNum() + "");
            requestResult.put("pageSize", request.getPageSize() + "");
            if (request.getPlayId() != null&&!request.getPlayId().isEmpty()) {
                requestResult.put("playId", request.getPlayId());
            }
            if (request.getDiskSurface() != null&&!request.getDiskSurface().isEmpty()) {
                requestResult.put("diskSurface", request.getDiskSurface());
            }
            if (request.getStatus() != null&&!request.getStatus().isEmpty()) {
                requestResult.put("status", request.getStatus());
            }
            if (request.getTicketId() != null) {
                requestResult.put("ticketId", request.getTicketId());
            }
            if (request.getIsLow() != null) {
                requestResult.put("isLow", request.getIsLow());
            }
            if (request.getStartDate() != null && !"0".equals(request.getStartDate())) {
                requestResult.put("startDate", request.getStartDate());
            }
            if (request.getEndDate() != null) {
                requestResult.put("endDate", request.getEndDate());
            }
            if (request.getIsSingle() != null) {
                requestResult.put("isSingle", request.getIsSingle());
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }*/


//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),requestResult.toString());
//        Single<ChaseListEntity> map = RetrofitHelper.getService(IRecordService.class).getChaselist(requestBody).map(new HttpResultFunc<>());

        return toSubscribe(map, s, e);
    }

    @Override
    public Disposable getFrontTradeTypes(Consumer s, Consumer e) {
        Single<List<FrontTradeTypesBean>> map = RetrofitHelper.getService(IRecordService.class)
                .getFrontTradeTypes().map(new HttpResultFunc<>());
        return toSubscribe(map, s, e);
    }

    @Override
    public Disposable getMoneyRecordData(RecordMoneyRequest request, Consumer s, Consumer e) {
        Single<RecordMoney> map = RetrofitHelper.getService(IRecordService.class)
                .getMoneyRecordList(TimeUtils.getDateFormat(request.getStartTime()),
                        request.getTradeType(),
                        request.getPageSize(),
                        String.valueOf(request.getPageNum())).map(new HttpResultFunc<>());
        return toSubscribe(map, s, e);
    }

    @Override
    public Disposable getDailyProfitLoss(ProfitLossRequest request, Consumer s, Consumer e) {
        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("pageNum", String.valueOf(request.getPageNum()));
        hashMap.put("pageSize", String.valueOf(request.getPageSize()));
        if (request.getTicketId() != null) {
            hashMap.put("ticketId", request.getTicketId());
        }

        hashMap.put("startDate", request.getStartDate());
        hashMap.put("endDate", request.getEndDate());
        Single<RecordProfit> map = RetrofitHelper.getService(IRecordService.class)
                .getDailyProfitLossList(hashMap).map(new HttpResultFunc<>());
        return toSubscribe(map, s, e);
    }

    @Override
    public Disposable betOrderCancel(String chaseId, Consumer s, Consumer e) {
        Single<Object> map = RetrofitHelper.getService(IRecordService.class)
                .betCancle(chaseId).map(new HttpResultFunc<>());
        return toSubscribe(map, s,e);
    }
}
