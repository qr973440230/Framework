package com.qr.core.framework;

import android.app.Activity;
import android.app.Service;

import com.google.gson.Gson;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.qr.core.framework.di.component.DaggerApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;
import okhttp3.OkHttpClient;

public class Application extends android.app.Application implements HasActivityInjector, HasServiceInjector {
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<Service> serviceDispatchingAndroidInjector;

    public static Application application;

    @Inject
    OkHttpClient okHttpClient;

    @Inject
    Gson gson;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        DaggerApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this);

        // 初始化日志记录 打印日志到文件
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    public OkHttpClient getOkHttpClient(){
        return okHttpClient;
    }
    public Gson getGson(){
        return gson;
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return serviceDispatchingAndroidInjector;
    }
}
