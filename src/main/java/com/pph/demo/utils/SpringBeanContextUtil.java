package com.pph.demo.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author PPH
 * @date 2019-07-29 17:48
 * @Description:
 */
@Component
public class SpringBeanContextUtil implements ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    /**
     * getBean
     *
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {
        if (StringUtils.isEmpty(beanName))
            throw new IllegalArgumentException("BeanName can not be blank!");

        try {
            return context.getBean(Class.forName(beanName));
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Class no such: ".concat(beanName));
        }
    }

    /**
     * getBean
     *
     * @param beanName
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getBean(String beanName, Class<T> clazz) {
        if (StringUtils.isEmpty(beanName))
            throw new IllegalArgumentException("BeanName can not be blank!");

        if (Objects.isNull(clazz))
            throw new IllegalArgumentException("Class can not be null!");

        try {
            return clazz.cast(Class.forName(beanName));
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Class no such: ".concat(beanName));
        }
    }
}
