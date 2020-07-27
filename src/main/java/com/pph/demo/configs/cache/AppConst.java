package com.pph.demo.configs.cache;

import java.util.concurrent.TimeUnit;

/**
 * @author: pph
 * @date: 2020/2/7 16:22
 * @description
 */
class AppConst {

    static final String BASE_PACKAGE_NAME = "com.pph.demo";
    /**
     * 缓存最大时间（24小时）
     */
    static final Long CACHE_MAXIMUM_SIZE = TimeUnit.MILLISECONDS.convert(24, TimeUnit.HOURS);
    /**
     * 十分钟
     */
    static final long CACHE_MINUTE = TimeUnit.MILLISECONDS.convert(10, TimeUnit.SECONDS);
}
