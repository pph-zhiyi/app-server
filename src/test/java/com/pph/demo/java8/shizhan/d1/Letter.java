package com.pph.demo.java8.shizhan.d1;

/**
 * @author: pph
 * @date: 2019/12/3 09:34
 * @description:
 */
public class Letter {

    public static String addHeader(String text) {
        return "From Raoul, Mario and Alan: " + text;
    }

    public static String addFooter(String text) {
        return text + " Kind regards";
    }

    public static String checkSpelling(String text) {
        return text.replaceAll("labda", "lambda");
    }
}
