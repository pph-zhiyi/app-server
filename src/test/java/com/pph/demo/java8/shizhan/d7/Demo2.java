package com.pph.demo.java8.shizhan.d7;

import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author: pph
 * @date 2019/12/11 09:18
 * @description:
 */
public class Demo2 {

    private static List<Shop> shops = Demo.shops;
    private static Executor EXECUTOR = Executors.newFixedThreadPool(Math.min(shops.size(), 100));

    private static final Random RANDOM = new Random();

    public static void randomDelay() {
        int delay = 500 + RANDOM.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> findPrices(String product) {
//        return shops.stream()
//                .map(s -> s.getPriceStr(product))
//                .map(Quote::parse)
//                .map(Discount::applyDiscount)
//                .collect(toList());

        List<CompletableFuture<String>> findPrices = shops.stream()
//                以异步方式取得每个 shop 中指定产品的原始价格
                .map(s -> CompletableFuture.supplyAsync(() -> s.getPriceStr(product), EXECUTOR))
//                Quote 对象存在时，对其返回的值进行转换
                .map(future -> future.thenApply(Quote::parse))
//                使用另一个异步任务构造期望的 Future，申请折扣
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), EXECUTOR)))
                .collect(toList());

        return findPrices.stream()
//                等待流中的所有Future执行 完毕，并提取各自的返回值
//                .map(f -> {
//                    try {
//                        return f.get();
//                    } catch (Exception e) {
//                        throw new IllegalArgumentException(e.getMessage());
//                    }
//                })
                .map(CompletableFuture::join)
                .collect(toList());
    }

    @Test
    public void d1() {
        long start = System.nanoTime();
        System.out.println(findPrices("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    private static Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
                .map(s -> CompletableFuture.supplyAsync(() -> s.getPriceStr(product), EXECUTOR))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), EXECUTOR)));
    }

    @Test
    public void d2() {
        CompletableFuture[] futures = findPricesStream("myPhone")
                .map(f -> f.thenAccept(System.out::println))
                .toArray(CompletableFuture[]::new);
//        任何一个执行完成
//        Object join = CompletableFuture.anyOf(futures).join();
//        所有执行完成
        CompletableFuture.allOf(futures).join();
    }

    @Test
    public void d3() {
        long start = System.nanoTime();
        CompletableFuture[] futures = findPricesStream("myPhone27S")
                .map(f -> f.thenAccept(s -> System.out.println(s + " (done in " +
                        ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
        System.out.println("All shops have now responded in " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
    }
}
