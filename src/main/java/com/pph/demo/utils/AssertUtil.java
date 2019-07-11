package com.pph.demo.utils;

import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Author: PPH
 * @Date: 2019-07-11 09:00
 * @Description:
 */
@Component
public final class AssertUtil {
    /**
     * 私有构造
     */
    private AssertUtil() {
    }

    /**
     * 对象非空校验：
     * <p>
     * 1、非空：返回原对象
     * 2、空：抛出 IllegalArgumentException 异常（无异常描述）
     *
     * @param obj
     * @param <E>
     * @return
     */
    public <E> E notNull(E obj) {
        return Optional.ofNullable(obj).orElseThrow(IllegalArgumentException::new);
    }

    /**
     * 对象非空校验：
     * <p>
     * 1、非空：返回原对象
     * 2、空：抛出 IllegalArgumentException 异常（自定义异常描述）
     *
     * @param obj
     * @param msg
     * @param <E>
     * @return
     */
    public <E> E notNull(E obj, String msg) {
        return Optional.ofNullable(obj).<IllegalArgumentException>orElseThrow(() -> {
            throw new IllegalArgumentException(notNull(msg));
        });
    }
}
