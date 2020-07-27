package com.pph.demo.effective.sigleton;

/**
 * @Author: PPH
 * @date 2019-06-06 17:00
 * @Description:
 */
public class Test {

    public static void main(String[] args) {

        NonNew.getInstance();

        for (int i = 0; i < 10; i++) {
            System.out.println(Elvis.INSTANCE);
        }
        System.out.println("--------------------");
        for (int i = 0; i < 10; i++) {
            System.out.println(Elvis2.getInstance());
        }
    }
}
