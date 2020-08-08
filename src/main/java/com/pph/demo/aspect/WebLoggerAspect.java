package com.pph.demo.aspect;

import com.alibaba.fastjson.JSON;
import com.pph.demo.utils.common.Params;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author PPH
 * @date 2019-08-06 10:48
 * @Description:
 */
@Aspect
@Component
public class WebLoggerAspect {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebLoggerAspect.class);

    /**
     * 切点（controller 每个方法）
     */
    @Pointcut("execution(public * com.pph.demo.controller.*.*(..))")
    private void cut() {
    }

    /**
     * controller 每个方法请求日志记录
     *
     * @param joinPoint
     */
    @Before("cut()")
    public void deBefore(JoinPoint joinPoint) {
        RequestAttributes requestAttributes = Params.notNull(RequestContextHolder.getRequestAttributes(),
                "requestAttributes can not be null!");
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        /*
         * 记录请求内容
         */
        try {
            LOGGER.info("\n" +
                            "^^^^^ \n" +
                            "\t DATE: {} \n" +
                            "\t URL: {} \n" +
                            "\t HTTP_METHOD: {} \n" +
                            "\t IP: {} \n" +
                            "\t REQUEST_PARAMS: {} \n" +
                            "^^^^^ \n",
                    Params.dateToStr(System.currentTimeMillis()),
                    request.getRequestURL(),
                    request.getMethod(),
                    request.getRemoteAddr(),
                    JSON.toJSONString(joinPoint.getArgs()));
        } catch (Throwable ignored) {
        }
    }
}
