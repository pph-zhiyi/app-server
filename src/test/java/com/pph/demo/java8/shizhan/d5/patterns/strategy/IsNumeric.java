package com.pph.demo.java8.shizhan.d5.patterns.strategy;

/**
 * @author: pph
 * @date: 2019/12/9 09:21
 * @description:
 */
public class IsNumeric implements ValidationStrategy {

    @Override
    public boolean execute(String s) {
        return s.matches("^\\d+$");
    }
}
