package com.pph.demo.vo.response;

import lombok.Data;

import java.util.Map;

/**
 * @Author: PPH
 * @Date: 2019-05-24 16:37
 * @Description:
 */
@Data
public class PageResult {

    private Object data;

    private Integer total;

    private Integer pageNo;

    private Integer pageSize;

    public PageResult(Object data, Integer total, Integer pageNo, Integer pageSize) {
        this.data = data;
        this.total = total;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public PageResult(Object data, Integer total, Map<String, Object> filter) {
        this.data = data;
        this.total = total;
        this.pageNo = Integer.valueOf(filter.get("pageNo").toString());
        this.pageSize = Integer.valueOf(filter.get("pageSize").toString());
    }
}
