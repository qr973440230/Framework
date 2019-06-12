package com.qr.core.framework.configration.room.base;

import androidx.annotation.WorkerThread;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface BaseDao<T> {
    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(T data);
    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<T> data);
    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(T[] data);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAsCompletable(T data);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAllAsCompletable(List<T> data);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAllAsCompletable(T[] data);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insertAsSingle(T localRedisEntity);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertAllAsSingle(T[] data);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertAllAsSingle(List<T> data);

    @WorkerThread
    @Delete
    void delete(T data);
    @WorkerThread
    @Delete
    void deleteAll(List<T> data);
    @WorkerThread
    @Delete
    void deleteAll(T[] data);
    @Delete
    Completable deleteAsCompletable(T data);
    @Delete
    Completable deleteAllAsCompletable(List<T> data);
    @Delete
    Completable deleteAllAsCompletable(T[] data);
    @Delete
    Single<Integer> deleteAsSingle(T data);
    @Delete
    Single<Integer> deleteAllAsSingle(List<T> data);
    @Delete
    Single<Integer> deleteAllAsSingle(T[] data);
}
