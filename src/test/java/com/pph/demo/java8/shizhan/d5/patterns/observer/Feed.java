package com.pph.demo.java8.shizhan.d5.patterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pph
 * @date 2019/12/9 18:46
 * @description
 */
public class Feed implements Subject {

    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObservers(String tweet) {
        observers.forEach(o -> o.notify(tweet));
    }
}
