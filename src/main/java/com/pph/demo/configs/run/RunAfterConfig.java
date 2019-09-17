package com.pph.demo.configs.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: pph
 * @Date: 2019/9/17 16:26
 * @Description: 项目启动后执行
 */
@Configuration
public class RunAfterConfig implements ApplicationRunner, CommandLineRunner {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RunAfterConfig.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("---------------- ApplicationRunner 项目启动后不知道做什么 -----------------");
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("---------------- CommandLineRunner 项目启动后不知道做什么 -----------------");
    }
}
