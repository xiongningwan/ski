package com.ski.box.mvp.presenter.my;

import android.content.Context;

import com.ski.box.bean.PersonProfileEntity;
import com.ski.box.bean.SelfProfileEntity;
import com.ski.box.mvp.contract.my.GameSetContract;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.utils.lottery.LotteryNoUtil;
import com.yb.core.base.RxPresenter;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class GameSetPresenter extends RxPresenter<GameSetContract.View> implements GameSetContract.Presenter {

    private UserModel mIUserModel;

    public GameSetPresenter(Context context) {
        super(context);
        mIUserModel = new UserModel();
    }

    @Override
    public void getSelfProfile() {
        Disposable disposable = mIUserModel.getSelfProfile(new Consumer<SelfProfileEntity>() {
            @Override
            public void accept(SelfProfileEntity entity) {
                if (null != entity) {
                    if (null != entity.getDoubleBetRangeList() && entity.getDoubleBetRangeList().size() > 0) {
                        mView.onDelayResult(entity);
                        mView.onRangListResult(LotteryNoUtil.getProfile(entity));
                    }
                }
            }

        });
        addDisposable(disposable);
    }



    @Override
    public void saveSelfProfile(String range, PersonProfileEntity entity) {
        Disposable disposable = mIUserModel.saveSelfProfile(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                mView.onSaveResult(range);
            }

        }, entity);
        addDisposable(disposable);
    }

//    MkTicketMethodManager.getInstance().saveSelfProfile(personProfileEntity, new MkRequestCallBack<MkBaseEntity>() {
//        @Override
//        public void onSuccess(MkBaseEntity bean) {
//            if (bean.getCode() == 0) {
//                toastShow("双面盘双面玩法投注区间已改为" + range);
//                getSetting();
//            } else {
//                toastShow(bean.getMsg());
//            }
//        }
//
//        @Override
//        public void onError(String s) {
//            onLockLimit(s);
//        }
//    });

}


