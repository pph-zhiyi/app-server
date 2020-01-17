package com.pph.demo.bingFaBianChengShiZhan.demo.d5;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: pph
 * @date: 2020/1/14 09:25
 * @description:
 */
public class Memoizer2<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new ConcurrentHashMap<>(16);

    private final Computable<A, V> c;

    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (Objects.isNull(result)) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
