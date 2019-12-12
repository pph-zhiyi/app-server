package com.pph.demo.java8.shizhan.d8;

import com.pph.demo.utils.common.Params;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static java.time.temporal.TemporalAdjusters.*;

/**
 * @author: pph
 * @date: 2019/12/12 08:24
 * @description:
 */
public class Demo {

    @Test
    public void d1() {
        Date date = new Date(119, 11, 12);
        System.out.println(Params.dateToStr(date));
    }

    @Test
    public void d2() {
        /*
         * LocalDate
         * 该类对实例是一个不可变对象，只提供简单的日期，并不包含当天时间信息
         * 也不包含任何时区相关的信息
         */
        LocalDate date = LocalDate.of(2019, 12, 12);
        System.out.println(date.toString());

        LocalDate parse = LocalDate.parse("2019-12-12");
        System.out.println(parse.toString());

        int year = date.getYear();
        System.out.println(year);

//        月份英文枚举
        Month month = date.getMonth();
        System.out.println(month.getValue());

        int day = date.getDayOfMonth();
        System.out.println(day);

//        周英文枚举
        DayOfWeek week = date.getDayOfWeek();
        System.out.println(week.getValue());

        int dayOfMonth = date.getDayOfMonth();
        System.out.println(dayOfMonth);

        boolean leapYear = date.isLeapYear();
        System.out.println(leapYear);

        LocalDate now = LocalDate.now();
        System.out.println(now.toString());

        System.out.println(date.get(ChronoField.YEAR));
        System.out.println(date.get(ChronoField.MONTH_OF_YEAR));
        System.out.println(date.get(ChronoField.DAY_OF_MONTH));
        System.out.println(date.get(ChronoField.DAY_OF_WEEK));

        LocalDateTime ldt = date.atTime(LocalTime.of(8, 53, 10));
        System.out.println(ldt.toString());
    }

    @Test
    public void d3() {
        /*
         * LocalTime
         */
        LocalTime time = LocalTime.of(8, 53, 10);
        System.out.println(time.toString());

        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println(time.getSecond());

        LocalTime parse = LocalTime.parse("08:53:10");
        System.out.println(parse.toString());

        LocalDateTime ldt = time.atDate(LocalDate.of(2019, 12, 12));
        System.out.println(ldt.toString());
    }

    @Test
    public void d4() {
        /*
         * LocalDateTime
         */
        LocalDateTime ldt1 = LocalDateTime.of(2019, Month.DECEMBER, 12, 8, 53, 10);
        System.out.println(ldt1.toString());
        System.out.println(ldt1.toLocalDate());
        System.out.println(ldt1.toLocalTime());

        LocalDateTime ldt2 = LocalDateTime.of(LocalDate.of(2019, 12, 12), LocalTime.of(8, 53, 10));
        System.out.println(ldt2.toString());
    }

    @Test
    public void d5() {
        /*
         * Instant
         * Instant 的设计初衷是为了便于机器使用。它包含的是由秒及纳秒所构成的数字。
         * 所以，它无法处理那些我们非常容易理解的时间单位。
         */
        Instant now = Instant.now();
        System.out.println("当前时间戳：" + now.getEpochSecond());

        Instant i1 = Instant.ofEpochSecond(3);
        System.out.println(i1.getEpochSecond());

        Instant i2 = Instant.ofEpochSecond(3, 0);
        System.out.println(i2.getEpochSecond());

//        2 秒之后再加上 100 万纳秒(1秒)
        Instant i3 = Instant.ofEpochSecond(2, 1_000_000_000);
        System.out.println(i3.getEpochSecond());

//        4 秒之前的 100 万纳秒(1秒)
        Instant i4 = Instant.ofEpochSecond(4, -1_000_000_000);
        System.out.println(i4.getEpochSecond());

//        java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: DayOfMonth
//        int day = Instant.now().get(ChronoField.DAY_OF_MONTH);
    }

