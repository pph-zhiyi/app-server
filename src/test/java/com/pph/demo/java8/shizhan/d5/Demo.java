package com.pph.demo.java8.shizhan.d5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author: pph
 * @date: 2019/12/9 08:48
 * @description:
 */
public class Demo {

    @Test
    public void d1() {
        Point p1 = new Point(5, 5);
        Point p2 = p1.moveRightBy(10);

        assertEquals(15, p2.getX());
        assertEquals(5, p2.getY());
    }

    @Test
    public void d2() {
        Point p1 = new Point(10, 15);
        Point p2 = new Point(10, 20);

        int result = Point.compareByXAndThenY.compare(p1 , p2);
        assertEquals(-1, result);
    }
}
