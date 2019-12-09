package com.pph.demo.java8.shizhan.d3;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * @author: pph
 * @date: 2019/12/4 09:24
 * @description:
 */
public class Demo {

    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    @Test
    public void d1() {
        List<Transaction> r1 = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());
        System.out.println(r1);

        List<String> r2 = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(toList());
        System.out.println(r2);

        List<Trader> r3 = transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> StringUtils.equals(t.getCity(), "Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());
        System.out.println(r3);

        String r4 = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce(StringUtils.EMPTY, String::concat);
//                .collect(joining(StringUtils.EMPTY));
        System.out.println(r4);

        boolean r5 = transactions.stream()
                .anyMatch(t -> StringUtils.equals(t.getTrader().getCity(), "Milan"));
        System.out.println(r5);

        int r6 = transactions.stream()
                .filter(t -> StringUtils.equals(t.getTrader().getCity(), "Cambridge"))
                .mapToInt(Transaction::getValue)
                .peek(System.out::println)
                .sum();
        System.out.println(r6);

        OptionalInt r7 = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max();
        System.out.println(r7.orElse(0));
        Optional<Transaction> r8 = transactions.stream()
                .min(comparing(Transaction::getValue));
        System.out.println(r8.get().getValue());
    }

    @Test
    public void d2() {
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                .filter(i -> i % 2 == 0);
        System.out.println(evenNumbers.count());

        List<Integer> r1 = IntStream.rangeClosed(1, 10)
                .filter(i -> i % 2 == 0)
                .boxed()
                .collect(toList());
        System.out.println(r1);
    }

    @Test
    public void d3() {
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );
        pythagoreanTriples.forEach(t -> System.out.println(String.format("%d, %d, %d", t[0], t[1], t[2])));

        Stream<double[]> stream = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)}))
                .filter(t -> t[2] % 1 == 0);
        stream.forEach(t -> System.out.println(String.format("%f, %f, %f", t[0], t[1], t[2])));
    }

    @Test
    public void d4() {
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers)
                .sum();
        System.out.println(sum);

        IntSummaryStatistics statistics = Arrays.stream(numbers)
                .summaryStatistics();
        System.out.println(statistics);
    }

    @Test
    public void d5() throws IOException {
        String property = System.getProperty("user.dir");
        final String path = property.concat("/src/test/java/com/pph/demo/java8/shizhan/d3/data.txt");
        List<String> collect = Files.lines(Paths.get(path), Charset.defaultCharset())
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .distinct()
                .collect(toList());
        System.out.println(collect);
    }

    @Test
    public void d6() {
        List<Integer> r1 = Stream.iterate(0, n -> n + 2)
                .limit(10)
                .collect(toList());
        System.out.println(r1);

//        斐波纳契数列
        List<List<Integer>> r2 = Stream.iterate(Arrays.asList(0, 1), t -> Arrays.asList(t.get(1), t.get(0) + t.get(1)))
                .limit(10)
                .collect(toList());
        System.out.println(r2);

        List<Double> r3 = Stream.generate(Math::random)
                .limit(5)
                .collect(toList());
        System.out.println(r3);

        Stream.generate(Thread::new).forEach(Thread::run);
    }
}
