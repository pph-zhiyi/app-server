package com.pph.demo.java8.shizhan.d5.patterns.observer;

import org.apache.commons.lang.StringUtils;

/**
 * @author: pph
 * @date: 2019/12/9 18:43
 * @description:
 */
public class LeMonde implements Observer {

    @Override
    public void notify(String tweet) {
        if (StringUtils.isNotBlank(tweet) && tweet.contains("wine")) {
            System.out.println("Today cheese, wine and news! " + tweet);
        }
    }
}
