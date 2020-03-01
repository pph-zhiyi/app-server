package com.pph.demo.configs.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

/**
 * @author: pph
 * @date: 2020/2/7 16:13
 * @description: 本地缓存提供者服务（Guava Cache）
 */
@Configuration
@ComponentScan(basePackages = AppConst.BASE_PACKAGE_NAME)
@Qualifier("localCacheProviderServiceImpl")
public class LocalCacheProviderServiceImpl implements CacheProviderService {

    private static Map<String, Cache<String, Object>> _cacheMap = Maps.newConcurrentMap();

    static {
        Cache<String, Object> cacheContainer = CacheBuilder.newBuilder()
                .maximumSize(AppConst.CACHE_MAXIMUM_SIZE)
//                最后一次写入后的一段时间移出
                .expireAfterWrite(AppConst.CACHE_MINUTE, TimeUnit.MILLISECONDS)
//                最后一次访问后的一段时间移出
//                .expireAfterAccess(AppConst.CACHE_MINUTE, TimeUnit.MILLISECONDS)
//                开启统计功能
                .recordStats()
                .build();

        _cacheMap.put(String.valueOf(AppConst.CACHE_MINUTE), cacheContainer);
    }

    @Override
    public <T> T get(String key) {
        return get(key, null, null, AppConst.CACHE_MINUTE);
    }

    @Override
    public <T> T get(String key, Function<String, T> function) {
        return get(key, function, key, AppConst.CACHE_MINUTE);
    }

    @Override
    public <T, M> T get(String key, Function<M, T> function, M funcParam) {
        return get(key, function, funcParam, AppConst.CACHE_MINUTE);
    }

    @Override
    public <T> T get(String key, Function<String, T> function, Long expireTime) {
        return get(key, function, key, expireTime);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, M> T get(String key, Function<M, T> function, M funcParam, Long expireTime) {
        T obj = null;
        if (StringUtils.isEmpty(key)) {
            return null;
        }

        expireTime = getExpireTime(expireTime);

        Cache<String, Object> cacheContainer = getCacheContainer(expireTime);
        if (Objects.nonNull(cacheContainer)) {
            try {
                if (Objects.isNull(function)) {
                    obj = (T) cacheContainer.getIfPresent(key);
                } else {
                    obj = (T) cacheContainer.get(key, () -> function.apply(funcParam));
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        return obj;
    }

    @Override
    public <T> void set(String key, T obj) {
        set(key, obj, AppConst.CACHE_MINUTE);
    }

    @Override
    public <T> void set(String key, T obj, Long expireTime) {
        if (StringUtils.isEmpty(key)) {
            return;
        }

        if (Objects.isNull(obj)) {
            return;
        }

        expireTime = getExpireTime(expireTime);
        Cache<String, Object> cacheContainer = getCacheContainer(expireTime);
        if (Objects.nonNull(cacheContainer)) {
            cacheContainer.put(key, obj);
        }
    }

    @Override
    public void remove(String key) {
        if (StringUtils.isEmpty(key)) {
            return;
        }

        long expireTime = getExpireTime(AppConst.CACHE_MINUTE);
        Cache<String, Object> cacheContainer = getCacheContainer(expireTime);
        if (Objects.nonNull(cacheContainer)) {
            cacheContainer.invalidate(key);
        }
    }

    @Override
    public boolean contains(String key) {
        boolean exists = false;

        if (StringUtils.isEmpty(key)) {
            return false;
        }

        Object obj = get(key);
        if (Objects.nonNull(obj)) {
            exists = true;
        }

        return exists;
    }

    /**
     * 获取过期时间（单位：毫秒）
     *
     * @param expireTime 传入过期时间（单位：毫秒）如果小于一分钟，默认为十分钟
     * @return 结果
     */
    private Long getExpireTime(Long expireTime) {
        Long result = expireTime;
        if (Objects.isNull(expireTime) || expireTime < AppConst.CACHE_MINUTE) {
            result = AppConst.CACHE_MINUTE;
        }
        return result;
    }

    private static Lock lock = new ReentrantLock();

    private Cache<String, Object> getCacheContainer(Long expireTime) {
        Cache<String, Object> cacheContainer;

        if (Objects.isNull(expireTime)) {
            return null;
        }

        String mapKey = String.valueOf(expireTime);
        if (_cacheMap.containsKey(mapKey)) {
            return _cacheMap.get(mapKey);
        }

        try {
            lock.lock();
            cacheContainer = CacheBuilder.newBuilder()
                    .maximumSize(AppConst.CACHE_MAXIMUM_SIZE)
//                最后一次写入后的一段时间移出
                    .expireAfterWrite(AppConst.CACHE_MINUTE, TimeUnit.MILLISECONDS)
//                最后一次访问后的一段时间移出
//                .expireAfterAccess(AppConst.CACHE_MINUTE, TimeUnit.MILLISECONDS)
//                开启统计功能
                    .recordStats()
                    .build();
            _cacheMap.put(String.valueOf(AppConst.CACHE_MINUTE), cacheContainer);
        } finally {
            lock.unlock();
        }

        return cacheContainer;
    }
}
