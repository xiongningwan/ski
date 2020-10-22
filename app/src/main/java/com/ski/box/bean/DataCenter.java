package com.ski.box.bean;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hwangjr.rxbus.RxBus;
import com.ski.box.bean.lottery.LotteryBean;
import com.ski.box.bean.lottery.LotteryConstant;
import com.ski.box.bean.lottery.LotteryPlayStart;
import com.ski.box.bean.lottery.LotteryPlaySub;
import com.ski.box.bean.lottery.LotterySer;
import com.ski.box.bean.lottery.LotteryUtil;
import com.ski.box.bean.lottery.RemoteLotteryPlay;
import com.ski.box.utils.lottery.LotteryTimeUtil;
import com.yb.core.utils.AppUtil;
import com.yb.core.utils.AssetsReader;
import com.yb.core.utils.LogUtils;
import com.yb.core.utils.SPUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.ski.box.ConstantValue.EVENT_RESULT_HISTORY_LIST_UPDATE;
import static com.ski.box.ConstantValue.EVENT_TYPE_BALANCE_SET;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_PLAY_MODE_STANDARD;

public class DataCenter {
    private static DataCenter instance;
    private static Context mContext;
    private static Gson mGson;
    private static String mToken;
    private String mMerchant; // 商户
    private String uerName;
    private List<LotterySer> mLotterySers = new ArrayList<>();  // 彩系
    private Map<String, List<LotteryPlayStart>> mPlayMap = new HashMap<>();
    private Map<String, List<LotteryPlayStart>> mRemotePlayMap = new HashMap<>();
    private Map<Integer, List<RemoteLotteryPlay>> zixuanbuzhongOdd_map = new HashMap<>();// 自选不中，赔率列表
    private int curLotteryId;
    private String curLotteryName;
    private int curLotterySerId;
    private int mQuickMoney; // 快捷金额
    //待开奖期号
    private String planId = "";
    private MkBetParamEntity betParamEntity = new MkBetParamEntity();
    private volatile long mServerTime = System.currentTimeMillis();
    private Map<Integer, Long> mLotteryTimeMap = new ConcurrentHashMap<>();
    private Map<Integer, String> mLotteryPlanMap = new ConcurrentHashMap<>();
    private Map<Integer, List<LotteryNumBean>> mLotteryHistoryMap = new HashMap<>();//历史开奖
    private MemberDetailEntity balance;
    private List<BallBean> ballBeanList;   // 路子图,快捷投注数据
    private User mUser;

    public static DataCenter getInstance() {
        if (instance == null) {
            instance = new DataCenter();
            mContext = AppUtil.getContext();
            mGson = new Gson();
        }
        return instance;
    }

    public void setToken(@NonNull String token) {
        mToken = token;
    }

    public String getToken() {
        return mToken;
    }

    public void setMerchant(String merchant) {
        this.mMerchant = merchant;
    }

    public String getMerchant() {
        return mMerchant;
    }

    public void setCurLotteryId(int curLotteryId) {
        this.curLotteryId = curLotteryId;
        this.curLotterySerId = LotteryUtil.getSerIdByLotteryId(curLotteryId);
    }

    public int getCurLotteryId() {
        return curLotteryId;
    }

    public int getLotterySeriesId() {
        return curLotterySerId;
    }

    public void setCurLotteryName(String curLotteryName) {
        this.curLotteryName = curLotteryName;
    }

    public String getCurLotteryName() {
        return curLotteryName;
    }

    /**
     * 获取彩系加彩种
     *
     * @return
     */
    public List<LotterySer> getLottery() {
        mLotterySers = getSpRemoteLottery();
        if (mLotterySers.size() == 0) {
            mLotterySers = getDefaultLocalLottery();
        }
        return mLotterySers;
    }

    public List<LotterySer> getDefaultLocalLottery() {
        String json = AssetsReader.getJson(mContext, "json" + File.separator + "lottery_ser_kind.json");
        List<LotterySer> lotterySers = mGson.fromJson(json, new TypeToken<List<LotterySer>>() {
        }.getType());
        return lotterySers;
    }

    public boolean isNeedUpdateAllLottery() {
        String key = getLotteryKey();
        String json = SPUtils.getString(mContext, key, "");
        if (TextUtils.isEmpty(json)) {
            return true;
        }
        return false;
    }

    public List<LotterySer> getSpRemoteLottery() {
        String key = getLotteryKey();
        String json = SPUtils.getString(mContext, key, "");
        if (TextUtils.isEmpty(json)) {
            return new ArrayList<>();
        }
        List<LotterySer> lotterySers = mGson.fromJson(json, new TypeToken<List<LotterySer>>() {
        }.getType());
        return lotterySers;
    }

