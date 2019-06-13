package com.qr.core.framework.configration.room.redis;

import androidx.annotation.WorkerThread;
import androidx.room.Room;

import com.google.gson.Gson;
import com.qr.core.framework.Application;

import java.lang.reflect.Type;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.schedulers.Schedulers;

@Singleton
public class LocalRedis {
    private LocalRedisDao localRedisDao;
    private Gson gson;
    private MemoryCache memoryCache;

    @Inject
    public LocalRedis(Application application, Gson gson) {
        this.localRedisDao = Room.databaseBuilder(application,
                LocalRedisDatabase.class,
                "LocalRedis.db")
                .allowMainThreadQueries()
                .build()
                .localRedisDao();
        this.gson = gson;
        memoryCache = new MemoryCache();
    }

    public void put(String key,Object o){
        Objects.requireNonNull(key);
        Schedulers.io().scheduleDirect(() ->{
            memoryCache.put(key,o);
            localRedisDao.insert(new LocalRedisEntity(key,gson.toJson(o)));
        });
    }

    @WorkerThread
    public <T> T get(String key,Class<T> tClass){
        Objects.requireNonNull(key);
        T t = memoryCache.get(key);

        if(t != null){
            return t;
        }

        T fromJson = gson.fromJson(localRedisDao.findByKey(key).value, tClass);
        if(fromJson != null){
            memoryCache.put(key,fromJson);
        }

        return fromJson;
    }

    @WorkerThread
    public <T> T get(String key, Type type){
        Objects.requireNonNull(key);
        T t = memoryCache.get(key);

        if(t != null){
            return t;
        }

        T fromJson = gson.fromJson(localRedisDao.findByKey(key).value, type);
        if(fromJson != null){
            memoryCache.put(key,fromJson);
        }

        return fromJson;
    }

    public void remove(String key){
        Schedulers.io().scheduleDirect(()->{
           memoryCache.remove(key);
           localRedisDao.deleteByKey(key);
        });
    }

    public void clear(){
        Schedulers.io().scheduleDirect(()->{
            memoryCache.clear();
            localRedisDao.clear();
        });
    }
}
