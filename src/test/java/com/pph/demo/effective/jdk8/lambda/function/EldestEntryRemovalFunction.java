package com.pph.demo.effective.jdk8.lambda.function;

/**
 * 接口                                          方法                    示例
 *
 * @see java.util.function.UnaryOperator<T>     T apply(T t)            String::toLowerCase
 * @see java.util.function.BinaryOperator<T>    T apply(T t1, T t2)     BigInteger::add
 * @see java.util.function.Predicate<T>         boolean test(T t)       Collection::isEmpty
 * @see java.util.function.Function<T, R>       R apply(T t)            Arrays::asList
 * @see java.util.function.Supplier<T>          T get()                 Instant::now
 * @see java.util.function.Consumer<T>          void accept(T t)        System.out::println
 * <p>
 * <p>
 * 在处理基本类型 int，long 和 double 的操作上，六个基本接口中还有三个变体。
 * 它们的名字是通过在基本接口前加一个基本类型而得到的。
 * 因此，例如，一个接受 int 的是一个，而一个接受两个 long 值并返回一个 long 的二元运算符是一个 LongBinaryOperator。
 * 除 Function 接口变体通过返回类型进行了参数化，其他变体类型都没有参数化。
 * 例如，LongFunction<int[]> 使用 long 类型作为参数并返回了 int[] 类型
 */

import java.util.Map;

/**
 * @Author: PPH
 * @Date: 2019-07-11 12:44
 * @Description: 优先使用标准的函数式接口
 */
@FunctionalInterface
public interface EldestEntryRemovalFunction<K, V> {

    boolean remove(Map<K, V> map, Map.Entry<K, V> eldest);
}