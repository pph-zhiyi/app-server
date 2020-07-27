package com.pph.demo.effective.jdk8.lambda.stream;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.LongStream;

/**
 * @Author: PPH
 * @date: 2019-07-18 17:26
 * @Description:
 */
public class S3 {

    public static void main(String[] args) {

        System.out.println(dateToStr(System.currentTimeMillis()));
        System.out.println(p1(10 * 10 * 10 * 10 * 10 * 10 * 10));
        System.out.println(dateToStr(System.currentTimeMillis()));
        System.out.println(p2(10 * 10 * 10 * 10 * 10 * 10 * 10));
        System.out.println(dateToStr(System.currentTimeMillis()));
    }

    /**
     * 作为并行性有效的流管道的简单示例，请考虑此函数来计算π(n)，素数小于或等于 n:
     * <p>
     * Prime-counting stream pipeline - benefits from parallelization
     *
     * @param n
     * @return
     */
    private static long p1(long n) {
        return LongStream.rangeClosed(2, n)
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count();
    }

    private static long p2(long n) {
        return LongStream.rangeClosed(2, n)
                .parallel()
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count();
    }

    /**
     * 格式化时间
     *
     * @param date
     * @return
     */
    private static String dateToStr(long date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(date));
    }
}
