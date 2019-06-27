package com.pph.demo.effective.other;

import com.pph.demo.effective.other.entity.stack.Stack;
import com.pph.demo.utils.JSONUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

        Collections.reverseOrder();
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
}
