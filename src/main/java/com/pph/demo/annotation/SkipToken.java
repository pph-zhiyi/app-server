package com.pph.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author pph
 * @date 2019/9/19 19:58
 * @description 作用在 Controller 的 API 上，默认跳过 Token 校验
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SkipToken {
    /**
     * 是否跳过 token 验证
     *
     * @return true 跳过，false 不跳过
     */
    boolean required() default true;
}
