package com.pph.demo.utils.common;

import lombok.Data;

/**
 * @Author: PPH
 * @Date: 2019-08-06 11:01
 * @Description:
 */
@Data
public class Result<T> {
    /**
     * 结果码
     */
    private Integer code;
    /**
     * API 返回结果
     */
    private T data;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 是否成功
     */
    private Boolean success;

    public Result() {
    }

    public Result(Integer code, T data, String message, Boolean success) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.success = success;
    }
}
