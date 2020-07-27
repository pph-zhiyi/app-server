package com.pph.demo.java8.shizhan.d5.patterns.link;

/**
 * @author: pph
 * @date 2019/12/9 19:02
 * @description:
 */
public class SpellCheckerProcessing extends ProcessingObject<String> {

    @Override
    protected String handleWork(String text) {
        return text.replaceAll("labda", "lambda");
    }
}
