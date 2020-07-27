package com.pph.demo.bingFaBianChengShiZhan.demo.d2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: pph
 * @date 2020/1/9 17:27
 * @description
 */
public class ListHelper<E> {

    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    public boolean putIfAbsent(E x) {
        synchronized (list) {
            boolean absent = !list.contains(x);
            if (absent) {
                list.add(x);
            }
            return absent;
        }
    }
}
