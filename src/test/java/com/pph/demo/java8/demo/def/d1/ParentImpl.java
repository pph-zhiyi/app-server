package com.pph.demo.java8.demo.def.d1;

/**
 * @author: pph
 * @date: 2019/11/26 18:27
 * @description:
 */
public class ParentImpl implements Parent {

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
