package com.pph.demo.java8.design.patterns.observer.impl;

import com.pph.demo.java8.design.patterns.observer.LandingObserver;

/**
 * @author: pph
 * @date: 2019/11/28 17:02
 * @description:
 */
public class Aliens implements LandingObserver {

    @Override
    public void observeLanding(String name) {
        if (name.contains("Apollo")) {
            System.out.println("They're distracted, lets invade earth!");
        }
    }
}
