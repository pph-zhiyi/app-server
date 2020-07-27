package com.pph.demo.java8.shizhan.d10;

import lombok.AllArgsConstructor;

import java.util.Spliterator;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

/**
 * @author pph
 * @date 2019/12/16 08:46
 * @description
 */
@AllArgsConstructor
public class BlockingQueueSpliterator<T> implements Spliterator<T> {

    private final BlockingQueue<T> q;

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        T t;

        while (true) {
            try {
                t = q.take();
                break;
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        if (t != ForkingStreamConsumer.END_OF_STREAM) {
            action.accept(t);
            return true;
        }

        return false;
    }

    @Override
    public Spliterator<T> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return 0;
    }

    @Override
    public int characteristics() {
        return 0;
    }
}
