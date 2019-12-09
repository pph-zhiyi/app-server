package com.pph.demo.java8.shizhan.d1;

import lombok.Data;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * @author: pph
 * @date: 2019/12/1 18:18
 * @description:
 */
public class Demo {

    @Data
    static class Apple {

        private String color;

        private Integer weight;

        private String type;

        Apple() {
        }

        Apple(Integer weight) {
            this.weight = weight;
        }

        Apple(String color, Integer weight) {
            this.color = color;
            this.weight = weight;
        }

        Apple(String color, Integer weight, String type) {
            this.color = color;
            this.weight = weight;
            this.type = type;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    ", type=" + type +
                    '}';
        }
    }

    private static <T> List<T> appleFilter(List<T> list, Predicate<T> p) {
        return list.stream()
                .filter(p)
                .collect(Collectors.toList());
    }

    @Test
    public void d1() {
        List<Apple> apples = Stream.of(new Apple("red", 90),
                new Apple("red", 120),
                new Apple("green", 70),
                new Apple("green", 100))
                .collect(toList());

        List<Apple> a1 = appleFilter(apples, (Apple a) -> a.getColor().equals("red"));
        System.out.println(a1);

        List<Apple> a2 = appleFilter(apples, (Apple a) -> a.getWeight() >= 100);
        System.out.println(a2);

        Map<String, List<Apple>> a3 = Stream.of(a1, a2)
                .flatMap(List::stream)
                .collect(groupingBy(Apple::getColor));
        System.out.println(a3);

        Function<Apple, String> getColor = Apple::getColor;
    }

    @Test
    public void d2() {
        List<String> str = Arrays.asList("a", "b", "A", "B");

        List<String> l1 = str.stream()
//                倒序
//                .sorted((a, b) -> b.compareToIgnoreCase(a))
                .sorted(String::compareToIgnoreCase)
                .collect(toList());
        System.out.println(l1);

        List<String> c2 = str.stream()
                .sorted()
                .collect(toList());
        System.out.println(c2);
    }

    private static List<Apple> weightsToApple(List<Integer> weights, Function<Integer, Apple> f) {
        return weights.stream()
                .map(f)
                .collect(toList());
    }

    @Test
    public void d3() {
        Supplier<Apple> apple = Apple::new;
        Apple a1 = apple.get();
        System.out.println(a1);

        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = weightsToApple(weights, Apple::new);
        System.out.println(apples);
        apples.sort(comparing(Apple::getWeight));
        System.out.println(apples);

        BiFunction<String, Integer, Apple> bi = Apple::new;
        Apple biApple = bi.apply("red", 100);
        System.out.println(biApple);

        BiApple<String, Integer, String, Apple> ba = Apple::new;
        Apple apply = ba.apply("red", 150, "红富士");
        System.out.println(apply);
    }

    @Test
    public void d4() {
        List<Apple> apples = Arrays.asList(new Apple("red", 90, "红富士"),
                new Apple("red", 120, "红富士"),
                new Apple("green", 70, "青苹果"),
                new Apple("green", 100, "青苹果"),
                new Apple("red", 100, "红富士"));

        apples.sort(comparing(Apple::getWeight));
        System.out.println("重量升序：" + apples);
        apples.sort(comparing(Apple::getWeight).reversed());
        System.out.println("重量降序：" + apples);
        apples.sort(comparing(Apple::getWeight).thenComparing(Apple::getType));
        System.out.println("重量相同就按品种排序：" + apples);
    }

    @Test
    public void d5() {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
//        f(g(x))
        Function<Integer, Integer> h1 = f.andThen(g);
        Integer r1 = h1.apply(1);
//        2 * (1 + 1)
        System.out.println(r1);

//        g(f(x))
        Function<Integer, Integer> h2 = f.compose(g);
        Integer r2 = h2.apply(1);
//        1 + (1 * 2)
        System.out.println(r2);
    }

    @Test
    public void d6() {
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline = addHeader.andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);
        String r1 = transformationPipeline.apply("呃呃呃 labda");
        System.out.println(r1);

        Function<String, String> t2 = addHeader.andThen(Letter::addFooter);
        String r2 = t2.apply("呃呃呃 labda");
        System.out.println(r2);
    }
}
