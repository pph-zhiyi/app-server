package com.pph.demo.configs.base;

import com.pph.demo.utils.common.ApiResult;
import com.pph.demo.utils.common.Result;
import com.pph.demo.utils.jwt.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * @param request  入
     * @param response 出
     * @param e        异常
     * @return 结果
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    public Result<Object> loginException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        LOGGER.error("^^^ loginException params: {}, error: {}", request.getParameterMap(), e);

//        解决跨域访问报错
        response.setHeader("Access-Control-Allow-Origin", "*");

        return ApiResult.error(401, null, e.getMessage());
    }

    /**
     * 处理所有 Exception
     *
     * @param request  入
     * @param response 出
     * @param e        异常
     * @return 结果
     */
    @ExceptionHandler(value = Exception.class)
    public Result<Object> globalException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        LOGGER.error("^^^ globalException params: {}, error: {}", request.getParameterMap(), e);

//        解决跨域访问报错
        response.setHeader("Access-Control-Allow-Origin", "*");

        int code = 500;
        if (e instanceof UnauthorizedException
                || e instanceof com.auth0.jwt.exceptions.TokenExpiredException) {
            code = 401;
        }
        return ApiResult.error(code, e);
    }
}
