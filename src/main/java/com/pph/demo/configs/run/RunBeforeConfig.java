package com.pph.demo.configs.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * @Author: pph
 * @Date: 2019/9/17 16:26
 * @Description: 项目启动前执行
 */
@Configuration
public class RunBeforeConfig implements InitializingBean, ServletContextAware {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RunBeforeConfig.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("---------------- InitializingBean 项目启动前不知道做什么 ------------------");
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        LOGGER.info("-------------- ServletContextAware 项目启动前不知道做什么 -----------------");
    }
}
