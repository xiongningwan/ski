package com.ski.box.mvp.presenter;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.ski.box.R;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.MemberDetailEntity;
import com.ski.box.bean.NoticeBean;
import com.ski.box.bean.SelfProfileEntity;
import com.ski.box.bean.SystemConfig;
import com.ski.box.bean.lottery.LotteryBean;
import com.ski.box.bean.lottery.LotterySer;
import com.ski.box.mvp.contract.HallContract;
import com.ski.box.mvp.remote.LotteryModel;
import com.ski.box.mvp.remote.SysModel;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.mvp.remote.imodel.ILotteryModel;
import com.ski.box.mvp.remote.imodel.ISysModel;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.ski.box.service.MQTTHelper;
import com.ski.box.utils.lottery.LotteryNoUtil;
import com.ski.box.utils.lottery.LotteryTimeUtil;
import com.yb.core.base.BaseConsumer;
import com.yb.core.base.RxPresenter;
import com.yb.core.utils.AppUtil;
import com.yb.core.utils.LogUtils;
import com.yb.core.utils.SPUtils;
import com.yb.core.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.yb.core.utils.AppUtil.getContext;

public class HallPresenter extends RxPresenter<HallContract.View> implements HallContract.Presenter {
    private ILotteryModel mLotteryModel;
    private IUserModel mUserModel;
    private ISysModel mSysModel;

