package com.ski.box;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.ski.box.exception.ApiExLister;
import com.yb.core.utils.ToastUtil;


public class SKIApplication extends Application {
    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        SKISdkManger.init(context);
      //  initOkhttp();

    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    /**
     * 获取全局上下文
     */
    public static Context getContext() {
        return context;
    }


}
