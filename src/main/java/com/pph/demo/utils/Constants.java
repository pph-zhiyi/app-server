package com.pph.demo.utils;

import com.pph.demo.utils.common.Params;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * @author PPH
 * @date 2019-07-02 15:42
 * @Description:
 */
public final class Constants {
    private Constants() {
    }

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Constants.class);

    private static final String CONFIG_FILE = "/application.yml";
    /**
     * 默认时间字符串格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 空字符串
     */
    public static final String BLANK = "";
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
     * 英文点符号
     */
    public static final String SIGN_5 = ".";
    /**
     * Excel 后缀 1
     */
    public static final String EXCEL_SUFFIX_1 = "xlsx";
    /**
     * 测试读取文件初始化
     */
    public static final String YML_TEST;

    static {
        Properties properties = loadProperties(CONFIG_FILE);
        YML_TEST = properties.getProperty("test");
    }

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

    /**
     * 读文件值
     *
     * @return
     */
    public static Properties defProperties() {
        return loadProperties(CONFIG_FILE);
    }

    /**
     * 读文件值
     *
     * @param path
     * @return
     */
    public static Properties loadProperties(String path) {
        InputStream is = Constants.class.getResourceAsStream(Params.notBlank(path, "path can not be blank!"));
        Properties properties = new Properties();

        try {
            properties.load(is);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (Objects.nonNull(is))
                    is.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        return properties;
    }
}
