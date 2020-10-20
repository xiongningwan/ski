package com.ski.box.mvp.contract;

import com.ski.box.bean.LongDragonPushInfoEntity;
import com.ski.box.bean.NoticeConfigInfoEntity;
import com.ski.box.bean.SelfProfileEntity;
import com.ski.box.bean.TicketLotteryTimeBean;
import com.ski.box.bean.lottery.RemoteLotteryPlay;
import com.yb.core.base.BaseContract;

import java.util.List;

/**
 * author Afton
 * date 2020/3/2
 */
public interface DragonContract {
    interface View extends BaseContract.BaseView {
        void onPlaysResult(List<RemoteLotteryPlay> plays);
        void onPlaysErrorResult(String s);

        void onLotteryConfigResult(List<NoticeConfigInfoEntity> list);

        void onLimitConfigResult(int min, int max, List<SelfProfileEntity.LongDragonTicketsBean> list);

        void onSaveLongDragonLimitSuccess(String s);

        void onSaveLongDragonLimitError(String s);

        void onSaveLongDragonTicketIdsSuccess(String s);

        void onSaveLongDragonTicketIdsError(String s);

        void onListSuccessResult(List<LongDragonPushInfoEntity> list);

        void onListErrorResult(String msg);

        void onSellsResult(TicketLotteryTimeBean dataBean);
    }

    interface Presenter extends BaseContract.BasePresenter<DragonContract.View> {
        /**
         * 双面盘 标准盘 玩法 接口
         * 标准盘 1 双面盘 2
         */
        void getPlays(int ticketId, int playMode);

        void getLongDragonConfigInfo(String groupMode);

        void getLongDragonPushInfo(String groupMode, String min, String max, List<Integer> ticketIdList);

        /*保存长龙期数*/
        void saveLongDragonLimit(String min, String max);

        void saveLongDragonTicketList(List<SelfProfileEntity.LongDragonTicketsBean> longDragonTicketList);
        /**长龙期数限制**/
        void getSelfProfile();

        void getSellings(String ids);
    }
}
