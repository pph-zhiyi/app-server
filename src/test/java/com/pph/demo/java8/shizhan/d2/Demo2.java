package com.pph.demo.java8.shizhan.d2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

/**
 * @author: pph
 * @date: 2019/12/5 19:14
 * @description:
 */
public class Demo2 {

    private static List<Dish> menu = Demo.menu;

    @Test
    public void d1() {
        List<Dish> r1 = menu.stream()
                .collect(new ToListCollector<>());
        System.out.println(r1);

        List<Object> r2 = menu.stream()
                .collect(ArrayList::new, List::add, List::addAll);
        System.out.println(r2);
    }

    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(this::isPrime));
    }

    private boolean isPrime(Integer candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

//    public static boolean isPrime(List<Integer> primes, int candidate) {
//        return primes.stream()
//                .noneMatch(i -> i % candidate == 0);
//    }

    public static boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return takeWhile(primes, i -> i <= candidateRoot)
                .stream()
                .noneMatch(p -> candidate % p == 0);
    }

    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A item : list) {
            if (p.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

    @Test
    public void d2() {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
//            partitionPrimes(1_000_000);
            partitionPrimesWithCustomCollector(1_000_000);
            long duration = System.nanoTime() - start;
            if (duration < fastest)
                fastest = duration;
            System.out.println("Fastest execution done in " + fastest + " msecs");
        }
    }

    public Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(
                        () -> new HashMap<Boolean, List<Integer>>() {{
                            put(true, new ArrayList<Integer>());
                            put(false, new ArrayList<Integer>());
                        }},
                        (acc, candidate) -> {
                            acc.get(isPrime(acc.get(true), candidate))
                                    .add(candidate);
                        },
                        (map1, map2) -> {
                            map1.get(true).addAll(map2.get(true));
                            map1.get(false).addAll(map2.get(false));
                        });
    }
}
