package com.pph.demo.java8.lambda.patterns.strategy.impl;

import com.pph.demo.java8.lambda.patterns.strategy.CompressionStrategy;

/**
 * @author: pph
 * @date 2019/11/28 16:36
 * @description
 */
public class GzipCompressionStrategy implements CompressionStrategy {

    @Override
    public void compress(String inFile, String outFile) {
        System.out.println(String.format("我是 gzip 将 %s 压缩到 %s", inFile, outFile));
    }
}
