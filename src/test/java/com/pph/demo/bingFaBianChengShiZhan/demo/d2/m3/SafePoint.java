package com.pph.demo.bingFaBianChengShiZhan.demo.d2.m3;

import lombok.AllArgsConstructor;

/**
 * @author: pph
 * @date: 2020/1/8 09:36
 * @description:
 */
@AllArgsConstructor
public class SafePoint {

    private int x, y;

    private SafePoint(int[] a) {
        this(a[0], a[1]);
    }

    public SafePoint(SafePoint p) {
        this(p.get());
    }

    public synchronized int[] get() {
        return new int[]{x, y};
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
