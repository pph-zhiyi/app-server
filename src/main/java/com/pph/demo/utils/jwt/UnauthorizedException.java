package com.pph.demo.utils.jwt;

/**
 * @Author: pph
 * @Date: 2019/9/20 09:27
 * @Description:
 */
public class UnauthorizedException extends Exception {

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
