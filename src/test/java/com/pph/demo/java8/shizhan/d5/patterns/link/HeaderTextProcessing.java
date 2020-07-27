package com.pph.demo.java8.shizhan.d5.patterns.link;

/**
 * @author: pph
 * @date 2019/12/9 18:59
 * @description:
 */
public class HeaderTextProcessing extends ProcessingObject<String> {

    @Override
    protected String handleWork(String text) {
        return "From Raoul, Mario and Alan: " + text;
    }
}
