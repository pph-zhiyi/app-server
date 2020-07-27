package com.pph.demo.bingFaBianChengShiZhan.demo.d6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: pph
 * @date 2020/1/17 10:58
 * @description:
 */
public class TestExecutionWebServer {

    private static final int N_THREADS = 100;

    private static final Executor exec = Executors.newFixedThreadPool(N_THREADS);

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);

        while (true) {
            final Socket connection = socket.accept();
            exec.execute(() -> handleRequest(connection));
        }
    }

    private static void handleRequest(Socket connection) {
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println(connection.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
