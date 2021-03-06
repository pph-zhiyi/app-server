package com.pph.demo.java8.shizhan.d5.patterns.observer;

import org.apache.commons.lang.StringUtils;

/**
 * @author pph
 * @date 2019/12/9 18:43
 * @description
 */
public class Guardian implements Observer {

    @Override
    public void notify(String tweet) {
        if (StringUtils.isNotBlank(tweet) && tweet.contains("queen")) {
            System.out.println("Yet another news in London... " + tweet);
        }
    }
}
