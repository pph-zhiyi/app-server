package com.pph.demo.utils.http;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: pph
 * @date 2020/1/31 15:51
 * @description
 */
public class HttpClient {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClient.class);

    private static CloseableHttpClient httpclient = HttpClients.custom().build();

    private String url;

    private List<NameValuePair> nameValuePairs;

    private HttpUriRequest httpMessage;

    private HttpStatusFeedback statusFeedback;

    private boolean hasPost;

    public static void main(String[] args) throws IOException {
        System.out.println(new HttpClient()
//                .get("http://open.api.tianyancha.com/services/v4/open/baseinfo?id=22822&name=北京百度网讯科技有限公司")
                .get("http://api.douban.com/v2/movie/in_theaters?apikey=0df993c66c0c636e29ecbb5344252a4a&start=0&count=10&city=常德")
//                .addParameter("name", "北京百度网讯科技有限公司")
//                .addHeader("Authorization", "******")
                .execute());
    }

    public HttpClient get(String url) {
        return service(url, new HttpGet(url), false);
    }

    public HttpClient post(String url) {
        return service(url, new HttpPost(url), true);
    }

    private HttpClient service(String url, HttpUriRequest httpMessage, boolean hasPost) {
        validateUrl(url);
        initFlag(url, hasPost);
        this.httpMessage = httpMessage;
        return this;
    }

    private void validateUrl(String url) {
        LOGGER.info("验证URL[{}]是否为空", url);
        if (StringUtils.isBlank(url)) {
            throw new IllegalArgumentException();
        }
    }

    public String execute() throws IOException {
        setParameter();
        LOGGER.info("访问请求地址[{}]获取结果", this.url);
        CloseableHttpResponse response = httpclient.execute(httpMessage);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "utf-8");
        return result;
    }

    private void setParameter() throws UnsupportedEncodingException {
        LOGGER.info("添加请求参数到请求主体上");
        if (httpMessage instanceof HttpGet) {
            if (nameValuePairs != null && !nameValuePairs.isEmpty()) {
                String tmp = URLEncodedUtils.format(nameValuePairs, "utf-8");
                this.url += (this.url.contains("?") ? "&" : "?") + tmp;
            }
            ((HttpGet) httpMessage).setURI(URI.create(this.url));
        } else if (httpMessage instanceof HttpPost) {
            ((HttpPost) httpMessage).setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
        }
    }

    private void initFlag(String url, boolean hasPost) {
        this.url = url;
        this.hasPost = hasPost;
    }

    public HttpClient addHeader(String key, String value) {
        LOGGER.info("校验请求主体是否存在");
        if (this.httpMessage == null)
            throw new NullPointerException("未调用get或post方法");
        LOGGER.info("设置请求头");
        this.httpMessage.setHeader(key, value);
        return this;
    }

    public synchronized HttpClient addParameter(String key, String value) {
        if (this.nameValuePairs == null) {
            this.nameValuePairs = new ArrayList<>();
        }
        LOGGER.info("添加请求参数[{}, {}]到内存", key, value);
        this.nameValuePairs.add(new BasicNameValuePair(key, value));
        return this;
    }

    public HttpClient statusFeedback(HttpStatusFeedback statusFeedback) {
        this.statusFeedback = statusFeedback;
        return this;
    }

    public boolean isSuccess() {
        return statusFeedback.isSuccess();
    }

    public boolean noData() {
        return statusFeedback.noData();
    }

    public String getStatusCode() {
        return statusFeedback.getStatusCode();
    }
}
