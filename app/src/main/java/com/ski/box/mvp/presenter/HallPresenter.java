package com.ski.box.mvp.presenter;

import android.content.Context;
import android.text.TextUtils;

import androidx.fragment.app.FragmentManager;

import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.bean.ActBean;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.MemberDetailEntity;
import com.ski.box.bean.NoticeBean;
import com.ski.box.bean.NoticeData;
import com.ski.box.bean.SelfProfileEntity;
import com.ski.box.bean.SystemConfig;
import com.ski.box.bean.lottery.LotteryBean;
import com.ski.box.bean.lottery.LotteryConstant;
import com.ski.box.bean.lottery.LotterySer;
import com.ski.box.bean.lottery.LotteryUtil;
import com.ski.box.bean.user.User;
import com.ski.box.bean.user.UserInfo;
import com.ski.box.exception.CusConsumer;
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
import com.yb.core.utils.SPUtils;
import com.yb.core.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.ski.box.ConstantValue.EVENT_TYPE_BALANCE_UPDATE;
import static com.ski.box.ConstantValue.EVENT_TYPE_USER_NAME_NICK_NAME;

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
                // 过滤没有启用的彩种
               // List<LotterySer> filterList = filterAllLottery(list);
//                if (filterList != null && filterList.size() > 0) {
                    DataCenter.getInstance().saveRemoteLottery(list);
//                }
                LotteryTimeUtil.setLotteryIds(getRemoteLotteryIds(list));
                mView.onAllLotteryResult(list);
                // 同步彩种数据
                crateSyncTimeTask();
            }


        });
        addDisposable(disposable);
    }

    private List<LotterySer> filterAllLottery(List<LotterySer> list) {
        List<Integer> idList = new ArrayList<>();
        Map<Integer, String> serMap = new HashMap<>();
        Map<Integer, String> lotteryMap = new HashMap<>();
        for (LotterySer ser : list) {
            serMap.put(ser.getId(), ser.getName());
            for (LotteryBean lotteryBean : ser.getList()) {
                idList.add(lotteryBean.getTicketId());
                lotteryMap.put(lotteryBean.getTicketId(), lotteryBean.getTicketName());
            }
        }
        List<LotterySer> sers = DataCenter.getInstance().getDefaultLocalLottery();
        for (LotterySer ser : sers) {
            List<LotteryBean> lotteryBeans = ser.getList();
            String serName = serMap.get(ser.getId());
            if (!TextUtils.isEmpty(serName)) {
                ser.setName(serName);
            }
            List<LotteryBean> noList = new ArrayList<>();
            for (LotteryBean lotteryBean : lotteryBeans) {
                if (!idList.contains(lotteryBean.getTicketId())) {
                    noList.add(lotteryBean);
                }
                String ticketName = lotteryMap.get(lotteryBean.getTicketId());
                if (!TextUtils.isEmpty(ticketName)) {
                    lotteryBean.setTicketName(ticketName);
                }
//                //todo 手动隐藏福建快三
//                if (LotteryConstant.LOTTERY_ID_K3_FJ==lotteryBean.getTicketId()) {
//                    noList.add(lotteryBean);
//                }
            }
            lotteryBeans.removeAll(noList);
        }

        return sers;
    }

    private List<Integer> getRemoteLotteryIds(List<LotterySer> lotterySers) {
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
        if (mUserModel == null) {
            mUserModel = new UserModel();
        }
        Disposable disposable = mUserModel.getMemberDetails(new Consumer<UserInfo>() {
            @Override
            public void accept(UserInfo bean) throws Exception {
                if (bean != null) {
                    if (!StringUtils.isEmpty(bean.getBalance())) {
                        DataCenter.getInstance().setUser(bean);
                        Disposable disposable2 = mSysModel.getSysConfig(new Consumer<SystemConfig>() {
                            @Override
                            public void accept(SystemConfig config) {
                                String mqttUrl = config.getMqtt_url();
                                User user = DataCenter.getInstance().getUser();
                                String topic = "BORACAY_UNICAST_MEMBER_TOPIC|" + user.getMerchantAccout() + "|" + bean.getMemberAccount();
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
    public void getNoticeList(int pageNum, int pageSize) {
        Disposable disposable = mSysModel.getNoticeList(new Consumer<NoticeData>() {
            @Override
            public void accept(NoticeData noticeData) {
                List<NoticeData.ListBean> listBean = noticeData.getList();
                List<String> list = new ArrayList<>();
                for (NoticeData.ListBean bean : listBean) {
                    list.add(bean.getNoticeTitle());
                }
                if (0 == list.size()) {
                    list.add("彩票新彩种，欢迎投注体验，随时充值...");
                }
                mView.onNoticeListResult(list);
            }

        }, new CusConsumer(false, false) {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
            }
        }, pageNum, pageSize);
        addDisposable(disposable);
    }

    @Override
    public void getBalance() {
        Disposable disposable = mUserModel.getMemberDetails(new Consumer<UserInfo>() {
            @Override
            public void accept(UserInfo bean) throws Exception {
                if (bean != null) {
                    if (!StringUtils.isEmpty(bean.getBalance())) {
                        DataCenter.getInstance().setUser(bean);
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
    public void getActList() {
        Disposable disposable = mUserModel.getActList(new Consumer<List<ActBean>>() {
            @Override
            public void accept(List<ActBean> list) {
                for (int i = 0; i < list.size(); i++) {
                    ActBean actBean = list.get(i);
                    if (0 == i) {
                        actBean.setLocalImg(R.mipmap.img_banner_03);
                        actBean.setTargetUrl("https://www.google.com");
                    } else if (1 == i) {
                        actBean.setLocalImg(R.mipmap.img_banner_04);
                        actBean.setTargetUrl("https://www.google.com");
                    }
                }
                mView.onActResult(list);
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


