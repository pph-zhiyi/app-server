package com.pph.demo.bingFaBianChengShiZhan.demo.d5;

/**
 * @author: pph
 * @date 2020/1/14 09:16
 * @description:
 */
public interface Computable<A, V> {

    V compute(A arg) throws InterruptedException;
}
