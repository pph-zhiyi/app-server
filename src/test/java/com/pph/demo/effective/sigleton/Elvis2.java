package com.pph.demo.effective.sigleton;

/**
 * @Author: PPH
 * @date 2019-06-06 17:03
 * @Description:
 */
public class Elvis2 {

    private static final Elvis2 INSTANCE = new Elvis2();

    private Elvis2() {
    }

    public static Elvis2 getInstance() {
        return INSTANCE;
    }
}
