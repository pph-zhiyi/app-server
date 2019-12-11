package com.pph.demo.java8.shizhan.d5.patterns.template;

/**
 * @author: pph
 * @date: 2019/12/9 12:50
 * @description:
 */
public class OnlineBankingHello extends OnlineBanking {

    @Override
    void makeCustomerHappy(Customer c) {
        System.out.println(String.format("Hello %s", c.getName()));
    }
}
