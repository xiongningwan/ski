package com.ski.box.bean.lottery;

import android.text.TextUtils;

import com.ski.box.bean.BallBean;
import com.ski.box.bean.DataCenter;
import com.yb.core.utils.LanguageUtil;
import com.yb.core.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_PLAY_MODE_DOUBLE;


/**
 * Created by tom on 2020/5/28.
 */
public class PlayUtil {
    protected static Map<String, RemoteLotteryPlay> mPlayMap = new HashMap<>();
    // 左标题
    protected static Map<String, RemoteLotteryPlay> mRemoteStartMap = new HashMap<>();
    // 副标题
    protected static Map<String, RemoteLotteryPlay> mRemoteSubPlayMap = new HashMap<>();

    public static void addLayoutData(List<RemoteLotteryPlay> remotePlays, int lotteryId, int mode) {
        List<LotteryPlayStart> plays = DataCenter.getInstance().getLotteryPlay(lotteryId, mode);
        int serId = LotteryUtil.getSerIdByLotteryId(lotteryId);
        setPlayMap(serId, remotePlays);
        // 后台隐藏玩法
        List<LotteryPlayStart> hidePlayStarts = new ArrayList<>();

        for (int i = 0; i < plays.size(); i++) {
            // 双面
            LotteryPlayStart playStart = plays.get(i);
            setRemoteStart(playStart, hidePlayStarts);
            for (int j = 0; j < playStart.getSubPlays().size(); j++) {
                // 副标题 双面副
                LotteryPlaySub playSub = playStart.getSubPlays().get(j);
                List<LotteryPlayEnd> playEnds = playSub.getLotteryPlayEnds();
                setRemoteSub(playSub);

                for (int k = 0; k < playEnds.size(); k++) {
                    // 总和龙虎和
                    LotteryPlayEnd playEnd = playEnds.get(k);
                    for (int z = 0; z < playEnd.getLotteryPlays().size(); z++) {
                        //da
                        LotteryPlay play = playEnd.getLotteryPlays().get(z);
                        RemoteLotteryPlay remotePlay = null;
                        // 正常
                        String key = playStart.getRemoteCode() + playEnd.getRemoteCode() + play.getRemoteCodeUp() + play.getRemoteCode();
                        remotePlay = mPlayMap.get(key);
                        if (remotePlay == null) {
                            //
                            key = playStart.getRemoteCode() + playSub.getRemoteCode() + playEnd.getRemoteCode() + play.getRemoteCode();
                            remotePlay = mPlayMap.get(key);
                        }
                        if (remotePlay == null) {
                            key = playStart.getRemoteCode() + playSub.getRemoteCode() + play.getRemoteCodeUp() + play.getRemoteCode();
                            remotePlay = mPlayMap.get(key);
                        }
                        if (remotePlay == null) {
                            // k3 长短牌
                            key = playStart.getRemoteCode() + playEnd.getRemoteCode() + play.getRemoteCodeUp() + play.getRemoteCode();
                            remotePlay = mPlayMap.get(key);
                        }

                        remotePlay = beforeDoSpecialRemotePlay(serId, playStart, playSub, play, remotePlay);

                        if (remotePlay == null) {
                            LogUtils.e("[" + playStart.getTitle() + " " + playSub.getTitleSub() + " " + playEnd.getTag() + " " + play.getCode() + " ] ");
                        }
                        // LogUtils.d("[" + key + " ] ");

                        if (remotePlay != null) {
                            remotePlay = specialPlay(lotteryId, playStart, playSub, null, play, remotePlay);
                            play.setId(remotePlay.getId());

                            if (!remotePlay.isDefaultName() && !TextUtils.isEmpty(remotePlay.getName())) {
                                play.setName(remotePlay.getName());
                            }
                            setFanyi(lotteryId, playStart, playSub, play);
                            if (!remotePlay.isDefaultCode() && !TextUtils.isEmpty(remotePlay.getCode())) {
                                play.setCode(remotePlay.getCode());
                            }
                            play.setOdds(remotePlay.getOdds());
                        }
                    }
                }
            }
        }

        // 删除后台隐藏玩法
        hidePlays(plays, hidePlayStarts);

        boolean isLoadFail = false;
        if (0 == remotePlays.size()) {
            isLoadFail = true;
        }
        DataCenter.getInstance().saveRemotePlay(lotteryId, mode, plays, isLoadFail);
    }

