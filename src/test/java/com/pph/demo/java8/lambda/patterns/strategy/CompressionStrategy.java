package com.pph.demo.java8.lambda.patterns.strategy;

/**
 * @author: pph
 * @date: 2019/11/28 16:34
 * @description:
 */
public interface CompressionStrategy {

    void compress(String inFile, String outFile);
}
