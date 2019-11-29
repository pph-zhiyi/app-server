package com.pph.demo.java8.stream.join;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author: pph
 * @date: 2019/11/27 17:48
 * @description:
 */
public class StringCollector implements Collector<String, StringCollector.StringCombiner, String> {
    /**
     * [ String ], [ StringCollector.StringCombiner ], [ String ]
     * <p>
     * [ 待收集元素的类型 ], [ 累加器的类型 ], [ 最终结果的类型 ];
     */
    private String prefix;

    private String delimiter;

    private String suffix;

    private StringCollector(String delimiter, String prefix, String suffix) {
        this.prefix = prefix;
        this.delimiter = delimiter;
        this.suffix = suffix;
    }

    public static StringCollector init(String delimiter, String prefix, String suffix) {
        return new StringCollector(delimiter, prefix, suffix);
    }

    @Override
    public Supplier<StringCombiner> supplier() {
//        supplier 创建容器的工厂
        return () -> new StringCombiner(delimiter, prefix, suffix);
    }

    @Override
    public BiConsumer<StringCombiner, String> accumulator() {
//        accumulator 是一个函数，它将当前元素叠加到收集器
        return StringCombiner::add;
    }

    @Override
    public BinaryOperator<StringCombiner> combiner() {
//        combiner 合并两个容器
        return StringCombiner::merge;
    }

    @Override
    public Function<StringCombiner, String> finisher() {
//        finisher 方法返回收集操作的最终结果
        return StringCombiner::toString;
    }

    @Override
    public Set<Collector.Characteristics> characteristics() {
//        特征是一组描述收集器的对象，框架 可以对其适当优化。characteristics 方法定义了特征。
        return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.CONCURRENT));
    }

    @Data
    static class StringCombiner {

        private StringBuilder builder = new StringBuilder();

        private String prefix;

        private String delimiter;

        private String suffix;

        StringCombiner(String delimiter, String prefix, String suffix) {
            this.delimiter = delimiter;
            this.prefix = prefix;
            this.suffix = suffix;
        }

        public StringCombiner add(String element) {
            if (StringUtils.isBlank(builder.toString())) {
                builder.append(prefix);
            } else {
                if (!StringUtils.equals(prefix, builder.toString())) {
                    builder.delete(builder.length() - suffix.length(), builder.length());
                    builder.append(delimiter);
                } else {
                    builder.append(delimiter);
                }
            }
            builder.append(element);
            builder.append(suffix);
            return this;
        }

        StringCombiner merge(StringCombiner other) {
            builder.append(other.builder);
            return this;
        }

        @Override
        public String toString() {
            return builder.toString();
        }
    }
}
