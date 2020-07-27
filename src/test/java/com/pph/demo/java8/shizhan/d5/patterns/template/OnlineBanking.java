package com.pph.demo.java8.shizhan.d5.patterns.template;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author pph
 * @date 2019/12/9 09:30
 * @description
 */
public abstract class OnlineBanking {

    public static final Map<Integer, Customer> CUSTOMER_MAP = new HashMap<>();

    static {
        CUSTOMER_MAP.put(1, new Customer(1, "哈哈"));
        CUSTOMER_MAP.put(2, new Customer(2, "呵呵"));
        CUSTOMER_MAP.put(3, new Customer(3, "嗯嗯"));
    }

    public void processCustomer(int id) {
        makeCustomerHappy(CUSTOMER_MAP.get(id));
    }

    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        makeCustomerHappy.accept(CUSTOMER_MAP.get(id));
    }

    abstract void makeCustomerHappy(Customer c);
}
