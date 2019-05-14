package com.qr.core.framework;

import android.app.Activity;
import android.app.Service;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.CsvFormatStrategy;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
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

    @Inject
    OkHttpClient okHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this);

        // 初始化日志记录
        if(BuildConfig.DEBUG){
            PrettyFormatStrategy prettyFormatStrategy = PrettyFormatStrategy.newBuilder()
                    .tag("Logger:")
                    .build();
            Logger.addLogAdapter(new AndroidLogAdapter(prettyFormatStrategy));
        }else{
            // 记录到文件中
            CsvFormatStrategy csvFormatStrategy = CsvFormatStrategy.newBuilder()
                    .tag("Logger:")
                    .build();
            Logger.addLogAdapter(new DiskLogAdapter(csvFormatStrategy));
        }

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    public OkHttpClient getOkHttpClient(){
        return okHttpClient;
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return serviceDispatchingAndroidInjector;
    }
}
