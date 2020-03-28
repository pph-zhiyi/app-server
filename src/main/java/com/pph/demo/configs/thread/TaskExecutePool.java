package com.pph.demo.configs.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: pph
 * @date: 2020/3/26 20:02
 * @description:
 */
@EnableAsync
@Configuration
public class TaskExecutePool {

    @Autowired
    private TaskPoolConfig taskPoolConfig;

    @Bean("pphTaskAsyncPool")
    public ThreadPoolTaskExecutor pphTaskAsyncPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        线程名称前缀
        executor.setThreadNamePrefix("pph_task_async-");
//        核心线程池大小
        executor.setCorePoolSize(taskPoolConfig.getCorePoolSize());
//        最大线程数
        executor.setMaxPoolSize(taskPoolConfig.getMaxPoolSize());
//        队列容量
        executor.setQueueCapacity(taskPoolConfig.getQueueCapacity());
//        活跃时间
        executor.setKeepAliveSeconds(taskPoolConfig.getKeepAliveSeconds());
//         setRejectedExecutionHandler：当 pool 已经达到 max size 的时候，如何处理新任务
//         CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        初始化
        executor.initialize();
        return executor;
    }
}
