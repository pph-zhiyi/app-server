package com.pph.demo.java8.shizhan.d10;

import lombok.AllArgsConstructor;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author pph
 * @date 2019/12/16 08:21
 * @description
 */
@AllArgsConstructor
public class StreamForker<T> {

    private final Stream<T> stream;

    private final Map<Object, Function<Stream<T>, ?>> forks = new HashMap<>(16);

    public StreamForker<T> fork(Object key, Function<Stream<T>, ?> f) {
//        使用一个键对流上的函数进行索引
        forks.put(key, f);

//        返回 this 从而保证多次流畅地调用 fork 方法
        return this;
    }

    private ForkingStreamConsumer<T> build() {
//        创建由队列组成的列表，每一个队列对应一个操作
        List<BlockingQueue<T>> queues = new ArrayList<>();

//        建立用于标识操作的键与包含操作结果的 Future 之间的映射关系
        Map<Object, Future<?>> actions = forks.entrySet().stream()
                .reduce(new HashMap<Object, Future<?>>(),
                        (map, e) -> {
                            map.put(e.getKey(), getOperationResult(queues, e.getValue()));
                            return map;
                        },
                        (m1, m2) -> {
                            m1.putAll(m2);
                            return m1;
                        });

        return new ForkingStreamConsumer<>(queues, actions);
    }

    private Future<?> getOperationResult(List<BlockingQueue<T>> queues, Function<Stream<T>, ?> f) {
//        创建一个队列，并将其添加到队列的列表中
        BlockingQueue<T> queue = new LinkedBlockingQueue<>();
        queues.add(queue);

//        创建一个 Spliterator，遍历队列中的元素
        Spliterator<T> spliterator = new BlockingQueueSpliterator<>(queue);

//        创建一个流，将 Spliterator 作为数据源
        Stream<T> source = StreamSupport.stream(spliterator, false);

//        创建一个Future对象，以异步方式计算在流上执行特定函数的结果
        return CompletableFuture.supplyAsync(() -> f.apply(source));
    }

    public Results getResults() {
        ForkingStreamConsumer<T> consumer = build();
        try {
            stream.sequential()
                    .forEach(consumer);
        } catch (Exception e) {
            consumer.finish();
        }
        return consumer;
    }
}
