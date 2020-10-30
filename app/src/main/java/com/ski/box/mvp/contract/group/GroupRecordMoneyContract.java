package com.ski.box.mvp.contract.group;


import com.ski.box.bean.FrontTradeTypesBean;
import com.ski.box.bean.group.GroupMoneyData;
import com.ski.box.bean.record.RecordMoneyRequest;
import com.yb.core.base.BaseContract;

import java.util.List;

public interface GroupRecordMoneyContract {

    interface View extends BaseContract.BaseView {
        void onSuccessful(GroupMoneyData o);
        void onFailResult(String s);
        void onMoneyTypeSuccess(List<FrontTradeTypesBean> beans);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getFrontTradeTypes();
        void getTeamTransList(String startDate, String endDate,  String status, String memberAccount, int pageSize, int pageNum);
    }

}
