package com.pph.demo.bingFaBianChengShiZhan.demo.d2;

import java.util.Vector;

/**
 * @author: pph
 * @date 2020/1/9 17:24
 * @description
 */
public class BetterVector<E> extends Vector<E> {

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !contains(x);
        if (absent) {
            add(x);
        }
        return absent;
    }
}
