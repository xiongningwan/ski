package com.ski.box.mvp.remote;


import com.google.gson.Gson;
import com.ski.box.bean.Balance;
import com.ski.box.bean.MemberDetailEntity;
import com.ski.box.bean.PersonProfileEntity;
import com.ski.box.bean.SelfProfileEntity;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.ski.box.mvp.service.IUserService;
import com.yb.core.base.BaseConsumer;
import com.yb.core.base.BaseModel;
import com.yb.core.net.RetrofitHelper;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class UserModel extends BaseModel implements IUserModel {

    @Override
    public Disposable register(Consumer s, Consumer e, String memberAccount, String password) {
        Single<Object> single = RetrofitHelper
                .getService(IUserService.class)
                .register(memberAccount, password)
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable login(Consumer s, String environment, String merchantId, String account, String password, int loginType, String timestamp) {
        Single<Object> single = RetrofitHelper
                .getService(IUserService.class)
                .login(environment, merchantId, account, password, loginType, timestamp)
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s);
    }


    @Override
    public Disposable getMemberDetails(Consumer s, Consumer e) {
        Single<MemberDetailEntity> single = RetrofitHelper
                .getService(IUserService.class)
                .getMemberDetails()
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable getSelfProfile(Consumer s) {
        Single<SelfProfileEntity> single = RetrofitHelper
                .getService(IUserService.class)
                .getSelfProfile()
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s);
    }

    @Override
    public Disposable saveSelfProfile(Consumer s, PersonProfileEntity entity) {
        Single<Object> single = RetrofitHelper
                .getService(IUserService.class)
                .saveSelfProfile(entity.getNextConfigId(), entity.isCountDownBeep(), entity.isDoubleBetConfirm(), entity.isMissRemind(), entity.isQuickConfirm(), entity.getLongDragonTicketList(),
                        entity.isWinBeep(), entity.isWinModal(), entity.isLongDragonNumPrompt(), entity.getLongDragonLimit(), entity.getChips(), entity.isVirtualKeyboard())
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s);
    }

    @Override
    public Disposable saveLongDragonLimit(Consumer s, Consumer e, String longDragonLimit, String longDragonLimitMax) {
        Single<Object> single = RetrofitHelper
                .getService(IUserService.class)
                .saveLongDragonLimit(longDragonLimit, longDragonLimitMax)
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable saveLongDragonTicketList(Consumer s, Consumer e, List<SelfProfileEntity.LongDragonTicketsBean> longDragonTicketList) {
        HashMap<String, List<SelfProfileEntity.LongDragonTicketsBean>> paramsMap = new HashMap<>();
        paramsMap.put("memberConfSeries", longDragonTicketList);
        String s1 = new Gson().toJson(paramsMap);

        RequestBody requestBody = RequestBody.create(s1, MediaType.parse("Content-Type, application/json;charset=UTF-8"));
        Single<Object> map = RetrofitHelper.getService(IUserService.class)
                .saveLongDragonTicketList(requestBody).map(new HttpResultFunc<>());
        return toSubscribe(map, s, e);
    }

    @Override
    public Disposable getMemberBalanceByMerchant(Consumer s) {
        Single<Balance> single = RetrofitHelper
                .getService(IUserService.class)
                .getMemberBalanceByMerchant()
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s);
    }

    @Override
    public Disposable gettransferOnMemberAndMerchant(Consumer s) {
        Single<Object> single = RetrofitHelper
                .getService(IUserService.class)
                .gettransferOnMemberAndMerchant()
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s);
    }

    @Override
    public Disposable getMerchantTransferType(Consumer s) {
        Single<Object> single = RetrofitHelper
                .getService(IUserService.class)
                .getMerchantTransferType()
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s);
    }

    /**
     * 首页公告
     *
     * @param s
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public Disposable getMerchantNoticeList(Consumer s, int pageSize, int pageNum) {
        Single<Object> single = RetrofitHelper
                .getService(IUserService.class)
                .getMerchantNoticeList(pageSize, pageNum)
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s);
    }

}
