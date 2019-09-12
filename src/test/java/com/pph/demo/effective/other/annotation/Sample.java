package com.pph.demo.effective.other.annotation;

/**
 * @Author: PPH
 * @Date: 2019-07-09 09:32
 * @Description:
 */
public class Sample {

    @Test
    public static void m1() {
        System.out.println("banner.txt");
    }

    public static void m2() {

    }

    @Test
    public static void m3() {
        throw new RuntimeException("Boom");
    }

    public static void m4() {
    }

    @Test
    public void m5() {
    }

    public static void m6() {
    }

    @Test
    public static void m7() {
        throw new RuntimeException("Crash");
    }

    public static void m8() {
    }
}
