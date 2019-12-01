package com.pph.demo.java8.lambda.patterns.template;

/**
 * @author: pph
 * @date: 2019/11/29 09:13
 * @description:
 */
public class ApplicationDenied extends Exception {

    public ApplicationDenied() {
        super();
    }

    public ApplicationDenied(Exception e) {
        super(e);
    }

    public ApplicationDenied(String msg) {
        super(msg);
    }
}
