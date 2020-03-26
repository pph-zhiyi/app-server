package com.pph.demo.configs.thread;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author: pph
 * @date: 2020/3/26 19:41
 * @description: 默认线程池配置
 */
@Data
@Component
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "task.pool.config")
public class TaskPoolConfig {
    /**
     * 核心线程数
     */
    private Integer corePoolSize;
    /**
     * 最大线程数
     */
    private Integer maxPoolSize;
    /**
     * 活跃时间
     */
    private Integer keepAliveSeconds;
    /**
     * 队列大小
     */
    private Integer queueCapacity;
}
