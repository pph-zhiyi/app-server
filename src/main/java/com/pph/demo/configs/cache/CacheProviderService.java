package com.pph.demo.configs.cache;

import java.util.function.Function;

/**
 * @author: pph
 * @date: 2020/2/7 15:57
 * @description: 缓存提供者接口
 */
public interface CacheProviderService {
    /**
     * 查询缓存
     *
     * @param key 缓存键
     * @param <T> 范型
     * @return 结果
     */
    <T> T get(String key);

    /**
     * 查询缓存
     *
     * @param key      缓存键
     * @param function 如果没有缓存，调用该callable 函数返回对象
     * @param <T>      范型
     * @return 结果
     */
    <T> T get(String key, Function<String, T> function);

    /**
     * 查询缓存
     *
     * @param key       缓存键
     * @param function  如果没有缓存，调用该callable 函数返回对象
     * @param funcParam function 函数的调用参数
     * @param <T>       范型
     * @return 结果
     */
    <T, M> T get(String key, Function<M, T> function, M funcParam);

    /**
     * 查询缓存
     *
     * @param key        缓存键
     * @param function   如果没有缓存，调用该callable 函数返回对象
     * @param expireTime 过期时间（单位：毫秒）
     * @param <T>        范型
     * @return 结果
     */
    <T> T get(String key, Function<String, T> function, Long expireTime);

    /**
     * 查询缓存
     *
     * @param key        缓存键
     * @param function   如果没有缓存，调用该callable 函数返回对象
     * @param funcParam  function 函数的调用参数
     * @param expireTime 过期时间（单位：毫秒）
     * @param <T>        范型
     * @return 结果
     */
    <T, M> T get(String key, Function<M, T> function, M funcParam, Long expireTime);

    /**
     * 设置缓存键
     *
     * @param key 缓存键
     * @param obj 缓存值
     * @param <T> 范型
     */
    <T> void set(String key, T obj);

    /**
     * 设置缓存键
     *
     * @param key        缓存键
     * @param obj        缓存值
     * @param expireTime 过期时间（单位：毫秒）
     * @param <T>        范型
     */
    <T> void set(String key, T obj, Long expireTime);

    /**
     * 移除缓存
     *
     * @param key 缓存键
     */
    void remove(String key);

    /**
     * 是否存在缓存
     *
     * @param key 缓存键
     * @return 结果
     */
    boolean contains(String key);
}
