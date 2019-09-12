package com.pph.demo.configs.base;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: pph
 * @Date: 2019/9/12 17:38
 * @Description:
 */
@Data
@Component
@ConfigurationProperties(prefix = "skip.uniform.result.processing.uri")
public class SkipUniformResultProcessingUri {
    /**
     * 跳过后置结果处理列表
     */
    private List<String> list;
}
