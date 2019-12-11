package com.pph.demo.java8.shizhan.d7;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

import static java.util.stream.Collectors.toList;

/**
 * @author: pph
 * @date: 2019/12/10 12:56
 * @description:
 */
public class Demo {

    static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"));

    static final Executor EXECUTOR =
//            创建一个线程池，线程池中线程的数目为100和商店数目二者中较小的一个值
            Executors.newFixedThreadPool(Math.min(shops.size(), 100),
                    r -> {
//                      使用守护线程——这种方式不会阻止程序的关停
                        Thread t = new Thread();
                        t.setDaemon(true);
                        return t;
                    });

    @Test
    public void d1() {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return doSomeLongComputation();
            }

            private Double doSomeLongComputation() {
                System.out.println("^^^ doSomeLongComputation");
                return 1.1D;
            }
        });

        doSomethingElse();
        try {
            future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // 当前线程在等待过程中被中断
            e.printStackTrace();
        } catch (ExecutionException e) {
            // 计算抛出一个异常
            e.printStackTrace();
        } catch (TimeoutException e) {
            // 在Future对象完成之前超过已过期
            e.printStackTrace();
        }
    }

    private void doSomethingElse() {
        System.out.println("*** doSomethingElse");
    }

    @Test
    public void d2() {
        Shop shop = new Shop("BestPrice");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        // 执行更多任务，比如查询其他商店
        doSomethingElse();

        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    public List<String> findPrices(String product) {
//        return shops.stream()
        return shops.parallelStream()
                .map(s -> String.format("%s price is %.2f", s.getName(), s.getPrice(product)))
                .collect(toList());
    }

    public List<String> findPricesAsync(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(s -> CompletableFuture.supplyAsync(() -> s.getName() + " price is " +
                        s.getPrice(product), EXECUTOR))
                .collect(toList());
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

    @Test
    public void t3() {
        long start = System.nanoTime();
        System.out.println(findPrices("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    @Test
    public void t4() {
        long start = System.nanoTime();
        System.out.println(findPricesAsync("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }
}
