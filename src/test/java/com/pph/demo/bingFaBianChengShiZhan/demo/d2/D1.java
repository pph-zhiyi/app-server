package com.pph.demo.bingFaBianChengShiZhan.demo.d2;

import com.pph.demo.utils.yml.YmlConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author: pph
 * @date 2019/12/31 09:13
 * @description
 */
public class D1 {

    private static String URL = YmlConfig.ALL_CONFIG_STRING.get("spring.datasource.url");
    private static String USERNAME = YmlConfig.ALL_CONFIG_STRING.get("spring.datasource.username");
    private static String PASSWORD = YmlConfig.ALL_CONFIG_STRING.get("spring.datasource.password");

    private static ThreadLocal<Connection> connectionHolder = ThreadLocal.withInitial(() -> {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    });

    public static Connection getConnection() {
        return connectionHolder.get();
    }

    public static void main(String[] args) {
        Connection connection = getConnection();
    }
}
