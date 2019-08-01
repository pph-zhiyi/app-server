package com.pph.demo.utils;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author: PPH
 * @Date: 2019-05-24 16:37
 * @Description:
 */
@Data
public class PageResult<E> {

    private List<E> data;

    private Integer total;

    private Integer pageNo;

    private Integer pageSize;

    public PageResult(List<E> data, Integer total, Integer pageNo, Integer pageSize) {
        this.data = data;
        this.total = total;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public PageResult(List<E> data, Integer total, Map<String, Object> filter) {
        this.data = data;
        this.total = total;
        this.pageNo = Integer.valueOf(filter.get(Constants.Page.PAGE_NO.val()).toString());
        this.pageSize = Integer.valueOf(filter.get(Constants.Page.PAGE_SIZE.val()).toString());
    }
}
