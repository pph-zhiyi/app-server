package com.pph.demo.utils.http;

import lombok.Data;

/**
 * @author: pph
 * @date: 2020/1/31 16:11
 * @description:
 */
@Data
public class HttpStatusFeedback {

    private boolean isSuccess;

    private boolean noData;

    private String statusCode;

    public boolean noData() {
        return false;
    }
}