    public void saveRemoteLottery(List<LotterySer> list) {
        // 保存数据
        String json = mGson.toJson(list);
        String key = getLotteryKey();
        SPUtils.putString(mContext, key, json);
    }

    private String getLotteryKey() {
        String key = mMerchant + "_ybcp_remote_lottery_all";
        return key;
    }


    public List<LotteryPlayStart> getLotteryPlay(int lotteryId, int mode) {
        List<LotteryPlayStart> list = null;
        int serId = LotteryUtil.getSerIdByLotteryId(lotteryId);
        switch (serId) {
            case LotteryConstant.SER_ID_PK10:
                list = getLotteryPlayStarts(lotteryId, mode, LotteryConstant.SER_NAME_PK10, "lottery_play_pk10_standard.json", "lottery_play_pk10_double.json");
                break;
            case LotteryConstant.SER_ID_SSC:
            case LotteryConstant.SER_ID_PL35:
                list = getLotteryPlayStarts(lotteryId, mode, LotteryConstant.SER_NAME_SSC, "lottery_play_ssc_standard.json", "lottery_play_ssc_double.json");
                break;
            case LotteryConstant.SER_ID_LHC:
                list = getLotteryPlayStarts(lotteryId, mode, LotteryConstant.SER_NAME_LHC, "lottery_play_lhc_standard.json", "lottery_play_lhc_double.json");
                break;
            case LotteryConstant.SER_ID_11X5:
                list = getLotteryPlayStarts(lotteryId, mode, LotteryConstant.SER_NAME_11X5, "lottery_play_11x5_standard.json", "lottery_play_11x5_double.json");
                break;
            case LotteryConstant.SER_ID_K3:
                list = getLotteryPlayStarts(lotteryId, mode, LotteryConstant.SER_NAME_K3, "lottery_play_k3_standard.json", "lottery_play_k3_double.json");
                break;
            case LotteryConstant.SER_ID_3D:
                list = getLotteryPlayStarts(lotteryId, mode, LotteryConstant.SER_NAME_3D, "lottery_play_3d_standard.json", "lottery_play_3d_double.json");
                break;
            case LotteryConstant.SER_ID_KL8:
                list = getLotteryPlayStarts(lotteryId, mode, LotteryConstant.SER_NAME_KL8, "lottery_play_kl8_standard.json", "lottery_play_kl8_double.json");
                break;
        }

        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }


    private List<LotteryPlayStart> getLotteryPlayStarts(int lotteryId, int mode, String serName, String s, String d) {
        List<LotteryPlayStart> list = null;
        String json;
        String key = "lotteryId_" + lotteryId + "_serName_" + serName + "_mode_" + mode;
       // list = mPlayMap.get(key);
        if (list == null) {
            if (LOTTERY_PLAY_MODE_STANDARD == mode) {
                json = AssetsReader.getJson(mContext, "json" + File.separator + s);
            } else {
                json = AssetsReader.getJson(mContext, "json" + File.separator + d);
            }
            list = mGson.fromJson(json, new TypeToken<List<LotteryPlayStart>>() {
            }.getType());

            // 加上前缀
//            if (LOTTERY_PLAY_MODE_STANDARD == mode) {
//                long id_Prefix = LotteryNoUtil.getPlayIdPrefix();
//                for (int i = 0; i < list.size(); i++) {
//                    LotteryPlayStart playStart = list.get(i);
//                    for (int j = 0; j < playStart.getSubPlays().size(); j++) {
//                        LotteryPlaySub playSub = playStart.getSubPlays().get(j);
//                        playSub.setPlayId(id_Prefix + playSub.getPlayId());
//                    }
//                }
//            }
       //     mPlayMap.put(key, list);
        }
        return list;
    }

    private List<LotteryPlayStart> getSpRemotePlay(int lotteryId, int mode) {
        String keyF = getPlayKey(lotteryId, mode);
        String json = SPUtils.getString(mContext, keyF, "");
        if (TextUtils.isEmpty(json)) {
            return new ArrayList<>();
        }
        List<LotteryPlayStart> lotteryPlayStarts = mGson.fromJson(json, new TypeToken<List<LotteryPlayStart>>() {
        }.getType());
        return lotteryPlayStarts;
    }

    public void saveRemotePlay(int lotteryId, int mode, List<LotteryPlayStart> lotteryPlayStarts, boolean isLoadFail) {
        // 保存时间
//        if (!isLoadFail) { // 失败了就不保存时间
//            String key = getPlayKey(lotteryId, mode) + SAVE_HISTORY_TIME;
//            long now = System.currentTimeMillis();
//            SPUtils.putLong(mContext, key, now);
//        }
//        // 保存数据
//        String json = mGson.toJson(lotteryPlayStarts);
        String keyF = getPlayKey(lotteryId, mode);
//        SPUtils.putString(mContext, keyF, json);
        mRemotePlayMap.put(keyF, lotteryPlayStarts);
    }

