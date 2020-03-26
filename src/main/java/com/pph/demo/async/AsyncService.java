package com.pph.demo.async;

/**
 * @author: pph
 * @date: 2020/3/26 20:16
 * @description:
 */
public interface AsyncService {
    /**
     * 执行异步任务
     *
     * @param objects 参数
     */
    void executeAsync(Object... objects);
}
