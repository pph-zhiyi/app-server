package com.pph.demo.java8.shizhan.d5.patterns.strategy;

import org.junit.Test;

/**
 * @author: pph
 * @date 2019/12/9 09:22
 * @description
 */
public class Testing {

    @Test
    public void d1() {
        Validator numericValidator = new Validator(new IsNumeric());
        boolean b1 = numericValidator.validate("aaa");
        System.out.println(b1);

        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean b2 = lowerCaseValidator.validate("bbb");
        System.out.println(b2);
    }

    @Test
    public void d2() {
        Validator numericValidator = new Validator((String s) -> s.matches("^\\d+$"));
        System.out.println(numericValidator.validate("aaa"));

        Validator lowerCaseValidator = new Validator((String s) -> s.matches("^[a-z]+$"));
        System.out.println(lowerCaseValidator.validate("bbb"));

    }
}
