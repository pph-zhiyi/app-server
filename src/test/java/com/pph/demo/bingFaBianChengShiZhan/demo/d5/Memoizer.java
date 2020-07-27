package com.pph.demo.bingFaBianChengShiZhan.demo.d5;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author: pph
 * @date 2020/1/15 09:20
 * @description:
 */
public class Memoizer<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>(16);

    private final Computable<A, V> c;

    public Memoizer(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (Objects.isNull(f)) {
            FutureTask<V> ft = new FutureTask<>(() -> c.compute(arg));
            f = cache.putIfAbsent(arg, ft);
            if (Objects.isNull(f)) {
                f = ft;
                ft.run();
            }
        }
        try {
            return f.get();
        } catch (ExecutionException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
