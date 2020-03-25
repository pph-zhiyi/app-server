package com.pph.demo.utils.common;

import com.alibaba.fastjson.JSON;
import com.pph.demo.model.User;
import com.pph.demo.utils.Constants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

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
     * @param obj 对象
     * @param <E> 范型
     * @return 结果
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
     * @param obj 对象
     * @param msg 异常谢谢
     * @param <E> 范型
     * @return 结果
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
     * @param str 字符串
     * @param <E> 范型
     * @return 结果
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
     * @param str 字符串
     * @param msg 异常信息
     * @param <E> 范型
     * @return 结果
     */
    public static <E extends String> E notBlank(E str, E msg) {
        if (StringUtils.isEmpty(str))
            throw new IllegalArgumentException(msg);
        return str;
    }

    /**
     * 首字母大写
     *
     * @param name 字符串
     * @return 结果字符串
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
     * @param columnName 字段名称
     * @return 驼峰命名字符串
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
     * @param date 时间
     * @return 结果字符串
     */
    public static String dateToStr(Date date) {
        return dateToStr(date, Constants.DEFAULT_DATE_FORMAT);
    }

    /**
     * 将 Date 格式化为自定义字符串类型
     *
     * @param date   时间
     * @param format 格式化
     * @return 结果字符串
     */
    public static String dateToStr(Date date, String format) {
        return new SimpleDateFormat(notBlank(format, "format can not be blank!"))
                .format(notNull(date, "date can not be null!"));
    }

    /**
     * 将 13位时间戳 格式化为字符串类型（默认：yyyy-MM-dd HH:mm:ss）
     *
     * @param date 时间
     * @return 结果字符串
     */
    public static String dateToStr(long date) {
        return dateToStr(new Date(date));
    }

    /**
     * 将 13位时间戳 格式化为自定义字符串类型
     *
     * @param date   时间
     * @param format 格式化
     * @return 结果字符串
     */
    public static String dateToStr(long date, String format) {
        return dateToStr(new Date(date), format);
    }

    /**
     * 分页参数初始化
     *
     * @param filter 入参
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

    /**
     * 获取对象不为 null 的所有属性
     *
     * @param o 对象
     * @return 集合
     */
    public static List<String> objNonNullFieldsList(Object o) {
        return Arrays.asList(objNonNullFieldsArr(o));
    }

    /**
     * 获取对象为 null 的所有属性
     *
     * @param o 对象
     * @return 集合
     */
    public static List<String> objIsNullFieldsList(Object o) {
        return Arrays.asList(objIsNullFieldsArr(o));
    }

    /**
     * 获取对象不为 null 的所有属性
     *
     * @param o 对象
     * @return 数组
     */
    public static String[] objNonNullFieldsArr(Object o) {
        try {
            return objFieldsGroup(o).get(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取对象为 null 的所有属性
     *
     * @param o 对象
     * @return 数组
     */
    public static String[] objIsNullFieldsArr(Object o) {
        try {
            return objFieldsGroup(o).get(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将对象为：null 的属性与不为 null 的属性分组
     *
     * @param o 对象
     * @return true: Non null，false: Is null
     * @throws IllegalAccessException 异常
     */
    private static Map<Boolean, String[]> objFieldsGroup(Object o) throws IllegalAccessException {
        List<String> nonNull = new ArrayList<>(), isNull = new ArrayList<>();
        Class<?> clazz = o.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            String name = field.getName();
//            设置对象的访问权限，保证对 private 的属性的访问
            field.setAccessible(true);
            if (Objects.nonNull(field.get(o))) {
                nonNull.add(name);
            } else {
                isNull.add(name);
            }
        }
        return new HashMap<Boolean, String[]>(2) {{
            put(true, nonNull.toArray(new String[0]));
            put(false, isNull.toArray(new String[0]));
        }};
    }

    /**
     * Bean copy
     *
     * @param source 源对象
     * @param <T>    范型
     * @return 结果
     */
    @SuppressWarnings("unchecked")
    public static <T> T copyBean(T source) {
        return Optional.ofNullable(source)
                .map(s -> {
                    T t;
                    try {
                        t = (T) s.getClass().newInstance();
                        BeanUtils.copyProperties(source, t);
                        return t;
                    } catch (Exception e) {
                        throw new IllegalArgumentException(e);
                    }
                })
                .orElse(null);
    }

    /**
     * 将 source 中值不为 null 的属性 set 到 target 中
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copySourceNonNullFields(Object source, Object target) {
        BeanUtils.copyProperties(
                notNull(source, "Source must not be null"),
                notNull(target, "Target must not be null"),
                objIsNullFieldsArr(source));
    }

    /**
     * 将 target 中值为 null 的属性替换为 source 属性的值
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyTargetIsNullFields(Object source, Object target) {
        BeanUtils.copyProperties(
                notNull(source, "Source must not be null"),
                notNull(target, "Target must not be null"),
                objNonNullFieldsArr(target));
    }
}
