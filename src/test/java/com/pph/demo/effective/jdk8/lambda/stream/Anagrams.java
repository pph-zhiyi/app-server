package com.pph.demo.effective.jdk8.lambda.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

/**
 * @Author: PPH
 * @date: 2019-07-12 09:31
 * @Description:
 */
public class Anagrams {

    public static void main(String[] args) {
        String path = "/Users/pph/Desktop/all/demo/hello.txt";
        System.out.println("---------- a1 ---------");
        a1(path, "1");
        System.out.println("---------- a2 ---------");
        a2(path, "1");
        System.out.println("---------- a3 ---------");
        a3(path, "1");
        System.out.println("---------- s1 ---------");
        "Hello world!".chars().forEach(System.out::print);
        System.out.println();
        System.out.println("---------- s2 ---------");
        "Hello world!".chars().forEach(h -> System.out.print((char) h));
    }

    private static void a3(String... args) {
        Path dictionary = Paths.get(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);
        try (Stream<String> words = Files.lines(dictionary)) {
//            words.collect(groupingBy(word -> alphabetize(word)))
            words.collect(groupingBy(Anagrams::alphabetize))
                    .values().stream()
                    .filter(group -> group.size() >= minGroupSize)
                    .forEach(g -> System.out.println(g.size() + ": " + g));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void a2(String... args) {
        Path dictionary = Paths.get(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);
        try (Stream<String> words = Files.lines(dictionary)) {
            words.collect(
                    groupingBy(word -> word.chars().sorted()
                            .collect(StringBuilder::new,
                                    (sb, c) -> sb.append((char) c),
                                    StringBuilder::append).toString())).values().stream()
                    .filter(group -> group.size() >= minGroupSize)
                    .map(group -> group.size() + ": " + group)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void a1(String... args) {
        int minGroupSize = Integer.parseInt(args[1]);
        Path dictionary = Paths.get(args[0]);
        Map<String, Set<String>> groups = new HashMap<>();
        try (Scanner s = new Scanner(dictionary)) {
            while (s.hasNext()) {
                String word = s.next();
                groups.computeIfAbsent(alphabetize(word), (unused) -> new TreeSet<>()).add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Set<String> group : groups.values())
            if (group.size() >= minGroupSize)
                System.out.println(group.size() + ": " + group);
    }

    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}
