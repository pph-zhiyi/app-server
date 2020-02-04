package com.pph.demo.bingFaBianChengShiZhan.demo.d7;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author: pph
 * @date: 2020/1/23 14:12
 * @description:
 */
public class ReaderThread extends Thread {

    private final Socket socket;

    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException ignored) {
        } finally {
            super.interrupt();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                byte[] buf = new byte[100];
                int count = in.read(buf);
                if (count > 0) {
//                    执行其他操作
                    System.out.println("action");
                } else {
                    break;
                }
            }
        } catch (IOException e) {
//            允许线程退出
        }
    }
}