    public List<LotteryPlayStart> getRemotePlay(int lotteryId, int mode) {
        String key = getPlayKey(lotteryId, mode);
        List<LotteryPlayStart> list = mRemotePlayMap.get(key);
        if (list == null || list.size() == 0) {
            list = getSpRemotePlay(lotteryId, mode);
        }
        return list;
    }

    public Map<String, List<LotteryPlayStart>> getRemotePlayMap() {
        return mRemotePlayMap;
    }

    private String getPlayKey(int lotteryId, int mode) {
        String key = mMerchant + "_" + "ybcp_remote_play_lotteryId_" + lotteryId + " _mode_" + mode;
        return key;
    }

    public List<RemoteLotteryPlay> getZixuanbuzhongOdd_list() {
        String key = "ybcp_lhc_zxbz" + getCurLotteryId();
        List<RemoteLotteryPlay> playList = zixuanbuzhongOdd_map.get(getCurLotteryId());
        if (playList == null || 0 == playList.size()) {
            String json = SPUtils.getString(mContext, key);
            if (json == null) {
                return new ArrayList<>();
            }
            try {
                playList = mGson.fromJson(json, new TypeToken<List<RemoteLotteryPlay>>() {
                }.getType());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (playList != null) {
                zixuanbuzhongOdd_map.put(getCurLotteryId(), playList);
            }
        }
        return playList;
    }

    public void setZixuanbuzhongOdd_List(List<RemoteLotteryPlay> list) {
        if (zixuanbuzhongOdd_map.get(getCurLotteryId()) != null) {
            return;
        }
        String key = "ybcp_lhc_zxbz" + getCurLotteryId();
        zixuanbuzhongOdd_map.put(getCurLotteryId(), list);
        String json = mGson.toJson(list);
        SPUtils.putString(mContext, key, json);
    }

    public int getQuickMoney() {
        return mQuickMoney;
    }

    public void setQuiickMoney(int quickMoney) {
        mQuickMoney = quickMoney;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setBetParamEntity(MkBetParamEntity betParamEntity) {
        this.betParamEntity = betParamEntity;
    }

    public MkBetParamEntity getBetParamEntity() {
        return betParamEntity;
    }

    /**
     * 服务器时间顺着计时器计时
     */
    public void cdServerTime() {
        if (mServerTime != 0) {
            mServerTime += 1000;
        }
    }

    public void setServerTime(long serverTime) {
        mServerTime = serverTime;
    }

    public long getServerTime() {
        return mServerTime;
    }


    public Map<Integer, Long> getLotteryTimeMap() {
        return mLotteryTimeMap;
    }


    public Map<Integer, String> getLotteryPlanMap() {
        return mLotteryPlanMap;
    }

    public void updateLotteryTime(int id, String planId, long time) {
        if(0 != id && !TextUtils.isEmpty(planId)) {
            mLotteryTimeMap.put(id, time);
            mLotteryPlanMap.put(id, planId);
        }
    }

    public long getLotteryTime(Integer id) {
        Long time = mLotteryTimeMap.get(id);
        if (time == null) {
            time = 0L;
        }
        return time;
    }

    public String getLotteryPlanId(Integer id) {
        String planId = mLotteryPlanMap.get(id);
        if (planId == null) {
            planId = "";
        }
        return planId;
    }

    public void setLotteryHistory(Integer lotteryId, List<LotteryNumBean> lotteryNumList) {
        mLotteryHistoryMap.put(lotteryId, lotteryNumList);
        RxBus.get().post(EVENT_RESULT_HISTORY_LIST_UPDATE, "100 期历史结果获取成功");
    }

    public List<LotteryNumBean> getLotteryHistory(Integer lotteryId) {
        List<LotteryNumBean> list = mLotteryHistoryMap.get(lotteryId);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    public void setBalance(MemberDetailEntity balance) {
        this.balance = balance;
    }

    public MemberDetailEntity getBalance() {
        return balance;
    }

    public void cleanBetData() {
        betParamEntity = new MkBetParamEntity();
    }


    public void initLotteryIds() {
        List<LotterySer> lotterySers = DataCenter.getInstance().getLottery();
        List<Integer> idList2 = new ArrayList<>();
        for (LotterySer bean : lotterySers) {
            for (LotteryBean ticket : bean.getList()) {
                idList2.add(ticket.getTicketId());
                LotteryTimeUtil.addLotteryIds(ticket.getTicketId());
            }
        }
    }

    public List<BallBean> getBallBeanList() {
        return ballBeanList;
    }

    public void setBallBeanList(List<BallBean> ballBeanList) {
        this.ballBeanList = ballBeanList;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public User getUser() {
        return mUser;
    }
}
