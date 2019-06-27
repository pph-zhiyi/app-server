package com.pph.demo.effective.other.entity.chooser;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: PPH
 * @Date: 2019-06-21 17:30
 * @Description:
 */
public class Chooser1 {

    private final Object choiceArray[];

    public Chooser1(Collection choices) {
        this.choiceArray = choices.toArray();
    }

    public Object choose() {
        Random random = ThreadLocalRandom.current();
        return this.choiceArray[random.nextInt(this.choiceArray.length)];
    }
}
