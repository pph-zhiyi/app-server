package com.pph.demo.bingFaBianChengShiZhan.demo.d6;

import java.util.concurrent.Executor;

/**
 * @author: pph
 * @date: 2020/1/17 19:21
 * @description:
 */
public class WithinThreadExecutor implements Executor {

    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
