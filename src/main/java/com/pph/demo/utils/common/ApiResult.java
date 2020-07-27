package com.pph.demo.utils.common;

import org.springframework.http.HttpStatus;

/**
 * @author PPH
 * @date 2019-08-06 12:41
 * @description
 */
public final class ApiResult {
    private ApiResult() {
    }

    /**
     * 默认 data
     */
    private static final Object DEFAULT_DATA = null;
    /**
     * 默认成功 success
     */
    private static final Boolean DEFAULT_SUCCESS = true;
    /**
     * 默认失败 success
     */
    private static final Boolean DEFAULT_ERROR = false;
    /**
     * 默认成功 msg
     */
    private static final String DEFAULT_SUCCESS_MSG = "请求成功";
    /**
     * 默认失败 msg
     */
    private static final String DEFAULT_ERROR_MSG = "请求失败";

    /**
     * success
     *
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data, String message) {
        return new Result<>(HttpStatus.OK.value(), data, message, DEFAULT_SUCCESS);
    }

    /**
     * success
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return success(data, DEFAULT_SUCCESS_MSG);
    }

    /**
     * error
     *
     * @param code
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(Integer code, T data, String message) {
        return new Result<>(code, data, message, DEFAULT_ERROR);
    }

    /**
     * error
     *
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String message, T data) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), data, message);
    }

    /**
     * error
     *
     * @param message
     * @return
     */
    public static Result<Object> error(String message) {
        return error(message, DEFAULT_DATA);
    }

    /**
     * error
     *
     * @param e
     * @param <E>
     * @return
     */
    public static <E extends Exception> Result<Object> error(E e) {
        return error(String.format("请求异常: Error type: %s; Error message: %s", e.getClass().getName(),
                e.getMessage()), DEFAULT_DATA);
    }

    /**
     * error
     *
     * @return
     */
    public static Result<Object> error() {
        return error(DEFAULT_ERROR_MSG);
    }

    public static <E extends Exception> Result<Object> error(Integer code, E e) {
        return error(code, DEFAULT_DATA,
                String.format("请求异常: Error type: %s; Error message: %s", e.getClass().getName(), e.getMessage()));
    }
}
