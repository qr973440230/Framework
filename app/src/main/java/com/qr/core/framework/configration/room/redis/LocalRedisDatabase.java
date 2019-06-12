package com.qr.core.framework.configration.room.redis;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = LocalRedisEntity.class,version = 1)
public abstract class LocalRedisDatabase extends RoomDatabase {
    public abstract LocalRedisDao localRedisDao();
}