    private static void hidePlays(List<LotteryPlayStart> plays, List<LotteryPlayStart> hidePlayStarts) {
//        List<LotteryPlayStart> hidePlayStarts = new ArrayList<>();
//        for (int i = 0; i < plays.size(); i++) {
//            // 双面
//            LotteryPlayStart playStart = plays.get(i);
//            for (int j = 0; j < playStart.getSubPlays().size(); j++) {
//                // 副标题 双面副
//                List<LotteryPlaySub> playSubs = playStart.getSubPlays();
//                LotteryPlaySub playSub = playSubs.get(j);
//                for(int k = 0; k < hidePlaySubs.size(); k++) {
//                    LotteryPlaySub playSub1 = hidePlaySubs.get(k);
//                    if(playSub.getPlayId() == playSub1.getPlayId()) {
//                        playSubs.remove(playSub);
//                    }
//                }
//                if(0 == playSubs.size()) {
//                    hidePlayStarts.add(playStart);
//                }
//            }
//        }

        plays.removeAll(hidePlayStarts);
    }

    private static RemoteLotteryPlay beforeDoSpecialRemotePlay(int serId, LotteryPlayStart playStart, LotteryPlaySub playSub, LotteryPlay play, RemoteLotteryPlay remotePlay) {
        String key;
        if (LotteryConstant.SER_ID_LHC == serId) {
            if ("tema".equals(playStart.getRemoteCode())) { //特码
                if ("texiaotouweixiao".equals(playSub.getRemoteCode())) { //特肖头尾数
                    if (remotePlay == null) {
                        key = playStart.getRemoteCode() + playSub.getRemoteCode() + "texiao_tematexiaobmn" + play.getRemoteCode();
                        remotePlay = mPlayMap.get(key);
                    }
                    if (remotePlay == null) {
                        key = playStart.getRemoteCode() + playSub.getRemoteCode() + "texiao_tematexiaofbmn" + play.getRemoteCode();
                        remotePlay = mPlayMap.get(key);
                    }
                }
            } else if ("zhengma".equals(playStart.getRemoteCode())) { //正码
                if ("zhengxiaoqisebo".equals(playSub.getRemoteCode())) { //正肖七色波
                    if (remotePlay == null) {
                        key = playStart.getRemoteCode() + playSub.getRemoteCode() + "zhengxiao_zhengxiaofbm" + play.getRemoteCode();
                        remotePlay = mPlayMap.get(key);
                    }
                    if (remotePlay == null) {
                        key = playStart.getRemoteCode() + playSub.getRemoteCode() + "zhengxiao_zhengxiaobm" + play.getRemoteCode();
                        remotePlay = mPlayMap.get(key);
                    }
                }
            } else if ("yixiaozongxiaopingteweishu".equals(playStart.getRemoteCode())) { //一肖总肖平特尾数
                if ("yixiao".equals(playSub.getRemoteCode())) { //一肖
                    if (remotePlay == null) {
                        key = playStart.getRemoteCode() + playSub.getRemoteCode() + "xuanshengxiao_zhengtemaxuanshengxiaofbm" + play.getRemoteCode();
                        remotePlay = mPlayMap.get(key);
                    }
                    if (remotePlay == null) {
                        key = playStart.getRemoteCode() + playSub.getRemoteCode() + "xuanshengxiao_zhengtemaxuanshengxiaobm" + play.getRemoteCode();
                        remotePlay = mPlayMap.get(key);
                    }
                }
            } else if ("lianma".equals(playStart.getRemoteCode())) {//连码
                if ("sanzhonger".equals(playSub.getRemoteCode())) { //三中二
                    key = "lianmasanzhongerzhengmasanzhongersanzhonger";
                    remotePlay = mPlayMap.get(key);
                } else if ("sanquanzhong".equals(playSub.getRemoteCode())) {//三全中
                    key = "lianmasanquanzhongzhengmasanquanzhongsanquanzhong";
                    remotePlay = mPlayMap.get(key);
                } else if ("erquanzhong".equals(playSub.getRemoteCode())) {//二全中
                    key = "lianmaerquanzhongzhengmaerquanzhongerquanzhong";
                    remotePlay = mPlayMap.get(key);
                } else if ("erzhongte".equals(playSub.getRemoteCode())) {//二中特
                    key = "lianmaerzhongtezhengtemaerzhongteerzhongte";
                    remotePlay = mPlayMap.get(key);
                } else if ("techuang".equals(playSub.getRemoteCode())) {//特串
                    key = "lianmatechuangzhengtematechuangtechuang";
                    remotePlay = mPlayMap.get(key);
                } else if ("siquanzhong".equals(playSub.getRemoteCode())) {//四全中
                    key = "lianmasiquanzhongzhengmasiquanzhongsiquanzhong";
                    remotePlay = mPlayMap.get(key);
                }
            } else if ("zixuanbuzhong".equals(playStart.getRemoteCode())) { //连码
                key = "zixuanbuzhongzixuanbuzhongzhengtemazixuanbuzhongwwwuhao";
                remotePlay = mPlayMap.get(key);
            }
        }
        return remotePlay;
    }

