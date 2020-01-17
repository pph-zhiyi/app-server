package com.pph.demo.bingFaBianChengShiZhan.demo.d2;

import java.util.Collections;
import java.util.List;

/**
 * @author: pph
 * @date: 2020/1/7 09:14
 * @description:
 */
public class PrivateLock {

    private final Object myLock = new Object();

    List<String> lists = Collections.emptyList();

    void someMethod() {
        synchronized (myLock) {
//            访问或修改 lists 的状态
        }
    }
}
