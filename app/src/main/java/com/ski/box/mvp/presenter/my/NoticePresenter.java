package com.ski.box.mvp.presenter.my;

import android.content.Context;

import com.ski.box.bean.NoticeData;
import com.ski.box.bean.user.BankCard;
import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.my.BankCardContract;
import com.ski.box.mvp.contract.my.NoticeContract;
import com.ski.box.mvp.remote.SysModel;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.mvp.remote.imodel.ISysModel;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.yb.core.base.RxPresenter;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class NoticePresenter extends RxPresenter<NoticeContract.View> implements NoticeContract.Presenter {
    private ISysModel mSysModel;

    public NoticePresenter(Context context) {
        super(context);
        mSysModel = new SysModel();
    }

    @Override
    public void getNoticeList(int pageNum, int pageSize) {
        Disposable disposable = mSysModel.getNoticeList(new Consumer<NoticeData>() {
            @Override
            public void accept(NoticeData noticeData) throws Exception {
                mView.onNoticeResult(noticeData);
            }
        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onNoticeFailResult();
            }
        }, pageNum, pageSize);
        addDisposable(disposable);
    }
}


