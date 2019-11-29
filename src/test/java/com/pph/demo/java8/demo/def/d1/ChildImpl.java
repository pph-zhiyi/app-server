package com.pph.demo.java8.demo.def.d1;

/**
 * @author: pph
 * @date: 2019/11/26 18:35
 * @description:
 */
public class ChildImpl implements Child {

    private String message;

    @Override
    public void message(String msg) {
        this.message = msg;
        System.out.println(msg);
    }

    @Override
    public String getLastMessage() {
        return this.message;
    }
}