    public HallPresenter(Context context) {
        super(context);
        mLotteryModel = new LotteryModel();
        mUserModel = new UserModel();
        mSysModel = new SysModel();
    }

//    @Override
//    public void loadLottery() {
//        String json = "{\"code\":0,\"msg\":\"query ticket type success\",\"sign\":null,\"data\":[{\"id\":1,\"name\":\"时时彩\",\"list\":[{\"ticketId\":1,\"ticketName\":\"重庆欢乐生肖\",\"path\":null,\"groupMode\":0,\"code\":\"SSC\"},{\"ticketId\":3,\"ticketName\":\"新疆时时彩\",\"path\":null,\"groupMode\":0,\"code\":\"SSC\"},{\"ticketId\":8,\"ticketName\":\"天津时时彩\",\"path\":null,\"groupMode\":0,\"code\":\"SSC\"},{\"ticketId\":9,\"ticketName\":\"黑龙江时时彩\",\"path\":null,\"groupMode\":1,\"code\":\"SSC\"},{\"ticketId\":45,\"ticketName\":\"幸运分分彩\",\"path\":null,\"groupMode\":1,\"code\":\"SSC\"},{\"ticketId\":55,\"ticketName\":\"幸运5分彩\",\"path\":null,\"groupMode\":0,\"code\":\"SSC\"},{\"ticketId\":56,\"ticketName\":\"河内5分彩\",\"path\":null,\"groupMode\":0,\"code\":\"SSC\"},{\"ticketId\":57,\"ticketName\":\"腾讯分分彩\",\"path\":null,\"groupMode\":0,\"code\":\"SSC\"}]},{\"id\":2,\"name\":\"11选5\",\"list\":[{\"ticketId\":4,\"ticketName\":\"广东11选5\",\"path\":null,\"groupMode\":0,\"code\":\"SYXW\"},{\"ticketId\":5,\"ticketName\":\"山东11选5\",\"path\":null,\"groupMode\":0,\"code\":\"SYXW\"},{\"ticketId\":11,\"ticketName\":\"江西11选5\",\"path\":null,\"groupMode\":0,\"code\":\"SYXW\"},{\"ticketId\":14,\"ticketName\":\"福建11选5\",\"path\":null,\"groupMode\":0,\"code\":\"SYXW\"},{\"ticketId\":15,\"ticketName\":\"广西11选5\",\"path\":null,\"groupMode\":0,\"code\":\"SYXW\"},{\"ticketId\":46,\"ticketName\":\"极速11选5\",\"path\":null,\"groupMode\":0,\"code\":\"SYXW\"},{\"ticketId\":58,\"ticketName\":\"幸运11选5\",\"path\":null,\"groupMode\":0,\"code\":\"SYXW\"}]},{\"id\":3,\"name\":\"六合彩\",\"list\":[{\"ticketId\":27,\"ticketName\":\"香港六合彩\",\"path\":null,\"groupMode\":0,\"code\":\"LHC\"},{\"ticketId\":59,\"ticketName\":\"六合5分彩\",\"path\":null,\"groupMode\":0,\"code\":\"LHC\"}]},{\"id\":4,\"name\":\"3D\",\"list\":[{\"ticketId\":6,\"ticketName\":\"福彩3D\",\"path\":null,\"groupMode\":0,\"code\":\"SD\"},{\"ticketId\":16,\"ticketName\":\"幸运3D\",\"path\":null,\"groupMode\":0,\"code\":\"SD\"},{\"ticketId\":17,\"ticketName\":\"极速3D\",\"path\":null,\"groupMode\":0,\"code\":\"SD\"}]},{\"id\":5,\"name\":\"快乐8\",\"list\":[{\"ticketId\":26,\"ticketName\":\"北京快乐8\",\"path\":null,\"groupMode\":0,\"code\":\"KLC\"},{\"ticketId\":47,\"ticketName\":\"极速快乐8\",\"path\":null,\"groupMode\":0,\"code\":\"KLC\"}]},{\"id\":6,\"name\":\"PK10\",\"list\":[{\"ticketId\":67,\"ticketName\":\"极速赛车\",\"path\":null,\"groupMode\":0,\"code\":\"PK10\"},{\"ticketId\":68,\"ticketName\":\"幸运飞艇\",\"path\":\"http://172.18.3.119:8002/f3a68235-6f03-4849-8237-e459783c65d4west.png\",\"groupMode\":0,\"code\":\"PK10\"},{\"ticketId\":69,\"ticketName\":\"马耳他飞艇\",\"path\":null,\"groupMode\":0,\"code\":\"PK10\"},{\"ticketId\":18,\"ticketName\":\"北京PK10\",\"path\":null,\"groupMode\":0,\"code\":\"PK10\"},{\"ticketId\":48,\"ticketName\":\"极速飞艇\",\"path\":null,\"groupMode\":0,\"code\":\"PK10\"},{\"ticketId\":54,\"ticketName\":\"欢乐飞艇\",\"path\":null,\"groupMode\":0,\"code\":\"PK10\"}]},{\"id\":7,\"name\":\"快三\",\"list\":[{\"ticketId\":20,\"ticketName\":\"吉林快三\",\"path\":null,\"groupMode\":0,\"code\":\"K3\"},{\"ticketId\":89,\"ticketName\":\"幸运快三\",\"path\":null,\"groupMode\":0,\"code\":\"K3\"},{\"ticketId\":41,\"ticketName\":\"江苏快三\",\"path\":null,\"groupMode\":0,\"code\":\"K3\"},{\"ticketId\":42,\"ticketName\":\"安徽快三\",\"path\":null,\"groupMode\":0,\"code\":\"K3\"},{\"ticketId\":43,\"ticketName\":\"广西快三\",\"path\":null,\"groupMode\":0,\"code\":\"K3\"},{\"ticketId\":44,\"ticketName\":\"福建快三\",\"path\":null,\"groupMode\":0,\"code\":\"K3\"},{\"ticketId\":50,\"ticketName\":\"极速快三\",\"path\":null,\"groupMode\":0,\"code\":\"K3\"}]},{\"id\":8,\"name\":\"P3P5\",\"list\":[{\"ticketId\":40,\"ticketName\":\"排列3/5\",\"path\":null,\"groupMode\":0,\"code\":\"P3P5\"}]}]}";
//
//    }

    @Override
    public void getAllLotteryResult() {
        Disposable disposable = mLotteryModel.getTickettypelist(new Consumer<List<LotterySer>>() {
            @Override
            public void accept(List<LotterySer> list) {
                LotteryTimeUtil.setLotteryIds(getRemoteLotteryIds(list));
                // 同步彩种数据
                mView.onAllLotteryResult(list);
                crateSyncTimeTask();
            }
        });
        addDisposable(disposable);
    }

