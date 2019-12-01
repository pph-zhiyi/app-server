package com.pph.demo.java8.lambda.optional;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @Author: pph
 * @Date: 2019/11/25 20:19
 * @Description:
 */
public class Demo01 {

    @Test
    public void d1() {
        Optional<String> a = Optional.of("a");
        assertEquals("a", a.get());
    }

    @Test
    public void d2() {
        Optional<String> a = Optional.of("a");
        Optional<Object> empty = Optional.empty();
        Optional<Object> alsoEmpty = Optional.ofNullable(null);

        assertFalse(empty.isPresent());
        assertFalse(alsoEmpty.isPresent());
        assertTrue(a.isPresent());

        assertEquals("b", empty.orElse("b"));
        assertEquals("c", empty.orElseGet(() -> "c"));
    }
}
