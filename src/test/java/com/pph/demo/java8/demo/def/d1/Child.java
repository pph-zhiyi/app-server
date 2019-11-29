package com.pph.demo.java8.demo.def.d1;

/**
 * @author: pph
 * @date: 2019/11/26 18:33
 * @description:
 */
public interface Child extends Parent {

    @Override
    default void welcome() {
        message("Child: Hi!");
    }
}
