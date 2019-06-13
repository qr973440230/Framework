package com.qr.core.framework.configration.room.redis;

import android.util.LruCache;

import androidx.annotation.NonNull;

class MemoryCache {
    private final LruCache<String, CacheValue> mMemoryCache;

    MemoryCache(){
        this.mMemoryCache = new LruCache<>(256);
    }

    MemoryCache(int maxSize) {
        this.mMemoryCache = new LruCache<>(maxSize);
    }

    void put(@NonNull final String key, final Object value) {
        put(key, value, -1);
    }

    void put(@NonNull final String key, final Object value, int saveTime) {
        if (value == null) return;
        long dueTime = saveTime < 0 ? -1 : System.currentTimeMillis() + saveTime * 1000;
        mMemoryCache.put(key, new CacheValue(dueTime, value));
    }

    <T> T get(@NonNull final String key) {
        return get(key, null);
    }

    <T> T get(@NonNull final String key, final T defaultValue) {
        CacheValue val = mMemoryCache.get(key);
        if (val == null) return defaultValue;
        if (val.dueTime == -1 || val.dueTime >= System.currentTimeMillis()) {
            //noinspection unchecked
            return (T) val.value;
        }
        mMemoryCache.remove(key);
        return defaultValue;
    }

    int getMaxSize() {
        return mMemoryCache.size();
    }

    Object remove(@NonNull final String key) {
        CacheValue remove = mMemoryCache.remove(key);
        if (remove == null) return null;
        return remove.value;
    }

    void clear() {
        mMemoryCache.evictAll();
    }

    private static final class CacheValue {
        long   dueTime;
        Object value;

        CacheValue(long dueTime, Object value) {
            this.dueTime = dueTime;
            this.value = value;
        }
    }
}