    private static void setRemoteStart(LotteryPlayStart playStart, List<LotteryPlayStart> hidePlayStarts) {
//        RemoteLotteryPlay remoteStartPlay = mRemoteStartMap.get(playStart.getTitle());
        RemoteLotteryPlay remoteStartPlay = mRemoteStartMap.get(playStart.getRemoteCode());
        if (remoteStartPlay != null) {
            playStart.setId(remoteStartPlay.getId());
            playStart.setCode(remoteStartPlay.getCode());
            playStart.setTitle(remoteStartPlay.getName());
        } else {
            // 添加隐藏
            hidePlayStarts.add(playStart);
        }
    }

    private static void setRemoteSub(LotteryPlaySub playSub) {
        RemoteLotteryPlay remotePlaySub = mRemoteSubPlayMap.get(playSub.getRemoteCode());
        if (remotePlaySub != null) {
            playSub.setCode(remotePlaySub.getCode());
            playSub.setTitleSub(remotePlaySub.getName());
        }
    }


    private static void setPlayMap(int serId, List<RemoteLotteryPlay> remotePlays) {
        mPlayMap.clear();
        mRemoteStartMap.clear();
        mRemoteSubPlayMap.clear();
        for (int i = 0; i < remotePlays.size(); i++) {
            // 双面
            RemoteLotteryPlay remote1 = remotePlays.get(i);
//            mRemoteStartMap.put(remote1.getName(), remote1);
            mRemoteStartMap.put(remote1.getCode(), remote1);
            for (int j = 0; j < remote1.getList().size(); j++) {
                // 总和龙虎和
                RemoteLotteryPlay remote2 = remote1.getList().get(j);
                mRemoteSubPlayMap.put(remote2.getCode(), remote2);
                for (int k = 0; k < remote2.getList().size(); k++) {
                    //总和大小
                    RemoteLotteryPlay remote3 = remote2.getList().get(k);
                    for (int z = 0; z < remote3.getList().size(); z++) {
                        //da
                        RemoteLotteryPlay play = remote3.getList().get(z);
                        String key = remote1.getCode() + remote2.getCode() + remote3.getCode() + play.getCode();
                        mPlayMap.put(key, play);
                        LogUtils.d(remote1.getCode() + remote2.getCode() + remote3.getCode() + play.getCode());
                    }
                }
            }
        }

    }


