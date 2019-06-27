package com.pph.demo.effective.other.entity.chooser;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: PPH
 * @Date: 2019-06-21 17:35
 * @Description:
 */
public class Chooser2<T> {

    private final T[] choiceArray;

    public Chooser2(Collection<T> choices) {
        this.choiceArray = (T[]) choices.toArray();
    }

    public Object choose() {
        Random random = ThreadLocalRandom.current();
        return this.choiceArray[random.nextInt(this.choiceArray.length)];
    }
}
