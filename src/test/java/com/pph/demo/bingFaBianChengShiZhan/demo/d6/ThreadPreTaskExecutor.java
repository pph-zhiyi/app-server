package com.pph.demo.bingFaBianChengShiZhan.demo.d6;

import java.util.concurrent.Executor;

/**
 * @author: pph
 * @date: 2020/1/17 19:19
 * @description:
 */
public class ThreadPreTaskExecutor implements Executor {

    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}
