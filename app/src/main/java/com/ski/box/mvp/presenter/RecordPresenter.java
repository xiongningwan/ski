package com.ski.box.mvp.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.FrontTradeTypesBean;
import com.ski.box.bean.chase.ChaseListEntity;
import com.ski.box.bean.chase.ChaseRecordRequest;
import com.ski.box.bean.record.CheDanErrorBean;
import com.ski.box.bean.record.ProfitLossRequest;
import com.ski.box.bean.record.RecordBet;
import com.ski.box.bean.record.RecordBetRequest;
import com.ski.box.bean.record.RecordMoney;
import com.ski.box.bean.record.RecordMoneyRequest;
import com.ski.box.bean.record.RecordProfit;
import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.RecordContract;
import com.ski.box.mvp.remote.RecordModel;
import com.yb.core.base.RxPresenter;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RecordPresenter extends RxPresenter<RecordContract.View> implements RecordContract.Presenter {
    com.ski.box.mvp.remote.RecordModel mRecordModel;
    public RecordPresenter(Context context) {
        super(context);
         mRecordModel = new RecordModel();
    }

    @Override
    public void getBetRecordData(RecordBetRequest mkBettingRecordRequest) {
        Disposable disposable = mRecordModel.getBetRecordData(mkBettingRecordRequest, new Consumer<RecordBet>() {
            @Override
            public void accept(RecordBet mkOrderListEntity) throws Exception {
                mView.onSuccessful(mkOrderListEntity);
            }


        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onError(throwable);
            }
        });
        addDisposable(disposable);
    }

    @Override
    public void getChaseNumsData(ChaseRecordRequest mkChaseRecordRequest) {
        Disposable disposable = mRecordModel.getChaseNumsData(mkChaseRecordRequest, new Consumer<ChaseListEntity>() {
            @Override
            public void accept(ChaseListEntity orderListEntity) throws Exception {
                mView.onSuccessful(orderListEntity);
            }


        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onError(throwable);

            }
        });
        addDisposable(disposable);
    }

    @Override
    public void getFrontTradeTypes() {
        Disposable disposable = mRecordModel.getFrontTradeTypes(new Consumer<List<FrontTradeTypesBean>>() {
            @Override
            public void accept(List<FrontTradeTypesBean> frontTradeTypesBeans) throws Exception {
                mView.getMoneyTypeSuccess(frontTradeTypesBeans);

            }
        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onError(throwable);
            }
        });
        addDisposable(disposable);
    }

    @Override
    public void getMoneyRecordData(RecordMoneyRequest mkMoneyRecordRequest) {
        Disposable disposable = mRecordModel.getMoneyRecordData(mkMoneyRecordRequest, new Consumer<RecordMoney>() {
            @Override
            public void accept(RecordMoney frontTradeTypesBeans) throws Exception {
                String s = new Gson().toJson(frontTradeTypesBeans);
                mView.onSuccessful(frontTradeTypesBeans);

            }
        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onError(throwable);
            }
        });
        addDisposable(disposable);
    }



    @Override
    public void getProfitLossDaily(ProfitLossRequest mkProfitLossRequest) {
        Disposable disposable = mRecordModel.getDailyProfitLoss(mkProfitLossRequest, new Consumer<RecordProfit>() {
            @Override
            public void accept(RecordProfit frontTradeTypesBeans) throws Exception {
                String s = new Gson().toJson(frontTradeTypesBeans);
                mView.onSuccessful(frontTradeTypesBeans);

            }
        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onError(throwable);
            }
        });
        addDisposable(disposable);
    }

    @Override
    public void showCancelDialog(RecordBet.ListBean listBean, int position) {
        String orderId = listBean.getOrderId();
        Disposable disposable = mRecordModel.betOrderCancel(orderId, new Consumer<Object>() {
            @Override
            public void accept(Object s) throws Exception {
                mView.onCancelSuccess();
            }
        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                String message = "请求超时";
                try {
                    CheDanErrorBean cheDanErrorBean = new Gson().fromJson(throwable.getMessage(), CheDanErrorBean.class);
                    message = cheDanErrorBean.getMessage();
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
                mView.onCancelFail(message);
            }
        });
        addDisposable(disposable);
    }


}
