package com.pph.demo.utils.common;

import com.pph.demo.utils.Constants;
import org.apache.commons.lang.StringUtils;

import java.util.Objects;

/**
 * @Author: PPH
 * @Date: 2019-07-30 15:02
 * @Description:
 */
public final class Params {
    private Params() {

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
        for (String s : split) {
            result.append(captureName(s));
        }

        return String.valueOf(result);
    }
}
