package com.pph.demo.effective.jdk8.lambda.stream;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @Author: PPH
 * @date: 2019-07-17 18:32
 * @Description:
 */
public class S1 {

    public static void main(String[] args) {

        List<Integer> ints = Stream.of(0, 1, 2, 3, 4, 5).collect(toList());
        List<String> str = Stream.of("a", "b", "c", "d", "e").collect(toList());

        List<Integer> sl = ints.stream().skip(0).limit(3).collect(toList());
        System.out.println(sl.toString());

        List<String> m1 = ints.stream().map(String::valueOf).collect(toList());
        System.out.println(m1);
    }
}
