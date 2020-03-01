package com.pph.demo.bingFaBianChengShiZhan.demo.d7;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author: pph
 * @date: 2020/2/4 16:23
 * @description:
 */
public class LoggerWriter {

    private final BlockingQueue<String> queue;

    private final LoggerThread logger;

    public LoggerWriter(Writer writer) {
        this.queue = new LinkedBlockingDeque<>(100);
        this.logger = new LoggerThread(writer);
    }

    public void start() {
        logger.start();
    }

    public void logger(String message) throws InterruptedException {
        queue.put(message);
    }

    private class LoggerThread extends Thread {

        private final PrintWriter writer;

        LoggerThread(Writer writer) {
            this.writer = (PrintWriter) writer;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    writer.println(queue.take());
                }
            } catch (InterruptedException ignored) {
            } finally {
                writer.close();
            }

        }
    }
}
