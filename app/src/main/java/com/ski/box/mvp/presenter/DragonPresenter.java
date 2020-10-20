package com.ski.box.mvp.presenter;

import android.content.Context;

import com.ski.box.bean.LongDragonPushInfoEntity;
import com.ski.box.bean.NoticeConfigInfoEntity;
import com.ski.box.bean.SelfProfileEntity;
import com.ski.box.bean.TicketLotteryTimeBean;
import com.ski.box.bean.lottery.PlayUtil;
import com.ski.box.bean.lottery.RemoteLotteryPlay;
import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.DragonContract;
import com.ski.box.mvp.remote.HotColdMissModel;
import com.ski.box.mvp.remote.LotteryModel;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.mvp.remote.imodel.IHotColdMissModel;
import com.ski.box.mvp.remote.imodel.ILotteryModel;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.yb.core.base.BaseConsumer;
import com.yb.core.base.RxPresenter;

import java.util.LinkedHashSet;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 长龙的控制器
 */
public class DragonPresenter extends RxPresenter<DragonContract.View> implements DragonContract.Presenter {

    private final IHotColdMissModel mHotColdMissModel;
    private final IUserModel mUserModel;
    private final ILotteryModel mLotteryModel;
    private boolean  mLoading;

    public DragonPresenter(Context context) {
        super(context);
        mLotteryModel = new LotteryModel();
        mHotColdMissModel = new HotColdMissModel();
        mUserModel = new UserModel();
    }

    @Override
    public void getPlays(int ticketId, int playMode) {
        Disposable disposable = mLotteryModel.getTicketPlayType((Consumer<List<RemoteLotteryPlay>>) remotePlays -> {
            PlayUtil.addLayoutData(remotePlays, ticketId, playMode);
            mView.onPlaysResult(remotePlays);
        }, new CusConsumer(false,false){
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onPlaysErrorResult(throwable.getMessage());
            }
        }, ticketId, playMode);
        addDisposable(disposable);
    }

    @Override
    public void getLongDragonConfigInfo(String groupMode) {
        Disposable disposable = mHotColdMissModel.getLongDragonConfigInfo((Consumer<List<NoticeConfigInfoEntity>>) (List<NoticeConfigInfoEntity> list) -> {
            mView.onLotteryConfigResult(list);
        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                //mView.onCancelFail(message);
            }
        }, groupMode);
        addDisposable(disposable);
    }

    /**
     * 提醒推送信息
     * @param groupMode
     * @param min
     * @param max
     * @param ticketIdList
     */
    @Override
    public void getLongDragonPushInfo(String groupMode, String min, String max, List<Integer> ticketIdList) {
        if (mLoading){
            return;
        }
        mLoading=true;
        Disposable disposable = mHotColdMissModel.getLongDragonPushInfo((Consumer<List<LongDragonPushInfoEntity>>) list -> {
            mView.onListSuccessResult(removeDuplicate(list));
            mLoading=false;
        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mView.onListErrorResult(throwable.getMessage());
                mLoading=false;
            }
        }, groupMode, min, max, ticketIdList);
        addDisposable(disposable);
    }


    /**
     * 读取用户设置 获取
     */
    @Override
    public void getSelfProfile() {
        Disposable disposable = mUserModel.getSelfProfile((Consumer<SelfProfileEntity>) entity -> {
            if (null != entity) {
                List<SelfProfileEntity.LongDragonTicketsBean> longDragonTickets = entity.getLongDragonTickets();
                mView.onLimitConfigResult(entity.getLongDragonLimit(),
                        entity.getLongDragonLimitMax(),longDragonTickets);
            }
        });
        addDisposable(disposable);
    }

    /**
     * 获取当前时间
     * @param ids
     */
    @Override
    public void getSellings(String ids) {
        Disposable disposable = mLotteryModel.getGetHeadTicketInfo(new Consumer<List<TicketLotteryTimeBean>>() {
            @Override
            public void accept(List<TicketLotteryTimeBean> data) throws Exception {
                if (data != null && data.size() > 0) {
                    TicketLotteryTimeBean selling = data.get(0);
                    mView.onSellsResult(selling);
                }
            }
        }, new BaseConsumer(false, false) {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
            }
        }, ids);
        addDisposable(disposable);
    }

    /**
     * 写入用户设置   长龙提醒的期数限制 上下限保存
     * @param min
     * @param max
     */
    @Override
    public void saveLongDragonLimit(String min, String max) {
        Disposable disposable = mUserModel.saveLongDragonLimit((Consumer<Object>) o -> mView.onSaveLongDragonLimitSuccess("修改成功"), new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onSaveLongDragonLimitError(throwable.getMessage());
            }
        }, min, max);
        addDisposable(disposable);
    }

    /**
     * 保存自定义彩种
     * @param longDragonTicketList
     */
    @Override
    public void saveLongDragonTicketList( List<SelfProfileEntity.LongDragonTicketsBean> longDragonTicketList) {
        Disposable disposable = mUserModel.saveLongDragonTicketList(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                mView.onSaveLongDragonTicketIdsSuccess("保存成功");
            }
        }, new CusConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                mView.onSaveLongDragonTicketIdsError(throwable.getMessage());
            }
        }, longDragonTicketList);
        addDisposable(disposable);
    }
    //去重
    private static <T> List<T> removeDuplicate(List<T> list) {
        LinkedHashSet<T> h = new LinkedHashSet();
        h.addAll(list);
        list.clear();
        list.addAll(h);
        return list;
    }

}


