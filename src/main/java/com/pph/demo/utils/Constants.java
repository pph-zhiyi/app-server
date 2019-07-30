package com.pph.demo.utils;

/**
 * @Author: PPH
 * @Date: 2019-07-02 15:42
 * @Description:
 */
public final class Constants {
    /**
     * 私有构造
     */
    private Constants() {
    }

    /**
     * 空字符串
     */
    public static final String EMPTY = "";

    /**
     * 通用空格占位符
     */
    public static final String SIGN_1 = " ";

    /**
     * 下划线符号
     */
    public static final String SIGN_2 = "_";

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
