package com.pph.demo.java8.shizhan.future;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author: pph
 * @date 2019/12/11 14:58
 * @description CompletableFuture 学习
 */
public class Demo {

    private static void zero() {
        System.out.println(0 / 0);
    }

    private static Function<Integer, CompletableFuture<Integer>> getIntFunction() {
        return (Integer i) -> CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(String.format("f%d = %d", i, t));
            return t;
        });
    }

    static void runAsync() throws Exception {
        /*
         * 创建任务，无返回值
         */
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("run end ...");
        });

        System.out.println("runAsync future.get: " + future.get());
    }

    static void supplyAsync() throws Exception {
        /*
         * 创建任务，有返回值
         */
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            return System.currentTimeMillis();
        });

        System.out.println("supplyAsync future.get: " + future.get());
    }

    static void wenComplete() throws Exception {
        /*
         * wenComplete 处理正常计算的结果或异常清空
         */
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            if (new Random().nextInt() % 2 >= 0) {
                zero();
            }
            System.out.println("run end ...");
            return 1;
        });

        future.whenComplete((Integer v, Throwable e) -> System.out.println("执行完成 whenComplete: " + v));

        future.exceptionally((Throwable e) -> {
            System.out.println("执行失败！" + e.getMessage());
            return -1;
        });

        System.out.println("future.get: " + future.get());
    }

    static void thenApply() throws Exception {
        /*
         * 当一个线程依赖另一个线程时，可以使用 thenApply 来把这两个线程串行化
         */
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int result = new Random().nextInt(10);
            System.out.println("result 1 = " + result);
            return result;
        }).thenApply((Integer i) -> {
            int result = i * 2;
            System.out.println("result 2 = " + result);
            return result;
        });

        System.out.println("future.get = " + future.get());
    }

    static void handel() throws Exception {
        /*
         * 执行任务时对结果处理
         * handel 处理方式基本和 thenApply 相同
         * 不同的是 handle 是在任务完成后再执行，还可以处理异常任务，
         * thenApply 只可以处理正常任务，任务出现异常则不执行
         */
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            zero();
            return new Random().nextInt(10);
        }).handle((Integer param, Throwable t) -> {
            int result = -1;
            if (Objects.isNull(t))
                result = param * 2;
            else
                System.out.println("出错啦" + t.getMessage());
            return result;
        });

        System.out.println("future.get = " + future.get());
    }

    static void thenAccept() throws Exception {
        /*
         * 消费处理结果
         * 接受任务处理结果，并消费处理
         * 无返回结果
         */
        CompletableFuture<Void> future =
                CompletableFuture.supplyAsync(() -> new Random().nextInt(10))
                        .thenAccept((Integer param) -> System.out.println("thenAccept param: " + param));

        System.out.println("future.get = " + future.get());
    }

    static void thenRun() throws Exception {
        /*
         * thenRun 与 thenAccept 类似，不同的是上一个处理完成后，
         * 不会把计算结果传给 thenRun 方法。只是继续执行后续的 thenRun 处理逻辑
         * 无返回结果
         */
        CompletableFuture<Void> future =
                CompletableFuture.supplyAsync(() -> new Random().nextInt(10))
                        .thenRun(() -> System.out.println("thenRun..."));

        System.out.println("future.get = " + future.get());
    }

    static void thenCombine() throws Exception {
        /*
         * 合并任务
         * 把两个 CompletionStage 的任务都执行完成后的结果交给 thenCombine 处理
         */

        Supplier<CompletableFuture<String>> supplier = () -> CompletableFuture.supplyAsync(() -> "hello");

        CompletableFuture<String> future = supplier.get()
                .thenCombine(supplier.get()
                                .thenCombine(supplier.get(),
                                        (String s1, String s2) -> String.format("%s %s", s1, s2)),
                        (String s1, String s2) -> String.format("%s %s", s1, s2));

        System.out.println("future.get = " + future.get());
    }

    static void thenAcceptBoth() throws Exception {
        /*
         * 当两个 CompletionStage 的任务都执行完成后，把结果一块交给 thenAcceptBoth 消费
         * 无返回结果
         */
        Function<Integer, CompletableFuture<Integer>> function = getIntFunction();

        CompletableFuture<Void> future = function.apply(1)
                .thenAcceptBoth(function.apply(2), (Integer i1, Integer i2) ->
                        System.out.println(String.format("f1 = %d; f2 = %d, sum = %d", i1, i2, i1 + i2)));

        System.out.println("future.get = " + future.get());
    }

    static void applyToEither() throws Exception {
        /*
         * 两个 CompletionStage 谁的执行快，就用谁的结果进行下一步转化操作
         */
        Function<Integer, CompletableFuture<Integer>> function = getIntFunction();

        CompletableFuture<Integer> future = function.apply(1)
                .applyToEither(function.apply(2), (Integer i) -> {
                    System.out.println("result = " + i);
                    return i * 2;
                });

        System.out.println("future.get = " + future.get());
    }

    static void acceptEither() throws Exception {
        /*
         * 两个 CompletionStage 谁的执行快，就用谁的结果进行下一步消费操作
         * 无返回结果
         */
        Function<Integer, CompletableFuture<Integer>> function = getIntFunction();

        CompletableFuture<Void> future = function.apply(1)
                .acceptEither(function.apply(2), (Integer i) -> System.out.println("result = " + i));

        System.out.println("future.get = " + future.get());
    }

    static void runAfterEither() throws Exception {
        /*
         * 两个 CompletionStage，任何一个计算完成都会执行下一步操作（Runnable）
         * 因为是执行 Runnable 所以无返回结果
         */
        Function<Integer, CompletableFuture<Integer>> function = getIntFunction();

        CompletableFuture<Void> future = function.apply(1)
                .runAfterEither(function.apply(2), () -> System.out.println("已经有一个任务执行完成了"));

        System.out.println("future.get = " + future.get());
    }

    static void runAfterBoth() throws Exception {
        /*
         * 两个 CompletionStage，都计算完成才会执行下一步操作（Runnable）
         * 因为是执行 Runnable 所以无返回结果
         */
        Function<Integer, CompletableFuture<Integer>> function = getIntFunction();

        CompletableFuture<Void> future = function.apply(1)
                .runAfterBoth(function.apply(2), () -> System.out.println("两个任务都执行完成了"));

        System.out.println("future.get = " + future.get());
    }

    static void thenCompose() throws Exception {
        /*
         * thenCompose 允许你对两个 CompletionStage 进行流水线操作，
         * 第一个操作完成时，将其结果作为参赛传递给第二个
         */
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            System.out.println("t1 = " + t);
            return t;
        }).thenCompose((Integer param) -> CompletableFuture.supplyAsync(() -> {
            int t = param * 2;
            System.out.println("t2 = " + t);
            return t;
        }));

        System.out.println("future.get = " + future.get());
    }
}
