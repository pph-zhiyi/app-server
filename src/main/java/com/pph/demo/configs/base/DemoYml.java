package com.pph.demo.configs.base;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author: pph
 * @Date: 2019/9/19 16:53
 * @Description:
 */
@Data
@Component
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "demo")
public class DemoYml {

    private List<String> d1;

    private String d2;

    private Integer d3;

    private Map<String, String> d4;
}
