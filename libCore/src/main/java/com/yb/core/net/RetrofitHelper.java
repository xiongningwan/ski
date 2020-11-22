package com.yb.core.net;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.Proxy;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    public static String baseUrl = "http://192.168.1.50:8080/api";
    private static final int DEFAULT_TIMEOUT_SECONDS = 20;
    private static final int DEFAULT_READ_TIMEOUT_SECONDS = 20;
    private static final int DEFAULT_WRITE_TIMEOUT_SECONDS = 20;
    private Retrofit mRetrofit;
    private boolean isDebug;
    private Map headerMap;


    public void init(String baseUrl, boolean isDebug, Map headerMap) {
        RetrofitHelper.baseUrl = baseUrl;
        this.isDebug = isDebug;
        this.headerMap = headerMap;
        createHelper();
    }


    private RetrofitHelper() {
    }

    private void createHelper() {
        HttpsUtils.SSLParams ssf = HttpsUtils.getSslSocketFactory(null, null, null);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .proxy(Proxy.NO_PROXY)
                .sslSocketFactory(ssf.sSLSocketFactory, ssf.trustManager)
                .hostnameVerifier(ssf.unSafeHostnameVerifier)
                .addInterceptor( new LoggingInterceptor(headerMap));


        // HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //   日志拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (isDebug) {
            interceptor.level(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.level(HttpLoggingInterceptor.Level.NONE);
        }
        builder.addInterceptor(interceptor);  // 添加httplog

//        if (mRetrofit == null ) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create(gson)) //添加Gson支持
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //添加RxJava支持
                    .client(builder.build()) //关联okhttp
                    .build();

//        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    private static class SingletonHolder {
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }

    public static RetrofitHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取服务对象   Rxjava+Retrofit建立在接口对象的基础上的
     * 泛型避免强制转换
     */
    public static <T> T getService(Class<T> classz) {
        return RetrofitHelper.getInstance().mRetrofit.create(classz);
    }


}
