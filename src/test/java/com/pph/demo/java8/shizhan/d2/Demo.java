package com.pph.demo.java8.shizhan.d2;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * @author: pph
 * @date: 2019/12/3 12:41
 * @description
 */
public class Demo {

    public static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    @Test
    public void d1() {

        List<String> r1 = Demo.menu.stream()
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
        System.out.println(r1);
//        并行流
        List<String> r2 = Demo.menu.parallelStream()
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
        System.out.println(r2);
    }

    @Test
    public void d2() {
        Map<Dish.Type, List<Dish>> r1 = menu.stream()
                .collect(groupingBy(Dish::getType));
        System.out.println(r1);
    }

    @Test
    public void d3() {
        List<String> r1 = menu.stream()
                .sorted(comparing(Dish::getCalories))
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
//                .skip(2)
                .limit(3)
                .collect(toList());
        System.out.println(r1);
    }

    @Test
    public void d4() {
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");

        List<String> uniqueCharacters = words.stream()
                .map(s -> s.split(StringUtils.EMPTY))
                .flatMap(Arrays::stream)
                .distinct()
                .filter(StringUtils::isNotBlank)
                .collect(toList());
        System.out.println(uniqueCharacters);

        List<Integer> n1 = Arrays.asList(1, 2, 3), n2 = Arrays.asList(3, 4);
        List<int[]> r1 = n1.stream()
                .flatMap(i -> n2.stream()
                        .map(j -> new int[]{i, j}))
                .collect(toList());
//        [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]
        System.out.println(r1);

        List<int[]> r2 = n1.stream()
                .flatMap(i -> n2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(toList());
//        [(2, 4), (3, 3)]
        System.out.println(r2);
    }

    @Test
    public void d5() {
//        检查谓词是否至少匹配一个元素。或运算符：||
        boolean b1 = menu.stream()
                .anyMatch(Dish::isVegetarian);
        System.out.println(b1);

//        检查谓词是否匹配所有元素。与运算符：&&
        boolean b2 = menu.stream()
                .allMatch(d -> d.getCalories() < 1000);
        System.out.println(b2);

//       没有任何元素与给定的谓词匹配。非运算符：!=
        boolean b3 = menu.stream()
                .noneMatch(d -> d.getCalories() >= 1000);
        System.out.println(b3);

        /*
         * 为什么会同时有findFirst和findAny呢? 答案是并行( parallelStream )。
         * 找到第一个元素在并行上限制更多。如果你不关心返回的元素是哪个，请使用findAny，因为它在使用并行流 时限制较少。
         */
//        findAny 方法将返回当前流中的任意元素
        Optional<Dish> any = menu.parallelStream()
                .filter(Dish::isVegetarian)
                .findAny();
        System.out.println(any);

//        findFirst 方法将返回当前流中的第一个元素
        Optional<Dish> first = menu.stream()
                .filter(Dish::isVegetarian)
                .findFirst();
        System.out.println(first);
    }

    @Test
    public void d6() {
        List<Integer> num = Arrays.asList(1, 2, 3, 4);
        Integer r1 = num.stream()
                .reduce(0, Integer::sum);
        System.out.println(r1);

        Integer r2 = num.stream()
                .reduce(1, (a, b) -> a * b);
        System.out.println(r2);

        Optional<Integer> r3 = num.stream()
                .reduce(Integer::sum);
        System.out.println(r3.orElse(0));

//        map和reduce的连接通常称为map-reduce模式
        Optional<Integer> r4 = menu.stream()
                .map(d -> 1)
                .reduce(Integer::sum);
        System.out.println(r4.orElse(0));
    }

    @Test
    public void d7() {
        Optional<Dish> r1 = menu.stream()
                .collect(maxBy(comparing(Dish::getCalories)));
        System.out.println(r1.orElse(null));

        int r2 = menu.stream()
                .collect(summingInt(Dish::getCalories));
        System.out.println(r2);

        double r3 = menu.stream()
                .collect(averagingDouble(Dish::getCalories));
        System.out.println(r3);

        IntSummaryStatistics r4 = menu.stream()
                .collect(summarizingInt(Dish::getCalories));
        System.out.println(r4);

        String r5 = menu.stream()
                .map(Dish::getName)
                .collect(joining(", ", "[ ", " ]"));
        System.out.println(r5);

        Integer r6 = menu.stream()
                .collect(reducing(0, Dish::getCalories, Integer::sum));
        System.out.println(r6);

        Optional<Dish> r7 = menu.stream()
                .collect(reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println(r7.orElse(null));
    }

    private static <T> Collector<T, ?, Long> counting() {
        return reducing(0L, e -> 1L, Long::sum);
    }

    @Test
    public void d8() {
        /*
         * 分组
         */
        Map<Dish.Type, List<Dish>> r1 = menu.stream()
                .collect(groupingBy(Dish::getType));
        System.out.println(r1);

        Map<String, List<Dish>> r2 = menu.stream()
                .collect(groupingBy(dish -> dish.getCalories() <= 400
                        ? "低热量"
                        : dish.getCalories() <= 700
                        ? "中热量" : "高热量"));
        System.out.println(r2);

        Map<Integer, Map<String, List<Dish>>> r3 = menu.stream()
                .collect(groupingBy(Dish::getCalories,
                        groupingBy(dish -> dish.getCalories() <= 400
                                ? "低热量"
                                : dish.getCalories() <= 700
                                ? "中热量" : "高热量")));
        System.out.println(r3);

        Map<Dish.Type, Optional<Dish>> r4 = menu.stream()
                .collect(groupingBy(Dish::getType,
                        maxBy(comparingInt(Dish::getCalories))));
        System.out.println(r4);

        Map<Dish.Type, Dish> r5 = menu.stream()
//                .collect(toMap(Dish::getType, Function.identity(), BinaryOperator.maxBy(comparingInt(Dish::getCalories))));
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println(r5);

        Map<Dish.Type, Integer> r6 = menu.stream()
                .collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        System.out.println(r6);

        Map<Dish.Type, Set<String>> r7 = menu.stream()
                .collect(groupingBy(Dish::getType,
                        mapping(dish -> dish.getCalories() <= 400
                                ? "低热量"
                                : dish.getCalories() <= 700
//                                ? "中热量" : "高热量", toSet())));
                                ? "中热量" : "高热量", toCollection(HashSet::new))));
        System.out.println(r7);
    }

    @Test
    public void d9() {
        /*
         * 分区
         */
        Map<Boolean, List<Dish>> r1 = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian));
        System.out.println(r1);

        Map<Boolean, Map<Dish.Type, List<Dish>>> r2 = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        groupingBy(Dish::getType)));
        System.out.println(r2);

        Map<Boolean, Dish> r3 = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println(r3);
    }
}
