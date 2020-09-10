package com.ski.box.mvp.presenter;

import android.content.Context;

import com.google.gson.internal.LinkedTreeMap;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.MemberDetailEntity;
import com.ski.box.bean.lottery.LotteryPlay;
import com.ski.box.bean.lottery.LotteryPlayEnd;
import com.ski.box.bean.lottery.LotteryPlayStart;
import com.ski.box.bean.lottery.LotteryPlaySub;
import com.ski.box.bean.lottery.PlayUtil;
import com.ski.box.bean.lottery.RemoteLotteryPlay;
import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.PlayContract;
import com.ski.box.mvp.remote.LotteryModel;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.mvp.remote.imodel.ILotteryModel;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.yb.core.base.BaseConsumer;
import com.yb.core.base.RxPresenter;
import com.yb.core.utils.LogUtils;
import com.yb.core.utils.StringUtils;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class PlayPresenter extends RxPresenter<PlayContract.View> implements PlayContract.Presenter {

    private final ILotteryModel mLotteryModel;
    private final IUserModel mUserModel;

    public PlayPresenter(Context context) {
        super(context);
        mLotteryModel = new LotteryModel();
        mUserModel = new UserModel();
    }

    @Override
    public void getBalance() {
        Disposable disposable = mUserModel.getMemberDetails(new Consumer<MemberDetailEntity>() {
            @Override
            public void accept(MemberDetailEntity bean) throws Exception {
                if (bean != null) {
                    if (!StringUtils.isEmpty(bean.getBalance())) {
                        DataCenter.getInstance().setBalance(bean);
                        mView.onBalanceResult(bean);
                    }
                }
            }
        }, new BaseConsumer(false, false) {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
            }
        });
        addDisposable(disposable);
    }


    @Override
    public void getPlays(int ticketId, int playMode) {
        long time1 =  System.currentTimeMillis();
        Disposable disposable = mLotteryModel.getTicketPlayType(new Consumer<List<RemoteLotteryPlay>>() {
            @Override
            public void accept(List<RemoteLotteryPlay> remotePlays) throws Exception {
                PlayUtil.addLayoutData(remotePlays, ticketId, playMode);
                mView.onPlaysResult(remotePlays);
            }
        }, new CusConsumer(false,false){
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onPlaysErrorResult(throwable.getMessage());
            }
        },ticketId, playMode);
        addDisposable(disposable);
    }

    @Override
    public void getRecentBetList(String memberId, String status) {
//        Disposable disposable = mRecordModel.getBettingRecordTop(new Consumer<RecentRecordBean>() {
//            @Override
//            public void accept(MkBettingRecordHistoryEntity bean) throws Exception {
//                DataCenter.getInstance(). setRecentData0(bean);
//            }
//        }, memberId, status);
//        addDisposable(disposable);
    }




    @Override
    public void getColdHot_d(int ticketId) {
//        Disposable disposable = mHotColdMissModel.getDoubleTicketHotColdData(new Consumer<Object>() {
//            @Override
//            public void accept(Object o) throws Exception {
//                try {
//                    setColdHot_d(o);
//                    mView.onHotColdResult();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }, ticketId);
//        addDisposable(disposable);
    }

    private void setColdHot_d(Object o) {
//        if (o instanceof LinkedTreeMap) {
//            LinkedTreeMap<String, List<LinkedTreeMap<String, LinkedTreeMap<String, List<List<String>>>>>> map = (LinkedTreeMap) o;
//
//            int lotteryId = DataCenter.getInstance().getCurLotteryId();
//            int mode = DataCenter.getInstance().getPlayMode();
//            List<LotteryPlayStart> plays = DataCenter.getInstance().getRemotePlay(lotteryId, mode);
//            for (int i = 0; i < plays.size(); i++) {
//                // 双面
//                LotteryPlayStart playStart = plays.get(i);
//                List<LinkedTreeMap<String, LinkedTreeMap<String, List<List<String>>>>> coldHotSubList = map.get(String.valueOf(playStart.getId()));
//                if (coldHotSubList == null) {
//                    return;
//                }
//                LinkedTreeMap<String, LinkedTreeMap<String, List<List<String>>>> coldHotSubMap = coldHotSubList.get(0);
//                for (int j = 0; j < playStart.getSubPlays().size(); j++) {
//                    // 副标题 双面副
//                    LotteryPlaySub subPlay = playStart.getSubPlays().get(j);
//                    List<LotteryPlayEnd> playEnds = subPlay.getLotteryPlayEnds();
//                    LinkedTreeMap<String, List<List<String>>> coldHotSub = coldHotSubMap.get(subPlay.getRemoteCode());
//                    if (coldHotSub == null) {
//                        continue;
//                    }
//                    List<List<String>> cdListList_20 = coldHotSub.get("20q");
//                    List<List<String>> cdListList_50 = coldHotSub.get("50q");
//                    List<List<String>> cdListList_100 = coldHotSub.get("100q");
//                    List<List<String>> cdListList_miss = coldHotSub.get("miss");
//
//                    for (int k = 0; k < playEnds.size(); k++) {
//                        // 总和龙虎和
//                        LotteryPlayEnd playEnd = playEnds.get(k);
//                        List<String> cdList_20 = cdListList_20.get(k);
//                        List<String> cdList_50 = cdListList_50.get(k);
//                        List<String> cdList_100 = cdListList_100.get(k);
//                        List<String> cdList_miss = null;
//                        if (k < cdListList_miss.size()) {
//                            cdList_miss = cdListList_miss.get(k);
//                        }
//
//                        for (int z = 0; z < playEnd.getLotteryPlays().size(); z++) {
//                            LotteryPlay play = playEnd.getLotteryPlays().get(z);
//                            play.setColdHot_20(cdList_20.get(z));
//                            play.setColdHot_50(cdList_50.get(z));
//                            play.setColdHot_100(cdList_100.get(z));
//                            if (cdList_miss != null && z < cdList_miss.size()) {
//                                play.setMiss(cdList_miss.get(z));
//                            }
//                        }
//                    }
////                 lotteryPlays.add(lp1);
//                }
//            }
//        }
    }
}
