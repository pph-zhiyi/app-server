package com.pph.demo.bingFaBianChengShiZhan.demo.d5;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author: pph
 * @date 2020/1/17 09:18
 * @description:
 */
public class Factorize implements Servlet {

    private final Computable<BigInteger, BigInteger[]> c = arg -> factor(arg);

    private BigInteger[] factor(BigInteger arg) {
        return new BigInteger[]{arg};
    }

    private final Computable<BigInteger, BigInteger[]> cache = new Memoizer<>(c);

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