    private static RemoteLotteryPlay specialPlay(int lotteryId, LotteryPlayStart
            playStart, LotteryPlaySub playSub, LotteryPlayEnd playEnd, LotteryPlay play, RemoteLotteryPlay remotePlay) {
        int serId = LotteryUtil.getSerIdByLotteryId(lotteryId);
        if (LotteryConstant.SER_ID_3D == serId) {
            if ("liangzidingwei".equals(playStart.getRemoteCode())) {//二字定位
                if (remotePlay != null && TextUtils.isEmpty(playSub.getOdds())) {
                    playSub.setOdds(remotePlay.getOdds());
//                    setRemotePlayNameToLocal(remotePlay);
                    remotePlay.setDefaultName(true);
                    remotePlay.setDefaultCode(true);
                }
            } else if ("sanzidingwei".equals(playStart.getRemoteCode())) { // 三字定位
                if (remotePlay != null && TextUtils.isEmpty(playSub.getOdds())) {
                    playSub.setOdds(remotePlay.getOdds());
//                    setRemotePlayNameToLocal(remotePlay);
                    remotePlay.setDefaultName(true);
                    remotePlay.setDefaultCode(true);
                }
            }
            if ("zusan".equals(playStart.getRemoteCode())) {//组三
                if (remotePlay != null && TextUtils.isEmpty(playSub.getOdds())) {
                    playSub.setOdds(remotePlay.getOdds());
//                    setRemotePlayNameToLocal(remotePlay);
                    remotePlay.setDefaultName(true);
                    remotePlay.setDefaultCode(true);
                }
            } else if ("zuliu".equals(playStart.getRemoteCode())) {//组六
                if (remotePlay != null && TextUtils.isEmpty(playSub.getOdds())) {
                    playSub.setOdds(remotePlay.getOdds());
//                    setRemotePlayNameToLocal(remotePlay);
                    remotePlay.setDefaultName(true);
                    remotePlay.setDefaultCode(true);
                }
            }
        } else if (LotteryConstant.SER_ID_11X5 == serId) {
            if ("zuxuan".equals(playStart.getCode())) {//组选
//                setRemotePlayNameToLocal(remotePlay);
                remotePlay.setDefaultName(true);
                remotePlay.setDefaultCode(true);
                playSub.setOdds(remotePlay.getOdds());
            } else if ("renxuan".equals(playStart.getCode())) {//任选
//                setRemotePlayNameToLocal(remotePlay);
                remotePlay.setDefaultName(true);
                remotePlay.setDefaultCode(true);
                playSub.setOdds(remotePlay.getOdds());
            } else if ("zhixuan".equals(playStart.getCode())) {//直选
//                setRemotePlayNameToLocal(remotePlay);
                remotePlay.setDefaultName(true);
                remotePlay.setDefaultCode(true);
                playSub.setOdds(remotePlay.getOdds());
            }
        } else if (LotteryConstant.SER_ID_LHC == serId) {
            if ("tema".equals(playStart.getRemoteCode())) { //特码
                if ("hexiao".equals(playSub.getRemoteCode())) { //合肖
                    remotePlay.setDefaultCode(true);
                    playSub.setOdds(remotePlay.getOdds());
                    remotePlay.setDefaultName(true);
                }
            } else if ("lianxiaolianwei".equals(playStart.getRemoteCode())) { //连肖连尾
                String key2 = "";
                if ("erlianxiao".equals(playSub.getRemoteCode())) {//二连肖
                    key2 = "lianxiaolianweierlianxiaozhengtemaerlianxiaorenxuanerxiao";
                } else if ("sanlianxiao".equals(playSub.getRemoteCode())) { //三连肖
                    key2 = "lianxiaolianweisanlianxiaozhengtemasanlianxiaorexuansanxiao";
                } else if ("silianxiao".equals(playSub.getRemoteCode())) {//四连肖
                    key2 = "lianxiaolianweisilianxiaozhengtemasilianxiaorenxuansixiao";
                } else if ("wulianxiao".equals(playSub.getRemoteCode())) {
                    key2 = "lianxiaolianweiwulianxiaozhengtemawulianxiaorenxuanwuxiao";
                } else if ("erlianwei".equals(playSub.getRemoteCode())) {//二连尾
                    key2 = "lianxiaolianweierlianweizhengtemaerlianweirenxuanerwei";
                } else if ("sanlianwei".equals(playSub.getRemoteCode())) {//三连尾
                    key2 = "lianxiaolianweisanlianweizhengtemasanlianweirenxuansanwei";
                } else if ("silianwei".equals(playSub.getRemoteCode())) {//四连尾
                    key2 = "lianxiaolianweisilianweizhengtemasilianweirenxuansiwei";
                } else if ("wulianwei".equals(playSub.getRemoteCode())) {//五连尾
                    key2 = "lianxiaolianweiwulianweizhengtemawulianweirenxuanwuwei";
                }

                RemoteLotteryPlay remotePlay2 = mPlayMap.get(key2);
                if (remotePlay2 != null) {
                    remotePlay.setId(remotePlay2.getId());
                }
                remotePlay.setDefaultCode(true);
                remotePlay.setDefaultName(true);
            } else if ("lianma".equals(playStart.getRemoteCode())) { //连码
                if ("sanzhonger".equals(playSub.getRemoteCode())) {//三中二
                    String key = "lianmasanzhongerzhengmasanzhongersanzhonger"; // id;
                    String key1 = "lianmasanzhongerzhengmasanzhongerzezhonger"; // 中2
                    String key2 = "lianmasanzhongerzhengmasanzhongerzszhongsan"; // 中3
                    RemoteLotteryPlay remotePlay1 = mPlayMap.get(key1);
                    RemoteLotteryPlay remotePlay2 = mPlayMap.get(key2);
                    if (remotePlay != null && remotePlay1 != null && remotePlay2 != null) {
                        remotePlay.setOdds(remotePlay2.getOdds());
                        playSub.setOdds(remotePlay1.getOdds() + "," + remotePlay2.getOdds());
                    }
                } else if ("sanquanzhong".equals(playSub.getRemoteCode())) {//三全中
                    playSub.setOdds(remotePlay.getOdds());
                } else if ("erquanzhong".equals(playSub.getRemoteCode())) {//二全中
                    playSub.setOdds(remotePlay.getOdds());
                } else if ("erzhongte".equals(playSub.getRemoteCode())) {//二中特
                    String key = "lianmaerzhongtezhengtemaerzhongteerzhongte"; // id;
                    String key1 = "lianmaerzhongtezhengtemaerzhongtezezhonger";
                    String key2 = "lianmaerzhongtezhengtemaerzhongteztzhongte";
                    RemoteLotteryPlay remotePlay1 = mPlayMap.get(key1);
                    RemoteLotteryPlay remotePlay2 = mPlayMap.get(key2);
                    if (remotePlay != null && remotePlay1 != null && remotePlay2 != null) {
                        remotePlay.setOdds(remotePlay1.getOdds());
                        playSub.setOdds(remotePlay1.getOdds() + "," + remotePlay2.getOdds());
                    }
                } else if ("techuang".equals(playSub.getRemoteCode())) {//特串
                    playSub.setOdds(remotePlay.getOdds());
                } else if ("siquanzhong".equals(playSub.getRemoteCode())) {//四全中
                    playSub.setOdds(remotePlay.getOdds());
                }
                if (remotePlay != null) {
                    remotePlay.setDefaultName(true);
                    remotePlay.setDefaultCode(true);
                }
            } else if ("zixuanbuzhong".equals(playStart.getRemoteCode()) && "01".equals(play.getCode())) { //自选不中
                playSub.setOdds("/");
                saveZixbzOdd();
                if (remotePlay != null) {
                    remotePlay.setDefaultName(true);
                    remotePlay.setDefaultCode(true);
                }
            }
        }
//        else if (LotteryConstant.SER_ID_F1_JJS == serId) {
//            if ("danhao".equals(playSub.getRemoteCode())) {//选号
//                playSub.setOdds(remotePlay.getOdds());
//                play.setName(ConfigurationUiUtils.getF1JJSName(remotePlay.getCode()));
//                if (remotePlay != null) {
//                    remotePlay.setDefaultName(true);
//                    remotePlay.setDefaultCode(true);
//                }
//            }
//        }

        return remotePlay;
    }

