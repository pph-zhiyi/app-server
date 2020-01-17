package com.pph.demo.bingFaBianChengShiZhan.demo.d3;

import lombok.AllArgsConstructor;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * @author: pph
 * @date: 2020/1/9 20:23
 * @description:
 */
@AllArgsConstructor
public class Indexer implements Runnable {

    private final BlockingQueue<File> queue;

    @Override
    public void run() {
        try {
            while (true) {
                indexFile(queue.take());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void indexFile(File take) {

    }
}
