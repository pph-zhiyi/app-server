package com.pph.demo.bingFaBianChengShiZhan.demo.d2.m1;

/**
 * @author: pph
 * @date 2020/1/7 09:24
 * @description
 */
public class MutablePoint {

    public int x, y;

    public MutablePoint() {
        this.x = 0;
        this.y = 0;
    }

    public MutablePoint(MutablePoint mp) {
        this.x = mp.x;
        this.y = mp.y;
    }
}
