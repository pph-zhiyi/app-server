package com.pph.demo.java8.design.patterns.template.la;

import com.pph.demo.java8.design.patterns.template.ApplicationDenied;

/**
 * @author: pph
 * @date: 2019/11/29 09:20
 * @description:
 */
public interface Criteria {

    void check() throws ApplicationDenied;
}
