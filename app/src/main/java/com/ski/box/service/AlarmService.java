package com.ski.box.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.ski.box.R;
import com.ski.box.bean.DataCenter;
import com.ski.box.utils.lottery.LotteryTimeUtil;
import com.yb.core.utils.AppUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AlarmService extends Service {
    public static final String CHANNEL_ID_STRING = "service_01";
    public static String ALARM_ACTION = "COM.YB.SDK.ALARM.SERVICE";
    private  Disposable subscribe;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 华为P30 重启后，不走该逻辑，其他手机无该问题
     */

    @SuppressLint("CheckResult")
    @Override
    public void onCreate() {
        super.onCreate();
        observableTime();

    }

    private  void observableTime() {

        //适配8.0service
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) AppUtil.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID_STRING, getString(R.string.app_name),
                    NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(channel);
            Notification notification = new Notification.Builder(getApplicationContext(), CHANNEL_ID_STRING).build();
            startForeground(1, notification);
        }

        subscribe = Observable.interval(0, 1, TimeUnit.SECONDS).flatMap(new Function<Long, Observable<Long>>() {
            @Override
            public Observable<Long> apply(Long integer) throws Exception {
                DataCenter.getInstance().cdServerTime();
                LotteryTimeUtil.countTime();
                Intent intent = new Intent(ALARM_ACTION + DataCenter.getInstance().getToken());
                AppUtil.getContext().sendBroadcast(intent);
                return Observable.just(1L);
            }
        }).subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {

                    }
                });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscribe != null) {
            subscribe.dispose();
        }

    }
}

