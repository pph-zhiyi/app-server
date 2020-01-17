package com.pph.demo.bingFaBianChengShiZhan.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
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

    /**
     * @see ConcurrentLinkedQueue   是一个传统的先进先出队列
     * @see PriorityQueue   是一个（非并发的）优先队列
     * @see BlockingQueue 扩展了 Queue 增加了可堵塞的插入和获取操作。
     * 如果队列为空，那么获取元素的操作将一直堵塞，直到队列中出现一个可用的元素。如果队列已满（对于有界队列来说），
     * 那么插入元素的操作将一直堵塞，直到队列中出现可用空间。
     */

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
