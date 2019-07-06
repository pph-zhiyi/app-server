package com.pph.demo.effective.other.entity.chooser;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: PPH
 * @Date: 2019-06-21 17:42
 * @Description:
 */
public class Test {

    public static void main(String[] args) {

        Chooser1 c1 = new Chooser1(Stream.of("Demo", "b", "c").collect(Collectors.toList()));
        System.out.println(c1.choose());

        Chooser2<String> c2 = new Chooser2<>(Stream.of("Demo", "b", "c").collect(Collectors.toList()));
        System.out.println(c2.choose());

        Chooser3<String> c3 = new Chooser3<>(Stream.of("Demo", "b", "c").collect(Collectors.toList()));
        System.out.println(c3.choose());
    }
}
