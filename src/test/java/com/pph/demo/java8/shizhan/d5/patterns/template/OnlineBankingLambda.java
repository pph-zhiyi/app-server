package com.pph.demo.java8.shizhan.d5.patterns.template;

import java.util.function.Consumer;

/**
 * @author pph
 * @date 2019/12/9 12:41
 * @description
 */
public class OnlineBankingLambda {

    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        Customer c = OnlineBanking.CUSTOMER_MAP.get(id);
        makeCustomerHappy.accept(c);
    }
}
