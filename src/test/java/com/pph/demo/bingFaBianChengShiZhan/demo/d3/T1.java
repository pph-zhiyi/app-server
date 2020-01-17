package com.pph.demo.bingFaBianChengShiZhan.demo.d3;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author: pph
 * @date: 2020/1/9 20:31
 * @description:
 */
public class T1 {

    private static final int BOUND = 10;

    private static final int N_CONSUMERS = 10;

    public static void main(String[] args) {
        File[] roots = new File[]{
                new File("/Users/pph/Desktop/all"),
                new File("/Users/pph/Desktop/all"),
                new File("/Users/pph/Desktop/all")
        };
        startIndexer(roots);
    }

    private static void startIndexer(File[] roots) {
        BlockingQueue<File> queue = new LinkedBlockingDeque<>(BOUND);

        FileFilter filter = pathname -> true;

        for (File root : roots) {
            new Thread(new FileCrawler(queue, filter, root)).start();
        }
        for (int i = 0; i < N_CONSUMERS; i++) {
            new Thread(new Indexer(queue)).start();
        }
    }
}
