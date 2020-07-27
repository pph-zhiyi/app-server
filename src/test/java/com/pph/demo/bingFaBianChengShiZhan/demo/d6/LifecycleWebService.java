package com.pph.demo.bingFaBianChengShiZhan.demo.d6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: pph
 * @date 2020/1/19 09:22
 * @description:
 */
public class LifecycleWebService {

    private final ExecutorService EXECUTOR_SERVICE;

    private final int SOCKET_PORT;

    public LifecycleWebService(int num, int port) {
        this.EXECUTOR_SERVICE = Executors.newFixedThreadPool(num);
        this.SOCKET_PORT = port;
    }

    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(SOCKET_PORT);

        while (!EXECUTOR_SERVICE.isShutdown()) {
            try {
                final Socket conn = socket.accept();
                System.out.println(conn.getChannel());
                EXECUTOR_SERVICE.execute(() -> handleRequest(conn));
                conn.close();
            } catch (IOException e) {
                if (!EXECUTOR_SERVICE.isShutdown()) {
                    System.out.println("task submission rejected" + e.getMessage());
                }
            }
        }
    }

    public void stop() {
        EXECUTOR_SERVICE.shutdown();
    }

    private void handleRequest(Socket conn) {
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(conn.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        LifecycleWebService service = new LifecycleWebService(100, 8080);
        service.start();
    }
}
