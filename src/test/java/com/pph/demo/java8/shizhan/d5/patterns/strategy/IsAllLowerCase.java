package com.pph.demo.java8.shizhan.d5.patterns.strategy;

/**
 * @author: pph
 * @date 2019/12/9 09:19
 * @description
 */
public class IsAllLowerCase implements ValidationStrategy {

    @Override
    public boolean execute(String s) {
        return s.matches("^[a-z]+$");
    }
}
