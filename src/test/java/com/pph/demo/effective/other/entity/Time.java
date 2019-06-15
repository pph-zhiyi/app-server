package com.pph.demo.effective.other.entity;

/**
 * @Author: PPH
 * @Date: 2019-06-12 21:37
 * @Description:
 */
public final class Time {

    private static final int HOURS_PER_DAY = 24;

    private static final int MINUTES_PER_HOUR = 60;

    private final int hour;

    private final int minute;

    public Time(int hour, int minute) {
        if (hour < 0 || hour > HOURS_PER_DAY)
            throw new IllegalArgumentException(String.format("hour %d error!", hour));
        if (minute < 0 || minute > MINUTES_PER_HOUR) {
            throw new IllegalArgumentException(String.format("minute %d error!", minute));
        }
        this.hour = hour;
        this.minute = minute;
    }
}
