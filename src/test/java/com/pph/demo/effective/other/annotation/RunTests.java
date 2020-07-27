package com.pph.demo.effective.other.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: PPH
 * @date 2019-07-09 09:33
 * @Description: 注解优于命名模式
 */
public class RunTests {

    public static void main(String[] args) throws Exception {
        test();
        System.out.println("-------------------------------");
        exceptionTest();
    }

    private static void test() throws ClassNotFoundException {
        int tests = 0, passed = 0;
        Class<?> clazz = Class.forName("com.pph.demo.effective.other.annotation.Sample");

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                tests++;
                try {
                    method.invoke(null);
                    passed++;
                } catch (InvocationTargetException e) {
                    Throwable t = e.getCause();
                    System.out.println(method + "failed: " + t);
                } catch (Exception e) {
                    System.out.println("Invalid @Test: " + method);
                }
            }
        }
        System.out.printf("tests: %d, Passed: %d, Failed: %d%n", tests, passed, tests - passed);
    }

    private static void exceptionTest() throws ClassNotFoundException {
        int tests = 0, passed = 0;
        Class<?> clazz = Class.forName("com.pph.demo.effective.other.annotation.Sample2");

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ExceptionTest.class)
                    || method.isAnnotationPresent(ExceptionTestContainer.class)) {
                tests++;
                try {
                    method.invoke(null);
                    System.out.printf("Test %s failed: no exception%n", method);
                    passed++;
                } catch (InvocationTargetException e) {
                    Throwable exc = e.getCause();
                    int oldPassed = passed;
                    ExceptionTest[] excTests = method.getAnnotationsByType(ExceptionTest.class);
                    for (ExceptionTest excTest : excTests) {
                        if (excTest.value().isInstance(exc)) {
                            passed++;
                            break;
                        }
                    }
                    if (passed == oldPassed)
                        System.out.printf("Test %s failed: %s %n", method, exc);
                } catch (Exception e) {
                    System.out.println("Invalid @Test: " + method);
                }
            }
        }
        System.out.printf("tests: %d, Passed: %d, Failed: %d%n", tests, passed, tests - passed);
    }
}
