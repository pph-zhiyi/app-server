package com.pph.demo.configs.jwt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pph.demo.annotation.SkipToken;
import com.pph.demo.model.User;
import com.pph.demo.service.UserService;
import com.pph.demo.utils.jwt.JwtUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.lang.reflect.Method;

/**
 * @Author: pph
 * @Date: 2019/9/19 19:23
 * @Description:
 */
public class Interceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        BufferedReader br = request.getReader();
        String str;
        StringBuilder body = new StringBuilder();
        while ((str = br.readLine()) != null) {
            body.append(str);
        }
        JSONObject params = JSON.parseObject(body.toString());

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(SkipToken.class)) {
            SkipToken passToken = method.getAnnotation(SkipToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        User userInfo = userService.queryUserByUserPwd(params.getString("user"), params.getString("password"));
        if (userInfo != null && StringUtils.equals(JwtUtil.decode(token), userInfo.getUser())) {
            return true;
        } else {
            throw new AuthenticationException("暂无权限");
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
    }
}
