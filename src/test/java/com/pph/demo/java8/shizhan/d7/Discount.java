package com.pph.demo.java8.shizhan.d7;

import lombok.AllArgsConstructor;

import static com.pph.demo.java8.shizhan.d7.Shop.delay;

/**
 * @author: pph
 * @date: 2019/12/11 09:07
 * @description:
 */
public class Discount {

    @AllArgsConstructor
    public enum Code {
        NONE(0),
        SILVER(5),
        GOLD(10),
        PLATINUM(15),
        DIAMOND(20);

        private final int percentage;
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " +
                Discount.apply(quote.getPrice(),
                        quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        delay();
//        return format(price * (100 - code.percentage) / 100);
        return price * (100 - code.percentage) / 100;
    }
}
