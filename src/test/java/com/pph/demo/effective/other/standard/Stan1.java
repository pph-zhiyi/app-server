package com.pph.demo.effective.other.standard;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @Author: PPH
 * @date 2019-07-22 09:38
 * @Description: 明智审慎地返回 Optional
 */
public class Stan1 {

    public static void main(String[] args) {
        System.out.println(max3(Stream.of(0, 10, 5).collect(toList())).isPresent());
//        System.out.println(max3(null).isPresent());

        System.out.println(max2(Stream.of(0, 10, 5).collect(toList())).isPresent());
        System.out.println(max2(null).isPresent());

        System.out.println(max1(Stream.of(0, 10, 5).collect(toList())));
        System.out.println(max1(null).toString());
    }

    /**
     * MAX
     *
     * @param c
     * @param <E>
     * @return
     */
    private static <E extends Comparable<E>> E max1(Collection<E> c) {
        if (Objects.isNull(c) || c.isEmpty())
            throw new IllegalArgumentException();

        E result = null;
        for (E e : c)
            if (result == null || e.compareTo(result) > 0)
                result = Objects.requireNonNull(e);

        return result;
    }

    /**
     * MAX
     *
     * @param c
     * @param <E>
     * @return
     */
    private static <E extends Comparable<E>> Optional<E> max2(Collection<E> c) {
        if (Objects.isNull(c) || c.isEmpty())
            return Optional.empty();

        E result = null;
        for (E e : c)
            if (Objects.isNull(result) || e.compareTo(result) > 0)
                result = Objects.requireNonNull(e);

        return Optional.of(result);
    }

    /**
     * MAX
     *
     * @param c
     * @param <E>
     * @return
     */
    private static <E extends Comparable<E>> Optional<E> max3(Collection<E> c) {
        return c.stream().max(Comparator.naturalOrder());
    }
}
