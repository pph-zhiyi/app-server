package com.pph.demo.java8.shizhan.d9;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.DoubleUnaryOperator;

import static java.util.stream.Collectors.toList;

/**
 * @author: pph
 * @date: 2019/12/12 15:25
 * @description:
 */
public class Demo {

    private static List<List<Integer>> subsets(List<Integer> list) {
        if (list.isEmpty()) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(Collections.emptyList());
            return ans;
        }
        Integer first = list.get(0);
        List<Integer> rest = list.subList(1, list.size());
        List<List<Integer>> subans = subsets(rest);
        List<List<Integer>> subans2 = insertAll(first, subans);
        return concat(subans, subans2);
    }

    private static List<List<Integer>> insertAll(Integer first, List<List<Integer>> lists) {
        return lists.stream()
                .map(l -> new ArrayList<Integer>() {{
                    add(first);
                    addAll(l);
                }}).collect(toList());
    }

    private static List<List<Integer>> concat(List<List<Integer>> a,
                                              List<List<Integer>> b) {
        List<List<Integer>> r = new ArrayList<>(a);
        r.addAll(b);
        return r;
    }

    @Test
    public void d1() {
        System.out.println(subsets(Arrays.asList(1, 4, 9)));
    }

    static DoubleUnaryOperator curriedConverter(double f, double b) {
        return (double x) -> x * f + b;
    }

    @Test
    public void d2() {
        DoubleUnaryOperator doubleUnaryOperator = curriedConverter(2, 4);
        System.out.println(doubleUnaryOperator.applyAsDouble(2));
    }

    public static LazyList<Integer> from(int n) {
        return new LazyList<>(n, () -> from(n + 1));
    }

    @Test
    public void d3() {
        MyList<Integer> l = new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>()));
        System.out.println(l);

        LazyList<Integer> numbers = from(2);
        Integer two = numbers.head();
        Integer three = numbers.tail().head();
        Integer four = numbers.tail().tail().head();

        System.out.println(two + " " + three + " " + four);

        double[] d = {1, 2, 3};
    }

    @Test
    public void d4() {
        LongAccumulator acc = new LongAccumulator(Long::sum, 1);
        acc.accumulate(10);
        System.out.println(acc.get());
        System.out.println(String.join(", ", "a", "b", "c"));
    }
}
