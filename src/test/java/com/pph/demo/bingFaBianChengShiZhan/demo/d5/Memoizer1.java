package com.pph.demo.bingFaBianChengShiZhan.demo.d5;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: pph
 * @date: 2020/1/14 09:21
 * @description:
 */
public class Memoizer1<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new HashMap<>(16);

    private final Computable<A, V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (Objects.isNull(result)) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
