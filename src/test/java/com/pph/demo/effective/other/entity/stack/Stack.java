package com.pph.demo.effective.other.entity.stack;

import java.util.Arrays;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;

/**
 * @author PPH
 * @date 2019-06-10 18:21
 * @description
 */
public class Stack {

    //    public static final String[] STRINGS_ARR = {};
    private static final String[] STRING_DEF = {"Demo", "b", "c"};
    //    不可改变 List 的值，否则抛出异常：java.lang.UnsupportedOperationException
    public static final List<String> STRING_LIST = Collections.unmodifiableList(Arrays.asList(STRING_DEF));

    public static final String[] getStringDef() {
        return STRING_DEF.clone();
    }

    private Object[] elements;

    private int size;

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[size--];

//        Eliminate obsolete reference
        elements[size] = null;
        return result;
    }

    /**
     * Ensure space for at least one more element.
     */
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }

    /**
     * Clone method for class with references to mutable state
     *
     * @return
     */
    @Override
    protected Stack clone() {
        try {
            Stack stack = (Stack) super.clone();
            stack.elements = elements.clone();
            return stack;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e.getMessage());
        }
    }
}
