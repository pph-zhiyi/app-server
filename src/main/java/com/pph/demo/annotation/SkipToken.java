package com.pph.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: pph
 * @Date: 2019/9/19 19:58
 * @Description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SkipToken {
    /**
     * 是否跳过 token 验证
     *
     * @return
     */
    boolean required() default true;
}
