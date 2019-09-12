package com.pph.demo.configs.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pph.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: pph
 * @Date: 2019/9/5 18:42
 * @Description:
 */
@Component
public class CommonMqListener {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonMqListener.class);

    @Autowired
    private ObjectMapper objectMapper;
    /**
     * login
     */
    @Autowired
    private UserService userService;
}
