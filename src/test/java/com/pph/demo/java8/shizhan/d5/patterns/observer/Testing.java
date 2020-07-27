package com.pph.demo.java8.shizhan.d5.patterns.observer;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * @author pph
 * @date 2019/12/9 18:48
 * @description
 */
public class Testing {

    @Test
    public void t1() {
        Feed f = new Feed();
        f.registerObserver(new NYTimes());
        f.registerObserver(new Guardian());
        f.registerObserver(new LeMonde());
        f.notifyObservers("The queen said her favourite book is Java 8 in Action!");
    }

    @Test
    public void t2() {
        Feed f = new Feed();

        f.registerObserver((String tweet) -> {
            if (StringUtils.isNotBlank(tweet) && tweet.contains("money")) {
                System.out.println("Breaking news in NY! " + tweet);
            }
        });

        f.registerObserver((String tweet) -> {
            if (StringUtils.isNotBlank(tweet) && tweet.contains("queen")) {
                System.out.println("Yet another news in London... " + tweet);
            }
        });

        f.registerObserver((String tweet) -> {
            if (StringUtils.isNotBlank(tweet) && tweet.contains("wine")) {
                System.out.println("Today cheese, wine and news! " + tweet);
            }
        });

        f.notifyObservers("The queen said her favourite book is Java 8 in Action!");
    }
}
