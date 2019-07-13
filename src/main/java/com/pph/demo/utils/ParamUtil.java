package com.pph.demo.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @Author: PPH
 * @Date: 2019-05-24 16:43
 * @Description:
 */
@Component
public final class ParamUtil {
    private ParamUtil() {
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
    public <E> E notNull(E obj) {
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
    public <E> E notNull(E obj, String msg) {
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
    public <E extends String> E notBlank(E str) {
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
    public <E extends String> E notBlank(E str, E msg) {
        if (StringUtils.isEmpty(str))
            throw new IllegalArgumentException(msg);
        return str;

    }

    /**
     * 分页参数初始化
     *
     * @param map
     */
    public static void makePageInfo(Map<String, Object> map) {
        String pageNo = Constants.Page.PAGE_NO.val(), pageSize = Constants.Page.PAGE_SIZE.val();
        String offSet = Constants.Page.OFF_SET.val(), isPage = Constants.Page.IS_PAGE.val();

        if (!map.containsKey(pageNo) || Objects.isNull(map.get(pageNo)))
            map.put(pageNo, 1);

        if (!map.containsKey(pageSize) || Objects.isNull(map.get(pageSize)))
            map.put(pageSize, 20);

        if (!map.containsKey(isPage) || Objects.isNull(map.get(isPage)))
            map.put(isPage, true);

        map.put(offSet, (Integer.parseInt(String.valueOf(map.get(pageNo))) - 1)
                * Integer.parseInt(String.valueOf(map.get(pageSize))));
    }
}
