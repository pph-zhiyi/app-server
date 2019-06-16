package com.pph.demo.configs.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: PPH
 * @Date: 2019-06-15 18:53
 * @Description:
 */
@Component
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 默认过期时长，单位：秒
     */
    private static final long DEFAULT_EXPIRE = TimeUnit.SECONDS.convert(1, TimeUnit.DAYS);

    /**
     * 不设置过期时长
     */
    private static final long NOT_EXPIRE = -1;

    /**
     * 是否存在此 key
     *
     * @param key
     * @return
     */
    public boolean existsKey(String key) {
        Assert.hasText(key, "key con not be blank!");
        return redisTemplate.hasKey(key);
    }

    /**
     * 重名名 key，如果 newKey 已经存在，则 newKey 的原值被覆盖
     *
     * @param oldKey
     * @param newKey
     */
    public void renameKey(String oldKey, String newKey) {
        Assert.hasText(oldKey, "oldKey con not be blank!");
        Assert.hasText(newKey, "newKey con not be blank!");
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * newKey 不存在时才重命名
     *
     * @param oldKey
     * @param newKey
     * @return
     */
    public boolean renameKeyNotExist(String oldKey, String newKey) {
        Assert.hasText(oldKey, "oldKey con not be blank!");
        Assert.hasText(newKey, "newKey con not be blank!");
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 删除 key
     *
     * @param key
     */
    public void deleteKey(String key) {
        Assert.hasText(key, "key con not be blank!");
        redisTemplate.delete(key);
    }

    /**
     * 删除多个 key
     *
     * @param keys
     */
    public void deleteKey(String... keys) {
        Assert.notNull(keys, "keys con not be null!");
        Set<String> kSet = Stream.of(keys).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }

    /**
     * 删除 Key 的集合
     *
     * @param keys
     */
    public void deleteKey(Collection<String> keys) {
        Assert.notNull(keys, "keys con not be null!");
        redisTemplate.delete(new HashSet<>(keys));
    }

    /**
     * 设置 key 的生命周期
     *
     * @param key
     * @param time
     * @param timeUnit
     */
    public void expireKey(String key, long time, TimeUnit timeUnit) {
        Assert.hasText(key, "key con not be blank!");
        redisTemplate.expire(key, time, timeUnit);
    }

    /**
     * 指定 key 在指定的日期过期
     *
     * @param key
     * @param date
     */
    public void expireKeyAt(String key, Date date) {
        Assert.hasText(key, "key con not be blank!");
        Assert.notNull(date, "date con not be null!");
        redisTemplate.expireAt(key, date);
    }

    /**
     * 查询 key 的生命周期
     *
     * @param key
     * @param timeUnit
     * @return
     */
    public long getKeyExpire(String key, TimeUnit timeUnit) {
        Assert.hasText(key, "key con not be blank!");
        return redisTemplate.getExpire(key, timeUnit);
    }

    /**
     * 将 key 设置为永久有效
     *
     * @param key
     */
    public void persistKey(String key) {
        Assert.hasText(key, "key con not be blank!");
        redisTemplate.persist(key);
    }
}
