package com.liongjfuan.android_distribution.cache;

import android.util.LruCache;

import java.io.PipedReader;

/**
 *
 * @author Lifu.Zheng
 * @date 2017.12.12
 */

public class CacheManager {

    private static CacheManager mCacheManager;
    private int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    private int cacheSize = maxMemory / 8;
    private LruCache<String, String> mMemoryCache;

    public CacheManager() {
        mMemoryCache = new LruCache<>(cacheSize);
    }

    /**
     * 获取CacheManager实例
     * @return
     */
    public static synchronized CacheManager getInstance() {
        if (CacheManager.mCacheManager == null) {
            CacheManager.mCacheManager = new CacheManager();
        }
        return CacheManager.mCacheManager;
    }

    public void putCache(String key, String data) {
        mMemoryCache.put(key, data);
    }

    public String getCache(String key) {
        return mMemoryCache.get(key);

    }
}
