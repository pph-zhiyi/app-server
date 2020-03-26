package com.pph.demo.async.impl;

import com.pph.demo.async.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: pph
 * @date: 2020/3/26 20:32
 * @description:
 */
@Service
public class PphAsyncServiceImpl implements AsyncService {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PphAsyncServiceImpl.class);

    @Override
    @Async("pphTaskAsyncPool")
    public void executeAsync(Object... objects) {
        LOGGER.info("*** executeAsync: {}", objects);
        try {
            System.out.println(objects[0]);
            TimeUnit.SECONDS.sleep(new Random().nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(objects[0] + "结束了");
    }
}
