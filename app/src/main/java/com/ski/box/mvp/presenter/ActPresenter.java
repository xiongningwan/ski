package com.ski.box.mvp.presenter;

import android.content.Context;

import com.ski.box.bean.ActBean;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.lottery.LotterySer;
import com.ski.box.mvp.contract.ActContract;
import com.ski.box.mvp.contract.EmptyContract;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.ski.box.utils.lottery.LotteryTimeUtil;
import com.yb.core.base.RxPresenter;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ActPresenter extends RxPresenter<ActContract.View> implements ActContract.Presenter {
    private IUserModel mUserModel;

    public ActPresenter(Context context) {
        super(context);
        mUserModel = new UserModel();
    }

    @Override
    public void getActList() {
        Disposable disposable = mUserModel.getActList(new Consumer<List<ActBean>>() {
            @Override
            public void accept(List<ActBean> list) {
                mView.onActResult(list);
            }
        });
        addDisposable(disposable);
    }
}


