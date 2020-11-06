package com.ski.box.mvp.contract.my;


import com.ski.box.bean.NoticeData;
import com.ski.box.bean.user.BankCard;
import com.yb.core.base.BaseContract;

import java.util.List;

public interface NoticeContract {
    interface View extends BaseContract.BaseView {
        void onNoticeResult(NoticeData noticeData);
        void onNoticeFailResult();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getNoticeList(int pageNum, int pageSize);
    }
}
