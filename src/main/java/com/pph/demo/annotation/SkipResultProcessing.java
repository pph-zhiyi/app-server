package com.pph.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author pph
 * @date 2020/3/26 21:43
 * @description 作用于 Controller 的 API 上，默认跳过结果格式化处理
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SkipResultProcessing {
    /**
     * 是否跳过结果格式化处理
     *
     * @return true 跳过，false 不跳过
     */
    boolean required() default true;
}
