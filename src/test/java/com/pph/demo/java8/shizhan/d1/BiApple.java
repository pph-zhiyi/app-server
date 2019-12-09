package com.pph.demo.java8.shizhan.d1;

/**
 * @author: pph
 * @date: 2019/12/3 08:57
 * @description:
 */
@FunctionalInterface
public interface BiApple<A, B, C, R> {

    R apply(A a, B b, C c);
}
