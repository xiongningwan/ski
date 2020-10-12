package com.ski.box;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.ski.box.exception.ApiExLister;
import com.ski.box.view.activity.old.HttpsUtils;
import com.yb.core.net.RetrofitHelper;
import com.yb.core.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


public class SKIApplication extends Application {
    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        SKISdkManger.init(context, new ApiExLister() {
            @Override
            public void tokenExpired() {
                ToastUtil.showWarning("token已经失效，请重新登录!");
            }
        });
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


    private void initOkhttp() {
        HttpsUtils.SSLParams ssf = HttpsUtils.getSslSocketFactory(null, null, null);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .sslSocketFactory(ssf.sSLSocketFactory, ssf.trustManager)
                .hostnameVerifier(ssf.unSafeHostnameVerifier)
                .build();

        OkHttpUtils.initClient(okHttpClient);

    }
}
