package com.pph.demo.java8.shizhan.d7;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author: pph
 * @date: 2019/12/10 19:19
 * @description:
 */
@Getter
@AllArgsConstructor
public class Shop {

    private final String name;

    private final Random random = new Random(100);

    public double getPrice(String product) {
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public String getPriceStr(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    public Future<Double> getPriceAsync(String product) {
//        java.util.concurrent.Future 表示一个异步计算(即调用线程可以继续运行，不会因为调用方法而阻塞)的结果。
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception e) {
//                否则就抛出导致失败的异常，完成这次 Future 操作
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;
    }

    public Future<Double> getPriceAsync2(String product) {
//        return CompletableFuture.supplyAsync(() -> calculatePrice(product), Executors.newSingleThreadExecutor());
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
