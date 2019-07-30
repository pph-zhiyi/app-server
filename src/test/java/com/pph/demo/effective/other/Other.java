package com.pph.demo.effective.other;

import com.pph.demo.effective.other.entity.stack.Stack;
import com.pph.demo.utils.JSONUtils;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: PPH
 * @Date: 2019-06-11 19:08
 * @Description:
 */
public class Other {

    public static void main(String[] args) {

        String[] stringDef = Stack.getStringDef();
//        不可变静态常量 list
        List<String> stringList = Stack.STRING_LIST;

        Set<String> s1 = Stream.of("a", "b").collect(Collectors.toSet());
        Set<String> s2 = Stream.of("aa", "bb").collect(Collectors.toSet());

        Set<String> union = union(s1, s2);
        System.out.println(JSONUtils.toJSONString(union));

//        Collections.reverseOrder();

        List<String> p1 = pickTwo("a", "b", "c");
        List<? extends Serializable> p2 = pickTwo("a", "b", 1);
        System.out.println(p1.toString());
        System.out.println(p2.toString());

        List<String> emptyList = getEmptyList();
    }

    /**
     * 合并 set 并去重
     *
     * @param s1
     * @param s2
     * @param <E>
     * @return
     */
    public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
        HashSet<E> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }

    /**
     * 合并 list
     *
     * @param lists
     * @param <T>
     * @return
     */
    @SafeVarargs
    public static <T> List<T> flatten(List<? extends T>... lists) {
        List<T> result = new ArrayList<>();
        for (List<? extends T> list : lists)
            result.addAll(list);
        return result;
    }

    /**
     * 三个入参中随机取两个入参生成 list
     *
     * @param a
     * @param b
     * @param c
     * @param <T>
     * @return
     */
    public static <T> List<T> pickTwo(T a, T b, T c) {
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0:
                return Stream.of(a, b).collect(Collectors.toList());
            case 1:
                return Stream.of(a, c).collect(Collectors.toList());
            case 2:
                return Stream.of(b, c).collect(Collectors.toList());
        }
        throw new AssertionError();
    }

    public static List<String> getEmptyList() {
        return Collections.emptyList();
    }
}
