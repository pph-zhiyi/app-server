package com.pph.demo.utils.common;

import com.alibaba.fastjson.JSON;
import com.pph.demo.utils.Constants;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
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
            return Constants.BLANK;

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
            return Constants.BLANK;

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

    /**
     * 分页参数初始化
     *
     * @param filter
     */
    public static void makePageInfo(Map<String, Object> filter) {
        Objects.requireNonNull(filter, "aspect can not be null!");

        String pageNo = Constants.Page.PAGE_NO.val(), pageSize = Constants.Page.PAGE_SIZE.val();
        String offSet = Constants.Page.OFF_SET.val(), isPage = Constants.Page.IS_PAGE.val();

        if (!filter.containsKey(pageNo) || Objects.isNull(filter.get(pageNo)))
            filter.put(pageNo, 1);

        if (!filter.containsKey(pageSize) || Objects.isNull(filter.get(pageSize)))
            filter.put(pageSize, 20);

        if (!filter.containsKey(isPage) || Objects.isNull(filter.get(isPage)))
            filter.put(isPage, true);

        filter.put(offSet, (Integer.parseInt(String.valueOf(filter.get(pageNo))) - 1)
                * Integer.parseInt(String.valueOf(filter.get(pageSize))));
    }

    /**
     * 对象转 JSON 字符串
     *
     * @param o 对象
     * @return JSON 字符串
     */
    public static String toJsonStr(Object o) {
        return Optional.ofNullable(o)
                .map(JSON::toJSONString)
                .orElse("{}");
    }
}
