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
    /**
     * 数据
     */
    private List<E> data;
    /**
     * 总条数
     */
    private Integer total;
    /**
     * 当前页
     */
    private Integer pageNo;
    /**
     * 分页条数
     */
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

    /**
     * 生成分页数据对象
     *
     * @param data
     * @param total
     * @param pageNo
     * @param pageSize
     * @param <E>
     * @return
     */
    public static <E> PageResult<E> make(List<E> data, Integer total, Integer pageNo, Integer pageSize) {
        return new PageResult<>(data, total, pageNo, pageSize);
    }

    /**
     * 生成分页数据对象
     *
     * @param data
     * @param total
     * @param filter
     * @param <E>
     * @return
     */
    public static <E> PageResult<E> make(List<E> data, Integer total, Map<String, Object> filter) {
        return new PageResult<>(data, total, filter);
    }
}
