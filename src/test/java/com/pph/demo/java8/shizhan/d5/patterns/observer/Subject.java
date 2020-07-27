package com.pph.demo.java8.shizhan.d5.patterns.observer;

/**
 * @author: pph
 * @date 2019/12/9 18:44
 * @description
 */
public interface Subject {

    void registerObserver(Observer o);

    void notifyObservers(String tweet);
}
