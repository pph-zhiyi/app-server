package com.pph.demo.java8.demo.def.d1;

/**
 * @author: pph
 * @date: 2019/11/26 18:38
 * @description:
 */
public class OverridingParent extends ParentImpl {

    @Override
    public void welcome() {
        message("Class Parent: Hi!");
    }
}
