package com.ski.box.mvp.contract.group;


import com.ski.box.bean.group.GroupBetData;
import com.ski.box.bean.group.InviteData;
import com.ski.box.bean.group.RebateKV;
import com.ski.box.exception.CusConsumer;
import com.yb.core.base.BaseContract;

import java.util.List;

import io.reactivex.functions.Consumer;

public interface GroupRecordBetContract {
    interface View extends BaseContract.BaseView {
        void onSuccessResult(GroupBetData o);

        void onFailResult(String s);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getTeamOrderList(String startDate, String endDate, String ticketId, String status, String memberAccount, int pageSize, int pageNum);
    }
}
