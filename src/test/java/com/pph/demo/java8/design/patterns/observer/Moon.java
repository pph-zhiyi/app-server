package com.pph.demo.java8.design.patterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: pph
 * @date: 2019/11/28 17:01
 * @description:
 */
public class Moon {

    private final List<LandingObserver> observers = new ArrayList<>();

    public void land(String name) {
        for (LandingObserver observer : observers) {
            observer.observeLanding(name);
        }
    }

    public void startSpying(LandingObserver observer) {
        observers.add(observer);
    }
}
