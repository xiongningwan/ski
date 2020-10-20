package com.ski.box.mvp.contract;


import com.ski.box.bean.LongDragonPushInfoEntity;
import com.ski.box.bean.LotteryNumBean;
import com.ski.box.bean.MemberDetailEntity;
import com.ski.box.bean.MkBetParamEntity;
import com.ski.box.bean.TicketLotteryTimeBean;
import com.ski.box.view.view.BetBottomView;
import com.yb.core.base.BaseContract;

import java.util.List;

public interface BetContract {

    interface View extends BaseContract.BaseView {
        void onHeadTicketInfoResult(TicketLotteryTimeBean dataBean);

        void onOpenResult(List<LotteryNumBean> list);

    }

    interface Presenter extends BaseContract.BasePresenter<BetContract.View> {
        void getGetHeadTicketInfo(String ids);

        void getLastOpenResult(int lotteryId, int num);


        void setSaleStatus(int setSaleStatus);


        //路子图 投注
        void showQuickBet(int lotteryId, String planId, int isBet, String title);

        void showLongDragonBet(int lotteryId, String planId, LongDragonPushInfoEntity infoEntity);

        //快速投注
        void showBetRightNow(BetBottomView betBottomView);

        void doubleBetNow(boolean showDialog, BetBottomView bottomView);

        void betSubmit(MkBetParamEntity entity);

        void getRecentBetList(String memberId, String status);
    }
}
