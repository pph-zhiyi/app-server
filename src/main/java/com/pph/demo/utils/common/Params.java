package com.pph.demo.utils.common;

import com.pph.demo.utils.Constants;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 * @Author: PPH
 * @Date: 2019-07-30 15:02
 * @Description:
 */
public final class Params {
    private Params() {

    }

    /**
     * 对象非空校验：
     * <p>
     * 1、False：返回原对象
     * 2、True：抛出 IllegalArgumentException 异常（无异常描述）
     *
     * @param obj
     * @param <E>
     * @return
     */
    public static <E> E notNull(E obj) {
        return notNull(obj, null);
    }

    /**
     * 对象非空校验：
     * <p>
     * 1、False：返回原对象
     * 2、True：抛出 IllegalArgumentException 异常（自定义异常描述）
     *
     * @param obj
     * @param msg
     * @param <E>
     * @return
     */
    public static <E> E notNull(E obj, String msg) {
        return Optional.ofNullable(obj).<IllegalArgumentException>orElseThrow(() -> {
            throw new IllegalArgumentException(msg);
        });
    }

    /**
     * 字符串非空校验：
     * <p>
     * 1、False：返回原字符串
     * 2、True：抛出 IllegalArgumentException 异常（无异常描述）
     *
     * @param str
     * @param <E>
     * @return
     */
    public static <E extends String> E notBlank(E str) {
        return notBlank(str, null);
    }

    /**
     * 字符串非空校验：
     * <p>
     * 1、False：返回原字符串
     * 2、True：抛出 IllegalArgumentException 异常（自定义异常描述）
     *
     * @param str
     * @param msg
     * @param <E>
     * @return
     */
    public static <E extends String> E notBlank(E str, E msg) {
        if (StringUtils.isEmpty(str))
            throw new IllegalArgumentException(msg);
        return str;
    }

    /**
     * 首字母大写
     *
     * @param name
     * @return
     */
    public static String captureName(String name) {
        if (StringUtils.isEmpty(name))
            return Constants.EMPTY;

        char[] cs = name.toCharArray();
        if (cs[0] >= 97 && cs[0] <= 122)
            cs[0] -= 32;

        return String.valueOf(cs);
    }

    /**
     * 格式化 DB columnName：user_name ---> setUserName
     *
     * @param columnName
     * @return
     */
    public static String makeColumnName(String columnName) {
        if (StringUtils.isEmpty(columnName))
            return Constants.EMPTY;

        StringBuilder result = new StringBuilder();
        String[] split = columnName.split(Constants.SIGN_2);
        for (String s : split)
            result.append(captureName(s));


        return String.valueOf(result);
    }

    /**
     * 将 Date 格式化为字符串类型（默认：yyyy-MM-dd HH:mm:ss）
     *
     * @param date
     * @return
     */
    public static String dateToStr(Date date) {
        return dateToStr(date, Constants.DEFAULT_DATE_FORMAT);
    }

    /**
     * 将 Date 格式化为自定义字符串类型
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToStr(Date date, String format) {
        return new SimpleDateFormat(notBlank(format, "format can not be blank!"))
                .format(notNull(date, "date can not be null!"));
    }

    /**
     * 将 13位时间戳 格式化为字符串类型（默认：yyyy-MM-dd HH:mm:ss）
     *
     * @param date
     * @return
     */
    public static String dateToStr(long date) {
        return dateToStr(new Date(date));
    }

    /**
     * 将 13位时间戳 格式化为自定义字符串类型
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToStr(long date, String format) {
        return dateToStr(new Date(date), format);
    }
}
