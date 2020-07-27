package com.pph.demo.java8.shizhan.d5.patterns.strategy;

import lombok.AllArgsConstructor;

/**
 * @author: pph
 * @date 2019/12/9 09:21
 * @description
 */
@AllArgsConstructor
public class Validator {

    private final ValidationStrategy strategy;

    public boolean validate(String s) {
        return strategy.execute(s);
    }
}
