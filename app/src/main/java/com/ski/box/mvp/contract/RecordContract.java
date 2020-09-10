package com.ski.box.mvp.contract;


import com.ski.box.bean.FrontTradeTypesBean;
import com.ski.box.bean.chase.ChaseRecordRequest;
import com.ski.box.bean.record.ProfitLossRequest;
import com.ski.box.bean.record.RecordBet;
import com.ski.box.bean.record.RecordBetRequest;
import com.ski.box.bean.record.RecordMoneyRequest;
import com.yb.core.base.BaseContract;

import java.util.List;

public interface RecordContract {

    interface View extends BaseContract.BaseView {
        void onSuccessful(Object o);
        void onError(Throwable o);
        void getMoneyTypeSuccess(List<FrontTradeTypesBean> beans);

        void onCancelSuccess();

        void onCancelFail(String s);

    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getBetRecordData(RecordBetRequest mkBettingRecordRequest);

        void getChaseNumsData(ChaseRecordRequest mkChaseRecordRequest);
       /*获取资金记录筛选样式*/
        void getFrontTradeTypes();
        void getMoneyRecordData(RecordMoneyRequest mkMoneyRecordRequest);

        void getProfitLossDaily(ProfitLossRequest mkProfitLossRequest);

        void showCancelDialog(RecordBet.ListBean bean, int position);


    }

}
