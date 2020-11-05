package com.ski.box.mvp.contract;


import com.ski.box.bean.ActBean;
import com.ski.box.bean.lottery.LotterySer;
import com.yb.core.base.BaseContract;

import java.util.List;

public interface ActContract {
    interface View extends BaseContract.BaseView {
        void onActResult(List<ActBean> list);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void getActList();
    }
}
