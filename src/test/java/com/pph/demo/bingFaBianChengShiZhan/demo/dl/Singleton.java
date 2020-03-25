package com.pph.demo.bingFaBianChengShiZhan.demo.dl;

import java.util.Objects;

/**
 * @author: pph
 * @date: 2020/3/19 08:30
 * @description:
 */
public class Singleton {
    /**
     * 使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。
     */
    private volatile static Singleton uniqueInstance;

    private Singleton() {
    }

    public static Singleton getUniqueInstance() {
//        双检锁
        if (Objects.isNull(uniqueInstance)) {
            synchronized (Singleton.class) {
                if (Objects.isNull(uniqueInstance)) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
