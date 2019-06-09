package com.pph.demo.effective.try_with_resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Author: PPH
 * @Date: 2019-06-09 17:09
 * @Description:
 */
public class Twr {

    public static void main(String[] args) throws Exception {

        System.out.println(firstLineOfFile("/Users/pph/Desktop/all/learn/finalize.txt", "暂无"));
    }

    /**
     * 读取文件第一行内容，异常则返回
     *
     * @param path
     * @param defaultVal
     * @return
     * @throws IOException
     */
    private static String firstLineOfFile(String path, String defaultVal) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        } catch (IOException e) {
            return defaultVal;
        }
    }
}
