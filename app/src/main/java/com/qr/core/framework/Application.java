package com.qr.core.framework;

import com.google.gson.Gson;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.qr.core.framework.di.component.DaggerGlobalComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import okhttp3.OkHttpClient;

public class Application extends android.app.Application implements HasAndroidInjector {
    public static Application application;

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Inject
    OkHttpClient okHttpClient;

    @Inject
    Gson gson;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        DaggerGlobalComponent.builder()
                .application(this)
                .build()
                .inject(this);

        // 初始化日志记录 打印日志到文件
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public Gson getGson() {
        return gson;
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
