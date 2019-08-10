package com.pph.demo.utils.common;

import org.springframework.http.HttpStatus;

/**
 * @Author: PPH
 * @Date: 2019-08-06 12:41
 * @Description:
 */
public final class ApiResult {
    private ApiResult() {
    }

    /**
     * success
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return success(data, "请求成功");
    }

    /**
     * success
     *
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data, String message) {
        return new Result<>(HttpStatus.OK.value(), data, message, true);
    }

    /**
     * error
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String message) {
        return new Result<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, message, false);
    }
}
