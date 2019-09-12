package com.pph.demo.effective.jdk8.lambda.la;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: PPH
 * @Date: 2019-07-09 17:19
 * @Description: lambda 表达式优于匿名类
 */
public class L2 {

    public static void main(String[] args) {
        List<String> words = Stream.of("aa", "a", "banner.txt").collect(Collectors.toList());
        System.out.println(words.toString());
        Collections.sort(words, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });
        System.out.println(words.toString());

        List<String> words2 = Stream.of("aa", "a", "banner.txt").collect(Collectors.toList());
        System.out.println(words2.toString());
        Collections.sort(words2, Comparator.comparingInt(String::length));
        System.out.println(words2.toString());

        List<String> words3 = Stream.of("aa", "a", "banner.txt").collect(Collectors.toList());
        System.out.println(words3.toString());
        words3.sort(Comparator.comparingInt(String::length));
        System.out.println(words3.toString());

        System.out.println(Operation.PLUS.apply(1, 1));
    }

    private enum Operation {
        PLUS("+", (x, y) -> x + y),
        MINUS("-", (x, y) -> x - y),
        TIMES("*", (x, y) -> x * y),
        DIVIDE("/", (x, y) -> x / y);

        private final String symbol;

        private final DoubleBinaryOperator op;

        Operation(String symbol, DoubleBinaryOperator op) {
            this.symbol = symbol;
            this.op = op;
        }

        @Override
        public String toString() {
            return symbol;
        }

        public double apply(double x, double y) {
            return op.applyAsDouble(x, y);
        }
    }
}
