package com.pph.demo.bingFaBianChengShiZhan.demo.d4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author: pph
 * @date: 2020/1/10 09:24
 * @description:
 */
public class TestHarness {

    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread(() -> {
                try {
                    startGate.await();
                    try {
                        task.run();
                    } finally {
                        endGate.countDown();
                    }
                } catch (InterruptedException ignored) {
                }
            });
            t.start();
        }

        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();

        return end - start;
    }

    public static void main(String[] args) throws InterruptedException {
        TestHarness t = new TestHarness();
        System.out.println("before");
        long time = t.timeTasks(10, () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(time);
    }
}
