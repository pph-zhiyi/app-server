package com.pph.demo.effective.other.entity;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author PPH
 * @date 2019-07-02 14:43
 * @description
 */
public class Favorites {

    private Map<Class<?>, Object> favorites = new HashMap<>();

    public <T> void putFavorites(Class<T> type, T instance) {
//        favorites.put(type, instance);
        favorites.put(type, type.cast(instance));
    }

    public <T> T getFavorites(Class<T> type) {
        return type.cast(favorites.get(type));
    }

    //    public static <T extends Annotation> T getAnnotation(Class<T> annotationType) {
    public Annotation getAnnotation(AnnotatedElement element, String annotationTypeName) {
        Class<?> annotationType;
        try {
            annotationType = Class.forName(annotationTypeName);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        return element.getAnnotation(annotationType.asSubclass(Annotation.class));
    }

    public static void main(String[] args) {
        Favorites f = new Favorites();
        f.putFavorites(String.class, "锤子🔨");
        f.putFavorites(List.class, Stream.of("Demo", "b", "c").collect(Collectors.toList()));
        String string = f.getFavorites(String.class);
        List<String> list = f.getFavorites(List.class);
        System.out.println(string);
        System.out.println(list.toString());

//        Annotation annotation = f.getAnnotation(List.class, "String.class");
//        System.out.println(annotation);
    }
}
