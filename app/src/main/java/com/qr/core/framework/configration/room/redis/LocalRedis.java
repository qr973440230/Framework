package com.qr.core.framework.configration.room.redis;

import androidx.annotation.WorkerThread;

import com.google.gson.Gson;

import java.util.Objects;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class LocalRedis {
    private LocalRedisDao localRedisDao;
    private Gson gson;

    public LocalRedis(LocalRedisDao localRedisDao, Gson gson) {
        this.localRedisDao = localRedisDao;
        this.gson = gson;
    }

    @WorkerThread
    public void put(String key,Object o){
        Objects.requireNonNull(key);
        localRedisDao.insert(new LocalRedisEntity(key,gson.toJson(o)));
    }
    public Completable putAsCompletable(String key, Object o){
        Objects.requireNonNull(key);
        return localRedisDao.insertAsCompletable(new LocalRedisEntity(key,gson.toJson(o)));
    }
    public Single<Long> putAsSingle(String key,Object o){
        Objects.requireNonNull(key);
        return localRedisDao.insertAsSingle(new LocalRedisEntity(key,gson.toJson(o)));
    }

    @WorkerThread
    public <T> T get(String key,Class<T> tClass){
        Objects.requireNonNull(key);
        return gson.fromJson(localRedisDao.findByKey(key).value,tClass);
    }

    public <T> Observable<T> getAsObservable(String key,Class<T> tClass){
        Objects.requireNonNull(key);
        return localRedisDao.findByKeyAsObservable(key)
                .map(localRedisEntity -> {
                    return gson.fromJson(localRedisEntity.value,tClass);
                });
    }
    public <T> Flowable<T> getAsFlowable(String key,Class<T> tClass){
        Objects.requireNonNull(key);
        return localRedisDao.findByKeyAsFlowable(key)
                .map(localRedisEntity -> {
                    return gson.fromJson(localRedisEntity.value,tClass);
                });
    }

    @WorkerThread
    public void clear(){
        localRedisDao.clear();
    }

    public Completable clearAsCompletable(){
        return localRedisDao.clearCompletable();
    }
}
