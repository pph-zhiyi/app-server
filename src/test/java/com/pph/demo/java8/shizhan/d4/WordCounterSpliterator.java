package com.pph.demo.java8.shizhan.d4;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author: pph
 * @date: 2019/12/9 08:21
 * @description:
 */
public class WordCounterSpliterator implements Spliterator<Character> {

    private final String string;

    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++));
        return currentChar < string.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        if (currentChar < 10)
            return null;
        for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
            if (Character.isWhitespace(string.charAt(splitPos))) {
                Spliterator<Character> spliterator =
                        new WordCounterSpliterator(string.substring(currentChar, splitPos));
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        /*
         * ORDERED(顺序就是 String 中各个 Character 的次序)、
         * SIZED(estimatedSize 方法的返回值是精确的)、
         * SUBSIZED(trySplit 方法创建的其他 Spliterator 也有确切大小)、
         * NONNULL(String 中不能有为 null 的 Character)、
         * IMMUTABLE(在解析 String 时不能再添加 Character，因为 String 本身是一个不可变类)的
         */
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
