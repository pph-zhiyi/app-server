package com.pph.demo.java8.shizhan.d4;

import lombok.AllArgsConstructor;

/**
 * @author: pph
 * @date 2019/12/9 08:35
 * @description
 */
@AllArgsConstructor
public class WordCounter {

    private final int counter;

    private final boolean lastSpace;

    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastSpace
                    ? this
                    : new WordCounter(counter, true);
        } else {
            return lastSpace
                    ? new WordCounter(counter + 1, false)
                    : this;
        }
    }

    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }

    public int getCounter() {
        return counter;
    }
}
