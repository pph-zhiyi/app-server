package com.pph.demo.effective.jdk8.lambda.stream;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * @Author: PPH
 * @date: 2019-07-18 09:16
 * @Description:
 */
public class S2 {

    public static void main(String[] args) {

        Stream<String> hello = Stream.of("hello", "word", "!");

        Iterable<String> strings = iterableOf(hello);

        System.out.println(m1(hello));
        System.out.println(m2(hello));
    }

    private static Map<String, Long> m1(Stream<String> hello) {

        return new HashMap<String, Long>(8) {
            {
                hello.forEach(h -> merge(h.toLowerCase(), 1L, Long::sum));
            }
        };
    }

    private static Map<String, Long> m2(Stream<String> hello) {

        return hello.collect(groupingBy(String::toLowerCase, counting()));
    }

    private static  <E> Iterable<E> iterableOf(Stream<E> stream) {
        return stream::iterator;
    }
}