    private static void setRemotePlayNameToLocal(RemoteLotteryPlay remotePlay) {
        if (!"zh".equals(LanguageUtil.getLanguage())) {
            remotePlay.setDefaultName(true);
        }
    }


    public static void saveZixbzOdd() {
        String key1 = "zixuanbuzhongzixuanbuzhongzhengtemazixuanbuzhongwwwuhao";//5
        String key2 = "zixuanbuzhongzixuanbuzhongzhengtemazixuanbuzhonglwliuhao";//6
        String key3 = "zixuanbuzhongzixuanbuzhongzhengtemazixuanbuzhongqwqihao";//7
        String key4 = "zixuanbuzhongzixuanbuzhongzhengtemazixuanbuzhongbwbahao";//8
        String key5 = "zixuanbuzhongzixuanbuzhongzhengtemazixuanbuzhongjwjiuhao";//9
        String key6 = "zixuanbuzhongzixuanbuzhongzhengtemazixuanbuzhongswshihao";//10
        String key7 = "zixuanbuzhongzixuanbuzhongzhengtemazixuanbuzhongyywyiyihao";//11
        List<RemoteLotteryPlay> list = new ArrayList<>();
        RemoteLotteryPlay remotePlay1 = mPlayMap.get(key1);
        RemoteLotteryPlay remotePlay2 = mPlayMap.get(key2);
        RemoteLotteryPlay remotePlay3 = mPlayMap.get(key3);
        RemoteLotteryPlay remotePlay4 = mPlayMap.get(key4);
        RemoteLotteryPlay remotePlay5 = mPlayMap.get(key5);
        RemoteLotteryPlay remotePlay6 = mPlayMap.get(key6);
        RemoteLotteryPlay remotePlay7 = mPlayMap.get(key7);
        if (remotePlay1 != null) {
            list.add(remotePlay1);
        }
        if (remotePlay2 != null) {
            list.add(remotePlay2);
        }
        if (remotePlay3 != null) {
            list.add(remotePlay3);
        }
        if (remotePlay4 != null) {
            list.add(remotePlay4);
        }
        if (remotePlay5 != null) {
            list.add(remotePlay5);
        }
        if (remotePlay6 != null) {
            list.add(remotePlay6);
        }
        if (remotePlay7 != null) {
            list.add(remotePlay7);
        }

        DataCenter.getInstance().setZixuanbuzhongOdd_List(list);
    }


