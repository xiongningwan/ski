package com.ski.box.mvp.contract;


import androidx.fragment.app.FragmentManager;

import com.ski.box.bean.MemberDetailEntity;
import com.ski.box.bean.NoticeBean;
import com.ski.box.bean.lottery.LotterySer;
import com.yb.core.base.BaseContract;

import java.util.List;

public interface HallContract {
    interface View extends BaseContract.BaseView {
        void onAllLotteryResult(List<LotterySer> list);

        void onBalanceResult(MemberDetailEntity bean);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void getAllLotteryResult();

        void getSysConfig();

        void crateSyncTimeTask();

        void getSelfProfile();

        void getBalance();

        void showAnnouncement(List<NoticeBean> announcements, FragmentManager childFragmentManager);
    }
}
