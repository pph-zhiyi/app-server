package com.pph.demo.java8.shizhan.d5.patterns.factory;

import org.junit.Test;

import java.util.function.Supplier;

/**
 * @author: pph
 * @date: 2019/12/9 19:22
 * @description:
 */
public class Testing {

    @Test
    public void t1() {
        Product loan = ProductFactory.createProduct("loan");
        loan.run();
    }

    @Test
    public void t2() {
        Product loan = ProductFactory.createProductLmd("loan");
        loan.run();
    }
}
