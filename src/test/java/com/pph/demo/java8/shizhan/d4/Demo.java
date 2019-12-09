package com.pph.demo.java8.shizhan.d4;

import org.junit.Test;

import java.util.Spliterator;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author: pph
 * @date: 2019/12/6 08:17
 * @description:
 */
public class Demo {

    @Test
    public void d1() {
        System.out.println(parallelSum(100));

        int count = Runtime.getRuntime().availableProcessors();
        System.out.println(count);

//        全剧设置并行流大小，不建议修改
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "15");
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1L)
                .limit(n)
                .parallel()
//                .sequential()
                .reduce(0L, Long::sum);
    }

    public long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest)
                fastest = duration;
        }
        return fastest;
    }

    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
    }

    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    @Test
    public void d2() {
        System.out.println("Parallel sum done in: " + measureSumPerf(Demo::parallelSum, 10_000_000) + " msecs");
        System.out.println("------------------");
        System.out.println("Parallel sum done in: " + measureSumPerf(Demo::rangedSum, 10_000_000) + " msecs");
        System.out.println("------------------");
        System.out.println("Parallel sum done in: " + measureSumPerf(Demo::parallelRangedSum, 10_000_000) + " msecs");
        System.out.println("------------------");
        System.out.println("ForkJoin sum done in: " + measureSumPerf(ForkJoinSumCalculator::forkJoinSum, 10_000_000) + " msecs");
    }

    public int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace)
                    counter++;
                lastSpace = false;
            }
        }
        return counter;
    }

    private int countWords(Stream<Character> stream) {
        return stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine)
                .getCounter();
    }

    @Test
    public void d3() {
        final String SENTENCE = " Nel   mezzo del cammin  di nostra  vita " +
                "mi  ritrovai in una  selva oscura" +
                " ché la  dritta via era   smarrita ";
        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");

        System.out.println("Found " + countWords(IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt)) + " words");

        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        System.out.println("Found " + countWords(stream) + " words");
    }
}
