package com.pph.demo.bingFaBianChengShiZhan.demo.dl;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author: pph
 * @date: 2020/3/23 08:53
 * @description:
 */
public class ThreadLocalExample implements Runnable {

    private static  final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));

    @Override
    public void run() {
        System.out.println(String.format("Thread name: %s, default Formatter: %s",
                Thread.currentThread().getName(), formatter.get().toPattern()));

        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        formatter.set(new SimpleDateFormat());

        System.out.println(String.format("Thread name: %s, default Formatter: %s",
                Thread.currentThread().getName(), formatter.get().toPattern()));
    }

    public static void main(String[] args) {
        ThreadLocalExample tle = new ThreadLocalExample();

//        Stream.iterate(10, (i) -> i--)
//                .forEach(i -> {
//                    Thread t = new Thread(tle, "" + i);
//                    try {
//                        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    t.start();
//                });

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(tle, "" + i);
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t.start();
        }
    }
}
