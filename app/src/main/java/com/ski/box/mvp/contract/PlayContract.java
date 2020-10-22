package com.ski.box.mvp.contract;


import com.ski.box.bean.MemberDetailEntity;
import com.ski.box.bean.lottery.RemoteLotteryPlay;
import com.yb.core.base.BaseContract;

import java.util.List;

public interface PlayContract {
    interface View extends BaseContract.BaseView {
        void onPlaysResult(List<RemoteLotteryPlay> plays);
        void onPlaysErrorResult(String s);

        void onHotColdResult();
    }

    interface Presenter extends BaseContract.BasePresenter<PlayContract.View> {
        void getBalance();
        /**
         * 双面盘 标准盘 玩法 接口
         * 标准盘 1 双面盘 2
         */
        void getPlays(int ticketId, int playMode);

        void getRecentBetList(String memberId, String status);

        void getColdHot_d(int ticketId);
    }
}