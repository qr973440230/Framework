package com.qr.core.framework.configration.room.redis;

import androidx.annotation.WorkerThread;
import androidx.room.Dao;
import androidx.room.Query;

import com.qr.core.framework.configration.room.base.BaseDao;

import java.util.List;

@Dao
interface LocalRedisDao extends BaseDao<LocalRedisEntity> {
    @Query("delete from localredisentity where `key` =:key")
    void deleteByKey(String key);
    @Query("delete from localredisentity")
    void clear();

    @WorkerThread
    @Query("select * from localredisentity where `key` =:key")
    LocalRedisEntity findByKey(String key);

    @WorkerThread
    @Query("select * from localredisentity")
    List<LocalRedisEntity> findAll();
}
