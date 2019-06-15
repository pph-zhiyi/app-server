package com.pph.demo.effective.other.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: PPH
 * @Date: 2019-06-12 22:22
 * @Description:
 */
public class InstrumentedHashSet<E> extends HashSet<E> {

    private int addCount;

    public InstrumentedHashSet() {
    }

    public InstrumentedHashSet(int initCap, float loadFactor) {
        super(initCap, loadFactor);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
//        c.forEach(this::add);
        addCount += c.size();
        return true;
    }

    public int getAddCount() {
        return addCount;
    }

    public static void main(String[] args) {

        InstrumentedHashSet<String> ihs = new InstrumentedHashSet<>();
        ihs.addAll(Stream.of("Snap", "Crackle", "Pop").collect(Collectors.toList()));
        System.out.println(ihs.getAddCount());
    }
}
