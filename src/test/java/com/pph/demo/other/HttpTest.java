package com.pph.demo.other;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author: pph
 * @date: 2020/2/1 10:43
 * @description:
 */
public class HttpTest {

    private static final String GET_URL = "http://api.douban.com/v2/movie/in_theaters?apikey=0df993c66c0c636e29ecbb5344252a4a&start=0&count=10&city=常德";

    private static final String POST_URL = "http://localhost:8080/wayBill/common/api";

    /**
     * get请求
     */
    @Test
    public void httpURLConnectionGET() {
        try {
            // 字符串转成请求地址
            URL url = new URL(GET_URL);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 连接会话
            connection.connect();
            // 响应结果为输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String line;
            // 输出的结果
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            // 断开连接
            connection.disconnect();
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("请求失败 :" + e.getMessage());
        }
    }

    /**
     * post请求
     */
    @Test
    public void httpURLConnectionPOST() {
        try {
            URL url = new URL(POST_URL);
            // 1. 将url 以 open方法返回的urlConnection
            // 连接强转为HttpURLConnection连接.此时cnnection只是为一个连接对象,待连接中
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 2. 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
            connection.setDoOutput(true);
            // 3. 设置连接输入流为true
            connection.setDoInput(true);
            // 4.设置请求方式为post
            connection.setRequestMethod("POST");
            // 5.post请求缓存设为false
            connection.setUseCaches(false);
            /*
             * 6.设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
             * application/xml:xml数据 ,application/json:json对象
             * text/html:表单数据
             */
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            // 7.建立连接
            // (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            connection.connect();
            // 8.创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
            DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
            // 9.入参:json格式
            String parm = "{\"pubRequest\":{\"token\":\"121212\",\"method\":\"auth.login\","
                    + "\"version\":\"1.0\",\"encryType\":\"6\"},\"body\":{\"account\":\"18584084561\","
                    + "\"password\":\"123456\"}}";
            // 10.将参数输出到连接
            dataout.writeBytes(parm);
            // 输出完成后刷新并关闭流
            dataout.flush();
            dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)
            // System.out.println("响应code："+connection.getResponseCode());
            // 连接发起请求,处理服务器响应 (从连接获取到输入流并包装为bufferedReader)
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder(); // 用来存储响应数据
            // 循环读取流,若不到结尾处
            while ((line = bf.readLine()) != null) {
                sb.append(line);//若要换行：sb.append(line).append(System.getProperty("line.separator"));
            }
            bf.close(); // 重要且易忽略步骤 (关闭流,切记!)
            connection.disconnect(); // 销毁连接
            System.out.println(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("请求失败:" + e.getMessage());
        }
    }
}
