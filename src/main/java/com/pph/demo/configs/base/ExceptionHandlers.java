package com.pph.demo.configs.base;

import com.pph.demo.utils.common.ApiResult;
import com.pph.demo.utils.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: pph
 * @Date: 2019/9/12 12:42
 * @Description:
 */
@RestControllerAdvice
public class ExceptionHandlers {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlers.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result<Object> handleException(HttpServletRequest request, Exception e) {
        LOGGER.error("^^^ exception params: {}, error: {}", request.getParameterMap(), e);
        return ApiResult.error(e);
    }
}
