package com.pph.demo.java8.lambda.demo.def;

import com.pph.demo.java8.lambda.demo.def.d1.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author: pph
 * @date: 2019/11/26 18:30
 * @description:
 */
public class D1 {

    /**
     * 增加默 认方法主要是为了在接口上向后兼容。让类中重写方法的优先级高于默认方法能简化很多 继承问题。
     */

    @Test
    public void d1() {
        Parent parent = new ParentImpl();

        parent.welcome();
        assertEquals("Parent: Hi!", parent.getLastMessage());
    }

    @Test
    public void d2() {
        Child child = new ChildImpl();

        child.welcome();
        assertEquals("Child: Hi!", child.getLastMessage());
    }

    @Test
    public void d3() {
        Parent parent = new OverridingParent();

        parent.welcome();
        assertEquals("Class Parent: Hi!", parent.getLastMessage());
    }

    @Test
    public void d4() {
        Child child = new OverridingChild();

        child.welcome();
        assertEquals("Class Parent: Hi!", child.getLastMessage());
    }
}
