package com.qr.core.framework.di.modules.base;

import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;
import com.qr.core.framework.Application;
import com.qr.core.framework.configration.room.redis.LocalRedis;
import com.qr.core.framework.configration.room.redis.LocalRedisDatabase;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class ApplicationModule {
    @Singleton
    @Provides
    static LocalRedis localRedis(Application application,Gson gson){
        return new LocalRedis(Room.databaseBuilder(application,
                LocalRedisDatabase.class, "LocalRedis.db")
                .build().localRedisDao(), gson);
    }

    // GSON
    @Singleton
    @Provides
    static Gson gson(){
        GsonBuilder builder = new GsonBuilder();

        // TODO: GSON Config
        builder.serializeNulls() // 序列化null
                .enableComplexMapKeySerialization();

        return builder.create();
    }

    // OkHttpClient
    @Singleton
    @Provides
    static OkHttpClient okHttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        // TODO: OkHttpClient Config
        // 设置超时时间
        builder.connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS);

        // 网络日志记录日志记录
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Logger.d(message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);

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

        // 使用Gson RxJava
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));

        return builder.build();
    }
}
