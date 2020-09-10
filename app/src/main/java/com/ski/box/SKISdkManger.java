package com.ski.box;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.ski.box.bean.DataCenter;
import com.ski.box.exception.ApiExLister;
import com.ski.box.service.AlarmService;
import com.yb.core.net.RetrofitHelper;
import com.yb.core.utils.AppUtil;
import com.yb.core.utils.LogUtils;
import com.yb.core.utils.ScreenUtils;
import com.yb.core.utils.ToastUtil;

import java.util.ArrayList;

public class SKISdkManger {
    private static ApiExLister mApiExLister;
    public static void init(@NonNull Context context, ApiExLister apiExLister) {
        AppUtil.init(context);
        ScreenUtils.init(context);
        ToastUtil.initToasty();
        mApiExLister = apiExLister;
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new LifecycleChecker());
    }

    public static void setUrlAndToken(String merchant, String url,boolean isDebug, String token) {
        LogUtils.setDebug(isDebug);
        DataCenter.getInstance().setMerchant(merchant);
        DataCenter.getInstance().setToken(token);
//        DataCenter.getInstance().getLottery();
        DataCenter.getInstance().initLotteryIds();
        RetrofitHelper.getInstance().init(url, isDebug, token);
    }

    //------------------倒计时服务---------------------------------------
    public static void startAlarmService(Context context) {
        String name = AlarmService.class.getName();
        boolean alarmService = isServiceRunning(context, name);
        if (!alarmService) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //P30 这里启动无效
                context.startForegroundService(new Intent(context, AlarmService.class));
            } else {
                context.startService(new Intent(context, AlarmService.class));
            }
        }else{
        }
    }

    private static boolean isServiceRunning(Context context, String ServiceName) {
        if (TextUtils.isEmpty(ServiceName)) {
            return false;
        }
        ActivityManager myManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        if (myManager != null) {
            ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) myManager
                    .getRunningServices(30);
            for (int i = 0; i < runningService.size(); i++) {
                if (runningService.get(i).service.getClassName().toString()
                        .equals(ServiceName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
