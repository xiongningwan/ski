package com.ski.box.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.hwangjr.rxbus.RxBus;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.LotteryNumBean;
import com.ski.box.bean.MemberDetailEntity;
import com.ski.box.bean.Mqtt_OpenResult;
import com.yb.core.utils.AppUtil;
import com.yb.core.utils.LogUtils;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONException;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.ski.box.ConstantValue.EVENT_GET_UNSETTLE_LIST;
import static com.ski.box.ConstantValue.EVENT_OPEN_RESULT_UPDATE;
import static com.ski.box.ConstantValue.EVENT_TYPE_BALANCE_UPDATE;


public class MQTTHelper {
    public static final String TAG = MQTTHelper.class.getSimpleName();
    private MqttAndroidClient mClient;
    private MqttConnectOptions mConOpt;
    //    private String mHost = "ws://172.18.3.11:8083/mqtt";
    private String mUserName = "admin";
    private String mPassWord = "password";
    private String mMQTTUrl;
    private String mTopic;
    private String mClientId = "test";
    private Disposable mTaskDisp;

    private static MQTTHelper instance;
    private static Gson mGson;
    private boolean mTaskRunning;

    public static MQTTHelper getInstance() {
        if (instance == null) {
            instance = new MQTTHelper();
        }
        return instance;
    }

    public void onDestroy() {
        unSubscribeConnectTask();
        disconnectClient();
    }

    private void disconnectClient() {
        try {
            if (mClient != null) {
                mClient.disconnect();
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String msg) {
        String topic = mTopic;
        Integer qos = 1;
        Boolean retained = false;
        try {
            mClient.publish(topic, msg.getBytes(), qos, retained.booleanValue());
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void init(Context context, String mqttUrl, String token, String topic) {
        mGson = new Gson();
        //*服务器地址（协议+地址+端口号）*//*
        mMQTTUrl = mqttUrl;
        mTopic = topic;
        mClientId = token;
        mClient = new MqttAndroidClient(context, mMQTTUrl, mClientId);
        //*设置MQTT监听并且接受消息*//*
        mClient.setCallback(mMqttCallback);
        mConOpt = new MqttConnectOptions();
        //*清除缓存*//*
        mConOpt.setCleanSession(true);
        //*设置超时时间，单位：秒*//*
        mConOpt.setConnectionTimeout(1000);
        //*心跳包发送时间间隔*//*
        mConOpt.setKeepAliveInterval(60);
        //*用户名*//*
        mConOpt.setUserName(mUserName);
        //*密码*//*
        mConOpt.setPassword(mPassWord.toCharArray());

        boolean doConnect = true;
        String message = "{\"terminal_uid\":\"" + mClientId + "\"}";
        Integer qos = 0;
        Boolean retained = false;
        if ((!message.equals("")) || (!topic.equals(""))) {
            try {
                mConOpt.setWill(topic, message.getBytes(), qos.intValue(), retained.booleanValue());
            } catch (Exception e) {
                doConnect = false;
                mIMqttActionListener.onFailure(null, e);
            }

        }
        if (doConnect) {
            doClientConnection();
        }
    }

    //*连接MQTT服务器*//*
    private void doClientConnection() {
        if (!mClient.isConnected() && isConnectIsNormal()) {
            try {
                mClient.connect(mConOpt, null, mIMqttActionListener);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    //*MQTT是否连接成功*//*
    private IMqttActionListener mIMqttActionListener = new IMqttActionListener() {
        @Override
        public void onSuccess(IMqttToken asyncActionToken) {
            //*订阅mTopic话题*//*
            try {
                LogUtils.e("连接成功：订阅mTopic话题");
                mClient.subscribe(mTopic, 1); // 订阅余额
                mClient.subscribe("SABANG_BROADCAST_MEMBER_TOPIC", 1); // 订阅奖期
                unSubscribeConnectTask();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
            //失败重连
            LogUtils.e("连接失败：" + exception.getMessage());
            crateConnectTask();
        }
    };

    // 设置MQTT监听并且接受消息
    private MqttCallback mMqttCallback = new MqttCallback() {
        @Override
        public void deliveryComplete(IMqttDeliveryToken token) {

        }

        @Override
        public void connectionLost(Throwable cause) {
            if (cause == null) {
                return;
            }
            // 失去连接重连
            LogUtils.e("连接丢失：" + cause.getMessage());
            crateConnectTask();
        }

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            onMessageResponse(message.toString());
        }


    };

    // 判断网络是否连接
    private boolean isConnectIsNormal() {
        ConnectivityManager connectivityManager = (ConnectivityManager) AppUtil.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            String name = networkInfo.getTypeName();
            return true;
        } else {
            return false;
        }
    }


    private static void onMessageResponse(String message) {
        try {
            LogUtils.d(message);
            org.json.JSONObject jsonObject = new org.json.JSONObject(message);
            boolean type1 = jsonObject.has("msgType");
            if (type1) {
                String type = jsonObject.getString("msgType");
                String data = jsonObject.getString("data");
                switch (type) {
                    case "1": // 近期开奖
                        RxBus.get().post(EVENT_GET_UNSETTLE_LIST, "推送未结算注单");
                        break;
                    case "2": {
                        break;
                    }
                    case "3": {
                        break;
                    }
                    case "4": {
//                        PrizeStateBean.DataBean bean = JSON.parseObject(data, new TypeReference<PrizeStateBean.DataBean>() {
//                        });
//                        Apollo.emit(ApolloEvent.PRIZE_STATE, bean);
//                        BettingManager.getInstance().removeDrawTicketIds(50);
                        break;
                    }
                    case "5":
                        // 余额变化
                        MemberDetailEntity bean = mGson.fromJson(data, MemberDetailEntity.class);
                        DataCenter.getInstance().setBalance(bean.getBalance());
                        break;
                    case "6":
                        // 开奖结果
                        Mqtt_OpenResult openResult = mGson.fromJson(data, Mqtt_OpenResult.class);
                        String openCode = openResult.getOpenCode().replace(",", " ");
                        LotteryNumBean lotteryNumBean = new LotteryNumBean(openResult.getTicketPlanNo(), openCode, openResult.getTicketId());
                        RxBus.get().post(EVENT_OPEN_RESULT_UPDATE, lotteryNumBean);
                        break;

                    default:
                        break;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void crateConnectTask() {
        if (mTaskRunning) {
            return;
        }
        if (mTaskDisp != null && !mTaskDisp.isDisposed()) {
            mTaskDisp.dispose();
        }
        mTaskRunning = true;
        long interval = 1000 * 5 * 1;
        mTaskDisp = Observable.interval(100, interval, TimeUnit.MILLISECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                doClientConnection();
            }
        });
    }

    protected void unSubscribeConnectTask() {
        mTaskRunning = false;
        if (this.mTaskDisp != null) {
            this.mTaskDisp.dispose();
        }
    }
}