    private List<Integer>  getRemoteLotteryIds(List<LotterySer> lotterySers) {
        List<Integer> lotteryIds = new ArrayList<>();
        for (LotterySer bean : lotterySers) {
            for (LotteryBean ticket : bean.getList()) {
                lotteryIds.add(ticket.getTicketId());
            }
        }
        return lotteryIds;
    }

    @Override
    public void getSysConfig() {
        if (mUserModel==null){
            mUserModel = new UserModel();
        }
        Disposable disposable = mUserModel.getMemberDetails(new Consumer<MemberDetailEntity>() {
            @Override
            public void accept(MemberDetailEntity bean) throws Exception {
                if (bean != null) {
                    if (!StringUtils.isEmpty(bean.getBalance())) {
                        DataCenter.getInstance().setBalance(bean);
                        if (!StringUtils.isEmpty(bean.getBalance())) {
                            DataCenter.getInstance().setBalance(bean);
                            mView.onBalanceResult(bean);
                        }
                        Disposable disposable2 = mSysModel.getSysConfig(new Consumer<SystemConfig>() {
                            @Override
                            public void accept(SystemConfig config) {
                                String mqttUrl = config.getMqtt_url();
                                MemberDetailEntity memberDetailEntity = DataCenter.getInstance().getBalance();
                                String topic = "BORACAY_UNICAST_MEMBER_TOPIC|" + memberDetailEntity.getMerchantAccout() + "|" + memberDetailEntity.getMemberAccount();
                                String token = DataCenter.getInstance().getToken();
                                MQTTHelper.getInstance().init(AppUtil.getContext(), mqttUrl, token, topic);
                                SPUtils.putString(AppUtil.getContext(), "mqtt_topic_balance", topic);
                                SPUtils.putString(AppUtil.getContext(), "mqtt_mqttUrl", mqttUrl);
                            }
                        }, new BaseConsumer(false, false) {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                super.accept(throwable);
                                String mqttUrl = SPUtils.getString(AppUtil.getContext(), "mqtt_mqttUrl");
                                String topic = SPUtils.getString(AppUtil.getContext(), "mqtt_topic_balance");
                                String token = DataCenter.getInstance().getToken();
                                MQTTHelper.getInstance().init(AppUtil.getContext(), mqttUrl, token, topic);
                            }
                        });
                        addDisposable(disposable2);
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


    Disposable mServerTimeTaskDisp;
    /**
     * 创建获取服务器时间任务
     */
    @Override
    public void crateSyncTimeTask() {
        if (mServerTimeTaskDisp != null && !mServerTimeTaskDisp.isDisposed()) {
            mServerTimeTaskDisp.dispose();
        }
        long interval = 1000 * 60 * 1;
        mServerTimeTaskDisp = Observable.interval(100, interval, TimeUnit.MILLISECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                LotteryTimeUtil.syncLotteryTimeAll();
            }
        });
        addDisposable(mServerTimeTaskDisp);
    }

    @Override
    public void getSelfProfile() {
        Disposable disposable = mUserModel.getSelfProfile(new Consumer<SelfProfileEntity>() {
            @Override
            public void accept(SelfProfileEntity entity) {
                if (null != entity) {
                    if (null != entity.getDoubleBetRangeList() && entity.getDoubleBetRangeList().size() > 0) {
                        LotteryNoUtil.getProfile(entity);
                    }
                }
            }

        });
        addDisposable(disposable);
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



    private long lastClickTime = 0L;
    // 两次点击间隔不能少于1000ms
    private static final int FAST_CLICK_DELAY_TIME = 1000;
    /**
     * 公告弹出框
     */
    @Override
    public void showAnnouncement(List<NoticeBean> announcements, FragmentManager fragmentManager) {
        if (System.currentTimeMillis() - lastClickTime < FAST_CLICK_DELAY_TIME) {
            return;
        }
        lastClickTime = System.currentTimeMillis();
      //  createNotice(announcements, fragmentManager);
    }

}

