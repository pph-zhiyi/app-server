package com.pph.demo.effective.other.annotation;

import java.lang.annotation.*;

/**
 * @Author: PPH
 * @Date: 2019-07-09 12:45
 * @Description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ExceptionTestContainer.class)
public @interface ExceptionTest {

    Class<? extends Throwable> value();

//    Class<? extends Throwable>[] value();
}
