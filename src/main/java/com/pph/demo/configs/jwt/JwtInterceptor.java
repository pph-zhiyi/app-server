package com.pph.demo.configs.jwt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pph.demo.annotation.SkipToken;
import com.pph.demo.service.UserService;
import com.pph.demo.utils.jwt.JwtUtil;
import com.pph.demo.utils.jwt.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @Author: pph
 * @date: 2019/9/19 19:23
 * @Description:
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

//        检查是否有 [ @SkipToken && required = true ]，则跳过认证
        if (isSkipToken((HandlerMethod) handler)) {
            return true;
        }

        final String[] keys = new String[]{"token"};
        checkUser(request.getHeader(keys[0]));

        return true;
    }

    /**
     * 检查是否有 [ @SkipToken && required = true ]，则跳过认证
     *
     * @param handler
     * @return
     */
    private boolean isSkipToken(HandlerMethod handler) {
        Method method = handler.getMethod();
        return method.isAnnotationPresent(SkipToken.class)
                && method.getAnnotation(SkipToken.class)
                .required();
    }

    /**
     * 获取请求中 body 的参数
     *
     * @param request
     * @return
     * @throws IOException
     */
    private JSONObject getBody(HttpServletRequest request) throws IOException {
        BufferedReader br = request.getReader();
        String p;
        StringBuilder body = new StringBuilder();
        while ((p = br.readLine()) != null) {
            body.append(p);
        }
        return JSON.parseObject(String.valueOf(body));
    }

    /**
     * 检查 user 与 token
     *
     * @param token
     * @throws UnauthorizedException
     */
    private void checkUser(String token) throws UnauthorizedException {
        if (userService.queryUserByTerms(new HashMap<String, Object>() {
            {
                put("user", JwtUtil.decode(token));
            }
        }).isEmpty()) {
            throw new UnauthorizedException("token error!");
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
