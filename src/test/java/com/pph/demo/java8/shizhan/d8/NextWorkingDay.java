package com.pph.demo.java8.shizhan.d8;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

import static java.time.temporal.TemporalAdjusters.ofDateAdjuster;

/**
 * @author: pph
 * @date: 2019/12/12 10:17
 * @description:
 */
public class NextWorkingDay implements TemporalAdjuster {

    @Override
    public Temporal adjustInto(Temporal temporal) {
//        读取当前日期
        DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int dayToAdd;
//        如果是周五增加 3 天
        if (dow == DayOfWeek.FRIDAY)
            dayToAdd = 3;
//        如果是周六增加 2 天
        else if (dow == DayOfWeek.SATURDAY)
            dayToAdd = 2;
//        其他情况增加 1 天
        else
            dayToAdd = 1;
        return temporal.plus(dayToAdd, ChronoUnit.DAYS);
    }

    public static TemporalAdjuster get(Temporal t) {
        return ofDateAdjuster(temporal -> {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
            else if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });
    }
}
