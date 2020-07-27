package com.pph.demo.java8.lambda.patterns.mandator.action.impl;

import com.pph.demo.java8.lambda.patterns.mandator.Editor;
import com.pph.demo.java8.lambda.patterns.mandator.action.Action;

/**
 * @author: pph
 * @date 2019/11/28 15:36
 * @description:
 */
public class Open implements Action {

    private final Editor editor;

    public Open(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void perform() {
        editor.open();
    }
}