    private static void setFanyi(int lotteryId, LotteryPlayStart
            playStart, LotteryPlaySub playSub, LotteryPlay play) {
        int serId = LotteryUtil.getSerIdByLotteryId(lotteryId);
        if (LotteryConstant.SER_ID_LHC == serId) {
            if ("tema".equals(playStart.getRemoteCode())) { //特码
                if ("hexiao".equals(playSub.getRemoteCode())) { //合肖
                    setFanyiSx(play);// 生肖
                } else if ("texiaotouweixiao".equals(playSub.getRemoteCode())) { //特肖头尾数
                    setFanyiSx(play); // 生肖
                }
            } else if ("zhengma".equals(playStart.getRemoteCode())) { //正码
                if ("zhengxiaoqisebo".equals(playSub.getRemoteCode())) { //正肖七色波
                    setFanyiSx(play); // 生肖
                }
            } else if ("lianxiaolianwei".equals(playStart.getRemoteCode())) { //连肖连尾
                String subRemoteCode = playSub.getRemoteCode();
                if ("erlianxiao".equals(subRemoteCode) //二连肖
                        || "sanlianxiao".equals(subRemoteCode)
                        || "silianxiao".equals(subRemoteCode)
                        || "wulianxiao".equals(subRemoteCode)) {
                    setFanyiSx(play); // 生肖
                }  else if("erlianwei".equals(subRemoteCode) //二连肖
                        || "sanlianwei".equals(subRemoteCode)
                        || "silianwei".equals(subRemoteCode)
                        || "wulianwei".equals(subRemoteCode)) {
                    play.setName(LanguageUtil.getText(play.getName()));
                }

            } else if ("yixiaozongxiaopingteweishu".equals(playStart.getRemoteCode())) { //一肖总肖平特尾数
                setFanyiSx(play);//连肖
            }
        }
    }

