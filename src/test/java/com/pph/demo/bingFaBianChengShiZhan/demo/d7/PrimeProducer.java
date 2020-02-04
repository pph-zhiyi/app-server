package com.pph.demo.bingFaBianChengShiZhan.demo.d7;

import lombok.AllArgsConstructor;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author: pph
 * @date: 2020/1/23 13:37
 * @description:
 */
@AllArgsConstructor
public class PrimeProducer extends Thread {

    private final BlockingQueue<BigInteger> queue;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        if (!Thread.currentThread().isInterrupted()) {
            try {
                queue.put(p = p.nextProbablePrime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void cancel() {
        interrupt();
    }

    public static void timerRun(Runnable r, long timeout, TimeUnit unit) {
    }
}
