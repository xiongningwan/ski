package com.ski.box.mvp.contract.money;


import com.ski.box.bean.money.DepositBack;
import com.ski.box.bean.money.PayType;
import com.yb.core.base.BaseContract;

import java.util.List;

public interface RechargeDetailContract {
    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
    }
}