    public static final String[] SHENG_XIAO = new String[]{"shu", "niu", "hu", "tu", "long", "she", "ma", "yang", "hou", "ji", "gou", "zhu"};
    public static final String[] shengXiaoZH = new String[]{"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊","猴", "鸡", "狗", "猪"};

    private static void setFanyiSx(LotteryPlay play) {
        for(int i = 0; i < SHENG_XIAO.length; i++) {
            if (SHENG_XIAO[i].equals(play.getCode())) {
                play.setName(LanguageUtil.getText(" " + shengXiaoZH[i] + " "));
                continue;
            }
        }
    }


    //------------------------路子图和长龙-------------------------------

    /**
     * 获取对应的赔率
     *
     * @return
     */
    public static List<BallBean> getRoadOdds(Integer[] ids, int index) {
        int lotteryId = DataCenter.getInstance().getCurLotteryId();
        List<BallBean> list = new ArrayList<>();
        // int mode = DataCenter.getInstance().getPlayMode();
        int mode = LOTTERY_PLAY_MODE_DOUBLE;
        List<LotteryPlayStart> plays = DataCenter.getInstance().getRemotePlay(lotteryId, mode);
        for (int i = 0; i < plays.size(); i++) {
            // 双面
            LotteryPlayStart playStart = plays.get(i);
            for (int j = 0; j < playStart.getSubPlays().size(); j++) {
                // 副标题 双面副
                LotteryPlaySub subPlay = playStart.getSubPlays().get(j);
                List<LotteryPlayEnd> playEnds = subPlay.getLotteryPlayEnds();
                for (int k = 0; k < playEnds.size(); k++) {
                    // 总和龙虎和
                    LotteryPlayEnd playEnd = playEnds.get(k);
                    for (int z = 0; z < playEnd.getLotteryPlays().size(); z++) {
                        //da
                        LotteryPlay play = playEnd.getLotteryPlays().get(z);
                        if (play != null) {
                            for (int h = 0; h < ids.length; h++) {
                                if (play.getRoadId() == ids[h]) {
                                    BallBean bean = new BallBean();
                                    bean.setPlayItemOdds(play.getOdds());
                                    bean.setPlayItemName(play.getName());
                                    bean.setPlayItemId(String.valueOf(play.getId()));
                                    bean.setPlayItemCode(play.getCode());
                                    bean.setCheck(index == h);
                                    list.add(bean);
                                }
                            }
                        }
                    }
                }
            }
        }
        return list;
    }


