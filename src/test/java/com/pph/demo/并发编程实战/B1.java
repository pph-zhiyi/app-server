package com.pph.demo.并发编程实战;

import com.pph.demo.model.User;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: PPH
 * @Date: 2019-07-25 12:24
 * @Description:
 */
public class B1 {

    static AtomicReference<User> atomicReference = new AtomicReference<>();

    public static void main(String[] args) {
    }
}
