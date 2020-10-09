package com.ski.box.mvp.contract;


import com.ski.box.bean.LotteryNumBean;
import com.ski.box.bean.road.RoadTitle;
import com.yb.core.base.BaseContract;

import java.util.List;

public interface RoadContract {

    interface View extends BaseContract.BaseView {
        void onRoadLoadSuccess(List<RoadTitle> roadTitles);
    }

    interface Presenter extends BaseContract.BasePresenter<RoadContract.View> {
        /**
         * 路子图历史数据
         *
         * @param lotteryId
         * @param num
         */
        void getHistoryResult(int lotteryId, int num, List<RoadTitle> roadTitles);

        void submitBet(int lotteryId, String planId);

        void refreshHistoryData(List<LotteryNumBean> lotteryNumBeanList, List<RoadTitle> roadTitles);
    }

}
