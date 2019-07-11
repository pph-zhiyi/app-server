package com.pph.demo.effective.jdk8.lambda;

import com.pph.demo.effective.builder.user.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: PPH
 * @Date: 2019-07-09 17:40
 * @Description: 方法引用优于 lambda 表达式
 */
public class L3 {

    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<String, Integer>(4) {{
            put("a", 1);
            put("b", 2);
        }};

        Integer a = map.merge("c", 3, Integer::sum);
        System.out.println(a);
        System.out.println(map.toString());
    }
}
