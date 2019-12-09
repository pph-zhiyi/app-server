package com.pph.demo.java8.shizhan.d2;

import lombok.Data;

/**
 * @author: pph
 * @date: 2019/12/3 12:48
 * @description:
 */
@Data
public class Dish {
    /**
     * 菜名
     */
    private final String name;
    /**
     * 是否素食
     */
    private final boolean vegetarian;
    /**
     * 能量
     */
    private final int calories;
    /**
     * 类型
     */
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public enum Type {MEAT, FISH, OTHER}
}
