package com.qr.core.framework.configration.room.redis;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LocalRedisEntity {
    @NonNull
    @PrimaryKey
    String key;
    String value;

    LocalRedisEntity(String key,String value){
        this.key = key;
        this.value = value;
    }
}
