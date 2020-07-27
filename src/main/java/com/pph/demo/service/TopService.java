package com.pph.demo.service;

import com.pph.demo.model.TopProductData;

import java.util.List;
import java.util.Map;

/**
 * @author: pph
 * @datetime 2019/12/22 19:12
 * @description:
 */
public interface TopService {
    /**
     * 将最新到 Excel 数据导入 DB
     *
     * @return 结果
     */
    Object excelToDb() throws Exception;

    /**
     * 查询产品数据
     *
     * @param filter 过滤条件
     * @return 结果
     */
    List<TopProductData> queryProductByTerms(Map<String, Object> filter);

    /**
     * 查询数量
     *
     * @param filter 过滤条件
     * @return 结果
     */
    int queryTotalByTerms(Map<String, Object> filter);

    /**
     * 查询所有过滤条件
     *
     * @return 结果
     */
    Map<String, List<Map<String, String>>> queryFilter();
}
