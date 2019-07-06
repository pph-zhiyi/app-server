package com.pph.demo.effective.other.enums;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: PPH
 * @Date: 2019-07-05 09:35
 * @Description: 使用 EnumSet 替代位属性
 */
public class Text {

    public enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}

    // Any Set could be passed in, but EnumSet is clearly best
    public void applyStyles(Set<Style> styles) {
        styles.forEach(System.out::println);
    }

    public static void main(String[] args) {
        Text text = new Text();
        text.applyStyles(Stream.of(Style.BOLD, Style.UNDERLINE, Style.STRIKETHROUGH).collect(Collectors.toSet()));
    }
}
