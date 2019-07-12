package com.pph.demo.utils;

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
    /**
     * 私有无参构造
     */
    private ParamUtil() {
    }

    /**
     * 对象非空校验：
     * <p>
     * 1、非空：返回原对象
     * 2、空：抛出 IllegalArgumentException 异常（无异常描述）
     *
     * @param obj
     * @param <E>
     * @return
     */
    public <E> E notNull(E obj) {
        return Optional.ofNullable(obj).orElseThrow(IllegalArgumentException::new);
    }

    /**
     * 对象非空校验：
     * <p>
     * 1、非空：返回原对象
     * 2、空：抛出 IllegalArgumentException 异常（自定义异常描述）
     *
     * @param obj
     * @param msg
     * @param <E>
     * @return
     */
    public <E> E notNull(E obj, String msg) {
        return Optional.ofNullable(obj).<IllegalArgumentException>orElseThrow(() -> {
            throw new IllegalArgumentException(notNull(msg));
        });
    }

    /**
     * 分页参数初始化
     *
     * @param obj
     */
    public static void makePageInfo(Map<String, Object> obj) {
        String pageNo = Constants.Page.PAGE_NO.val(), pageSize = Constants.Page.PAGE_SIZE.val();
        String offSet = Constants.Page.OFF_SET.val(), isPage = Constants.Page.IS_PAGE.val();
        if (!obj.containsKey(pageNo) || Objects.isNull(obj.get(pageNo))) {
            obj.put(pageNo, 1);
        }
        if (!obj.containsKey(pageSize) || Objects.isNull(obj.get(pageSize))) {
            obj.put(pageSize, 20);
        }
        if (!obj.containsKey(isPage) || Objects.isNull(obj.get(isPage))) {
            obj.put(isPage, true);
        }
        obj.put(offSet, (Integer.parseInt(String.valueOf(obj.get(pageNo))) - 1)
                * Integer.parseInt(String.valueOf(obj.get(pageSize))));
    }
}
