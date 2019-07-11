package com.pph.demo.effective.other.annotation;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: PPH
 * @Date: 2019-07-09 12:47
 * @Description:
 */
public class Sample2 {

    @ExceptionTest(ArithmeticException.class)
    public static void m1() {
        int i = 0;
        i = i / i;
    }

    @ExceptionTest(ArithmeticException.class)
    public static void m2() {
        int[] a = new int[0];
        int i = a[1];
    }

    @ExceptionTest(ArithmeticException.class)
    public static void m3() {
    }

//    @ExceptionTest({IndexOutOfBoundsException.class, NullPointerException.class})
//    public static void doublyBad() {
//        List<String> list = new ArrayList<>();
//        // The spec permits this method to throw either
//        // IndexOutOfBoundsException or NullPointerException
//        list.addAll(5, null);
//    }

    @ExceptionTest(NullPointerException.class)
    @ExceptionTest(IndexOutOfBoundsException.class)
    public static void doublyBad() {
        List<String> list = new ArrayList<>();
        // The spec permits this method to throw either
        // IndexOutOfBoundsException or NullPointerException
        list.addAll(5, null);
    }
}
