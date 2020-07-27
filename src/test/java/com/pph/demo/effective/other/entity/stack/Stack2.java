package com.pph.demo.effective.other.entity.stack;

import org.omg.CORBA.Object;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * @author PPH
 * @date 2019-06-21 17:59
 * @description
 */
public class Stack2<E> {

    private E[] elements;

    private int size;

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public Stack2() {
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size == 0)
            throw new EmptyStackException();
        E result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}
