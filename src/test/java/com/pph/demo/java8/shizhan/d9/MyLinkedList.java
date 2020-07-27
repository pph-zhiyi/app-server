package com.pph.demo.java8.shizhan.d9;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @author pph
 * @date 2019/12/13 08:45
 * @description
 */
@AllArgsConstructor
@ToString
public class MyLinkedList<T> implements MyList<T> {

    private final T head;

    private final MyList<T> tail;

    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList<T> tail() {
        return tail;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
