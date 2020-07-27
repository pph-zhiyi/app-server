package com.pph.demo.java8.shizhan.d5;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Comparator;

import static java.util.Comparator.comparing;

/**
 * @author: pph
 * @date 2019/12/9 19:42
 * @description:
 */
@AllArgsConstructor
@Getter
public class Point {

    public final static Comparator<Point> compareByXAndThenY = comparing(Point::getX).thenComparing(Point::getY);

    private final int x;

    private final int y;

    public Point moveRightBy(int x) {
        return new Point(this.x + x, y);
    }
}
