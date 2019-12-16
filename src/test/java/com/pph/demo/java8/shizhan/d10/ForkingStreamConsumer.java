package com.pph.demo.java8.shizhan.d10;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.function.Consumer;

/**
 * @author: pph
 * @date: 2019/12/16 08:27
 * @description:
 */
@AllArgsConstructor
public class ForkingStreamConsumer<T> implements Consumer<T>, Results {

    static final Object END_OF_STREAM = new Object();

    private final List<BlockingQueue<T>> queues;

    private final Map<Object, Future<?>> actions;

    @Override
    public <R> R get(Object key) {
        try {
//            等待 Future 完成相关的计算，返回由特定键标识的处理结果
            return ((Future<R>) actions.get(key)).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void accept(T t) {
//        将流中遍历的元素添加到所有的队列中
        queues.forEach(q -> q.add(t));
    }

    @Override
    public Consumer<T> andThen(Consumer<? super T> after) {
        return null;
    }

    void finish() {
//        将最后一个元素添加到队列中，表明该流已经结束
        accept((T) END_OF_STREAM);
    }
}
