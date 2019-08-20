package com.pph.demo.utils;

import com.pph.demo.utils.common.Params;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: PPH
 * @Date: 2019-08-20 10:43
 * @Description:
 */
public final class DateGranularity {
    private DateGranularity() {
    }

    /**
     * 默认时间间隔
     */
    private static final int DEFAULT_INTERVAL = 1;
    /**
     * 默认时间字符串格式
     */
    private static final String DEFAULT_DATE_FORMAT = Constants.DEFAULT_DATE_FORMAT;

    /**
     * 按粒度获取某个时间段内所有时间
     *
     * @param begin
     * @param end
     * @param gran
     * @return
     */
    public static List<String> getDateAll(Date begin, Date end, Granularity gran) {
        return getDateAll(Params.dateToStr(begin), Params.dateToStr(end), gran);
    }

    /**
     * 按粒度获取某个时间段内所有时间
     *
     * @param begin
     * @param end
     * @param gran
     * @return
     */
    public static List<String> getDateAll(String begin, String end, Granularity gran) {
        SimpleDateFormat sdf = new SimpleDateFormat(gran.format);
        Date from, to;
        try {
            from = sdf.parse(begin);
            to = sdf.parse(end);
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间解析异常，请传人正确的时间字符串");
        }
        return findDates(from, to, gran);
    }

    /**
     * 获取时间点
     *
     * @param begin
     * @param end
     * @param gran
     * @return
     */
    private static List<String> findDates(Date begin, Date end, Granularity gran) {
        List<String> result = new ArrayList<>();
        result.add(Params.dateToStr(begin));

        Calendar calBegin = Calendar.getInstance(), calEnd = Calendar.getInstance();
        calBegin.setTime(begin);
        calEnd.setTime(end);

        while (end.after(calBegin.getTime())) {
            calBegin.add(gran.code, DEFAULT_INTERVAL);
            result.add(Params.dateToStr(calBegin.getTime()));
        }

        return result;
    }

    /**
     * 时间粒度枚举
     */
    public enum Granularity {
        /**
         * 时间粒度枚举
         */
        SECOND(13, DEFAULT_DATE_FORMAT, "秒"),
        MINUTE(12, "yyyy-MM-dd HH:mm", "分"),
        HOUR(10, "yyyy-MM-dd HH", "时"),
        DAY(6, "yyyy-MM-dd", "天"),
        MONTH(2, "yyyy-MM", "月"),
        YEAR(1, "yyyy", "年");

        final int code;
        final String format;
        final String desc;

        Granularity(int code, String format, String desc) {
            this.code = code;
            this.format = format;
            this.desc = desc;
        }
    }
}
