package com.pph.demo.java8.shizhan.d5.patterns.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author pph
 * @date 2019/12/9 19:16
 * @description
 */
public class ProductFactory {

    public static Product createProduct(String name) {
        switch (name) {
            case "loan":
                return new Loan();
            case "stock":
                return new Stock();
            case "bond":
                return new Bond();
            default:
                throw new IllegalArgumentException("No such product " + name);
        }
    }

    private final static Map<String, Supplier<Product>> PRODUCT_MAPS = new HashMap<>(4);

    static {
        PRODUCT_MAPS.put("loan", Loan::new);
        PRODUCT_MAPS.put("stock", Stock::new);
        PRODUCT_MAPS.put("bond", Bond::new);
    }

    public static Product createProductLmd(String name) {
        Supplier<Product> p;
        if (Objects.nonNull(p = PRODUCT_MAPS.get(name)))
            return p.get();
        throw new IllegalArgumentException("No such product " + name);
    }
}
