package com.ycl.car.utils;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ycl.car.ContantValue;
import com.ycl.car.MainActivity;
import com.ycl.car.MyApp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by y11621546 on 2017/1/21.
 */

public class HttpManager {
    private volatile static HttpManager INSTANCE;
    private String BASE_URL = ContantValue.BASE_URL;
    private final long DEFAULT_TIMEOUT = 60 * 1000;
    private HttpService httpService;
    private Retrofit retrofit;

    //构造方法私有
    private HttpManager() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        if (MyApp.getInstance().getSharedPreferences().contains("ip")) {
            BASE_URL =  MyApp.getInstance().getSharedPreferences().getString("ip", ContantValue.BASE_URL) ;
        }
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        httpService = retrofit.create(HttpService.class);
    }

    public void setBASE_URL() {
        Log.d("HttpManager", MyApp.getInstance().getSharedPreferences().getString("ip", ""));
        Log.d("HttpManager1", ContantValue.BASE_URL);
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        if (MyApp.getInstance().getSharedPreferences().contains("ip")) {
            BASE_URL =  MyApp.getInstance().getSharedPreferences().getString("ip", ContantValue.BASE_URL) ;
        }
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        httpService = retrofit.create(HttpService.class);
    }

    //获取单例
    public static HttpManager getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpManager();
                }
            }
        }
        return INSTANCE;
    }


    public HttpService getHttpService() {
        return httpService;

    }
}
