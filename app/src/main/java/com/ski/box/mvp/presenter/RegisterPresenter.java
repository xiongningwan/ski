package com.ski.box.mvp.presenter;

import android.content.Context;

import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.RegisterContract;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.ski.box.utils.SignUtil;
import com.yb.core.base.RxPresenter;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class RegisterPresenter extends RxPresenter<RegisterContract.View> implements RegisterContract.Presenter {
    private IUserModel mUserModel;

    public RegisterPresenter(Context context) {
        super(context);
        mUserModel = new UserModel();
    }


    @Override
    public void doRegister(String merchant, String member, String password, int tester, String prizeGroup, long timestamp) {
        Map map = new HashMap();
        map.put("merchant", merchant);
        map.put("member", member);
        map.put("password", password);
        map.put("tester", tester);
        map.put("prizeGroup", prizeGroup);
        map.put("timestamp", timestamp);
        String sign = SignUtil.sortHashMap(map);

        Disposable disposable = mUserModel.register(new Consumer<String>() {
            @Override
            public void accept(String str) {
                mView.onRegisterSuccessResult(str);
            }
        }, new CusConsumer(){
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onRegisterFailResult("");
            }
        },merchant, member, password, tester, prizeGroup, timestamp, sign);
        addDisposable(disposable);
    }

    @Override
    public void doRegister(String merchant, String member, String password, int tester, long timestamp) {
        Map map = new HashMap();
        map.put("merchant", merchant);
        map.put("member", member);
        map.put("password", password);
        map.put("tester", tester);
        map.put("timestamp", timestamp);
        String sign = SignUtil.sortHashMap(map);

        Disposable disposable = mUserModel.register(new Consumer<String>() {
            @Override
            public void accept(String str) {
                mView.onRegisterSuccessResult(str);
            }
        }, new CusConsumer(){
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onRegisterFailResult("");
            }
        },merchant, member, password, tester,  timestamp, sign);
        addDisposable(disposable);
    }

}


