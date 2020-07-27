package com.pph.demo.bingFaBianChengShiZhan.demo.d2;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: pph
 * @date 2020/1/7 09:02
 * @description
 */
public class StrSet {

    private Set<String> set = new HashSet<>();

    public synchronized void addStr(String str) {
        set.add(str);
    }

    public synchronized boolean containsStr(String str) {
        return set.contains(str);
    }
}
