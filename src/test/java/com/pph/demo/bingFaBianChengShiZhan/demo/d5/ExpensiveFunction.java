package com.pph.demo.bingFaBianChengShiZhan.demo.d5;

import java.math.BigInteger;

/**
 * @author: pph
 * @date: 2020/1/14 09:18
 * @description:
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {

    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        // TODO: 2020/1/14 计算。。。
        return new BigInteger(arg);
    }
}
