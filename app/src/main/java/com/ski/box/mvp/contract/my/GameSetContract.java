package com.ski.box.mvp.contract.my;


import com.ski.box.bean.PersonProfileEntity;
import com.ski.box.bean.SelfProfileEntity;
import com.yb.core.base.BaseContract;

import java.util.List;

public interface GameSetContract {

    interface View extends BaseContract.BaseView {
        void onDelayResult(SelfProfileEntity selfProfileEntity);

        void onRangListResult(List<SelfProfileEntity.DoubleBetRangeListBean> list);

        void onSaveResult(Object o);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void getSelfProfile();

        void saveSelfProfile(String range, PersonProfileEntity personProfileEntity);

    }

}
