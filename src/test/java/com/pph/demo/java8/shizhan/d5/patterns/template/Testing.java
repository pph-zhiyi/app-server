package com.pph.demo.java8.shizhan.d5.patterns.template;

import org.junit.Test;

/**
 * @author pph
 * @date 2019/12/9 12:39
 * @description
 */
public class Testing {

    @Test
    public void t1() {
        new OnlineBankingLambda().processCustomer(1,
                (Customer c) -> System.out.println(String.format("Hello %s", c.getName())));

        OnlineBanking onlineBanking = new OnlineBankingHello();
        onlineBanking.processCustomer(1);
    }
}
