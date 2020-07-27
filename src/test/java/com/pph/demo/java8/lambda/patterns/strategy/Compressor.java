package com.pph.demo.java8.lambda.patterns.strategy;

/**
 * @author: pph
 * @date 2019/11/28 16:37
 * @description
 */
public class Compressor {

    private final CompressionStrategy strategy;

    public Compressor(CompressionStrategy strategy) {
        this.strategy = strategy;
    }

    public void compress(String inFile, String outFile) {
        strategy.compress(inFile, outFile);
    }

    public static Compressor init(CompressionStrategy strategy) {
        return new Compressor(strategy);
    }
}
