package com.pph.demo.configs.base;

import com.pph.demo.utils.common.ApiResult;
import com.pph.demo.utils.common.Result;
import com.pph.demo.utils.jwt.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    /**
     * 处理登录 Exception
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    public Result<Object> loginException(HttpServletRequest request, Exception e) {
        LOGGER.error("^^^ loginException params: {}, error: {}", request.getParameterMap(), e);
        return ApiResult.error(401, null, e.getMessage());
    }

    /**
     * 处理所有 Exception
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result<Object> globalException(HttpServletRequest request, Exception e) {
        LOGGER.error("^^^ globalException params: {}, error: {}", request.getParameterMap(), e);
        int code = 500;
        if (e instanceof UnauthorizedException || e instanceof com.auth0.jwt.exceptions.TokenExpiredException) {
            code = 401;
        }
        return ApiResult.error(code, e);
    }
}
