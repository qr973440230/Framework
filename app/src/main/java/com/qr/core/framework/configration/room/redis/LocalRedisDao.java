package com.qr.core.framework.configration.room.redis;

import androidx.annotation.WorkerThread;
import androidx.room.Dao;
import androidx.room.Query;

import com.qr.core.framework.configration.room.base.BaseDao;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

@Dao
public interface LocalRedisDao extends BaseDao<LocalRedisEntity> {
    @Query("delete from localredisentity")
    void clear();
    @Query("delete from localredisentity")
    Completable clearCompletable();

    @WorkerThread
    @Query("select * from localredisentity where `key` =:key")
    LocalRedisEntity findByKey(String key);
    @Query("select * from localredisentity where `key` =:key")
    Observable<LocalRedisEntity> findByKeyAsObservable(String key);
    @Query("select * from localredisentity where `key` =:key")
    Flowable<LocalRedisEntity> findByKeyAsFlowable(String key);

    @WorkerThread
    @Query("select * from localredisentity")
    List<LocalRedisEntity> findAll();
    @Query("select * from localredisentity")
    Observable<List<LocalRedisEntity>> findAllAsObservable();
    @Query("select * from localredisentity")
    Flowable<List<LocalRedisEntity>> findAllAsFlowable();
}
