package com.pph.demo.bingFaBianChengShiZhan.demo.d4;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author: pph
 * @date 2020/1/10 11:37
 * @description:
 */
public class BoundedHashSet<T> {

    private final Set<T> set;

    private final Semaphore sem;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<>());
//        同步关键类，构造方法传入的数字是多少，则同一个时刻，只运行多少个进程同时运行制定代码
        this.sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        /*
         * Semaphore 是 synchronized 的加强版，作用是控制线程的并发数量。
         * 在 semaphore.acquire() 和 semaphore.release()之间的代码，同一时刻只允许制定个数的线程进入，
         * 因为 semaphore 的构造方法是1，则同一时刻只允许一个线程进入，其他线程只能等待。
         */
        sem.acquire();
        boolean wasAddend = false;
        try {
            wasAddend = set.add(o);
            return wasAddend;
        } finally {
            if (!wasAddend) {
                sem.release();
            }
        }
    }

    public boolean remove(T o) {
        boolean wasRemoved = set.remove(o);
        if (wasRemoved) {
            sem.release();
        }
        return wasRemoved;
    }

    public int size() {
        return set.size();
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedHashSet<String> set = new BoundedHashSet<>(5);
        for (int i = 0; i < 10; i++) {
            set.add("a" + i);
            System.out.println(set.size());
            if (set.size() >= 5) {
                TimeUnit.SECONDS.sleep(2);
                set.remove("a" + i);
            }
        }
    }
}
