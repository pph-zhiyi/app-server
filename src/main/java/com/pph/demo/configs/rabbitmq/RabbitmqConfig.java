package com.pph.demo.configs.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;

/**
 * @Author: pph
 * @Date: 2019/9/3 09:12
 * @Description:
 */
@Configuration
public class RabbitmqConfig {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqConfig.class);

    @Value("${log.user.queue.name}")
    private String log_user_queue_name;

    @Autowired
    private Environment environment;

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    /**
     * 单一消费者
     *
     * @return
     */
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(1);
        factory.setPrefetchCount(1);
        factory.setTxSize(1);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return factory;
    }

    /**
     * 多个消费者
     *
     * @return
     */
    @Bean(name = "multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerContainer() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(factory, connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        factory.setConcurrentConsumers(environment.getProperty("spring.rabbitmq.listener.concurrency", int.class));
        factory.setMaxConcurrentConsumers(environment.getProperty("spring.rabbitmq.listener.max-concurrency", int.class));
        factory.setPrefetchCount(environment.getProperty("spring.rabbitmq.listener.prefetch", int.class));
        return factory;
    }

    @Bean(name = "rabbitTemplate")
    public RabbitTemplate rabbitTemplate() {
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) ->
                LOGGER.info("消息发送成功: correlationData({}), ack({}), cause({})", correlationData, ack, cause));
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) ->
                LOGGER.info("消息丢失: exchange({}), route({}), replyCode({}), replyText({}), message:{}",
                        exchange, routingKey, replyCode, replyText, message));
        return rabbitTemplate;
    }

    //  --------------------------- 用户操作日志消息模型 ---------------------------
    @Bean(name = "logUserQueue")
    public Queue logUserQueue() {
        return new Queue(Objects.requireNonNull(environment.getProperty("log.user.queue.name")), true);
    }

    @Bean(name = "logUserExchange")
    public DirectExchange logUserExchange() {
        return new DirectExchange(environment.getProperty("log.user.exchange.name"), true, false);
    }

    @Bean(name = "logUserBinding")
    public Binding logUserBinding() {
        return BindingBuilder.bind(logUserQueue())
                .to(logUserExchange())
                .with(environment.getProperty("log.user.routing.name"));
    }


}
