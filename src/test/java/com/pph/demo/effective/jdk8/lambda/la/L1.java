package com.pph.demo.effective.jdk8.lambda.la;

import java.util.function.Supplier;

/**
 * @Author: PPH
 * @Date: 2019-06-09 12:28
 * @Description:
 */
public class L1 {

    public static void main(String[] args) {

        System.out.println(String.format("Double 类型随机数：%s", doubleRandom()));
    }

    private static Double doubleRandom() {
        Supplier<Double> d = () -> Math.random();
        return d.get();
    }
}
