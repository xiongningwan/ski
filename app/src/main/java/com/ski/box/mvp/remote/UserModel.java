package com.ski.box.mvp.remote;


import com.google.gson.Gson;
import com.ski.box.bean.Balance;
import com.ski.box.bean.PersonProfileEntity;
import com.ski.box.bean.SelfProfileEntity;
import com.ski.box.bean.user.Bank;
import com.ski.box.bean.user.BankCard;
import com.ski.box.bean.user.LoginInfo;
import com.ski.box.bean.user.MemberInfo;
import com.ski.box.bean.user.UserInfo;
import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.ski.box.mvp.service.IUserService;
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
    public Disposable register(Consumer s, CusConsumer e, String memberAccount, String password) {
        Single<Object> single = RetrofitHelper
                .getService(IUserService.class)
                .register(memberAccount, password)
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable login(Consumer s, CusConsumer e, String memberAccount, String password) {
        Single<LoginInfo> single = RetrofitHelper
                .getService(IUserService.class)
                .login(memberAccount, password)
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable logout(Consumer s, CusConsumer e) {
        Single<Object> single = RetrofitHelper
                .getService(IUserService.class)
                .logout()
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable memberUpdateAlias(Consumer s, CusConsumer e, String alias) {
        Single<Object> single = RetrofitHelper
                .getService(IUserService.class)
                .memberUpdateAlias(alias)
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable updateLoginPwd(Consumer s, CusConsumer e, String loginPwd, String loginPwdNew) {
        Single<Object> single = RetrofitHelper
                .getService(IUserService.class)
                .updateLoginPwd(loginPwd, loginPwdNew)
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable updateFundPwd(Consumer s, CusConsumer e, String fundPwd, String fundPwdNew) {
        Single<Object> single = RetrofitHelper
                .getService(IUserService.class)
                .updateFundPwd(fundPwd, fundPwdNew)
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable bindPhone(Consumer s, CusConsumer e, String mobile) {
        Single<Object> single = RetrofitHelper
                .getService(IUserService.class)
                .bindPhone(mobile)
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable bindEmail(Consumer s, CusConsumer e, String email) {
        Single<Object> single = RetrofitHelper
                .getService(IUserService.class)
                .bindEmail(email)
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable getBankCardList(Consumer s, CusConsumer e) {
        Single<List<BankCard>> single = RetrofitHelper
                .getService(IUserService.class)
                .getBankCardList()
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable getBankList(Consumer s, CusConsumer e) {
        Single<List<Bank>> single = RetrofitHelper
                .getService(IUserService.class)
                .getBankList()
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable bindBank(Consumer s, CusConsumer e, String bankCode, String bankName, String bankSubName, String cardName, String cardNo, String cardNoSec) {
        Single<Object> single = RetrofitHelper
                .getService(IUserService.class)
                .bindBank(bankCode, bankName, bankSubName, cardName, cardNo, cardNoSec)
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

//    @Override
//    public Disposable login(Consumer s, String environment, String merchantId, String account, String password, int loginType, String timestamp) {
//        Single<Object> single = RetrofitHelper
//                .getService(IUserService.class)
//                .login(environment, merchantId, account, password, loginType, timestamp)
//                .map(new HttpResultFunc<>());
//        return toSubscribe(single, s);
//    }


    @Override
    public Disposable getMemberDetails(Consumer s, Consumer e) {
        Single<UserInfo> single = RetrofitHelper
                .getService(IUserService.class)
                .getMemberDetails()
                .map(new HttpResultFunc<>());
        return toSubscribe(single, s, e);
    }

    @Override
    public Disposable getMemberInfo(Consumer s, Consumer e) {
        Single<MemberInfo> single = RetrofitHelper
                .getService(IUserService.class)
                .getMemberInfo()
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
