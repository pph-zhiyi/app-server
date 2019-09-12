package com.pph.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: PPH
 * @Date: 2019-05-20 21:01
 * @Description:
 */

@SpringBootApplication
@MapperScan(value = "com.pph.demo.mapper.*")
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }
}
