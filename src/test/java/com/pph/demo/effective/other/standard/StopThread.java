package com.pph.demo.effective.other.standard;

import java.util.concurrent.TimeUnit;

/**
 * @author PPH
 * @date 2019-07-23 18:29
 * @Description:
 */
public class StopThread {

    private static Boolean stopRequested;

//    public static void main(String[] args) throws InterruptedException {
//
//        Thread thread = new Thread(() -> {
//            int i = 0;
//            while (!stopRequested)
//                i++;
//        });
//
//        thread.start();
//        TimeUnit.SECONDS.sleep(1);
//        stopRequested = true;
//    }

    private static synchronized void requestStop() {
        stopRequested = true;
    }

    private static synchronized Boolean stopRequested() {
        return stopRequested;
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stopRequested())
                i++;
        });

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        requestStop();
    }
}
