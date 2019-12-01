package com.pph.demo.java8.lambda.patterns.observer.impl;

import com.pph.demo.java8.lambda.patterns.observer.LandingObserver;

/**
 * @author: pph
 * @date: 2019/11/28 17:04
 * @description:
 */
public class Nasa implements LandingObserver {

    @Override
    public void observeLanding(String name) {
        if (name.contains("Apollo")) {
            System.out.println("We made it!");
        }
    }
}
