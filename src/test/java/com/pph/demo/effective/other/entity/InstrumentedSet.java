package com.pph.demo.effective.other.entity;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Author: PPH
 * @Date: 2019-06-12 22:42
 * @Description:
 */
public class InstrumentedSet<E> extends ForwardingSet<E> {

    private int addCount;

    public InstrumentedSet(Set<E> s) {
        super(s);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }

    public static void main(String[] args) {

        Set<Instant> times = new InstrumentedSet<>(new TreeSet<>());
        Set<Object> s = new InstrumentedSet<>(new HashSet<>());
    }
}
