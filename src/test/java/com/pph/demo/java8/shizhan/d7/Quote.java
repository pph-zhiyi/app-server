package com.pph.demo.java8.shizhan.d7;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: pph
 * @date 2019/12/11 09:12
 * @description:
 */
@Getter
@AllArgsConstructor
public class Quote {

    private final String shopName;

    private final double price;

    private final Discount.Code discountCode;

    public static Quote parse(String s) {
        String[] split = s.split(":");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        Discount.Code discountCode = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, price, discountCode);
    }
}
