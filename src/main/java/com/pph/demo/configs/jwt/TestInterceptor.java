package com.pph.demo.configs.jwt;

import com.pph.demo.annotation.SkipToken;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author: pph
 * @datetime: 2020/3/26 21:14
 * @description:
 */
public class TestInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        SkipToken annotation = method.getAnnotation(SkipToken.class);
        boolean required = annotation.required();
        return super.preHandle(request, response, handler);
    }
}
