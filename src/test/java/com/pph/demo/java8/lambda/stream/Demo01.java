package com.pph.demo.java8.lambda.stream;

import com.pph.demo.java8.lambda.stream.join.StringCollector;
import com.pph.demo.java8.lambda.stream.join.StringCombiner;
import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;
import static org.junit.Assert.assertEquals;

/**
 * @author: pph
 * @date: 2019/11/26 09:11
 * @description:
 */
public class Demo01 {

    @Data
    private static class User {

        private String name;

        private String sex;

        private Integer age;

        User(String name, String sex) {
            this.name = name;
            this.sex = sex;
        }

        User(String name, String sex, Integer age) {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }
    }

    @Test
    public void d1() {
        /*
         * 合并流
         */
        List<Integer> collect = Stream.of(asList(1, 2), asList(3, 4))
                .flatMap(List::stream)
                .collect(toList());

        assertEquals(asList(1, 2, 3, 4), collect);
    }

    @Test
    public void d2() {
        /*
         * summaryStatistics 方法，这个方法能计算出各种各样的统计 值
         */
        IntSummaryStatistics res = Stream.of(asList("1", "6"), asList("8", "5", "0"))
                .flatMap(List::stream)
                .mapToInt(Integer::parseInt)
                .summaryStatistics();

        System.out.println("count: " + res.getCount());
        System.out.println("sum: " + res.getSum());
        System.out.println("avg: " + res.getAverage());
        System.out.println("min: " + res.getMin());
        System.out.println("max: " + res.getMax());
    }

    @Test
    public void d3() {
        /*
         * 直观上看，流是有序的，因为流中的元素都是按顺序处理的。这种顺序称为出现顺序。出 现顺序的定义依赖于数据源和对流的操作。
         */

        List<Integer> numbers = asList(1, 2, 3, 4);
        List<Integer> sameOrder = numbers.stream().collect(toList());
//        总是成功
        assertEquals(numbers, sameOrder);

        Set<Integer> numbers2 = new HashSet<>(asList(4, 3, 2, 1));
        List<Integer> sameOrder2 = numbers2.stream().collect(toList());
        // 该断言有时会失败
        assertEquals(asList(4, 3, 2, 1), sameOrder2);
    }

    @Test
    public void d4() {
        /*
         * 流的目的不仅是在集合类之间做转换，而且同时提供了一组处理数据的通用操作。有些集 合本身是无序的，但这些操作有时会产生顺序
         */

        Set<Integer> numbers2 = new HashSet<>(asList(4, 3, 2, 1));
        List<Integer> sameOrder2 = numbers2.stream()
                .sorted()
                .collect(toList());
        // 总是成功
        assertEquals(asList(1, 2, 3, 4), sameOrder2);
    }

    @Test
    public void d5() {
        /*
         * 使用 toCollection，用定制的集合收集元素
         */

        Set<Integer> treeSet = Stream.of(1, 2, 3, 4)
                .collect(toCollection(TreeSet::new));
        System.out.println(treeSet.toString());
    }

    @Test
    public void d6() {
        /*
         * maxBy 和 minBy 允许用户按某种特定的顺序生成一个 值
         */

        Optional<Integer> max = Stream.of(1, 2, 3, 4)
                .collect(maxBy(comparing(Integer::valueOf)));
        Optional<Integer> min = Stream.of(1, 2, 3, 4)
                .collect(minBy(comparing(Integer::valueOf)));
        Double avg = Stream.of("1", "2", "3", "4")
                .collect(averagingDouble(Double::parseDouble));

        System.out.println(max.get());
        System.out.println(min.get());
        System.out.println(avg);
    }

    @Test
    public void d7() {
        /*
         * 收集器 partitioningBy，它接受一个流，并将其分成两部分
         */

        Map<Boolean, List<Integer>> collect = Stream.of(1, 2, 3, 4)
                .collect(partitioningBy(v -> v % 2 != 0));

        System.out.println("单数：" + collect.get(true));
        System.out.println("双数：" + collect.get(false));
    }

    @Test
    public void d8() {
        /*
         * 数据分组是一种更自然的分割数据操作，与将数据分成 true 和 false 两部分不同，可以使用任意值对数据分组。
         */

        Map<String, List<User>> collect = Stream.of(new User("哈哈", "男"),
                new User("呵呵", "女"), new User("喔喔", "女"))
                .collect(groupingBy(User::getSex));
        System.out.println(collect.toString());
    }

    @Test
    public void d9() {
        /*
         * Collectors.joining 收集流中的值，该方法 可以方便地从一个流得到一个字符串，允许用户提供分隔符(用以分隔元素)、前缀和后缀。
         */

        String collect = Stream.of(new User("哈哈", "男"),
                new User("呵呵", "女"), new User("喔喔", "女"))
                .map(User::getName)
                .collect(joining(", ", "[ ", " ]"));

        System.out.println(collect);
    }

