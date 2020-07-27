package com.pph.demo.effective.other.enums;

/**
 * @Author: PPH
 * @date: 2019-07-02 16:17
 * @Description:
 */
public enum Planet {
    TEST   (1.1, 2.2),
    MERCURY(3.302e+23, 2.439e6),
    VENUS  (4.869e+24, 6.052e6),
    EARTH  (5.975e+24, 6.378e6),
    MARS   (6.419e+23, 3.393e6),
    JUPITER(1.899e+27, 7.149e7),
    SATURN (5.685e+26, 6.027e7),
    URANUS (8.683e+25, 2.556e7),
    NEPTUNE(1.024e+26, 2.477e7);

    private final double mass;

    private final double radius;

    private final double surfaceGravity;

    private static final double G = 6.67300E-11;

    Planet(double d1, double d2) {
        this.mass = d1;
        this.radius = d2;
        this.surfaceGravity = G * this.mass / (this.radius * this.radius);
    }

    public double mass() {
        return this.mass;
    }

    public double radius() {
        return this.radius;
    }

    public double surfaceGravity() {
        return this.surfaceGravity;
    }

    public double surfaceWeight(double mass) {
        return mass * this.surfaceGravity;
    }

    public static void main(String[] args) {
        Planet test = Planet.TEST;
        System.out.println(test);
        System.out.println(test.mass);
        System.out.println(test.radius);
    }
}
