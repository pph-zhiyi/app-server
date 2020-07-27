package com.pph.demo.java8.shizhan.future;

import org.junit.Test;

/**
 * @author: pph
 * @date 2019/12/11 15:13
 * @description:
 */
public class Testing {

    @Test
    public void t1() throws Exception {
        Demo.runAsync();
        Demo.supplyAsync();
    }

    @Test
    public void t2() throws Exception {
        Demo.wenComplete();
    }

    @Test
    public void t3() throws Exception {
        Demo.thenApply();
    }

    @Test
    public void t4() throws Exception {
        Demo.handel();
    }

    @Test
    public void t5() throws Exception {
        Demo.thenAccept();
    }

    @Test
    public void t6() throws Exception {
        Demo.thenRun();
    }

    @Test
    public void t7() throws Exception {
        Demo.thenCombine();
    }

    @Test
    public void t8() throws Exception {
        Demo.thenAcceptBoth();
    }

    @Test
    public void t9() throws Exception {
        Demo.applyToEither();
    }

    @Test
    public void t10() throws Exception {
        Demo.acceptEither();
    }

    @Test
    public void t11() throws Exception {
        Demo.runAfterEither();
    }

    @Test
    public void t12() throws Exception {
        Demo.runAfterBoth();
    }

    @Test
    public void t13() throws Exception {
        Demo.thenCompose();
    }
}
