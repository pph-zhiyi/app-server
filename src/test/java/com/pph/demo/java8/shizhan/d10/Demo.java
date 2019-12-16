package com.pph.demo.java8.shizhan.d10;

import com.pph.demo.java8.shizhan.d2.Dish;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

/**
 * @author: pph
 * @date: 2019/12/16 08:21
 * @description:
 */
public class Demo {

    static List<Dish> menu = com.pph.demo.java8.shizhan.d2.Demo.menu;

    @Test
    public void d1() {
        Stream<Dish> menuStream = menu.stream();

        Results results = new StreamForker<>(menuStream)
                .fork("shortMenu", s -> s.map(Dish::getName)
                        .collect(joining(", ", "[ ", " ]")))
                .fork("totalCalories", s -> s.mapToInt(Dish::getCalories).sum())
//                .fork("mostCaloricDish", s -> s.collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get())
                .fork("mostCaloricDish", s -> s.reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2))
                .fork("dishesByType", s -> s.collect(groupingBy(Dish::getType)))
                .getResults();

        String shortMenu = results.get("shortMenu");
        int totalCalories = results.get("totalCalories");
        Dish mostCaloricDish = results.get("mostCaloricDish");
        Map<Dish.Type, List<Dish>> dishesByType = results.get("dishesByType");

        System.out.println("Short menu: " + shortMenu);
        System.out.println("Total calories: " + totalCalories);
        System.out.println("Most caloric dish: " + mostCaloricDish);
        System.out.println("Dishes by type: " + dishesByType);
    }
}
