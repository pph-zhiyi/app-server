package com.pph.demo.java8.shizhan.d2;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * @author: pph
 * @date: 2019/12/5 18:50
 * @description:
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    @Override
    public Supplier<List<T>> supplier() {
        /*
         * 建立新的结果容器: supplier 方法
         * supplier方法必须返回一个结果为空的Supplier，也就是一个无参数函数，
         * 在调用时它会创建一个空的累加器实例，供数据收集过程使用。很明显，对于将累加器本身作为结果返回的收集器，
         * 比如我们的ToListCollector，在对空流执行操作的时候，这个空的累加器也代表了收集过程的结果。
         */
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        /*
         * 将元素添加到结果容器: accumulator 方法
         * accumulator方法会返回执行归约操作的函数。当遍历到流中第n个元素时，
         * 这个函数执行时会有两个参数:保存归约结果的累加器(已收集了流中的前 n1 个项目)，
         * 还有第n个元素本身。 5 该函数将返回void，因为累加器是原位更新，即函数的执行改变了它的内部状态以体现遍历的元素的效果。
         * 对于ToListCollector，这个函数仅仅会把当前项目添加至已经遍历过的项目的 列表:
         */
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        /*
         * 合并两个结果容器: combiner 方法
         * 四个方法中的最后一个——combiner方法会返回一个供归约操作使用的函数，它定义了对 流的各个子部分进行并行处理时，
         * 各个子部分归约所得的累加器要如何合并。对于toList而言， 这个方法的实现非常简单，
         * 只要把从流的第二个部分收集到的项目列表加到遍历第一部分时得到 的列表后面就行了:
         */
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        /*
         * 对结果容器应用最终转换: finisher 方法
         * 在遍历完流后，finisher方法必须返回在累积过程的最后要调用的一个函数，以便将累加 器对象转换为整个集合操作的最终结果。
         * 通常，就像ToListCollector的情况一样，累加器对 象恰好符合预期的最终结果，因此无需进行转换。
         * 所以finisher方法只需返回identity函数
         */
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        /*
         * characteristics会返回一个不可变的Characteristics集合，它定义 12 了收集器的行为——尤其是关于流是否可以并行归约，
         * 以及可以使用哪些优化的提示。 Characteristics是一个包含三个项目的枚举。
         *
         * UNORDERED —— 归约结果不受流中项目的遍历和累积顺序的影响。
         * CONCURRENT —— accumulator 函数可以从多个线程同时调用，且该收集器可以并行归约流。如果收集器没有标为 UNORDERED，
         *               那它仅在用于无序数据源时才可以并行归约。
         * IDENTITY_FINISH —— 这表明完成器方法返回的函数是一个恒等函数，可以跳过。这种情况下，累加器对象将会直接用作归约过程的最终结果。
         *                    这也意味着，将累加器 A 不加检查地转换为结果 R 是安全的。
         */
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
    }
}
