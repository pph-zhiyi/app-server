package com.pph.demo.controller;

import com.pph.demo.service.LoginService;
import com.pph.demo.utils.oval.OvalVerify;
import com.pph.demo.vo.request.login.LoginVo;
import com.pph.demo.vo.request.login.RedisStringVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: PPH
 * @Date: 2019-05-26 18:55
 * @Description:
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    /**
     * login
     */
    @Autowired
    private LoginService loginService;

    /**
     * 登陆
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/exists", method = RequestMethod.POST)
    public Boolean isExists(@RequestBody LoginVo request) {
        LOGGER.info("isExists request: {}", request);
        OvalVerify.verifyObj(request);
        return loginService.isExists(request);
    }

    /**
     * 注册
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Integer register(@RequestBody LoginVo request) {
        LOGGER.info("register request: {}", request);
        OvalVerify.verifyObj(request);
        return loginService.register(request);
    }

    /**
     * 测试 redis
     *
     * @param request
     * @return
     */
    @RequestMapping("/redis/string")
    public String redisString(@RequestBody RedisStringVo request) {
        LOGGER.info("redisString request: {}", request);
        OvalVerify.verifyObj(request);
        switch (request.getType()) {
            case "get":
                return loginService.getRedisString(request.getKey());
            case "set":
                loginService.setRedisString(request.getKey(), request.getVal());
                return "OK";
            default:
                throw new IllegalArgumentException("type is must [get or set]");
        }
    }
}
