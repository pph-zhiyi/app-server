package com.pph.demo.java8.demo.def.d1;

/**
 * @author: pph
 * @date: 2019/11/26 18:26
 * @description:
 */
public interface Parent {

    default void welcome() {
        message("Parent: Hi!");
    }

    void message(String msg);

    String getLastMessage();
}
