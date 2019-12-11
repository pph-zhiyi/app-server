package com.pph.demo.java8.shizhan.d5.patterns.observer;

import org.apache.commons.lang.StringUtils;

/**
 * @author: pph
 * @date: 2019/12/9 18:42
 * @description:
 */
public class NYTimes implements Observer {

    @Override
    public void notify(String tweet) {
        if (StringUtils.isNotBlank(tweet) && tweet.contains("money")) {
            System.out.println("Breaking news in NY! " + tweet);
        }
    }
}
