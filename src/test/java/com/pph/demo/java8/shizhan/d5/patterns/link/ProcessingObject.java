package com.pph.demo.java8.shizhan.d5.patterns.link;

import lombok.Setter;

import java.util.Objects;

/**
 * @author: pph
 * @date 2019/12/9 18:55
 * @description: 责任链模式
 */
@Setter
public abstract class ProcessingObject<T> {

    protected ProcessingObject<T> successor;

    public T handle(T input) {
        T r = handleWork(input);
        if (Objects.nonNull(successor)) {
            return successor.handle(r);
        }
        return r;
    }

    protected abstract T handleWork(T input);
}
