package com.pph.demo.effective.jdk8.optional;

import com.pph.demo.model.User;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @Author: PPH
 * @date: 2019-07-03 20:23
 * @Description:
 */
public class Demo {

    /**
     * 从 Java 8 引入的一个很有趣的特性是 Optional  类。Optional 类主要解决的问题是臭名昭著的空指针异常（NullPointerException） —— 每个 Java 程序员都非常了解的异常。
     * <p>
     * 本质上，这是一个包含有可选值的包装类，这意味着 Optional 类既可以含有对象也可以为空。
     * <p>
     * Optional 是 Java 实现函数式编程的强劲一步，并且帮助在范式中实现。但是 Optional 的意义显然不止于此。
     */


    /**
     * 这个类型的对象可能包含值，也可能为空。你可以使用同名方法创建一个空的 Optional。
     * <p>
     * 毫不奇怪，尝试访问 emptyOpt 变量的值会导致 NoSuchElementException。
     */
    @Test(expected = NoSuchElementException.class)
    public void whenCreateEmptyOptionalThenNull() {
        Optional<User> emptyOpt = Optional.empty();
        User user = emptyOpt.get();
    }

    /**
     * 使用  of() 和 ofNullable() 方法创建包含值的 Optional。两个方法的不同之处在于如果你把 null 值作为参数传递进去，of()
     * 方法会抛出 NullPointerException：
     */
    @Test(expected = NullPointerException.class)
    public void whenCreateOfEmptyOptional_thenNullPointerException() {
        User user = new User();
        /*
         * 我们并没有完全摆脱 NullPointerException。因此，你应该明确对象不为 null  的时候使用 of()。
         */
        Optional<User> opt = Optional.of(user);
        /*
         * 如果对象即可能是 null 也可能是非 null，你就应该使用 ofNullable() 方法：
         */
        Optional<User> opt2 = Optional.ofNullable(user);
    }

    /**
     * 从 Optional 实例中取回实际值对象的方法之一是使用 get() 方法：
     */
    @Test
    public void whenCreateOfNullableOptional_thenOk() {
        String name = "PPH";
        Optional<String> opt = Optional.ofNullable(name);

        assertEquals("PPH", opt.get());
    }

    /**
     * 这个方法会在值为 null 的时候抛出异常。要避免异常，你可以选择首先验证是否有值：
     */
    @Test
    public void whenCheckIfPresent_thenOk() {
        User user = new User();
        user.setName("PPH");
        user.setSex("PPH");
        Optional<User> opt = Optional.ofNullable(user);
        assertTrue(opt.isPresent());

        assertEquals(user.getName(), user.getSex());

        opt.ifPresent( u -> assertEquals(u.getName(), u.getSex()));
    }
}
