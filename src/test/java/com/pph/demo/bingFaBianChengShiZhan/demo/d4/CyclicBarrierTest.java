package com.pph.demo.bingFaBianChengShiZhan.demo.d4;

import lombok.AllArgsConstructor;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author: pph
 * @date: 2020/1/13 09:25
 * @description:
 */
public class CyclicBarrierTest {

    /**
     * @see CyclicBarrier 和
     * @see CountDownLatch 的区别
     * <p>
     * CountDownLatch 的计数器只能使用一次，而 CyclicBarrier 的计数器可以使用 reset() 方法重置，可以使用多次，
     * 所以 CyclicBarrier 能够处理更为复杂的场景；
     * <p>
     * CyclicBarrier 还提供了一些其他有用的方法，比如 getNumberWaiting() 方法可以获得 CyclicBarrier 阻塞的线程数量，
     * isBroken() 方法用来了解阻塞的线程是否被中断；
     * <p>
     * CountDownLatch 允许一个或多个线程等待一组事件的产生，而 CyclicBarrier 用于等待其他线程运行到栅栏位置。
     *
     * 另一个栅栏
     */

    @AllArgsConstructor
    public static class Worker extends Thread {

        private CyclicBarrier cyclicBarrier;

        @Override
        public void run() {
            super.run();
            String name = Thread.currentThread().getName();
            System.out.println(name + " 开始等待其他线程");
            try {
                cyclicBarrier.await();
                System.out.println(name + " 开始执行");
                TimeUnit.SECONDS.sleep(1);
                System.out.println(name + " 执行结束");
            } catch (InterruptedException e) {
                System.out.println(name + " 线程终止");
            } catch (BrokenBarrierException e) {
                System.out.println(name + " 线程损坏");
            }
        }
    }

    public static void main(String[] args) {
        int threadCount = 3;

        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount);

        for (int i = 0; i < threadCount; i++) {
            System.out.println("创建工作线程: " + i);
            Worker worker = new Worker(cyclicBarrier);
            worker.start();
        }
    }
}