    /**
     * @param
     * @param ticketId
     * @return
     */
    public static List<BallBean> getDragonOdds(long playItemId, int ticketId, int mode) {
        List<BallBean> list = new ArrayList<>();
        List<LotteryPlayStart> plays = DataCenter.getInstance().getRemotePlay(ticketId, mode);
        for (int i = 0; i < plays.size(); i++) {
            // 双面
            LotteryPlayStart playStart = plays.get(i);
            for (int j = 0; j < playStart.getSubPlays().size(); j++) {
                // 副标题 双面副
                LotteryPlaySub subPlay = playStart.getSubPlays().get(j);
                List<LotteryPlayEnd> playEnds = subPlay.getLotteryPlayEnds();
                for (int k = 0; k < playEnds.size(); k++) {
                    // 总和龙虎和
                    LotteryPlayEnd playEnd = playEnds.get(k);
                    List<LotteryPlay> lotteryPlays = playEnd.getLotteryPlays();

                    for (int z = 0; z < lotteryPlays.size(); z++) {
                        //da
                        LotteryPlay lotteryPlay = lotteryPlays.get(z);
                        long id = lotteryPlay.getId();
                        if (id == playItemId) {
                            String playCode = lotteryPlay.getCode();
                            HashSet<String> codeList = getPlayName(playCode);
                            for (int w = 0; w < lotteryPlays.size(); w++) {
                                LotteryPlay play = lotteryPlays.get(w);
                                String code = play.getCode();
                                Iterator<String> iterator = codeList.iterator();
                                while (iterator.hasNext()) {
                                    String s = iterator.next();
                                    if (code.equalsIgnoreCase(s)) {
                                        BallBean bean = new BallBean();
                                        bean.setPlayItemOdds(play.getOdds());
                                        bean.setPlayItemName(play.getName());
                                        bean.setPlayItemId(String.valueOf(play.getId()));
                                        bean.setPlayItemCode(play.getCode());
                                        bean.setCheck(playCode.equalsIgnoreCase(s));
                                        list.add(bean);
                                    }
                                }

                            }


                        }

                    }
                }
            }
        }
        return list;
    }

    private static HashSet<String> getPlayName(String code) {
        HashSet<String> codes = new HashSet<>();
        switch (code) {
            case "da":
            case "xiao":
                codes.add("da");
                codes.add("xiao");
                break;
            case "dan":
            case "shuang":
                codes.add("dan");
                codes.add("shuang");
                break;
            case "zhi":
            case "hen":
                codes.add("zhi");
                codes.add("hen");
                break;
            case "long":
            case "hu":
            case "he":
                codes.add("long");
                codes.add("hu");
                codes.add("he");
                break;
            case "heweixiao":
            case "heweida":
                codes.add("heweixiao");
                codes.add("heweida");
                break;
            case "heweizhi":
            case "heweihe":
                /*3d*/
                codes.add("heweizhi");
                codes.add("heweihe");
                break;
            case "hedan":
            case "heshuang":
                /*3d*/
                codes.add("hedan");
                codes.add("heshuang");
                break;
            case "weida":
            case "weixiao":
                /*11xuan5*/
                codes.add("weida");
                codes.add("weixiao");
                break;
            case "qianduo":
            case "houduo":
                /*快乐彩*/
                codes.add("qianduo");
                codes.add("houduo");
                break;
            case "danduo":
            case "shuangduo":
                /*快乐彩*/
                codes.add("danduo");
                codes.add("shuangduo");
                break;
            case "heda":
            case "hexiao":
                /*六合彩*/
                codes.add("heda");
                codes.add("hexiao");
                break;
            case "tianxiao":
            case "dixiao":
                /*六合彩*/
                codes.add("tianxiao");
                codes.add("dixiao");
                break;
            case "qianxiao":
            case "houxiao":
                /*六合彩*/
                codes.add("qianxiao");
                codes.add("houxiao");
                break;
            case "jiaxiao":
            case "yexiao":
                /*六合彩*/
                codes.add("jiaxiao");
                codes.add("yexiao");
                break;
            case "hongbo":
            case "lvbo":
            case "lanbo":
                /*六合彩*/
                codes.add("hongbo");
                codes.add("lvbo");
                codes.add("lanbo");
                break;


        }
        return codes;
    }


}
