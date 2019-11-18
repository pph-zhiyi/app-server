package com.pph.demo.configs.db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: pph
 * @Date: 2019/11/18 15:11
 * @Description:
 */
@Configuration
@MapperScan(basePackages = "mapper.pph")
public class PphDbConfig {
}
