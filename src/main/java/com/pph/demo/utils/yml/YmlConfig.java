package com.pph.demo.utils.yml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.*;

/**
 * @author: pph
 * @date: 2019/12/31 09:31
 * @description:
 */
public class YmlConfig {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(YmlConfig.class);
    /**
     * 存储读取 MAP 的所有 String 值
     */
    private static Map<String, String> STRING_ALL_MAP = new HashMap<>(32);
    /**
     * 存储读取 MAP 的所有 List 值
     */
    private static Map<String, List<String>> LIST_ALL_MAP = new HashMap<>(16);
    /**
     * 对外暴露不可不变 STRING_MAP
     */
    public static final Map<String, String> ALL_CONFIG_STRING = Collections.synchronizedMap(STRING_ALL_MAP);
    /**
     * 对外暴露不可不变 LIST_MAP
     */
    public static final Map<String, List<String>> ALL_CONFIG_LIST = Collections.synchronizedMap(LIST_ALL_MAP);

    static {
        init("/application.yml");
        init("/application-skip-result-processing-uri.yml");
    }

    /**
     * 初始化
     *
     * @param path 路径
     */
    @SuppressWarnings("unchecked")
    private static void init(String path) {
        Yaml yaml = new Yaml();
        InputStream inputStream = YmlConfig.class.getResourceAsStream(path);
        for (Object o : yaml.loadAll(inputStream)) {
            iteratorYml((Map<String, Object>) o, null);
        }
    }

    /**
     * 迭代 YML
     *
     * @param map MAP
     * @param key KEY
     */
    @SuppressWarnings("unchecked")
    private static void iteratorYml(Map<String, Object> map, String key) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key2 = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                if (Objects.isNull(key)) {
                    iteratorYml((Map<String, Object>) value, key2);
                } else {
                    iteratorYml((Map<String, Object>) value, String.format("%s.%s", key, key2));
                }
            }
            if (value instanceof String || value instanceof Number) {
                if (Objects.isNull(key)) {
                    STRING_ALL_MAP.put(key2, String.valueOf(value));
                }
                if (Objects.nonNull(key)) {
                    STRING_ALL_MAP.put(String.format("%s.%s", key, key2), String.valueOf(value));
                }
            }
            if (value instanceof List) {
                if (Objects.isNull(key)) {
                    LIST_ALL_MAP.put(key2, (List<String>) value);
                }
                if (Objects.nonNull(key)) {
                    LIST_ALL_MAP.put(String.format("%s.%s", key, key2), (List<String>) value);
                }
            }
        }
    }

    public static void main(String[] args) {
        Map<String, String> stringAllMap = STRING_ALL_MAP;
        Map<String, List<String>> listAllMap = LIST_ALL_MAP;
        System.out.println(listAllMap);
        String url = stringAllMap.get("spring.datasource.url");
        System.out.println(url);
    }
}
