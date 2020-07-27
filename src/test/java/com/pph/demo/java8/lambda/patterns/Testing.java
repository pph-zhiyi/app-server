package com.pph.demo.java8.lambda.patterns;

import com.pph.demo.java8.lambda.patterns.mandator.Editor;
import com.pph.demo.java8.lambda.patterns.mandator.Macro;
import com.pph.demo.java8.lambda.patterns.mandator.impl.Operation1;
import com.pph.demo.java8.lambda.patterns.mandator.impl.Operation2;
import com.pph.demo.java8.lambda.patterns.observer.Moon;
import com.pph.demo.java8.lambda.patterns.observer.impl.Aliens;
import com.pph.demo.java8.lambda.patterns.observer.impl.Nasa;
import com.pph.demo.java8.lambda.patterns.strategy.Compressor;
import com.pph.demo.java8.lambda.patterns.strategy.impl.GzipCompressionStrategy;
import com.pph.demo.java8.lambda.patterns.strategy.impl.ZipCompressionStrategy;
import com.pph.demo.java8.lambda.patterns.template.ApplicationDenied;
import com.pph.demo.java8.lambda.patterns.template.la.Company01;
import com.pph.demo.java8.lambda.patterns.template.la.Company02;
import com.pph.demo.java8.lambda.patterns.template.la.CompanyLoanApplication;
import com.pph.demo.java8.lambda.patterns.template.la.LoanApplication;
import org.junit.Test;

/**
 * @author: pph
 * @date 2019/11/28 15:47
 * @description:
 */
public class Testing {

    @Test
    public void mandator() {
        /*
         * 命令者模式：
         *      命令者是一个对象，它封装了调用另一个方法的所有细节，命令者模式使用该对象，
         *      可以编写出根据运行期条件，顺序调用方法的一般化代码。
         *
         * 命令接收者：执行实际任务。
         * 命令者：封装了所有调用命令执行者的信息。
         * 发起者：控制一个或多个命令的顺序和执行。
         * 客户端：创建具体的命令者实例。
         */
        Macro macro = new Macro();
        Editor e1 = new Operation1();
        Editor e2 = new Operation2();

        macro.record(e1::open);
        macro.record(e2::close);
        macro.record(e1::save);
        macro.record(e1::close);
        macro.record(e2::open);
        macro.record(e2::save);
        macro.record(e2::close);

        macro.run();
    }

    @Test
    public void strategy() {
        /*
         * 策略模式：
         *      策略模式能在运行时改变软件的算法行为。如何实现策略模式根据你的情况而定，但其主要思想是定义一个通用的问题，
         *      使用不同的算法来实现，然后将这些算法都封装在一个统一接口的背后。
         */

        final String inFile = "/桌面/strategy";
        final String outFile = "/收藏/strategy";

        Compressor gzipCompressor = Compressor.init(new GzipCompressionStrategy());
        gzipCompressor.compress(inFile, outFile);

        Compressor zipCompressor = Compressor.init(new ZipCompressionStrategy());
        zipCompressor.compress(inFile, outFile);
    }

    @Test
    public void observer() {
        /*
         * 在观察者模式中，被观察者持有一个观察者列表。当被观察者的状态发生改变，会通知观察者。
         * 观察者模式被大量应用于基于 MVC 的 GUI 工具中，以此让模型状态发生变化时，自动刷新视图模块，达到二者之间的解耦。
         */
        Moon m1 = new Moon();
        m1.startSpying(new Nasa());
        m1.startSpying(new Aliens());
        m1.land("An asteroid");
        m1.land("Apollo 11");

        Moon m2 = new Moon();
        m2.startSpying(name -> {
            if (name.contains("Apollo"))
                System.out.println("We made it!");
        });
        m2.startSpying(name -> {
            if (name.contains("Apollo"))
                System.out.println("They're distracted, lets invade earth!");
        });
        m2.land("An asteroid");
        m2.land("Apollo 11");
    }

    @Test
    public void template() throws ApplicationDenied {
        /*
         * 整体算法的设计是一个抽象类，它有一系列抽象方法，代表算法中可被定制的步骤，同时这个类中包含了一些通用代码。
         * 算法的每一个变种 由具体的类实现，它们重写了抽象方法，提供了相应的实现。
         */

        LoanApplication c1 = new CompanyLoanApplication(new Company01());
        c1.checkLoanApplication();

        LoanApplication c2 = new CompanyLoanApplication(new Company02());
        c2.checkLoanApplication();
    }
}
