package com.pph.demo.java8.shizhan.d9;

import lombok.AllArgsConstructor;

import java.util.function.Supplier;

/**
 * @author: pph
 * @date 2019/12/13 08:50
 * @description:
 */
@AllArgsConstructor
public class LazyList<T> implements MyList<T> {

    private final T head;

    private final Supplier<MyList<T>> tail;

    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList<T> tail() {
        return tail.get();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
