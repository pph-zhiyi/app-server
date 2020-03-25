package com.pph.demo.bingFaBianChengShiZhan.demo.d7.l1;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: pph
 * @date: 2020/3/1 11:32
 * @description:
 */
public class LogService {

    private final BlockingQueue<String> queue;

    private final LoggerThread loggerThread;

    private final PrintWriter writer;

    public LogService(PrintWriter writer) {
        this.queue = new LinkedBlockingQueue<>();
        this.loggerThread = new LoggerThread();
        this.writer = writer;
    }

    private boolean isShutdown;

    private int reservations;

    public void start() {
        loggerThread.start();
    }

    public void stop() {
        synchronized (this) {
            isShutdown = true;
        }
        loggerThread.interrupt();
    }

    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            if (isShutdown)
                throw new IllegalArgumentException("日志记录关闭。");
            reservations++;
        }
        queue.put(msg);
    }

    private class LoggerThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    try {
                        synchronized (LogService.this) {
                            if (isShutdown && reservations == 0)
                                break;
                        }
                        String msg = queue.take();
                        synchronized (LogService.this) {
                            --reservations;
                        }
                        writer.println(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                writer.close();
            }
        }
    }

    /**
     * 通过注册一个关闭勾子来停止日志服务
     */
    public void showdownGz() {
        Runtime.getRuntime()
                .addShutdownHook(new Thread(LogService.this::stop));
    }
}
