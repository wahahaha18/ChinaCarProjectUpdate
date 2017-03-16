package com.ycl.car;

import android.app.Application;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;

/**
 * 应用入口
 * Created by y11621546 on 2017/1/17.
 */

public class MyApp extends Application {
    public static final String TAG = MyApp.class.getName();
    private SharedPreferences sharedPreferences;
    private static MyApp instance;
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override

    public void onCreate() {
        super.onCreate();
        instance = this;
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        sharedPreferences = getSharedPreferences("MyApp", MODE_PRIVATE);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("TAG"))
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    public static MyApp getInstance() {
        return instance;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
