package com.pph.demo.java8.shizhan.d9;

/**
 * @author pph
 * @date 2019/12/13 08:43
 * @description
 */
public interface MyList<T> {

    T head();

    MyList<T> tail();

    default boolean isEmpty() {
        return true;
    }
}
