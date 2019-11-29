package com.pph.demo.java8.design.patterns.mandator.action.impl;

import com.pph.demo.java8.design.patterns.mandator.Editor;
import com.pph.demo.java8.design.patterns.mandator.action.Action;

/**
 * @author: pph
 * @date: 2019/11/28 15:37
 * @description:
 */
public class Close implements Action {

    private final Editor editor;

    public Close(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void perform() {
        editor.close();
    }
}
