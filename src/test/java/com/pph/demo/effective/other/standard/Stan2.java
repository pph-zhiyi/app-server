package com.pph.demo.effective.other.standard;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author PPH
 * @date 2019-07-23 12:29
 * @description
 */
public class Stan2 {

    public static void main(String[] args) {

//        随机数
        System.out.println(ThreadLocalRandom.current().nextInt(10));

        Class<? extends Set<String>> c1 = null;
    }
}
