package com.pph.demo.effective.builder.pizza;

import static com.pph.demo.effective.builder.pizza.NyPizza.Size.SMALL;
import static com.pph.demo.effective.builder.pizza.Pizza.Topping.*;

/**
 * @Author: PPH
 * @Date: 2019-05-30 17:56
 * @Description:
 */
public class Test {

    public static void main(String[] args) {

        NyPizza pizza = new NyPizza.Builder(SMALL)
                .addTopping(SAUSAGE).addTopping(ONION).build();

        Calzone calzone = new Calzone.Builder()
                .addTopping(HAM).sauceInside().build();
    }
}
