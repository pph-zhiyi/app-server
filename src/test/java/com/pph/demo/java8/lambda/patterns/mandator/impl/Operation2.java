package com.pph.demo.java8.lambda.patterns.mandator.impl;

import com.pph.demo.java8.lambda.patterns.mandator.Editor;

/**
 * @author: pph
 * @date 2019/11/28 15:46
 * @description:
 */
public class Operation2 implements Editor {
    @Override
    public void save() {
        System.out.println("Operation2：执行保存");
    }

    @Override
    public void open() {
        System.out.println("Operation2：执行打开");
    }

    @Override
    public void close() {
        System.out.println("Operation2：执行关闭");
    }
}
