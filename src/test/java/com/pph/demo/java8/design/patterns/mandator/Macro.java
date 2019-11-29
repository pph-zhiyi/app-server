package com.pph.demo.java8.design.patterns.mandator;

import com.pph.demo.java8.design.patterns.mandator.action.Action;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: pph
 * @date: 2019/11/28 15:38
 * @description:
 */
public class Macro {

    private final List<Action> actions;

    public Macro() {
        this.actions = new ArrayList<>();
    }

    public void record(Action action) {
        this.actions.add(action);
    }

    public void record(List<Action> actions) {
        this.actions.addAll(actions);
    }

    public void run() {
        actions.forEach(Action::perform);
    }
}
