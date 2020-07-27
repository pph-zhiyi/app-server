package com.pph.demo.configs.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pph.demo.mapper.LoginLogInfoMapper;
import com.pph.demo.model.LoginLogInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: pph
 * @date: 2019/9/5 18:42
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

    @Autowired
    private LoginLogInfoMapper loginLogInfoMapper;

    /**
     * 监听 mq message 插入 db
     *
     * @param message
     */
    @RabbitListener(queues = "${log.user.queue.name}", containerFactory = "singleListenerContainer")
    public void consumeUserLogQueue(@Payload byte[] message) {
        try {
            LoginLogInfo loginLogInfo = objectMapper.readValue(message, LoginLogInfo.class);
            LOGGER.info("监听消费用户登录日志 info: {}", loginLogInfo);

            LOGGER.info("^^^consumeUserLogQueue save: {}", loginLogInfoMapper.save(loginLogInfo));
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }
}
