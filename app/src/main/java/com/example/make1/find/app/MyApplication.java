package com.example.make1.find.app;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import okhttp3.OkHttpClient;

/**
 * Created by make1 on 2017/7/25.
 * 初始化、OkHttp网络请求
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
        SDKInitializer.initialize(this);//在应用程序创建时初始化SDK引用的Context全局变量
                                        //在Application初始化方法中，initialize(this)；
                                        //在其他类中，initialize(getApplicationContext()

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("TAG"))//其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient); //初始化okHttpClient

    }
}