    @Test
    public void d6() {
        /*
         * Duration Period
         * 由于LocalDateTime和Instant是为不同的目的而设计的，一个是为了便于人阅读使用，
         * 另一个是为了便于机器处理，所以你不能将二者混用。如果你试图在这两类对象之间创建 duration，
         * 会触发一个DateTimeException异常。此外，由于Duration类主要用于以秒和纳 秒衡量时间的长短，
         * 你不能仅向between方法传递一个LocalDate对象做参数。如果你需要以年、月或者日的方式对多个时间单位建模，
         * 可以使用Period类。
         */
        Duration between = Duration.between(LocalTime.of(8, 0, 0), LocalTime.of(8, 30, 0));
        System.out.println("间隔秒: " + between.getSeconds());

        Duration b2 = Duration.between(Instant.ofEpochSecond(2), Instant.ofEpochSecond(2, 1_000_000_000));
        System.out.println("间隔秒: " + b2.getSeconds());

        Period p1 = Period.between(LocalDate.of(2019, 12, 10), LocalDate.of(2019, 12, 12));
        System.out.println(p1.getYears());
        System.out.println(p1.getMonths());
        System.out.println(p1.getDays());
    }

    @Test
    public void d7() {
        /*
         * TemporalAdjuster
         * 有的时候，你需要进行一些更加复杂的操作，比如，将日期调整到下个周日、下个工作日，或者是本月的最后一天。
         * 这时，你可以使用重载版本的 with 方法，向其传递一个提供了更多定制化选择的 TemporalAdjuste r对象，更加灵活地处理日期。
         * import static java.time.temporal.TemporalAdjusters.*;
         */
        LocalDate ld = LocalDate.of(2019, 12, 12);

        LocalDate sunday = ld.with(nextOrSame(DayOfWeek.SUNDAY));
        System.out.println("周日: " + sunday.toString());

        LocalDate lastDayOfMonth = ld.with(lastDayOfMonth());
        System.out.println("" + ld.with(firstDayOfMonth()).toString() + " - " + lastDayOfMonth.toString());
    }

    @Test
    public void d8() {
        /*
         * 实现一个定制的 TemporalAdjuster
         */
        LocalDate date = LocalDate.now();
        LocalDate d1 = date.with(temporal -> {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
            else if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });
        System.out.println("d1: " + d1.toString());

        LocalDate d2 = date.with(t -> new NextWorkingDay().adjustInto(t));
        System.out.println("d2: " + d2.toString());

        TemporalAdjuster nextWorkingDay = ofDateAdjuster(temporal -> {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
            else if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });

        LocalDate d3 = date.with(nextWorkingDay);
        System.out.println("d3: " + d3.toString());
    }

    @Test
    public void d9() {
        /*
         * 印输出及解析日期时间对象
         */
        LocalDate date = LocalDate.now();

        System.out.println(date.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));

        LocalDate p1 = LocalDate.parse("20191212", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate p2 = LocalDate.parse("2019-12-12", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(p1);
        System.out.println(p2);

        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String f1 = dtf1.format(date);
        System.out.println(f1);

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        String f2 = date.format(dtf2);
        System.out.println(f2);
        LocalDate p3 = LocalDate.parse(f2, dtf2);
        System.out.println(p3);

        DateTimeFormatter italianFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);
        System.out.println(italianFormatter.format(date));
    }

    @Test
    public void d10() {
        ZoneId zoneId = TimeZone.getDefault().toZoneId();
        System.out.println(zoneId.toString());
        ZoneId sh = ZoneId.of("Asia/Shanghai");

        LocalDate date = LocalDate.of(2019, Month.DECEMBER, 12);
        ZonedDateTime zdt1 = date.atStartOfDay(sh);
        System.out.println(zdt1.toString());

        LocalDateTime dateTime = LocalDateTime.of(2019, Month.DECEMBER, 12, 8, 8, 8);
        ZonedDateTime zdt2 = dateTime.atZone(sh);
        System.out.println(zdt2.toString());

        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(sh);
        System.out.println(zdt3.toString());
    }
}
