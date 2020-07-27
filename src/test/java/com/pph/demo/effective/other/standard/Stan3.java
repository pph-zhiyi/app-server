package com.pph.demo.effective.other.standard;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author PPH
 * @date 2019-07-24 09:03
 * @description executor 、task 和 stream 优先于线程
 * <p>
 * 《Java Concurrency in Practice》一书[Goetz06] 。
 */
public class Stan3 {

    /**
     * 同步器(Synchronizer)是使线程能够等待另一个线程的对象，允许它们协调动作。
     * <p>
     * 最常用的同步器是:
     *
     * @see java.util.concurrent.CountDownLatch
     * @see java.util.concurrent.Semaphore
     * <p>
     * 较不常用的是:
     * @see java.util.concurrent.CyclicBarrier
     * @see java.util.concurrent.Exchanger
     * <p>
     * 功能最强大的同步器是:
     * @see java.util.concurrent.Phaser
     */


    public static void main(String[] args) throws Exception {

        executors();
    }

    /**
     * 线程池
     */
    private static void executors() throws InterruptedException {

        ExecutorService exec = Executors.newCachedThreadPool();

        for (int i = 0; i < 20; i++) {
            int finalI = i;
            Future<?> submit = exec.submit(() -> System.out.println(finalI));
//            exec.awaitTermination(1L, TimeUnit.SECONDS);
        }
        exec.shutdown();
    }

    /**
     * 必须手动同步返回的 Map,不遵循这个建议可能会导致不确定的行为。
     */
    private static void synchronizedMap() {
        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());

        Set<Map.Entry<String, String>> entries = map.entrySet();

        synchronized (map) {
            for (Map.Entry<String, String> entry : entries) {
                String key = entry.getKey();
                String value = entry.getValue();
//                ....
            }
        }
    }
}
