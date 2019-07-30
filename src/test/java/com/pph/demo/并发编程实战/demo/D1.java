package com.pph.demo.并发编程实战.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @Author: PPH
 * @Date: 2019-07-26 08:47
 * @Description:
 */
public class D1 {

    public static void main(String[] args) {

        List<String> strings = Collections.unmodifiableList(Stream.of("a", "b", "c").collect(toList()));

        System.out.println(strings);

        List<String> cp = new CopyOnWriteArrayList<>();

        Lock lock = new ReentrantLock();
        lock.lock();

        AtomicReference<List<String>> al = new AtomicReference<>(new ArrayList<>());
        List<String> list = al.get();

        BlockingQueue bq = new LinkedBlockingDeque(100);
    }
}
