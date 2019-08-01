package com.pph.demo.utils;

/**
 * @Author: PPH
 * @Date: 2019-07-02 15:42
 * @Description:
 */
public final class Constants {
    private Constants() {

    }

    /**
     * 默认时间字符串格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 空字符串
     */
    public static final String EMPTY = "";
    /**
     * 通用空格占位符
     */
    public static final String SIGN_1 = " ";
    /**
     * 下划线符号拼接符
     */
    public static final String SIGN_2 = "_";
    /**
     * 逗号
     */
    public static final String SIGN_3 = ",";
    /**
     * URL 参数拼接 与字符
     */
    public static final String SIGN_4 = "&";

    /**
     * 分页属性 KEY
     */
    public enum Page {
        PAGE_NO("pageNo"),
        PAGE_SIZE("pageSize"),
        OFF_SET("offSet"),
        IS_PAGE("isPage");

        private final String val;

        Page(String val) {
            this.val = val;
        }

        public String val() {
            return val;
        }
    }
}
