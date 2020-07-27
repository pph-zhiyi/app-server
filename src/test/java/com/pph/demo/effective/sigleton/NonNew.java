package com.pph.demo.effective.sigleton;

/**
 * @Author: PPH
 * @date 2019-06-06 17:19
 * @Description:
 */
public class NonNew {

    private NonNew() {
        throw new AssertionError();
    }

    public static NonNew getInstance() {
        return new NonNew();
    }
}
