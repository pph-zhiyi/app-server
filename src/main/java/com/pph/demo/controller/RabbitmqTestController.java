package com.pph.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pph.demo.model.User;
import com.pph.demo.service.UserService;
import com.pph.demo.utils.Constants;
import com.pph.demo.utils.common.Params;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: pph
 * @Date: 2019/9/4 19:17
 * @Description:
 */
@RestController
@RequestMapping("/rabbitmq/test")
public class RabbitmqTestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Environment environment;
    /**
     * login
     */
    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestParam(value = "name") String name) {
        String response = "失败";
        Map<String, Object> filter = new HashMap<String, Object>(2) {
            {
                put(Constants.Page.IS_PAGE.val(), false);
                put("name", name);
            }
        };
        List<User> users = userService.queryUserByTerms(filter);

        //TODO：执行登录逻辑
        if (!users.isEmpty()) {
            //TODO：异步写用户日志
            try {
                User user = users.get(0);
                objectMapper.writeValueAsString(user);
                Map<String, Object> userLog = new HashMap<String, Object>(8) {
                    {
                        put("userName", user.getName());
                        put("Login", "Login");
                        put("login", "login");
                        put("om", objectMapper.writeValueAsString(user));
                        put("createTime", Params.dateToStr(new Date()));
                    }
                };
                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                rabbitTemplate.setExchange(environment.getProperty("log.user.exchange.name"));
                rabbitTemplate.setRoutingKey(environment.getProperty("log.user.routing.name"));

                Message message = MessageBuilder.withBody(objectMapper.writeValueAsBytes(userLog))
                        .setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
                message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,
                        MessageProperties.CONTENT_TYPE_JSON);
                rabbitTemplate.convertAndSend(message);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            response = "成功";
            //TODO：塞权限数据-资源数据-视野数据
        }
        return response;
    }
}