    @Test
    public void d10() {
        /*
         * 核心类库已经提供了一个这样的收集器: counting。收集 groupingBy 的 count
         */

        Map<String, Long> count = Stream.of(new User("哈哈", "男"),
                new User("呵呵", "女"), new User("喔喔", "女"))
                .collect(groupingBy(User::getSex, counting()));

        System.out.println(count.toString());

        /*
         * mapping 允许在收集器的容器上执行类似 map 的操作。
         * 但是需要指明使用什么样的集合类 存储结果，比如 toList。
         * 这些收集器就像乌龟叠罗汉，龟龟相驮以至无穷。
         * mapping 收集器和 map 方法一样，接受一个 Function 对象作为参数。
         */

        Map<String, List<String>> name = Stream.of(new User("哈哈", "男"),
                new User("呵呵", "女"), new User("喔喔", "女"))
                .collect(groupingBy(User::getSex, mapping(User::getName, toList())));

        System.out.println(name.toString());
    }

    @Test
    public void d11() {
        List<User> users = Stream.of(new User("哈哈", "男"), new User("呵呵", "女"),
                new User("喔喔", "女")).collect(toList());

        StringBuilder names1 = users.stream()
                .map(User::getName)
                .reduce(new StringBuilder(), (builder, name) -> {
                    if (builder.length() > 0)
                        builder.append(", ");
                    builder.append(name);
                    return builder;
                }, StringBuilder::append);
        names1.insert(0, "[ ");
        names1.append(" ]");

        String names2 = users.stream()
                .map(User::getName)
                .reduce(new StringCombiner(", ", "[ ", " ]"),
                        StringCombiner::add,
                        StringCombiner::merge)
                .toString();

        String names3 = users.stream()
                .map(User::getName)
                .collect(StringCollector.init(", ", "[ ", " ]"));

        String names4 = users.stream()
                .map(User::getName)
                .collect(joining(", ", "[ ", " ]"));

        System.out.println(names1);
        System.out.println(names2);
        System.out.println(names3);
        System.out.println(names4);
    }

    @Test
    public void d12() {
        List<User> users = Stream.of(new User("哈哈", "男"), new User("呵呵", "女"),
                new User("喔喔", "女")).collect(toList());
        Map<String, User> map = users.stream()
                .collect(toMap(User::getName, Function.identity(), (o, n) -> n));

        System.out.println(map.computeIfAbsent("哈哈", this::mapDef).toString());
        System.out.println(map.computeIfAbsent("啊啊", this::mapDef).toString());

        System.out.println(map.compute("哈哈", this::mapDef).toString());
    }

    private User mapDef(String s, User user) {
        System.out.println("暂无" + s);
        System.out.println("暂无" + user);
        return new User("暂无", "暂无");
    }

    private User mapDef(String name) {
        System.out.println("默认" + name);
        return new User("默认", "默认");
    }

    @Test
    public void d13() {
        List<User> users = Stream.of(new User("哈哈", "男", 20), new User("呵呵", "女", 22),
                new User("喔喔", "女", 18)).collect(toList());

        Map<String, List<User>> map = users.parallelStream()
                .filter(u -> u.getAge() >= 20)
                .collect(groupingBy(User::getSex));
        System.out.println(map.toString());
    }

    @Test
    public void d14() {
        /*final int N = 100;
        double fraction = 1.0 / N;

        IntStream.range(0, N)
                .parallel()
                .mapToObj(twoDiceThrows())
                .collect(groupingBy(side -> side, summingDouble(n -> fraction)));*/

        /*
         * 使用并行化数组操作初始化数组
         * 首先提供了一个用 于操作的数组，然后传入一个 Lambda 表达式，根据数组下标计算元素的值。
         * 数组下标和元素的值是一样的。使用这些方法有一点要小心: 它们改变了传入的数组，而没有创建一个新的数组。
         */
        int size = 10;
        double[] v1 = new double[size];
        for (int i = 0; i < v1.length; i++) {
            v1[i] = i;
        }
        Arrays.stream(v1).forEach(System.out::print);
        System.out.println();

        double[] v2 = new double[size];
        Arrays.parallelSetAll(v2, d -> d);
        Arrays.stream(v2).forEach(System.out::print);
    }

    @Test
    public void d15() {
        /*
         * 操作流过程中查看值，peek
         */
        List<String> collect = Stream.of("aAa", "bBb", "cCc")
                .peek(nation -> System.out.println("default: ".concat(nation)))
                .map(String::toLowerCase)
                .peek(nation -> System.out.println("toLowerCase: ".concat(nation)))
                .map(String::toUpperCase)
                .peek(nation -> System.out.println("toUpperCase: ".concat(nation)))
                .collect(toList());
        System.out.println(collect.toString());
    }
}
