package com.pph.demo.bingFaBianChengShiZhan.demo.d4;

import com.pph.demo.utils.JSONUtils;
import com.pph.demo.utils.yml.YmlConfig;
import org.apache.tomcat.jni.Error;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author: pph
 * @date: 2020/1/10 09:35
 * @description:
 */
public class Preloader {

    private final FutureTask<Map<String, String>> future = new FutureTask<>(() -> YmlConfig.ALL_CONFIG_STRING);

    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public Map<String, String> get() throws InterruptedException, Error {
        TimeUnit.SECONDS.sleep(3);
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof IllegalArgumentException) {
                throw (IllegalArgumentException) cause;
            } else {
                throw launderThrowable(cause);
            }
        }
    }

    private RuntimeException launderThrowable(Throwable t) throws Error {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new IllegalArgumentException("Not unchecked", t);
        }
    }

    public static void main(String[] args) throws Exception {
        Preloader p = new Preloader();
        p.start();
        System.out.println("-------------");
        System.out.println(JSONUtils.toJSONString(p.get()));
    }
}
