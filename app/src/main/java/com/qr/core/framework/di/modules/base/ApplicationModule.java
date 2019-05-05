package com.qr.core.framework.di.modules.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;
import com.qr.core.framework.Application;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import me.jessyan.progressmanager.ProgressManager;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {
    // GSON
    @Singleton
    @Provides
    static Gson gson(){
        GsonBuilder builder = new GsonBuilder();

        // TODO: GSON Config
        builder.serializeNulls()
                .enableComplexMapKeySerialization();

        return builder.create();
    }

    // OkHttpClient
    @Singleton
    @Provides
    static OkHttpClient okHttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        // TODO: OkHttpClient Config
        // 超时时间
        builder.connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS);

        // 网络日志记录日志记录
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Logger.i("Network Logger: " + message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);

        // 上传和下载进度监听
        ProgressManager.getInstance().with(builder);
        // 支持多个BaseUrl 和动态改变BaseUrl
        RetrofitUrlManager.getInstance().with(builder);
        return builder.build();
    }

    // Retrofit
    @Singleton
    @Provides
    static Retrofit retrofit(OkHttpClient client, Gson gson){
        Retrofit.Builder builder = new Retrofit.Builder();

        // TODO: Retrofit Config
        builder.baseUrl("Http://www.baidu.com")
                .client(client);

        // 使用RxJava Gson
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));

        return builder.build();
    }

    // RxCache
    @Singleton
    @Provides
    static RxCache rxCache(Application application, Gson gson){
        RxCache.Builder builder = new RxCache.Builder();
        // TODO: RxCache Config

        // 设定存储路径
        File cacheFile = new File(application.getExternalCacheDir(),"RxCache");
        if(!cacheFile.exists()){
            cacheFile.mkdirs();
        }

        builder.useExpiredDataIfLoaderNotAvailable(true);
        return builder.persistence(cacheFile,new GsonSpeaker(gson));
    }

    // RxBus
    @Singleton
    @Provides
    static Subject<Object> rxBus(){
        // TODO: 修改RxBus Subject类型
        return BehaviorSubject.create().toSerialized();
    }
}
