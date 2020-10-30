package com.ski.box.mvp.contract.group;


import com.ski.box.bean.FrontTradeTypesBean;
import com.ski.box.bean.record.RecordMoneyRequest;
import com.yb.core.base.BaseContract;

import java.util.List;

public interface GroupRecordMoneyContract {

    interface View extends BaseContract.BaseView {
        void onSuccessful(Object o);
        void onError(Throwable o);
        void onMoneyTypeSuccess(List<FrontTradeTypesBean> beans);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
       /*获取资金记录筛选样式*/
        void getFrontTradeTypes();
        void getMoneyRecordData(RecordMoneyRequest mkMoneyRecordRequest);
    }

}
