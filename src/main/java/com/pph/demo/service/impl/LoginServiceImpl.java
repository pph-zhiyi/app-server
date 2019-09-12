package com.pph.demo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pph.demo.configs.redis.RedisService;
import com.pph.demo.mapper.LoginMapper;
import com.pph.demo.model.Login;
import com.pph.demo.model.User;
import com.pph.demo.service.LoginService;
import com.pph.demo.utils.RedisKeyUtil;
import com.pph.demo.utils.common.Params;
import com.pph.demo.vo.request.login.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: PPH
 * @Date: 2019-05-26 19:04
 * @Description:
 */
@Service
public class LoginServiceImpl implements LoginService {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisKeyUtil redisKeyUtil;

    @Autowired
    private ValueOperations<String, Object> valueOperations;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Boolean isExists(String user, String password) {
        LOGGER.info("isExists user: {}, password: {}", user, password);
        Login login = loginMapper.queryLoginByUserAndPwd(user, password);

        if (Objects.nonNull(login)) {
//            try {
//                objectMapper.writeValueAsString(login);
//                Map<String, Object> userLog = new HashMap<String, Object>(8) {
//                    {
//                        put("userName", user.getName());
//                        put("Login", "Login");
//                        put("login", "login");
//                        put("om", objectMapper.writeValueAsString(user));
//                        put("createTime", Params.dateToStr(new Date()));
//                    }
//                };
//                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
//                rabbitTemplate.setExchange(environment.getProperty("log.user.exchange.name"));
//                rabbitTemplate.setRoutingKey(environment.getProperty("log.user.routing.name"));
//
//                Message message = MessageBuilder.withBody(objectMapper.writeValueAsBytes(userLog))
//                        .setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
//                message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,
//                        MessageProperties.CONTENT_TYPE_JSON);
//                rabbitTemplate.convertAndSend(message);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
            return true;
        }

        return false;
    }

    @Override
    public Integer register(LoginVo r) {
        LOGGER.info("isExists params: {}", r);
        return loginMapper.createLogin(r);
    }

    @Override
    public List<String> queryUserNameAll() {
        return null;
    }

    @Override
    public String getRedisString(String key) {
        LOGGER.info("getRedisString key: {}", key);
        Object o = valueOperations.get(key);
        return String.valueOf(o);
    }

    @Override
    public void setRedisString(String key, String val) {
        LOGGER.info("setRedisString key: {}, val: {}", key, val);
        /*
         * 将字符串插入到 redis
         */
        valueOperations.set(key, val);
        /*
         * 设置过期时间 30 分钟
         */
        redisService.expireKey(key, 30, TimeUnit.MINUTES);
    }
}
