package com.pph.demo.effective.other.entity.chooser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: PPH
 * @Date: 2019-06-21 17:42
 * @Description:
 */
public class Chooser3<T> {

    private final List<T> choiceList;

    public Chooser3(Collection<T> choices) {
        this.choiceList = new ArrayList<>(choices);
    }

    public T choose() {
        Random random = ThreadLocalRandom.current();
        return this.choiceList.get(random.nextInt(this.choiceList.size()));
    }
}
