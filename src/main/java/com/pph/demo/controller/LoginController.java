package com.pph.demo.controller;

import com.pph.demo.service.LoginService;
import com.pph.demo.vo.request.login.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

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
    @RequestMapping(value = "/exists", method = RequestMethod.GET)
    public Boolean isExists(LoginVo request) {
        logger.info("isExists request: {}", request);
        return loginService.isExists(request);
    }

    /**
     * 注册
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/exists", method = RequestMethod.POST)
    public Integer register(LoginVo request) {
        logger.info("register request: {}", request);
        return loginService.register(request);
    }
}
