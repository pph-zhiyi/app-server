package com.pph.demo.java8.shizhan.d9;

/**
 * @author: pph
 * @date: 2019/12/13 08:46
 * @description:
 */
public class Empty<T> implements MyList<T> {

    @Override
    public T head() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MyList<T> tail() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "[]";
    }
}
