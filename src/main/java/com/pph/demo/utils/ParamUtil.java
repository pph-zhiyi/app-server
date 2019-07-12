package com.pph.demo.utils;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: PPH
 * @Date: 2019-05-24 16:43
 * @Description:
 */
@Component
public final class ParamsDisposeUtil {

    /**
     * 分页参数初始化
     *
     * @param filter
     */
    public static void addFilterPageInfo(Map<String, Object> filter) {
        if (!filter.containsKey("pageNo") || filter.get("pageNo") == null) {
            filter.put("pageNo", 1);
        }
        if (!filter.containsKey("pageSize") || filter.get("pageSize") == null) {
            filter.put("pageSize", 20);
        }
        if (!filter.containsKey("isPage") || filter.get("isPage") == null) {
            filter.put("isPage", true);
        }
        filter.put("offSet", (Integer.parseInt(filter.get("pageNo").toString()) - 1)
                * Integer.parseInt(filter.get("pageSize").toString()));
    }
}
