package com.pph.demo.effective.other.enums;

/**
 * @Author: PPH
 * @Date: 2019-07-05 09:12
 * @Description: 使用实例属性替代序数
 */
public enum Ensemble {

//    SOLO, DUET, TRIO, QUARTET, QUINTET,
//    SEXTET, SEPTET, OCTET, NONET, DECTET;
//
//    public int numberOfMusicians() {
//        return ordinal() + 1;
//    }


    OLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5),
    SEXTET(6), SEPTET(7), OCTET(8), DOUBLE_QUARTET(8),
    NONET(9), DECTET(10), TRIPLE_QUARTET(12);

    private final int numberOfMusicians;

    Ensemble(int size) {
        this.numberOfMusicians = size;
    }

    public int numberOfMusicians() {
        return this.numberOfMusicians;
    }

    public static void main(String[] args) {
        System.out.println(Ensemble.TRIO.numberOfMusicians());
        System.out.println(Ensemble.TRIPLE_QUARTET.numberOfMusicians());
    }
}