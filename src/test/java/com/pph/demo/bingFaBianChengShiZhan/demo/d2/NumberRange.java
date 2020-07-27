package com.pph.demo.bingFaBianChengShiZhan.demo.d2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: pph
 * @date 2020/1/8 09:30
 * @description: 不变性条件 lower <= upper
 */
public class NumberRange {

    private final AtomicInteger lower = new AtomicInteger(0);

    private final AtomicInteger upper = new AtomicInteger(0);
}
